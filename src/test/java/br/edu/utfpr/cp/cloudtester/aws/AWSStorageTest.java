package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.handler.StorageTestHandler;
import java.io.IOException;
import org.junit.*;

/**
 *
 * @author Douglas
 */
public class AWSStorageTest extends AWSTest {

    public AWSStorageTest() {
    }

    @Test
    public void storageTest() throws IOException {
        StorageTestHandler.uploadTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.downloadTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.listTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
        StorageTestHandler.deleteTest(awsFactory, CONTAINER_NAME_AWS, TEST_TIMES);
    }

}
