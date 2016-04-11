package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.handler.StoreTestHandler;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import br.edu.utfpr.cp.cloudtester.tool.FeatureManagerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.junit.*;

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
        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        IDENTITY_AWS = props.get("IDENTITY_AWS");
        CREDENTIAL_AWS = props.get("CREDENTIAL_AWS");
        CONTAINER_NAME_AWS = props.get("CONTAINER_NAME_AWS");

        awsFactory = new JCloudFeatureManagerFactory(PROVIDER_AWS, IDENTITY_AWS, CREDENTIAL_AWS);

        IDENTITY_AZURE = props.get("IDENTITY_AZURE");
        CREDENTIAL_AZURE = props.get("CREDENTIAL_AZURE");
        CONTAINER_NAME_AZURE = props.get("CONTAINER_NAME_AZURE");

        azureFactory = new JCloudFeatureManagerFactory(PROVIDER_AZURE, IDENTITY_AZURE, CREDENTIAL_AZURE);
    }

    @Test
    public void azureTest() throws IOException {
        StoreTestHandler.uploadTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StoreTestHandler.downloadTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StoreTestHandler.listTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
    }

    @Test
    public void awsUploadTest() throws IOException {
        StoreTestHandler.uploadTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StoreTestHandler.downloadTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StoreTestHandler.listTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
    }

}
