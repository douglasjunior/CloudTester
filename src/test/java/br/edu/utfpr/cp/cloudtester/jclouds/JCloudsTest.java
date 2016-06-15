package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.junit.BeforeClass;

/**
 *
 * @author Douglas
 */
public abstract class JCloudsTest {

    protected static final int TEST_TIMES = 10;

    // configuração do jclouds
    private static final String PROVIDER_AZURE_BLOB = "azureblob";
    private static final String PROVIDER_AWS_S3 = "aws-s3";
    private static final String PROVIDER_AWS_SQS = "aws-sqs";

    // parâmetros lidos do credentials.properties
    protected static String CONTAINER_NAME_AZURE;
    protected static String CONTAINER_NAME_AWS;

    // fábricas
    protected static ServiceManagerFactory jcloudsAzureFactory;
    protected static ServiceManagerFactory jcloudsAwsFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {

        Iterable<Module> modules = null;
        // módulo utilizado para habilitar o Log no JClouds
        //modules = ImmutableSet.of(new SLF4JLoggingModule());

        Map<String, String> props = CredentialsLoader.getCredentials();
        System.out.println("Credentials: " + props.toString());

        Authentication awsAuth = new Authentication(
                props.get("IDENTITY_AWS"),
                props.get("CREDENTIAL_AWS"),
                PROVIDER_AWS_S3,
                PROVIDER_AWS_SQS);
        CONTAINER_NAME_AWS = props.get("CONTAINER_NAME_AWS");

        jcloudsAwsFactory = new JCloudsServiceManagerFactory(
                awsAuth,
                props.get("REGION_AWS"),
                modules);

        Authentication awsAzure = new Authentication(
                props.get("IDENTITY_AZURE"),
                props.get("CREDENTIAL_AZURE"),
                PROVIDER_AZURE_BLOB,
                null);
        CONTAINER_NAME_AZURE = props.get("CONTAINER_NAME_AZURE");

        jcloudsAzureFactory = new JCloudsServiceManagerFactory(
                awsAzure,
                "",
                modules);
    }

}
