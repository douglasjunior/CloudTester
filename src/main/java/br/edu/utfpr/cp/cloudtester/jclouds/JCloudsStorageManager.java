package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceByteArray;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.domain.Blob;
import org.jclouds.blobstore.domain.PageSet;
import org.jclouds.blobstore.domain.StorageMetadata;
import org.jclouds.blobstore.options.PutOptions;
import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.jclouds.blobstore.domain.BlobMetadata;
import br.edu.utfpr.cp.cloudtester.tool.StorageManager;

/**
 *
 * @author Douglas
 */
class JCloudsStorageManager implements StorageManager {

    private final BlobStoreContext context;
    private final BlobStore blobStore;

    JCloudsStorageManager(BlobStoreContext context) {
        this.context = context;
        this.blobStore = context.getBlobStore();
    }

    @Override
    public void stores(Resource resource, String containerName) throws IOException {
        try (InputStream is = resource.getInputStream()) {
            Blob blob = blobStore.blobBuilder(resource.getName())
                    .payload(is)
                    .contentLength(resource.getLength())
                    .build();
            blobStore.putBlob(containerName, blob, PutOptions.Builder.multipart());
        }
    }

    @Override
    public Resource retrieves(String name, String containerName) throws IOException {
        Blob blob = blobStore.getBlob(containerName, name);
        try (InputStream is = blob.getPayload().openStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();) {

            IOUtils.copy(is, baos);

            byte[] bytes = baos.toByteArray();

            return new ResourceByteArray(bytes, name);
        }
    }

    @Override
    public Resource retrieves(ResourceMetadata metadata) throws IOException {
        return retrieves(metadata.getName(), metadata.getContainerName());
    }

    @Override
    public List<ResourceMetadata> list(String containerName) {
        PageSet<? extends StorageMetadata> page = blobStore.list(containerName);
        List<ResourceMetadata> result = new ArrayList<>(page.size());
        for (Iterator<? extends StorageMetadata> iterator = page.iterator(); iterator.hasNext();) {
            StorageMetadata metaData = iterator.next();
            ResourceMetadata resourceMetadata = new JCloudsResourceMetadata(metaData, containerName);
            result.add(resourceMetadata);
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        context.close();
    }

    @Override
    public ResourceMetadata getResourceMetadata(String name, String containerName) {
        BlobMetadata blobMetadata = blobStore.blobMetadata(containerName, name);
        return new JCloudsResourceMetadata(blobMetadata, containerName);
    }

    @Override
    public void delete(String name, String containerName) throws IOException {
        blobStore.removeBlob(containerName, name);
    }

}
