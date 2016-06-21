package br.edu.utfpr.cp.cloudtester.experiment.aws;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueMessage;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import java.util.List;

/**
 *
 * @author Douglas
 */
class AwsQueue implements Queue {

    private final String name;
    private final AmazonSQS sqs;
    private String url = null;

    public AwsQueue(AmazonSQS sqs, String name) {
        this.sqs = sqs;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void create() {
        CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName(name);
        url = sqs.createQueue(createQueueRequest).getQueueUrl();
    }

    @Override
    public void delete() {
        DeleteQueueRequest deleteQueueRequest = new DeleteQueueRequest().withQueueUrl(url);
        sqs.deleteQueue(deleteQueueRequest);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QueueMessage addMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QueueMessage addMessage(byte[] message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMessage(QueueMessage message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QueueMessage peekMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QueueMessage retrieveMessage(int visibilityTimeoutInSeconds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<QueueMessage> retrieveMessages(int maxMessages) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getApproximateMessageCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
