package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.FeatureManagerFactory;
import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStoreContext;

/**
 *
 * @author Douglas
 */
public class JCloudFeatureManagerFactory extends FeatureManagerFactory {

    private final String provider;
    private final String identity;
    private final String credential;

    public JCloudFeatureManagerFactory(String provider, String identity, String credential) {
        this.provider = provider;
        this.identity = identity;
        this.credential = credential;
    }

    @Override
    public JCloudStoreManager createStoreManager() {
        BlobStoreContext context = ContextBuilder.newBuilder(provider)
                .credentials(identity, credential)
                .buildView(BlobStoreContext.class);
        return new JCloudStoreManager(context);
    }

    @Override
    public JCloudQueueManager createQueueManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JCloudVMManager createVMManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JCloudDBManager createDBManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
