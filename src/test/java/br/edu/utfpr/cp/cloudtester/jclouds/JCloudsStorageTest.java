package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.handler.StorageTestHandler;
import java.io.IOException;
import org.junit.*;

/**
 *
 * @author Douglas
 */
public class JCloudsStorageTest extends JCloudsTest {

    public JCloudsStorageTest() {
    }

    @Test
    public void azureTest() throws IOException {
        StorageTestHandler.uploadTest(jcloudsAzureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.downloadTest(jcloudsAzureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.listTest(jcloudsAzureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.deleteTest(jcloudsAzureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
    }

    @Test
    public void awsTest() throws IOException {
        StorageTestHandler.uploadTest(jcloudsAwsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.downloadTest(jcloudsAwsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.listTest(jcloudsAwsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.deleteTest(jcloudsAwsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
    }

}
