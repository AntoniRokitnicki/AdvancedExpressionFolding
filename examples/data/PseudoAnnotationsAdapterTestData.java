package data;

interface PseudoAnnotationsAdapterListener {
    void onEvent();
    int onCompute();
    Boolean isReady();
}

abstract class PseudoAnnotationsAdapterProcessor {
    public abstract void execute();
    protected abstract long scheduledAt();
}
