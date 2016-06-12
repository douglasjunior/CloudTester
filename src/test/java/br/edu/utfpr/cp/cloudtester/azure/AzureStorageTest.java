package br.edu.utfpr.cp.cloudtester.azure;

import br.edu.utfpr.cp.cloudtester.handler.StorageTestHandler;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author Douglas
 */
public class AzureStorageTest extends AzureTest {

    public AzureStorageTest() {
    }

    @Test
    public void storageTest() throws IOException {
        StorageTestHandler.uploadTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.downloadTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.listTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
        StorageTestHandler.deleteTest(azureFactory, CONTAINER_NAME_AZURE, TEST_TIMES);
    }

}
