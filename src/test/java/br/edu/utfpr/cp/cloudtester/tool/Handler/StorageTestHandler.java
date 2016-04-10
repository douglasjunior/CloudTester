package br.edu.utfpr.cp.cloudtester.tool.Handler;

import br.edu.utfpr.cp.cloudtester.tool.FeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceFile;
import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;
import br.edu.utfpr.cp.cloudtester.tool.StoreManager;
import com.google.common.io.Closeables;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;

/**
 *
 * @author Douglas
 */
public class StorageTestHandler {

    public static void uploadTest(final FeatureManagerFactory factory, final String containerName, final int testTimes) throws IOException {
        System.out.println("Testing Upload in " + factory);
        try {
            StoreManager storeManager = factory.createStoreManager();

            for (int i = 0; i < testTimes; i++) {
                Resource file = new ResourceFile("file" + i + ".txt");
                long start = System.currentTimeMillis();
                storeManager.stores(file, containerName);
                long diff = System.currentTimeMillis() - start;
                System.out.println("Uploaded File " + file.getName() + " in " + diff + " millis");
            }

            Closeables.close(storeManager, true);
        } catch (Exception ex) {
            Assert.fail("Test Upload failed with " + factory);
            throw ex;
        }
    }

    public static void downloadTest(final FeatureManagerFactory factory, final String containerName, final int testTimes) throws IOException {
        System.out.println("Testing Download in " + factory);
        try {
            StoreManager storeManager = factory.createStoreManager();

            for (int i = 0; i < testTimes; i++) {
                long start = System.currentTimeMillis();
                Resource resourceByteArray = storeManager.retrieves("file" + i + ".txt", containerName);
                long diff = System.currentTimeMillis() - start;
                System.out.println("Downloaded File " + resourceByteArray.getName()
                        + " size " + resourceByteArray.getLength() + " in " + diff + " millis");
            }

            Closeables.close(storeManager, true);
        } catch (Exception ex) {
            Assert.fail("Test Download failed with " + factory);
            throw ex;
        }
    }

    public static void listTest(final FeatureManagerFactory factory, final String containerName, final int testTimes) throws IOException {
        System.out.println("Testing List Files in " + factory);
        try {
            StoreManager storeManager = factory.createStoreManager();

            for (int i = 0; i < testTimes; i++) {
                long start = System.currentTimeMillis();
                List<? extends ResourceMetadata> result = storeManager.list(containerName);
                long diff = System.currentTimeMillis() - start;
                System.out.println("Listed " + result.size() + " files in " + diff + " millis");
            }

            Closeables.close(storeManager, true);
        } catch (Exception ex) {
            Assert.fail("Test List Files failed with " + factory);
            throw ex;
        }
    }
}
