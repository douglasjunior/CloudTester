package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 * @param <STORE>
 * @param <QUEUE>
 * @param <VM>
 * @param <DB>
 */
public abstract class FeatureManagerFactory<STORE extends StoreManager, QUEUE extends QueueManager, VM extends VMManager, DB extends DBManager> {

    public abstract STORE createStoreManager();

    public abstract QUEUE createQueueManager();

    public abstract VM createVMManager();

    public abstract DB createDBManager();
}
