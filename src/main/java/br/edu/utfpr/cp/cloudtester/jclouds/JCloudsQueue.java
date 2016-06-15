package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueMessage;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.jclouds.sqs.SQSApi;
import org.jclouds.sqs.domain.Message;
import org.jclouds.sqs.features.MessageApi;
import org.jclouds.sqs.features.QueueApi;
import org.jclouds.sqs.options.ReceiveMessageOptions;

/**
 *
 * @author Douglas
 */
public class JCloudsQueue implements Queue {

    private static final String ATTR_ApproximateNumberOfMessages = "ApproximateNumberOfMessages";

    private final SQSApi sqsApi;
    private final QueueApi queueApi;
    private MessageApi messageApi;
    private final String name;
    private URI url;

    JCloudsQueue(SQSApi sqsApi, QueueApi queueApi, String name, URI url) {
        this.sqsApi = sqsApi;
        this.queueApi = queueApi;
        this.name = name;
        this.url = url;
        checkQueue();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void create() {
        url = queueApi.create(name);
        checkQueue();
    }

    @Override
    public void delete() {
        checkQueue();
        queueApi.delete(url);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public QueueMessage addMessage(String message) {
        checkQueue();
        return new JCloudsQueueMessage(messageApi.send(message).getId(), message, null);
    }

    @Override
    public QueueMessage addMessage(byte[] message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteMessage(QueueMessage message) {
        checkQueue();
        messageApi.delete((String) message.getIdentifier());
    }

    @Override
    public QueueMessage peekMessage() {
        return retrieveMessage(0);
    }

    @Override
    public QueueMessage retrieveMessage(int visibilityTimeoutInSeconds) {
        ReceiveMessageOptions options = new ReceiveMessageOptions();
        options.visibilityTimeout(visibilityTimeoutInSeconds);
        Message message = messageApi.receive(options);
        return new JCloudsQueueMessage(message.getId(), message.getBody(), message.getReceiptHandle());
    }

    @Override
    public List<QueueMessage> retrieveMessages(int maxMessages) {
        List<QueueMessage> messages = new ArrayList<>();
        for (Message message : messageApi.receive(maxMessages)) {
            messages.add(new JCloudsQueueMessage(message.getId(), message.getBody(), message.getReceiptHandle()));
        }
        return messages;
    }

    @Override
    public long getApproximateMessageCount() {
        String number = queueApi.getAttribute(url, ATTR_ApproximateNumberOfMessages);
        return Long.parseLong(number);
    }

    private void checkQueue() {
        if (url == null) {
            url = queueApi.get(name);
        }
        if (url != null && messageApi == null) {
            messageApi = sqsApi.getMessageApiForQueue(url);
        }
    }

}
