package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.Resource;
import java.io.IOException;
import br.edu.utfpr.cp.cloudtester.tool.ResourceMetadata;

/**
 *
 * @author Douglas
 */
public class JCloudQueueManager implements QueueManager {

    @Override
    public ResourceMetadata send(Resource file, Queue queue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resource retrieves(ResourceMetadata id, Queue queue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
