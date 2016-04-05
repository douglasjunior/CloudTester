package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 */
public interface StoreManager {

    public ResourceId stores(ResourceFile file);

    public ResourceFile retrieves(ResourceId id);

}
