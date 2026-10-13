package android.adservices.ondevicepersonalization;

/**
 * Contains all the information needed for a run of model inference. The input of {@link
 * ModelManager#run}.
 */
public final class InferenceInput {
    private InferenceInput() {
    }

    public static final class Params {
        /**
         * The model inference will run on CPU.
         */
        public static final int DELEGATE_CPU = 1;

        /**
         * The model is a tensorflow lite model.
         */
        public static final int MODEL_TYPE_TENSORFLOW_LITE = 1;

        private Params() {
        }
    }
}
