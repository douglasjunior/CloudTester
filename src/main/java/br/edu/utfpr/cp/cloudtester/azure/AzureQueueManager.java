package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.queue.CloudQueue;
import com.microsoft.azure.storage.queue.CloudQueueClient;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class AzureQueueManager implements QueueManager {

    private final CloudQueueClient queueClient;

    AzureQueueManager(CloudQueueClient queueClient) {
        this.queueClient = queueClient;
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public Queue retrieveQueue(String name) {
        try {
            CloudQueue cloudQueue = queueClient.getQueueReference(name);
            return new AzureQueue(cloudQueue);
        } catch (URISyntaxException | StorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Queue> listQueues() {
        List<Queue> queues = new ArrayList<>();
        for (CloudQueue cloudQueue : queueClient.listQueues()) {
            queues.add(new AzureQueue(cloudQueue));
        }
        return queues;
    }

    @Override
    public void close() throws IOException {
        
    }

}
