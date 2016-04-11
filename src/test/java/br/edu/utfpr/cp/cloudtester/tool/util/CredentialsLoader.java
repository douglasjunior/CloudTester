package br.edu.utfpr.cp.cloudtester.tool.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 *
 * @author Douglas
 */
public class CredentialsLoader {

    private static final String FILE_NAME = "credentials.properties";

    private static Map<String, String> credentials;

    public static Map<String, String> getCredentials() throws FileNotFoundException, IOException {
        if (credentials == null) {
            loadCredentials();
        }
        return new HashMap<>(credentials);
    }

    private static void loadCredentials() throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(FILE_NAME)) {
            Properties props = new Properties();
            props.load(fis);
            credentials = new HashMap<>();
            for (Entry entry : props.entrySet()) {
                credentials.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
    }
}
