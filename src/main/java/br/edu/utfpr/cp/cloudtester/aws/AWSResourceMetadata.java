package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.net.URI;
import java.util.Date;

/**
 *
 * @author Douglas
 */
class AWSResourceMetadata implements ResourceMetadata {

    private final ObjectMetadata objectMetadata;
    private final String containerName;
    private final String name;
    private final URI uri;

    public AWSResourceMetadata(String name, String containerName, ObjectMetadata objectMetadata, URI uri) {
        this.name = name;
        this.containerName = containerName;
        this.objectMetadata = objectMetadata;
        this.uri = uri;
    }

    @Override
    public String getEtag() {
        return objectMetadata.getETag();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getContainerName() {
        return containerName;
    }

    @Override
    public URI getUri() {
        return uri;
    }

    @Override
    public Date getCreationDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date getLastModified() {
        return objectMetadata.getLastModified();
    }

    @Override
    public long getContentLenght() {
        return objectMetadata.getContentLength();
    }

}
