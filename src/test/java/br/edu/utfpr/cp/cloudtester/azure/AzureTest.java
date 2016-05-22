package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.handler.QueueTestHandler;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.handler.StorageTestHandler;
import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.junit.Test;
import org.junit.BeforeClass;

/**
 *
 * @author Douglas
 */
public class AzureTest {

    public AzureTest() {
    }

    private static String IDENTITY_AZURE;
    private static String CREDENTIAL_AZURE;
    private static String CONTAINER_NAME_AZURE;

    private static ServiceManagerFactory azureFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        IDENTITY_AZURE = props.get("IDENTITY_AZURE");
        CREDENTIAL_AZURE = props.get("CREDENTIAL_AZURE");
        CONTAINER_NAME_AZURE = props.get("CONTAINER_NAME_AZURE");

        azureFactory = new AzureServiceManagerFactory(new Authentication(IDENTITY_AZURE, CREDENTIAL_AZURE), "");
    }

    @Test
    public void storageTest() throws IOException {
        int count = 10;
        StorageTestHandler.uploadTest(azureFactory, CONTAINER_NAME_AZURE, count);
        StorageTestHandler.downloadTest(azureFactory, CONTAINER_NAME_AZURE, count);
        StorageTestHandler.listTest(azureFactory, CONTAINER_NAME_AZURE, count);
    }

    @Test
    public void queueTest() throws IOException {
        int count = 10;
        QueueTestHandler.createQueue(azureFactory, count);
        QueueTestHandler.retrieveQueue(azureFactory, count);
        QueueTestHandler.addMessage(azureFactory, count);
        QueueTestHandler.peekMessage(azureFactory, count);
        QueueTestHandler.retrieveMessage(azureFactory, count);
        QueueTestHandler.deleteMessage(azureFactory, count);
        QueueTestHandler.deleteQueue(azureFactory, count);
    }
}
