package br.edu.utfpr.cp.cloudtester.experiment.aws;

import br.edu.utfpr.cp.cloudtester.tool.Authentication;
import br.edu.utfpr.cp.cloudtester.tool.DBManager;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.ServiceManagerFactory;
import br.edu.utfpr.cp.cloudtester.tool.StorageManager;
import br.edu.utfpr.cp.cloudtester.tool.VMManager;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;

/**
 *
 * @author Douglas
 */
public class AwsServiceManagerFactory extends ServiceManagerFactory {

    private final AWSCredentials credentials;
    private final Region awsRegion;

    public AwsServiceManagerFactory(Authentication authentication, String region) {
        super(authentication, region);
        credentials = new BasicAWSCredentials(authentication.getIdentity(), authentication.getCredential());
        awsRegion = Region.getRegion(Regions.fromName(region));
    }

    @Override
    public StorageManager createStorageManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QueueManager createQueueManager() {
        AmazonSQS sqs = new AmazonSQSClient(credentials);
        sqs.setRegion(awsRegion);
        return new AwsQueueManager(sqs);
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
