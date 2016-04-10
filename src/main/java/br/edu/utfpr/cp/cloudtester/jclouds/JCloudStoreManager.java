package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceByteArray;
import br.edu.utfpr.cp.cloudtester.tool.StoreManager;
import com.google.common.io.ByteStreams;
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
import org.jclouds.blobstore.domain.BlobMetadata;

/**
 *
 * @author Douglas
 */
public class JCloudStoreManager implements StoreManager {

    private final BlobStoreContext context;
    private final BlobStore blobStore;

    JCloudStoreManager(BlobStoreContext context) {
        this.context = context;
        this.blobStore = context.getBlobStore();
    }

    @Override
    public void stores(Resource file, String containerName) throws IOException {
        Blob blob = blobStore.blobBuilder(file.getName())
                .payload(file.getInputStream())
                .contentLength(file.getLength())
                .build();
        blobStore.putBlob(containerName, blob, PutOptions.Builder.multipart());
    }

    @Override
    public Resource retrieves(String name, String containerName) throws IOException {
        Blob blob = blobStore.getBlob(containerName, name);

        InputStream is = blob.getPayload().openStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ByteStreams.copy(is, baos);

        byte[] file = baos.toByteArray();

        return new ResourceByteArray(file, name);
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
            ResourceMetadata resourceMetadata = new JCloudResourceMetadata(metaData, containerName);
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
        return new JCloudResourceMetadata(blobMetadata, containerName);
    }

}
