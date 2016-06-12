package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.junit.*;

/**
 *
 * @author Douglas
 */
public abstract class AWSTest {

    protected static final int TEST_TIMES = 10;

    protected static String CONTAINER_NAME_AWS;

    protected static ServiceManagerFactory awsFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        Authentication auth = new Authentication(
                props.get("IDENTITY_AWS"), 
                props.get("CREDENTIAL_AWS"));
        
        CONTAINER_NAME_AWS = props.get("CONTAINER_NAME_AWS");

        awsFactory = new AWSServiceManagerFactory(auth, props.get("REGION_AWS"));
    }
}
