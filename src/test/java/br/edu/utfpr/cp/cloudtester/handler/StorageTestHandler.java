package br.edu.utfpr.cp.cloudtester.handler;

import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceFile;
import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import java.io.IOException;
import java.util.List;
import br.edu.utfpr.cp.cloudtester.tool.StorageManager;

/**
 *
 * @author Douglas
 */
public class StorageTestHandler {

    public static void uploadTest(final ServiceManagerFactory factory, final String containerName, final int testTimes) throws IOException {
        System.out.println("Testing Upload in " + factory);

        try (StorageManager storeManager = factory.createStorageManager()) {
            for (int i = 0; i < testTimes; i++) {
                Resource file = new ResourceFile("file" + i + ".txt");
                long start = System.currentTimeMillis();
                storeManager.stores(file, containerName);
                long diff = System.currentTimeMillis() - start;
                System.out.println("Uploaded File " + file.getName() + " in " + diff + " millis");
            }
        }

    }

    public static void downloadTest(final ServiceManagerFactory factory, final String containerName, final int testTimes) throws IOException {
        System.out.println("Testing Download in " + factory);

        try (StorageManager storeManager = factory.createStorageManager()) {
            for (int i = 0; i < testTimes; i++) {
                long start = System.currentTimeMillis();
                Resource resourceByteArray = storeManager.retrieves("file" + i + ".txt", containerName);
                long diff = System.currentTimeMillis() - start;
                System.out.println("Downloaded File " + resourceByteArray.getName()
                        + " size " + resourceByteArray.getLength() + " in " + diff + " millis");
            }
        }
    }

    public static void listTest(final ServiceManagerFactory factory, final String containerName, final int testTimes) throws IOException {
        System.out.println("Testing List Files in " + factory);

        try (StorageManager storeManager = factory.createStorageManager()) {
            for (int i = 0; i < testTimes; i++) {
                long start = System.currentTimeMillis();
                List<? extends ResourceMetadata> result = storeManager.list(containerName);
                long diff = System.currentTimeMillis() - start;
                System.out.println("Listed " + result.size() + " files in " + diff + " millis");
            }
        }
    }

    public static void deleteTest(final ServiceManagerFactory factory, final String containerName, final int testTimes) throws IOException {
        System.out.println("Testing Delete in " + factory);

        try (StorageManager storeManager = factory.createStorageManager()) {
            for (int i = 0; i < testTimes; i++) {
                String fileName = "file" + i + ".txt";
                long start = System.currentTimeMillis();
                storeManager.delete(fileName, containerName);
                long diff = System.currentTimeMillis() - start;
                System.out.println("Deleted File " + fileName + " in " + diff + " millis");
            }
        }
    }
}
