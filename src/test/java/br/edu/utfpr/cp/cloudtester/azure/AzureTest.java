package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.FeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.handler.StoreTestHandler;
import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.junit.Test;
import org.junit.BeforeClass;

/**
 *
 * @author Douglas
 */
public class AzureTest {

    public AzureTest() {
    }

    private static String IDENTITY_AZURE;
    private static String CREDENTIAL_AZURE;
    private static String CONTAINER_NAME_AZURE;

    private static FeatureManagerFactory azureFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        IDENTITY_AZURE = props.get("IDENTITY_AZURE");
        CREDENTIAL_AZURE = props.get("CREDENTIAL_AZURE");
        CONTAINER_NAME_AZURE = props.get("CONTAINER_NAME_AZURE");

        azureFactory = new AzureFeatureManagerFactory(new Authentication(IDENTITY_AZURE, CREDENTIAL_AZURE));
    }

    @Test
    public void test() throws IOException {
        StoreTestHandler.uploadTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StoreTestHandler.downloadTest(azureFactory, CONTAINER_NAME_AZURE, 10);
        StoreTestHandler.listTest(azureFactory, CONTAINER_NAME_AZURE, 10);
    }

}