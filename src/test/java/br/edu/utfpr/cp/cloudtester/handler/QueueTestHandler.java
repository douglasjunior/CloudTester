package br.edu.utfpr.cp.cloudtester.handler;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.QueueMessage;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import java.io.IOException;

/**
 *
 * @author Douglas
 */
public class QueueTestHandler {

    public static void createQueue(ServiceManagerFactory factory, int count) throws IOException {
        System.out.println("Testing Create Queue in " + factory);

        try (QueueManager queueManager = factory.createQueueManager()) {
            for (int i = 0; i < count; i++) {
                Queue queue = queueManager.retrieveQueue(getQueueName(i));
                long start = System.currentTimeMillis();
                queue.create();
                long diff = System.currentTimeMillis() - start;
                System.out.println("Queue " + queue.getName() + " Created in " + diff + " millis");
            }
        }
    }

    public static void deleteQueue(ServiceManagerFactory factory, int count) throws IOException {
        System.out.println("Testing Delete Queue in " + factory);

        try (QueueManager queueManager = factory.createQueueManager()) {
            for (int i = 0; i < count; i++) {
                Queue queue = queueManager.retrieveQueue(getQueueName(i));
                long start = System.currentTimeMillis();
                queue.delete();
                long diff = System.currentTimeMillis() - start;
                System.out.println("Queue " + queue.getName() + " Deleted in " + diff + " millis");
            }
        }
    }

    public static void retrieveQueue(ServiceManagerFactory factory, int count) throws IOException {
        System.out.println("Testing Retrieve Queue in " + factory);

        try (QueueManager queueManager = factory.createQueueManager()) {
            for (int i = 0; i < count; i++) {
                long start = System.currentTimeMillis();
                Queue queue = queueManager.retrieveQueue(getQueueName(i));
                long diff = System.currentTimeMillis() - start;
                System.out.println("Queue \"" + queue.getName() + "\" Retrieve in " + diff + " millis");
            }
        }
    }

    public static void addMessage(ServiceManagerFactory factory, int count) throws IOException {
        System.out.println("Testing Add Message in " + factory);

        try (QueueManager queueManager = factory.createQueueManager()) {
            Queue queue = queueManager.retrieveQueue(getQueueName(0));
            queue.create();
            for (int i = 0; i < count; i++) {
                long start = System.currentTimeMillis();
                QueueMessage message = queue.addMessage(getMessageContent(i));
                long diff = System.currentTimeMillis() - start;
                System.out.println("Message \"" + message.getContentAsString() + "\" Added in " + diff + " millis");
            }
        }
    }

    public static void peekMessage(ServiceManagerFactory factory, int count) throws IOException {
        System.out.println("Testing Peek Message in " + factory);

        try (QueueManager queueManager = factory.createQueueManager()) {
            Queue queue = queueManager.retrieveQueue(getQueueName(0));
            for (int i = 0; i < count; i++) {
                long start = System.currentTimeMillis();
                QueueMessage message = queue.peekMessage();
                long diff = System.currentTimeMillis() - start;
                System.out.println("Message \"" + message.getContentAsString() + "\" Peeked in " + diff + " millis");
            }
        }
    }

    public static void retrieveMessage(ServiceManagerFactory factory, int count) throws IOException {
        System.out.println("Testing Retrieve Message in " + factory);

        try (QueueManager queueManager = factory.createQueueManager()) {
            Queue queue = queueManager.retrieveQueue(getQueueName(0));
            for (int i = 0; i < count; i++) {
                long start = System.currentTimeMillis();
                // retorna a mensagem e torna ocultas por 1 segundo
                QueueMessage message = queue.retrieveMessage(1);
                long diff = System.currentTimeMillis() - start;
                System.out.println("Message \"" + message.getContentAsString() + "\" Retrieved in " + diff + " millis");
            }
        }
    }

    public static void deleteMessage(ServiceManagerFactory factory, int count) throws IOException {
        try {
            // aguarda 2 segundos para que as mensagens fiquem visíveis novamente
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("Testing Delete Message in " + factory);

        try (QueueManager queueManager = factory.createQueueManager()) {
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

    private static String getQueueName(int i) {
        return "cloudtester-queue-" + i;
    }

    private static String getMessageContent(int i) {
        return "Cloud Tester Message " + i;
    }

}
