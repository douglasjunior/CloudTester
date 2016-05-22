package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.net.URI;
import java.util.Date;

/**
 *
 * @author Douglas
 */
class AWSS3OSResourceMetadata implements ResourceMetadata {

    private final String containerName;
    private final S3ObjectSummary objectSummary;

    public AWSS3OSResourceMetadata(String containerName, S3ObjectSummary objectSummary) {
        this.containerName = containerName;
        this.objectSummary = objectSummary;
    }

    @Override
    public String getEtag() {
        return objectSummary.getETag();
    }

    @Override
    public String getName() {
        return objectSummary.getKey();
    }

    @Override
    public String getContainerName() {
        return containerName;
    }

    @Override
    public URI getUri() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getCreationDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getLastModified() {
        return objectSummary.getLastModified();
    }

    @Override
    public long getContentLenght() {
        return objectSummary.getSize();
    }

}
