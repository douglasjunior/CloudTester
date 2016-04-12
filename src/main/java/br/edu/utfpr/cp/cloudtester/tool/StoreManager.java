package br.edu.utfpr.cp.cloudtester.tool;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Douglas
 */
public interface StoreManager extends FeatureManager {

    public void stores(Resource resource, String containerName) throws IOException;

    public Resource retrieves(ResourceMetadata metadata) throws IOException;

    public Resource retrieves(String name, String containerName) throws IOException;

    public ResourceMetadata getResourceMetadata(String name, String containerName);

    public List<? extends ResourceMetadata> list(String containerName);

}
