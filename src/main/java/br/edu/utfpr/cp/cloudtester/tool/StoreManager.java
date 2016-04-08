package br.edu.utfpr.cp.cloudtester.tool;

import java.io.IOException;

/**
 *
 * @author Douglas
 */
public interface StoreManager extends FeatureManager {

    public ResourceId stores(Resource file) throws IOException;

    public Resource retrieves(ResourceId id) throws IOException;

}
