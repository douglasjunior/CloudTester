package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.DBManager;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.VMManager;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.queue.CloudQueueClient;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import br.edu.utfpr.cp.cloudtester.tool.StorageManager;

/**
 *
 * @author Douglas
 */
public class AzureServiceManagerFactory extends ServiceManagerFactory {

    public final String storageConnectionString;

    public AzureServiceManagerFactory(Authentication authentication, String region) {
        super(authentication, region);
        storageConnectionString
                = "DefaultEndpointsProtocol=https;"
                + "AccountName=" + authentication.getIdentity() + ";"
                + "AccountKey=" + authentication.getCredential();
    }

    @Override
    public StorageManager createStorageManager() {
        try {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

            return new AzureStorageManager(blobClient);
        } catch (URISyntaxException | InvalidKeyException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public QueueManager createQueueManager() {
        try {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
            CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

            return new AzureQueueManager(queueClient);
        } catch (URISyntaxException | InvalidKeyException ex) {
            throw new RuntimeException(ex);
        }
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
        return getClass().getSimpleName();
    }
}
