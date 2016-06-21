package br.edu.utfpr.cp.cloudtester.experiment.azure;

import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceFile;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.StorageManager;
import br.edu.utfpr.cp.cloudtester.util.CredentialsLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Douglas
 */
public class AzureStorageTest {

    public AzureStorageTest() {
    }

    protected static String CONTAINER_NAME_AZURE;

    protected static ServiceManagerFactory factory;

    @BeforeClass
    public static void loadCredentials() throws FileNotFoundException, IOException {
        Map<String, String> props = CredentialsLoader.getCredentials();

        System.out.println("Credentials: " + props.toString());

        Authentication auth = new Authentication(
                props.get("IDENTITY_AZURE"),
                props.get("CREDENTIAL_AZURE"));

        CONTAINER_NAME_AZURE = props.get("CONTAINER_NAME_AZURE");

        factory = new AzureServiceManagerFactory(auth, null);
    }

    @Test
    public void storageTest() throws IOException {
        String path = "C:\\images\\";
        String name = "dog.png";

        StorageManager storageManager = factory.createStorageManager();

        // Upload
        Resource res = new ResourceFile(path + name);
        storageManager.stores(res, CONTAINER_NAME_AZURE);

        // Download
        Resource res2 = storageManager.retrieves(name, CONTAINER_NAME_AZURE);
        try (InputStream is = res2.getInputStream();
                OutputStream os = new FileOutputStream(new File(path, "downloaded_" + name))) {
            IOUtils.copy(is, os);
        }
    }
}
