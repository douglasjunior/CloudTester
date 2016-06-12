package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.handler.QueueTestHandler;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author Douglas
 */
public class AzureQueueTest extends AzureTest {

    public AzureQueueTest() {
    }

    @Test
    public void queueTest() throws IOException {
        QueueTestHandler.createQueue(azureFactory, TEST_TIMES);
        QueueTestHandler.retrieveQueue(azureFactory, TEST_TIMES);
        QueueTestHandler.addMessage(azureFactory, TEST_TIMES);
        QueueTestHandler.peekMessage(azureFactory, TEST_TIMES);
        QueueTestHandler.retrieveMessage(azureFactory, TEST_TIMES);
        QueueTestHandler.deleteMessage(azureFactory, TEST_TIMES);
        QueueTestHandler.deleteQueue(azureFactory, TEST_TIMES);
    }
}
