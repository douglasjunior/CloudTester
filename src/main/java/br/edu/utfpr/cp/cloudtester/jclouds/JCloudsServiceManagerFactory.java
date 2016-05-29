package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.DBManager;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.VMManager;
import org.jclouds.ContextBuilder;
import org.jclouds.blobstore.BlobStoreContext;
import br.edu.utfpr.cp.cloudtester.tool.StorageManager;
import com.google.inject.Module;
import org.jclouds.sqs.SQSApi;

/**
 *
 * @author Douglas
 */
public class JCloudsServiceManagerFactory extends ServiceManagerFactory {

    private final Iterable<Module> modules;

    public JCloudsServiceManagerFactory(Authentication authentication, String region, Iterable<Module> modules) {
        super(authentication, region);
        this.modules = modules;
    }

    @Override
    public StorageManager createStorageManager() {
        ContextBuilder builder = ContextBuilder.newBuilder(authentication.getStorageProvider())
                .credentials(authentication.getIdentity(), authentication.getCredential());
        if (modules != null) {
            builder.modules(modules);
        }
        BlobStoreContext context = builder.buildView(BlobStoreContext.class);
        return new JCloudsStorageManager(context);
    }

    @Override
    public QueueManager createQueueManager() {
        ContextBuilder builder = ContextBuilder.newBuilder(authentication.getQueueProvider())
                .credentials(authentication.getIdentity(), authentication.getCredential());
        if (modules != null) {
            builder.modules(modules);
        }
        SQSApi sqsApi = builder.buildApi(SQSApi.class);
        return new JCloudsQueueManager(sqsApi, region);
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
        return getClass().getSimpleName() + " with provider " + authentication.getStorageProvider() + ", " + authentication.getQueueProvider();
    }
}
