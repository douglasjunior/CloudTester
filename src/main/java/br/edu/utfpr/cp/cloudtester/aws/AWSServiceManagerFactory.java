package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.DBManager;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.VMManager;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import br.edu.utfpr.cp.cloudtester.tool.StorageManager;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;

/**
 *
 * @author Douglas
 */
public class AWSServiceManagerFactory extends ServiceManagerFactory {

    private final AWSCredentials credentials;

    public AWSServiceManagerFactory(Authentication authentication, String region) {
        super(authentication, region);
        credentials = new BasicAWSCredentials(authentication.getIdentity(), authentication.getCredential());
    }

    @Override
    public StorageManager createStorageManager() {
        AmazonS3 s3Client = new AmazonS3Client(credentials);
        s3Client.setRegion(Region.getRegion(Regions.fromName(region)));
        return new AWSStorageManager(s3Client);
    }

    @Override
    public QueueManager createQueueManager() {
        AmazonSQS sqsClient = new AmazonSQSClient(credentials);
        sqsClient.setRegion(Region.getRegion(Regions.fromName(region)));
        return new AWSQueueManager(sqsClient);
    }

    @Override
    public VMManager createVMManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DBManager createDBManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
