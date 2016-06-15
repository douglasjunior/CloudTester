package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.handler.QueueTestHandler;
import java.io.IOException;
import org.junit.*;

/**
 *
 * @author Douglas
 */
public class JCloudsQueueTest extends JCloudsTest {

    public JCloudsQueueTest() {
    }

    @Test
    public void awsQueueTest() throws IOException {
        QueueTestHandler.createQueue(jcloudsAwsFactory, TEST_TIMES);
        QueueTestHandler.retrieveQueue(jcloudsAwsFactory, TEST_TIMES);
        QueueTestHandler.addMessage(jcloudsAwsFactory, TEST_TIMES);
        QueueTestHandler.peekMessage(jcloudsAwsFactory, TEST_TIMES);
        QueueTestHandler.retrieveMessage(jcloudsAwsFactory, TEST_TIMES);
        QueueTestHandler.deleteMessage(jcloudsAwsFactory, TEST_TIMES);
        QueueTestHandler.deleteQueue(jcloudsAwsFactory, TEST_TIMES);
    }

}
