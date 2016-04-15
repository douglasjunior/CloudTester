package br.edu.utfpr.cp.cloudtester.comparation;

import br.edu.utfpr.cp.cloudtester.azure.AzureFeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.jclouds.JCloudFeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.FeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.handler.StoreTestHandler;
import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.junit.*;

/**
 *
 * @author Douglas
 */
public class JCloudAzureComparationTest {

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

        azureFactory = new AzureFeatureManagerFactory(new Authentication(IDENTITY_AZURE, CREDENTIAL_AZURE));
        jcloudFactory = new JCloudFeatureManagerFactory(new Authentication(IDENTITY_AZURE, CREDENTIAL_AZURE, PROVIDER_AZURE));
    }

    @Test
    public void uploadComparationTest() throws IOException {
        StoreTestHandler.uploadTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StoreTestHandler.uploadTest(jcloudFactory, CONTAINER_NAME_AZURE, 10);
    }

    @Test
    public void downloadComparationTest() throws IOException {
        StoreTestHandler.downloadTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StoreTestHandler.downloadTest(jcloudFactory, CONTAINER_NAME_AZURE, 10);
    }

    @Test
    public void listComparationTest() throws IOException {
        StoreTestHandler.listTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StoreTestHandler.listTest(jcloudFactory, CONTAINER_NAME_AZURE, 10);
    }

}
