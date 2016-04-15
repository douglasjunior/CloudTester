package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.handler.StoreTestHandler;
import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.FeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.junit.*;

/**
 *
 * @author Douglas
 */
public class AWSTest {

    public AWSTest() {
    }

    private static String IDENTITY_AWS;
    private static String CREDENTIAL_AWS;
    private static String CONTAINER_NAME_AWS;

    private static FeatureManagerFactory awsFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        IDENTITY_AWS = props.get("IDENTITY_AWS");
        CREDENTIAL_AWS = props.get("CREDENTIAL_AWS");
        CONTAINER_NAME_AWS = props.get("CONTAINER_NAME_AWS");

        awsFactory = new AWSFeatureManagerFactory(new Authentication(IDENTITY_AWS, CREDENTIAL_AWS));
    }

    @Test
    public void test() throws IOException {
        StoreTestHandler.uploadTest(awsFactory, CONTAINER_NAME_AWS, 10);
        StoreTestHandler.downloadTest(awsFactory, CONTAINER_NAME_AWS, 10);
        StoreTestHandler.listTest(awsFactory, CONTAINER_NAME_AWS, 10);
    }

}
