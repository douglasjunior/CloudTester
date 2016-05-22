package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import com.amazonaws.services.sqs.AmazonSQS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class AWSQueueManager implements QueueManager {

    private final AmazonSQS sqsClient;

    AWSQueueManager(AmazonSQS sqsClient) {
        this.sqsClient = sqsClient;
    }

    @Override
    public Queue retrieveQueue(String name) {
        return new AWSQueue(sqsClient, null, name);
    }

    @Override
    public List<Queue> listQueues() {
        List<Queue> queues = new ArrayList<>();
        for (String queueUrl : sqsClient.listQueues().getQueueUrls()) {
            queues.add(new AWSQueue(sqsClient, queueUrl, null));
        }
        return queues;
    }

    @Override
    public void close() throws IOException {
        
    }

}
