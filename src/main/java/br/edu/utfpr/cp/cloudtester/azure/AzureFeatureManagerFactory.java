package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.DBManager;
import br.edu.utfpr.cp.cloudtester.tool.FeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.StoreManager;
import br.edu.utfpr.cp.cloudtester.tool.VMManager;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;

/**
 *
 * @author Douglas
 */
public class AzureFeatureManagerFactory extends FeatureManagerFactory {

    public final String storageConnectionString;

    public AzureFeatureManagerFactory(String identity, String credential) {
        storageConnectionString
                = "DefaultEndpointsProtocol=http;"
                + "AccountName=" + identity + ";"
                + "AccountKey=" + credential;
    }

    @Override
    public StoreManager createStoreManager() {
        try {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

            return new AzureStoreManager(blobClient);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
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
        return getClass().getSimpleName();
    }
}
