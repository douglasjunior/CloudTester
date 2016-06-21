package br.edu.utfpr.cp.cloudtester.experiment.azure;

import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceByteArray;
import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import br.edu.utfpr.cp.cloudtester.tool.StorageManager;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author Douglas
 */
class AzureStorageManager implements StorageManager {

    private final CloudStorageAccount storageAccount;
    private final CloudBlobClient blobClient;

    public AzureStorageManager(CloudStorageAccount storageAccount) {
        this.storageAccount = storageAccount;
        // Create the blob client.
        blobClient = storageAccount.createCloudBlobClient();
    }

    @Override
    public void stores(Resource resource, String containerName) throws IOException {
        try (InputStream is = resource.getInputStream()) {
            // Retrieve reference to a previously created container.
            CloudBlobContainer container = blobClient.getContainerReference(containerName);
            // Create or overwrite the "myimage.jpg" blob with contents from a local file.
            CloudBlockBlob blob = container.getBlockBlobReference(resource.getName());
            blob.upload(is, resource.getLength());
        } catch (URISyntaxException | StorageException ex) {
            throw new IOException(ex);
        }
    }

    @Override
    public Resource retrieves(ResourceMetadata metadata) throws IOException {
        return retrieves(metadata.getName(), metadata.getContainerName());
    }

    @Override
    public Resource retrieves(String name, String containerName) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // Retrieve reference to a previously created container.
            CloudBlobContainer container = blobClient.getContainerReference(containerName);
            // Create or overwrite the "myimage.jpg" blob with contents from a local file.
            CloudBlockBlob blob = container.getBlockBlobReference(name);
            blob.download(baos);
            return new ResourceByteArray(baos.toByteArray(), name);
        } catch (URISyntaxException | StorageException ex) {
            throw new IOException(ex);
        }
    }

    @Override
    public void delete(String name, String containerName) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResourceMetadata getResourceMetadata(String name, String containerName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends ResourceMetadata> list(String containerName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
