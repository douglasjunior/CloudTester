package br.edu.utfpr.cp.cloudtester.tool.comparation;

import br.edu.utfpr.cp.cloudtester.azure.AzureFeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.jclouds.JCloudFeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.FeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.handler.StoreTestHandler;
import br.edu.utfpr.cp.cloudtester.tool.util.CredentialsLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.junit.*;

/**
 *
 * @author Douglas
 */
public class JCloudAzureTest {

    private static final String PROVIDER_AZURE = "azureblob";

    private static String IDENTITY_AZURE;
    private static String CREDENTIAL_AZURE;
    private static String CONTAINER_NAME_AZURE;

    private static FeatureManagerFactory azureFactory;
    private static FeatureManagerFactory jcloudFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        IDENTITY_AZURE = props.get("IDENTITY_AZURE");
        CREDENTIAL_AZURE = props.get("CREDENTIAL_AZURE");
        CONTAINER_NAME_AZURE = props.get("CONTAINER_NAME_AZURE");

        azureFactory = new AzureFeatureManagerFactory(IDENTITY_AZURE, CREDENTIAL_AZURE);
        jcloudFactory = new JCloudFeatureManagerFactory(PROVIDER_AZURE, IDENTITY_AZURE, CREDENTIAL_AZURE);
    }

    @Test
    public void uploadComparationTest() throws IOException {
        StoreTestHandler.uploadTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StoreTestHandler.uploadTest(jcloudFactory, CONTAINER_NAME_AZURE, 10);
    }
}