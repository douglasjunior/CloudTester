package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.QueueMessage;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.queue.CloudQueueMessage;

/**
 *
 * @author Douglas
 */
class AzureQueueMessage implements QueueMessage {

    private final CloudQueueMessage queueMessage;

    AzureQueueMessage(CloudQueueMessage queueMessage) {
        this.queueMessage = queueMessage;
    }

    @Override
    public byte[] getContentAsByte() {
        try {
            return queueMessage.getMessageContentAsByte();
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getContentAsString() {
        try {
            return queueMessage.getMessageContentAsString();
        } catch (StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setMessageContent(byte[] content) {
        queueMessage.setMessageContent(content);
    }

    @Override
    public void setMessageContent(String content) {
        queueMessage.setMessageContent(content);
    }

    @Override
    public Object getIdentifier() {
        return queueMessage;
    }
}
