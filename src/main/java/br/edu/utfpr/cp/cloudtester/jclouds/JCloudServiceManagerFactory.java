package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.DBManager;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.StoreManager;
import br.edu.utfpr.cp.cloudtester.tool.VMManager;
import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStoreContext;

/**
 *
 * @author Douglas
 */
public class JCloudServiceManagerFactory extends ServiceManagerFactory {

    public JCloudServiceManagerFactory(Authentication authentication) {
        super(authentication);
    }

    @Override
    public StoreManager createStoreManager() {
        BlobStoreContext context = ContextBuilder.newBuilder(authentication.getProvider())
                .credentials(authentication.getIdentity(), authentication.getCredential())
                .buildView(BlobStoreContext.class);
        return new JCloudStoreManager(context);
    }

    @Override
    public QueueManager createQueueManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VMManager createVMManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DBManager createDBManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " with provider " + authentication.getProvider();
    }
}
