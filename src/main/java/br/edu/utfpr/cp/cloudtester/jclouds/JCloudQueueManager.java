package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.Queue;
import br.edu.utfpr.cp.cloudtester.tool.QueueManager;
import br.edu.utfpr.cp.cloudtester.tool.Resource;
import br.edu.utfpr.cp.cloudtester.tool.ResourceId;
import java.io.IOException;

/**
 *
 * @author Douglas
 */
public class JCloudQueueManager implements QueueManager {

    @Override
    public ResourceId send(Resource file, Queue queue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resource retrieves(ResourceId id, Queue queue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
