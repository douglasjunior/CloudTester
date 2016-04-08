package br.edu.utfpr.cp.cloudtester.tool;

import br.edu.utfpr.cp.cloudtester.jclouds.JCloudFeatureManagerFactory;
import com.google.common.io.Closeables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Douglas
 */
public class JCloudAzureTest {

    public JCloudAzureTest() {
    }

    private static String PROVIDER;
    private static String IDENTITY;
    private static String CREDENTIAL;
    private static String CONTAINER_NAME;

    private static FeatureManagerFactory factory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream("credentials.properties")) {
            Properties props = new Properties();
            props.load(fis);
            PROVIDER = props.getProperty("PROVIDER");
            IDENTITY = props.getProperty("IDENTITY");
            CREDENTIAL = props.getProperty("CREDENTIAL");
            CONTAINER_NAME = props.getProperty("CONTAINER_NAME");

            factory = new JCloudFeatureManagerFactory(PROVIDER, IDENTITY, CREDENTIAL, CONTAINER_NAME);
        }
    }

    private static ResourceId fileId;

    @Test
    public void uploadTest() throws IOException {
        StoreManager storeManager = factory.createStoreManager();

        Resource file = new ResourceFile("file.txt");

        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            fileId = storeManager.stores(file);
            long diff = System.currentTimeMillis() - start;
            System.out.println("Uploaded File " + fileId.getId() + " in " + diff + " millis");
        }

        Closeables.close(storeManager, true);
    }

    @Test
    public void downloadTest() throws IOException {
        StoreManager storeManager = factory.createStoreManager();

        long start = System.currentTimeMillis();
        Resource resourceByteArray = storeManager.retrieves(fileId);
        long diff = System.currentTimeMillis() - start;
        System.out.println("Downloaded File " + resourceByteArray.getName()
                + " size " + resourceByteArray.getLength() + " in " + diff + " millis");

        Closeables.close(storeManager, true);
    }
}
