package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceByteArray;
import br.edu.utfpr.cp.cloudtester.tool.ResourceId;
import br.edu.utfpr.cp.cloudtester.tool.StoreManager;
import com.google.common.io.ByteStreams;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.BlobStoreContext;
import org.jclouds.blobstore.domain.Blob;
import org.jclouds.blobstore.options.PutOptions;

/**
 *
 * @author Douglas
 */
public class JCloudStoreManager implements StoreManager {

    private final BlobStoreContext context;
    private final String containerName;
    private final BlobStore blobStore;

    JCloudStoreManager(BlobStoreContext context, String containerName) {
        this.context = context;
        this.containerName = containerName;
        this.blobStore = context.getBlobStore();
    }

    @Override
    public ResourceId stores(Resource file) throws IOException {
        Blob blob = blobStore.blobBuilder(file.getName())
                .payload(file.getInputStream())
                .contentLength(file.getLength())
                .build();
        String etags = blobStore.putBlob(containerName, blob, PutOptions.Builder.multipart());
        return new JCloudResourceId(file.getName(), containerName, etags);
    }

    @Override
    public Resource retrieves(ResourceId id) throws IOException {
        Blob blob = blobStore.getBlob(id.getContainerName(), id.getName());

        InputStream is = blob.getPayload().openStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ByteStreams.copy(is, baos);

        byte[] file = baos.toByteArray();

        return new ResourceByteArray(file, id.getName());
    }

    @Override
    public void close() throws IOException {
        context.close();
    }

}
