package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 */
public class Authentication {

    private final String identity;
    private final String credential;
    private final String provider;

    public Authentication(String identity, String credential, String provider) {
        this.identity = identity;
        this.credential = credential;
        this.provider = provider;
    }

    public Authentication(String identity, String credential) {
        this(identity, credential, null);
    }

    public String getProvider() {
        return provider;
    }

    public String getIdentity() {
        return identity;
    }

    public String getCredential() {
        return credential;
    }

}
