package br.edu.utfpr.cp.cloudtester.comparation;

import br.edu.utfpr.cp.cloudtester.azure.AzureServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.jclouds.JCloudsServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.handler.StorageTestHandler;
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

    private static ServiceManagerFactory azureFactory;
    private static ServiceManagerFactory jcloudFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        IDENTITY_AZURE = props.get("IDENTITY_AZURE");
        CREDENTIAL_AZURE = props.get("CREDENTIAL_AZURE");
        CONTAINER_NAME_AZURE = props.get("CONTAINER_NAME_AZURE");

        azureFactory = new AzureServiceManagerFactory(new Authentication(IDENTITY_AZURE, CREDENTIAL_AZURE), "");
        jcloudFactory = new JCloudsServiceManagerFactory(new Authentication(IDENTITY_AZURE, CREDENTIAL_AZURE, PROVIDER_AZURE, null), "", null);
    }

    @Test
    public void uploadComparationTest() throws IOException {
        StorageTestHandler.uploadTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StorageTestHandler.uploadTest(jcloudFactory, CONTAINER_NAME_AZURE, 10);
    }

    @Test
    public void downloadComparationTest() throws IOException {
        StorageTestHandler.downloadTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StorageTestHandler.downloadTest(jcloudFactory, CONTAINER_NAME_AZURE, 10);
    }

    @Test
    public void listComparationTest() throws IOException {
        StorageTestHandler.listTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StorageTestHandler.listTest(jcloudFactory, CONTAINER_NAME_AZURE, 10);
    }

}
