package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 */
public abstract class ServiceManagerFactory {

    protected final Authentication authentication;
    protected final String region;

    public ServiceManagerFactory(Authentication authentication, String region) {
        this.authentication = authentication;
        this.region = region;
    }

    public abstract StorageManager createStorageManager();

    public abstract QueueManager createQueueManager();

    public abstract VMManager createVMManager();

    public abstract DBManager createDBManager();
}
