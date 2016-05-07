package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 * @param <Q>
 */
public interface QueueManager<Q extends Queue> extends ServiceManager {

    public ResourceMetadata send(Resource file, Q queue);

    public Resource retrieves(ResourceMetadata id, Q queue);

}
