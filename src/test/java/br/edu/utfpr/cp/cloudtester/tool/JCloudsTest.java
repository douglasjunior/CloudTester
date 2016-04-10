package br.edu.utfpr.cp.cloudtester.tool;

import br.edu.utfpr.cp.cloudtester.jclouds.JCloudFeatureManagerFactory;
import com.google.common.io.Closeables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Douglas
 */
public class JCloudsTest {

    public JCloudsTest() {
    }

    private static final String PROVIDER_AZURE = "azureblob";
    private static final String PROVIDER_AWS = "aws-s3";

    private static String IDENTITY_AZURE;
    private static String CREDENTIAL_AZURE;
    private static String CONTAINER_NAME_AZURE;

    private static String IDENTITY_AWS;
    private static String CREDENTIAL_AWS;
    private static String CONTAINER_NAME_AWS;

    private static FeatureManagerFactory azureFactory;

    private static FeatureManagerFactory awsFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream("credentials.properties")) {
            Properties props = new Properties();
            props.load(fis);

            System.out.println("Credentials: " + props.toString());

            IDENTITY_AWS = props.getProperty("IDENTITY_AWS");
            CREDENTIAL_AWS = props.getProperty("CREDENTIAL_AWS");
            CONTAINER_NAME_AWS = props.getProperty("CONTAINER_NAME_AWS");

            awsFactory = new JCloudFeatureManagerFactory(PROVIDER_AWS, IDENTITY_AWS, CREDENTIAL_AWS);

            IDENTITY_AZURE = props.getProperty("IDENTITY_AZURE");
            CREDENTIAL_AZURE = props.getProperty("CREDENTIAL_AZURE");
            CONTAINER_NAME_AZURE = props.getProperty("CONTAINER_NAME_AZURE");

            azureFactory = new JCloudFeatureManagerFactory(PROVIDER_AZURE, IDENTITY_AZURE, CREDENTIAL_AZURE);
        }
    }

    @Test
    public void azureUploadTest() throws IOException {
        System.out.println("azureUploadTest");
        uploadTest(azureFactory, CONTAINER_NAME_AZURE);
    }

    @Test
    public void azureDownloadTest() throws IOException {
        System.out.println("azureDownloadTest");
        downloadTest(azureFactory, CONTAINER_NAME_AZURE);
    }

    @Test
    public void azureListTest() throws IOException {
        System.out.println("azureListTest");
        listTest(azureFactory, CONTAINER_NAME_AZURE);
    }

    @Test
    public void awsUploadTest() throws IOException {
        System.out.println("awsUploadTest");
        uploadTest(awsFactory, CONTAINER_NAME_AWS);
    }

    @Test
    public void awsDownloadTest() throws IOException {
        System.out.println("awsDownloadTest");
        downloadTest(awsFactory, CONTAINER_NAME_AWS);
    }

    @Test
    public void awsListTest() throws IOException {
        System.out.println("awsListTest");
        listTest(awsFactory, CONTAINER_NAME_AWS);
    }

    private void uploadTest(FeatureManagerFactory factory, String containerName) throws IOException {
        StoreManager storeManager = factory.createStoreManager();

        for (int i = 0; i < 10; i++) {
            Resource file = new ResourceFile("file" + i + ".txt");
            long start = System.currentTimeMillis();
            storeManager.stores(file, containerName);
            long diff = System.currentTimeMillis() - start;
            System.out.println("Uploaded File " + file.getName() + " in " + diff + " millis");
        }

        Closeables.close(storeManager, true);
    }

    private void downloadTest(FeatureManagerFactory factory, String containerName) throws IOException {
        StoreManager storeManager = factory.createStoreManager();

        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            Resource resourceByteArray = storeManager.retrieves("file" + i + ".txt", containerName);
            long diff = System.currentTimeMillis() - start;
            System.out.println("Downloaded File " + resourceByteArray.getName()
                    + " size " + resourceByteArray.getLength() + " in " + diff + " millis");
        }

        Closeables.close(storeManager, true);
    }

    private void listTest(FeatureManagerFactory factory, String containerName) throws IOException {
        StoreManager storeManager = factory.createStoreManager();

        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            List<? extends ResourceMetadata> result = storeManager.list(containerName);
            long diff = System.currentTimeMillis() - start;
            System.out.println("Listed " + result.size() + " files in " + diff + " millis");
        }

        Closeables.close(storeManager, true);
    }
}
