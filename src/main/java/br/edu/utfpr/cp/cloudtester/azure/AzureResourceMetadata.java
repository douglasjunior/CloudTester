package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import com.microsoft.azure.storage.blob.BlobProperties;
import java.net.URI;
import java.util.Date;

/**
 *
 * @author Douglas
 */
class AzureResourceMetadata implements ResourceMetadata {

    private final String name;
    private final String containerName;
    private final BlobProperties properties;
    private final URI uri;

    AzureResourceMetadata(String name, String containerName, BlobProperties properties, URI uri) {
        this.name = name;
        this.containerName = containerName;
        this.properties = properties;
        this.uri = uri;
    }

    @Override
    public String getEtag() {
        return properties.getEtag();
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
        throw new UnsupportedOperationException("CreationDate not available");
    }

    @Override
    public Date getLastModified() {
        return properties.getLastModified();
    }

}
