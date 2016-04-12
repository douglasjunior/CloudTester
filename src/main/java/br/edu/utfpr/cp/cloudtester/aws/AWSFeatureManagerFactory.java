package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.tool.DBManager;
import br.edu.utfpr.cp.cloudtester.tool.FeatureManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.StoreManager;
import br.edu.utfpr.cp.cloudtester.tool.VMManager;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 *
 * @author Douglas
 */
public class AWSFeatureManagerFactory extends FeatureManagerFactory {

    private final AWSCredentials credentials;

    public AWSFeatureManagerFactory(String identity, String credential) {
        credentials = new BasicAWSCredentials(identity, credential);
    }

    @Override
    public StoreManager createStoreManager() {
        AmazonS3 s3Client = new AmazonS3Client(credentials);
        return new AWSStoreManager(s3Client);
    }

    @Override
    public QueueManager createQueueManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VMManager createVMManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DBManager createDBManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
