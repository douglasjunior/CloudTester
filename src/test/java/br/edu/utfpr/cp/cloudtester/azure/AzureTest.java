package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.junit.BeforeClass;

/**
 *
 * @author Douglas
 */
public abstract class AzureTest {

    protected static final int TEST_TIMES = 10;

    protected static String CONTAINER_NAME_AZURE;

    protected static ServiceManagerFactory azureFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        Authentication auth = new Authentication(
                props.get("IDENTITY_AZURE"), 
                props.get("CREDENTIAL_AZURE"));
        
        CONTAINER_NAME_AZURE = props.get("CONTAINER_NAME_AZURE");

        azureFactory = new AzureServiceManagerFactory(auth, "");
    }

}
