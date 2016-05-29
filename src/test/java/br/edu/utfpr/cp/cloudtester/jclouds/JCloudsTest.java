package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.handler.QueueTestHandler;
import br.edu.utfpr.cp.cloudtester.handler.StorageTestHandler;
import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.junit.*;

/**
 *
 * @author Douglas
 */
public class JCloudsTest {

    public JCloudsTest() {
    }

    private static final int TEST_TIMES = 10;

    private static final String PROVIDER_AZURE_BLOB = "azureblob";
    private static final String PROVIDER_AWS_S3 = "aws-s3";
    private static final String PROVIDER_AWS_SQS = "aws-sqs";

    private static String IDENTITY_AZURE;
    private static String CREDENTIAL_AZURE;
    private static String CONTAINER_NAME_AZURE;

    private static String IDENTITY_AWS;
    private static String CREDENTIAL_AWS;
    private static String CONTAINER_NAME_AWS;
    private static String REGION_AWS;

    private static ServiceManagerFactory azureFactory;

    private static ServiceManagerFactory awsFactory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        // módulo utilizado para habilitar o Log no JClouds
        Iterable<Module> modules = null;
        //modules = ImmutableSet.of(new SLF4JLoggingModule());

        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        IDENTITY_AWS = props.get("IDENTITY_AWS");
        CREDENTIAL_AWS = props.get("CREDENTIAL_AWS");
        CONTAINER_NAME_AWS = props.get("CONTAINER_NAME_AWS");
        REGION_AWS = props.get("REGION_AWS");

        awsFactory = new JCloudsServiceManagerFactory(new Authentication(IDENTITY_AWS, CREDENTIAL_AWS, PROVIDER_AWS_S3, PROVIDER_AWS_SQS), REGION_AWS, modules);

        IDENTITY_AZURE = props.get("IDENTITY_AZURE");
        CREDENTIAL_AZURE = props.get("CREDENTIAL_AZURE");
        CONTAINER_NAME_AZURE = props.get("CONTAINER_NAME_AZURE");

        azureFactory = new JCloudsServiceManagerFactory(new Authentication(IDENTITY_AZURE, CREDENTIAL_AZURE, PROVIDER_AZURE_BLOB, null), "", modules);
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

    @Test
    public void awsQueueTest() throws IOException {
        int count = 10;
        QueueTestHandler.createQueue(awsFactory, count);
        QueueTestHandler.retrieveQueue(awsFactory, count);
        QueueTestHandler.addMessage(awsFactory, count);
        QueueTestHandler.peekMessage(awsFactory, count);
        QueueTestHandler.retrieveMessage(awsFactory, count);
        QueueTestHandler.deleteMessage(awsFactory, count);
        QueueTestHandler.deleteQueue(awsFactory, count);
    }

}
