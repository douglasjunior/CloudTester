package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.IPAddress;
import br.edu.utfpr.cp.cloudtester.tool.StartVM;
import br.edu.utfpr.cp.cloudtester.tool.VMManager;
import java.io.IOException;

/**
 *
 * @author Douglas
 */
public class JCloudVMManager implements VMManager {

    @Override
    public IPAddress request(StartVM startVM) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
