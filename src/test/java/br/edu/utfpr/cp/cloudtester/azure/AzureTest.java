package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.handler.StorageTestHandler;
import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.QueueMessage;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        azureFactory = new AzureServiceManagerFactory(new Authentication(IDENTITY_AZURE, CREDENTIAL_AZURE));
    }

    @Test
    public void storageTest() throws IOException {
        StorageTestHandler.uploadTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StorageTestHandler.downloadTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StorageTestHandler.listTest(azureFactory, CONTAINER_NAME_AZURE, 10);
    }

    @Test
    public void queueTest() throws IOException {

        int count = 10;

        createQueue(count);
        retrieveQueue(count);
        addMessage(count);
        peekMessage(count);
        retrieveMessage(count);
        deleteMessage(count);
        deleteQueue(count);

    }

    private void createQueue(int count) throws IOException {
        System.out.println("Testing Create Queue in " + azureFactory);

        try (QueueManager queueManager = azureFactory.createQueueManager()) {
            for (int i = 0; i < count; i++) {
                Queue queue = queueManager.retrieveQueue(getQueueName(i));
                long start = System.currentTimeMillis();
                queue.createIfNotExists();
                long diff = System.currentTimeMillis() - start;
                System.out.println("Queue " + queue.getName() + " Created in " + diff + " millis");
            }
        }
    }

    private void deleteQueue(int count) throws IOException {
        System.out.println("Testing Delete Queue in " + azureFactory);

        try (QueueManager queueManager = azureFactory.createQueueManager()) {
            for (int i = 0; i < count; i++) {
                Queue queue = queueManager.retrieveQueue(getQueueName(i));
                long start = System.currentTimeMillis();
                queue.deleteIfExists();
                long diff = System.currentTimeMillis() - start;
                System.out.println("Queue " + queue.getName() + " Deleted in " + diff + " millis");
            }
        }
    }

    private void retrieveQueue(int count) throws IOException {
        System.out.println("Testing Retrieve Queue in " + azureFactory);

        try (QueueManager queueManager = azureFactory.createQueueManager()) {
            for (int i = 0; i < count; i++) {
                long start = System.currentTimeMillis();
                Queue queue = queueManager.retrieveQueue(getQueueName(i));
                long diff = System.currentTimeMillis() - start;
                System.out.println("Queue \"" + queue.getName() + "\" Retrieve in " + diff + " millis");
            }
        }
    }

    private void addMessage(int count) throws IOException {
        System.out.println("Testing Add Message in " + azureFactory);

        try (QueueManager queueManager = azureFactory.createQueueManager()) {
            Queue queue = queueManager.retrieveQueue(getQueueName(0));
            queue.createIfNotExists();
            for (int i = 0; i < count; i++) {
                long start = System.currentTimeMillis();
                QueueMessage message = queue.addMessage(getMessageContent(i));
                long diff = System.currentTimeMillis() - start;
                System.out.println("Message \"" + message.getContentAsString() + "\" Added in " + diff + " millis");
            }
        }
    }

    private void peekMessage(int count) throws IOException {
        System.out.println("Testing Peek Message in " + azureFactory);

        try (QueueManager queueManager = azureFactory.createQueueManager()) {
            Queue queue = queueManager.retrieveQueue(getQueueName(0));
            for (int i = 0; i < count; i++) {
                long start = System.currentTimeMillis();
                QueueMessage message = queue.peekMessage();
                long diff = System.currentTimeMillis() - start;
                System.out.println("Message \"" + message.getContentAsString() + "\" Peeked in " + diff + " millis");
            }
        }
    }

    private void retrieveMessage(int count) throws IOException {
        System.out.println("Testing Retrieve Message in " + azureFactory);

        try (QueueManager queueManager = azureFactory.createQueueManager()) {
            Queue queue = queueManager.retrieveQueue(getQueueName(0));
            for (int i = 0; i < count; i++) {
                long start = System.currentTimeMillis();
                QueueMessage message = queue.retrieveMessage(1);
                long diff = System.currentTimeMillis() - start;
                System.out.println("Message \"" + message.getContentAsString() + "\" Retrieved in " + diff + " millis");
            }
        }
    }

    private void deleteMessage(int count) throws IOException {
        try {
            // aguarda 2 segundos para que as mensagens fiquem visíveis novamente
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("Testing Delete Message in " + azureFactory);

        try (QueueManager queueManager = azureFactory.createQueueManager()) {
            Queue queue = queueManager.retrieveQueue(getQueueName(0));
            for (int i = 0; i < count; i++) {
                QueueMessage message = queue.retrieveMessage(1);
                long start = System.currentTimeMillis();
                queue.deleteMessage(message);
                long diff = System.currentTimeMillis() - start;
                System.out.println("Message \"" + message.getContentAsString() + "\" Deleted in " + diff + " millis");
            }
        }
    }

    private String getQueueName(int i) {
        return "cloudtester-queue-" + i;
    }

    private String getMessageContent(int i) {
        return "Cloud Tester Message " + i;
    }

}
