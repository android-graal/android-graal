#include <jni.h>
#include <malloc.h>
#include <pthread.h>
#include <stdlib.h>

typedef struct graal_isolatethread_t graal_isolatethread_t;

int graal_detach_thread(graal_isolatethread_t *thread);

static JavaVM *artVm;
static pthread_key_t artKey;
static pthread_key_t isolateKey;
static pthread_key_t transportKey;

typedef struct block {
    struct block *next;
    void *data;
} block;

static void detachArt(void *javaVm) {
    (*(JavaVM *) javaVm)->DetachCurrentThread(javaVm);
}

static void detachIsolate(void *thread) {
    graal_detach_thread(thread);
}

static void freeBlocks(void *first) {
    block *b = first;
    while (b != NULL) {
        block *next = b->next;
        free(b->data);
        free(b);
        b = next;
    }
}

/* Runs once, before any other call of this file. */
int androidgraal_init(JNIEnv *env) {
    (*env)->GetJavaVM(env, &artVm);
    int error = pthread_key_create(&artKey, detachArt);
    if (error != 0) {
        return error;
    }
    error = pthread_key_create(&isolateKey, detachIsolate);
    if (error != 0) {
        return error;
    }
    return pthread_key_create(&transportKey, freeBlocks);
}

JNIEnv *androidgraal_env(const char *name) {
    JNIEnv *env = NULL;
    jint status = (*artVm)->GetEnv(artVm, (void **) &env, JNI_VERSION_1_6);
    if (status == JNI_OK) {
        return env;
    }
    JavaVMAttachArgs args = {JNI_VERSION_1_6, name, NULL};
    if ((*artVm)->AttachCurrentThreadAsDaemon(artVm, &env, &args) != JNI_OK) {
        return NULL;
    }
    pthread_setspecific(artKey, artVm);
    return env;
}

void androidgraal_detach_thread_on_exit(graal_isolatethread_t *thread) {
    pthread_setspecific(isolateKey, thread);
}

void *androidgraal_thread_alloc(size_t bytes) {
    block *b = malloc(sizeof(block));
    if (b == NULL) {
        return NULL;
    }
    b->data = malloc(bytes);
    if (b->data == NULL) {
        free(b);
        return NULL;
    }
    b->next = pthread_getspecific(transportKey);
    pthread_setspecific(transportKey, b);
    return b->data;
}

void androidgraal_mallinfo(struct mallinfo *info) {
    *info = mallinfo();
}
