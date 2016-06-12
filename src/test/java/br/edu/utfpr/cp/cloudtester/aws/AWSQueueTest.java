package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.handler.QueueTestHandler;
import java.io.IOException;
import org.junit.*;

/**
 *
 * @author Douglas
 */
public class AWSQueueTest extends AWSTest {

    public AWSQueueTest() {
    }

    @Test
    public void queueTest() throws IOException {
        QueueTestHandler.createQueue(awsFactory, TEST_TIMES);
        QueueTestHandler.retrieveQueue(awsFactory, TEST_TIMES);
        QueueTestHandler.addMessage(awsFactory, TEST_TIMES);
        QueueTestHandler.peekMessage(awsFactory, TEST_TIMES);
        QueueTestHandler.retrieveMessage(awsFactory, TEST_TIMES);
        QueueTestHandler.deleteMessage(awsFactory, TEST_TIMES);
        QueueTestHandler.deleteQueue(awsFactory, TEST_TIMES);
    }
}
