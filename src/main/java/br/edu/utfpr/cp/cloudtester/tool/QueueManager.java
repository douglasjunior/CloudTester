package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 * @param <Q>
 */
public interface QueueManager<Q extends Queue> extends FeatureManager {

    public ResourceId send(Resource file, Q queue);

    public Resource retrieves(ResourceId id, Q queue);

}
