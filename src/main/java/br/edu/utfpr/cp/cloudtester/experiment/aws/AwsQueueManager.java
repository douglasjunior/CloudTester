package br.edu.utfpr.cp.cloudtester.experiment.aws;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import com.amazonaws.services.sqs.AmazonSQS;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Douglas
 */
class AwsQueueManager implements QueueManager {

    private AmazonSQS sqs;

    public AwsQueueManager(AmazonSQS sqs) {
        this.sqs = sqs;
    }

    @Override
    public AwsQueue retrieveQueue(String name) {
        return new AwsQueue(sqs, name);
    }

    @Override
    public List<Queue> listQueues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws IOException {
        sqs = null;
    }
}
