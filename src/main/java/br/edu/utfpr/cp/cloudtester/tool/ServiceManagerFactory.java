package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 */
public abstract class ServiceManagerFactory {

    protected final Authentication authentication;

    public ServiceManagerFactory(Authentication authentication) {
        this.authentication = authentication;
    }

    public abstract StorageManager createStorageManager();

    public abstract QueueManager createQueueManager();

    public abstract VMManager createVMManager();

    public abstract DBManager createDBManager();
}
