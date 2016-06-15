package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.jclouds.sqs.SQSApi;
import org.jclouds.sqs.features.QueueApi;

/**
 *
 * @author Douglas
 */
public class JCloudsQueueManager implements QueueManager {

    private final SQSApi sqsApi;
    private final QueueApi queueApi;

    JCloudsQueueManager(SQSApi sqsApi, String region) {
        this.sqsApi = sqsApi;
        this.queueApi = sqsApi.getQueueApiForRegion(region);
    }

    @Override
    public Queue retrieveQueue(String name) {
        return new JCloudsQueue(sqsApi, queueApi, name, null);
    }

    @Override
    public List<Queue> listQueues() {
        List<Queue> queues = new ArrayList<>();
        for (URI url : queueApi.list()) {
            queues.add(new JCloudsQueue(sqsApi, queueApi, null, url));
        }
        return queues;
    }

    @Override
    public void close() throws IOException {
        sqsApi.close();
    }

}
