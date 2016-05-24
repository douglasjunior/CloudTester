package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueMessage;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.PurgeQueueRequest;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Douglas
 */
public class AWSQueue implements Queue {

    private static final String ATTR_ApproximateNumberOfMessages = "ApproximateNumberOfMessages";

    private final AmazonSQS sqsClient;
    private final String name;
    private String url;

    AWSQueue(AmazonSQS sqsClient, String url, String name) {
        this.sqsClient = sqsClient;
        this.url = url;
        this.name = name;
        try {
            checkUrl();
        } catch (QueueDoesNotExistException ex) {
            System.err.println(ex.toString());
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void create() {
        url = sqsClient.createQueue(name).getQueueUrl();
    }

    @Override
    public void delete() {
        checkUrl();
        sqsClient.deleteQueue(url);
    }

    @Override
    public void clear() {
        checkUrl();
        sqsClient.purgeQueue(new PurgeQueueRequest(url));
    }

    @Override
    public QueueMessage addMessage(String message) {
        checkUrl();
        String messageId = sqsClient.sendMessage(url, message).getMessageId();
        return new AWSQueueMessage(messageId, message, null);
    }

    @Override
    public QueueMessage addMessage(byte[] message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteMessage(QueueMessage message) {
        checkUrl();
        sqsClient.deleteMessage(url, (String) message.getIdentifier());
    }

    @Override
    public QueueMessage peekMessage() {
        return retrieveMessage(0);
    }

    @Override
    public QueueMessage retrieveMessage(int visibilityTimeoutInSeconds) {
        checkUrl();
        ReceiveMessageRequest request = new ReceiveMessageRequest(url)
                .withVisibilityTimeout(visibilityTimeoutInSeconds)
                .withMaxNumberOfMessages(1);
        List<Message> messages = sqsClient.receiveMessage(request).getMessages();
        if (messages == null || messages.isEmpty()) {
            return null;
        }
        Message msg = messages.get(0);
        return new AWSQueueMessage(msg.getMessageId(), msg.getBody(), msg.getReceiptHandle());
    }

    @Override
    public List<QueueMessage> retrieveMessages(int maxMessages) {
        checkUrl();
        ReceiveMessageRequest request = new ReceiveMessageRequest(url)
                .withMaxNumberOfMessages(maxMessages);
        List<QueueMessage> queueMessages = new ArrayList<>();
        for (Message msg : sqsClient.receiveMessage(request).getMessages()) {
            queueMessages.add(new AWSQueueMessage(msg.getMessageId(), msg.getBody(), msg.getReceiptHandle()));
        }
        return queueMessages;
    }

    @Override
    public long getApproximateMessageCount() {
        checkUrl();
        GetQueueAttributesRequest request = new GetQueueAttributesRequest(url);
        request.withAttributeNames(ATTR_ApproximateNumberOfMessages);
        GetQueueAttributesResult queueAttributes = sqsClient.getQueueAttributes(request);
        Map<String, String> attrs = queueAttributes.getAttributes();
        return Long.parseLong(attrs.get(ATTR_ApproximateNumberOfMessages));
    }

    private void checkUrl() {
        if (url == null) {
            url = sqsClient.getQueueUrl(name).getQueueUrl();
        }
    }

    @Override
    public String toString() {
        return "name=" + name + ",url=" + url;
    }
}
