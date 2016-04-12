package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceByteArray;
import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import br.edu.utfpr.cp.cloudtester.tool.StoreManager;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.common.io.ByteStreams;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Douglas
 */
class AWSStoreManager implements StoreManager {

    private final AmazonS3 s3Client;

    public AWSStoreManager(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void stores(Resource resource, String containerName) throws IOException {
        try (InputStream is = resource.getInputStream();) {
            ObjectMetadata om = new ObjectMetadata();
            om.setContentLength(resource.getLength());
            s3Client.putObject(containerName, resource.getName(), is, om);
        }
    }

    @Override
    public Resource retrieves(ResourceMetadata metadata) throws IOException {
        return retrieves(metadata.getName(), metadata.getContainerName());
    }

    @Override
    public Resource retrieves(String name, String containerName) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(containerName, name);
        try (S3Object s3Object = s3Client.getObject(getObjectRequest);
                InputStream is = s3Object.getObjectContent();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();) {

            ByteStreams.copy(is, baos);

            byte[] bytes = baos.toByteArray();

            return new ResourceByteArray(bytes, name);
        }
    }

    @Override
    public ResourceMetadata getResourceMetadata(String name, String containerName) {
        try (S3Object s3Object = s3Client.getObject(containerName, name);) {
            ObjectMetadata om = s3Object.getObjectMetadata();
            URL url = s3Client.generatePresignedUrl(containerName, name, null);
            return new AWSResourceMetadata(name, containerName, om, url.toURI());
        } catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<? extends ResourceMetadata> list(String containerName) {
        ObjectListing list = s3Client.listObjects(containerName);
        List<ResourceMetadata> result = new ArrayList<>();
        for (S3ObjectSummary os : list.getObjectSummaries()) {
            result.add(new S3OSResourceMetadata(containerName, os));
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }

}
