package br.edu.utfpr.cp.cloudtester.experiment.azure;

import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.DBManager;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.StorageManager;
import br.edu.utfpr.cp.cloudtester.tool.VMManager;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class AzureServiceManagerFactory extends ServiceManagerFactory {

    private final String storageConnectionString;

    public AzureServiceManagerFactory(Authentication authentication, String region) {
        super(authentication, region);
        storageConnectionString
                = "DefaultEndpointsProtocol=http"
                + ";AccountName=" + authentication.getIdentity()
                + ";AccountKey=" + authentication.getCredential();
    }

    @Override
    public StorageManager createStorageManager() {
        try {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
            return new AzureStorageManager(storageAccount);
        } catch (URISyntaxException | InvalidKeyException ex) {
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

}
