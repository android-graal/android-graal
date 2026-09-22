#include <android/log.h>
#include <dlfcn.h>
#include <errno.h>
#include <jni.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>

#define TAG "GraalHello"

static int stdio_pipe[2];
static pthread_once_t pump_once = PTHREAD_ONCE_INIT;

static void *pump(void *unused) {
    (void) unused;
    char buf[512];
    size_t len = 0;
    for (;;) {
        ssize_t n = read(stdio_pipe[0], buf + len, sizeof(buf) - len - 1);
        if (n < 0 && errno == EINTR) {
            continue;
        }
        if (n <= 0) {
            break;
        }
        len += (size_t) n;
        char *line = buf, *nl;
        while ((nl = memchr(line, '\n', (size_t) (buf + len - line))) != NULL) {
            *nl = '\0';
            __android_log_write(ANDROID_LOG_INFO, TAG, line);
            line = nl + 1;
        }
        len = (size_t) (buf + len - line);
        memmove(buf, line, len);
        if (len == sizeof(buf) - 1) {
            buf[len] = '\0';
            __android_log_write(ANDROID_LOG_INFO, TAG, buf);
            len = 0;
        }
    }
    return NULL;
}

/* fd 1 and 2 of an app process are /dev/null, and the image's System.out writes fd 1. */
static void start_pump(void) {
    pthread_t thread;
    if (pipe(stdio_pipe) != 0 || pthread_create(&thread, NULL, pump, NULL) != 0) {
        __android_log_print(ANDROID_LOG_ERROR, TAG, "cannot start the stdout pump");
        return;
    }
    pthread_detach(thread);
    dup2(stdio_pipe[1], STDOUT_FILENO);
    dup2(stdio_pipe[1], STDERR_FILENO);
}

JNIEXPORT jint JNICALL Java_org_graalvm_android_hello_MainActivity_startGraalApp(JNIEnv *env, jclass clazz) {
    pthread_once(&pump_once, start_pump);
    void *image = dlopen("libhello.so", RTLD_NOW);
    int (*run_main)(int, char **) = image != NULL ? (int (*)(int, char **)) dlsym(image, "run_main") : NULL;
    if (run_main == NULL) {
        __android_log_print(ANDROID_LOG_ERROR, TAG, "%s", dlerror());
        return -1;
    }
    char *argv[] = {"hello", NULL};
    return run_main(1, argv);
}
