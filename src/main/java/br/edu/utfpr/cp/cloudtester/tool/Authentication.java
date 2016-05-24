package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 */
public class Authentication {

    private final String identity;
    private final String credential;
    private final String storageProvider;
    private final String queueProvider;

    public Authentication(String identity, String credential, String storageProvider, String queueProvider) {
        this.identity = identity;
        this.credential = credential;
        this.storageProvider = storageProvider;
        this.queueProvider = queueProvider;
    }

    public Authentication(String identity, String credential) {
        this(identity, credential, null, null);
    }

    public String getStorageProvider() {
        return storageProvider;
    }

    public String getQueueProvider() {
        return queueProvider;
    }

    public String getIdentity() {
        return identity;
    }

    public String getCredential() {
        return credential;
    }

}
