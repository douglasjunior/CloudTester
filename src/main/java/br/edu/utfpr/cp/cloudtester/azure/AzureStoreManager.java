package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import br.edu.utfpr.cp.cloudtester.tool.StoreManager;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Douglas
 */
class AzureStoreManager implements StoreManager {

    private final Map<String, CloudBlobContainer> cacheContainer = new HashMap<>();

    private final CloudBlobClient blobClient;

    public AzureStoreManager(CloudBlobClient blobClient) {
        this.blobClient = blobClient;
    }

    @Override
    public void stores(Resource file, String containerName) throws IOException {
        try {
            CloudBlobContainer container = getContainer(containerName);
            CloudBlockBlob blob = container.getBlockBlobReference(file.getName());
            blob.upload(file.getInputStream(), file.getLength());
        } catch (URISyntaxException | StorageException ex) {
            throw new IOException(ex);
        }
    }

    @Override
    public Resource retrieves(ResourceMetadata metadata) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resource retrieves(String name, String containerName) throws IOException {
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
        cacheContainer.clear();
    }

    private CloudBlobContainer getContainer(String containerName) throws URISyntaxException, StorageException {
        CloudBlobContainer container = cacheContainer.get(containerName);
        if (container == null) {
            container = blobClient.getContainerReference(containerName);
            cacheContainer.put(containerName, container);
        }
        return container;
    }

}
