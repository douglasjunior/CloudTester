package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import java.net.URI;
import java.util.Date;
import java.util.Objects;
import org.jclouds.blobstore.domain.StorageMetadata;

/**
 *
 * @author Douglas
 */
public class JCloudResourceMetadata implements ResourceMetadata {

    private final StorageMetadata storageMetadata;
    private final String containerName;

    JCloudResourceMetadata(StorageMetadata storageMetadata, String containerName) {
        this.storageMetadata = storageMetadata;
        this.containerName = containerName;
    }

    @Override
    public String getId() {
        return storageMetadata.getETag();
    }

    @Override
    public String getName() {
        return storageMetadata.getName();
    }

    @Override
    public String getContainerName() {
        return containerName;
    }

    @Override
    public URI getUri() {
        return storageMetadata.getUri();
    }

    @Override
    public Date getCreationDate() {
        return storageMetadata.getCreationDate();
    }

    @Override
    public Date getLastModified() {
        return storageMetadata.getLastModified();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.storageMetadata);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JCloudResourceMetadata other = (JCloudResourceMetadata) obj;
        if (!Objects.equals(this.storageMetadata, other.storageMetadata)) {
            return false;
        }
        return true;
    }

}
