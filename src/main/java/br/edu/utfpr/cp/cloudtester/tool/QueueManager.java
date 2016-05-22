package br.edu.utfpr.cp.cloudtester.tool;

import java.util.List;

/**
 *
 * @author Douglas
 * @param <Q>
 */
public interface QueueManager<Q extends Queue> extends ServiceManager {

    public Queue retrieveQueue(String name);

    public List<Queue> listQueues();

}
