package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceByteArray;
import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import br.edu.utfpr.cp.cloudtester.tool.StoreManager;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        return retrieves(metadata.getName(), metadata.getContainerName());
    }

    @Override
    public Resource retrieves(String name, String containerName) throws IOException {
        try {
            CloudBlobContainer container = getContainer(containerName);
            CloudBlockBlob blob = container.getBlockBlobReference(name);
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
                blob.download(baos);
                return new ResourceByteArray(baos.toByteArray(), name);
            }
        } catch (URISyntaxException | StorageException ex) {
            throw new IOException(ex);
        }
    }

    @Override
    public ResourceMetadata getResourceMetadata(String name, String containerName) {
        try {
            CloudBlobContainer container = getContainer(containerName);
            CloudBlockBlob blob = container.getBlockBlobReference(name);
            blob.downloadAttributes();
            return new AzureResourceMetadata(name, containerName, blob.getProperties(), blob.getUri());
        } catch (URISyntaxException | StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<? extends ResourceMetadata> list(String containerName) {
        try {
            List<AzureResourceMetadata> result = new ArrayList<>();
            CloudBlobContainer container = getContainer(containerName);
            for (Iterator<ListBlobItem> iterator = container.listBlobs().iterator(); iterator.hasNext();) {
                CloudBlockBlob item = (CloudBlockBlob) iterator.next();
                result.add(new AzureResourceMetadata(item.getName(), containerName, item.getProperties(), item.getUri()));
            }
            return result;
        } catch (URISyntaxException | StorageException ex) {
            throw new RuntimeException(ex);
        }
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
