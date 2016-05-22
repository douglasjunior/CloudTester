package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueMessage;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.queue.CloudQueue;
import com.microsoft.azure.storage.queue.CloudQueueMessage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class AzureQueue implements Queue {

    private final CloudQueue cloudQueue;

    AzureQueue(CloudQueue cloudQueue) {
        this.cloudQueue = cloudQueue;
    }

    @Override
    public String getName() {
        return cloudQueue.getName();
    }

    @Override
    public void create() {
        try {
            cloudQueue.create();
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void clear() {
        try {
            cloudQueue.clear();
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete() {
        try {
            cloudQueue.delete();
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public QueueMessage addMessage(String message) {
        try {
            CloudQueueMessage queueMessage = new CloudQueueMessage(message);
            cloudQueue.addMessage(queueMessage);
            return new AzureQueueMessage(queueMessage);
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public QueueMessage addMessage(byte[] message) {
        try {
            CloudQueueMessage queueMessage = new CloudQueueMessage(message);
            cloudQueue.addMessage(queueMessage);
            return new AzureQueueMessage(queueMessage);
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteMessage(QueueMessage message) {
        try {
            cloudQueue.deleteMessage((CloudQueueMessage) message.getIdentifier());
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public QueueMessage peekMessage() {
        try {
            return new AzureQueueMessage(cloudQueue.peekMessage());
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public QueueMessage retrieveMessage(int visibilityTimeoutInSeconds) {
        try {
            return new AzureQueueMessage(cloudQueue.retrieveMessage(visibilityTimeoutInSeconds, null, null));
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<QueueMessage> retrieveMessages(int maxMessages) {
        try {
            List<QueueMessage> queueMessages = new ArrayList<>();
            for (CloudQueueMessage message : cloudQueue.retrieveMessages(maxMessages)) {
                queueMessages.add(new AzureQueueMessage(message));
            }
            return queueMessages;
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public long getApproximateMessageCount() {
        try {
            if (cloudQueue.getMetadata() == null) {
                cloudQueue.downloadAttributes();
            }
            return cloudQueue.getApproximateMessageCount();
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

}
