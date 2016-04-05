package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 * @param <Q>
 */
public interface QueueManager<Q extends Queue> {

    public ResourceId send(ResourceFile file, Q queue);

    public ResourceFile retrieves(ResourceId id, Q queue);

}
