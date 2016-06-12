package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.handler.QueueTestHandler;
import br.edu.utfpr.cp.cloudtester.handler.StorageTestHandler;
import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import com.google.inject.Module;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
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
        int count = 10;
        QueueTestHandler.createQueue(awsFactory, count);
        QueueTestHandler.retrieveQueue(awsFactory, count);
        QueueTestHandler.addMessage(awsFactory, count);
        QueueTestHandler.peekMessage(awsFactory, count);
        QueueTestHandler.retrieveMessage(awsFactory, count);
        QueueTestHandler.deleteMessage(awsFactory, count);
        QueueTestHandler.deleteQueue(awsFactory, count);
    }

}
