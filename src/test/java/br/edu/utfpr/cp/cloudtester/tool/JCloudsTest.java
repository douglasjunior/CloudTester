package br.edu.utfpr.cp.cloudtester.tool;

import br.edu.utfpr.cp.cloudtester.tool.Handler.StorageTestHandler;
import br.edu.utfpr.cp.cloudtester.jclouds.JCloudFeatureManagerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Douglas
 */
public class JCloudsTest {

    public JCloudsTest() {
    }

    private static final int TEST_TIMES = 10;

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
    public void azureTest() throws IOException {
        StorageTestHandler.uploadTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.downloadTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.listTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
    }

    @Test
    public void awsUploadTest() throws IOException {
        StorageTestHandler.uploadTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.downloadTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.listTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
    }

}
