package br.edu.utfpr.cp.cloudtester.experiment.aws;

import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Douglas
 */
public class AwsQueueTest {

    protected static ServiceManagerFactory awsFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        Authentication auth = new Authentication(
                props.get("IDENTITY_AWS"),
                props.get("CREDENTIAL_AWS"));

        awsFactory = new AwsServiceManagerFactory(auth, props.get("REGION_AWS"));
    }
    
    @Test
    public void queueTest() throws InterruptedException{
        QueueManager queueManager = awsFactory.createQueueManager();
        Queue queue = queueManager.retrieveQueue("cloudtester-experiment");
        
        // Create queue
        queue.create();

        // Delete queue
        queue.delete();
    }
}
