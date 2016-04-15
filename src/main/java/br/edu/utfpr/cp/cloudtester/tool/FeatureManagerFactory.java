package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 */
public abstract class FeatureManagerFactory {

    protected final Authentication authentication;

    public FeatureManagerFactory(Authentication authentication) {
        this.authentication = authentication;
    }

    public abstract StoreManager createStoreManager();

    public abstract QueueManager createQueueManager();

    public abstract VMManager createVMManager();

    public abstract DBManager createDBManager();
}
