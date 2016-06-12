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
        StorageTestHandler.uploadTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.downloadTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.listTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.deleteTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
    }

    @Test
    public void awsTest() throws IOException {
        StorageTestHandler.uploadTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.downloadTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.listTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.deleteTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
    }

}
