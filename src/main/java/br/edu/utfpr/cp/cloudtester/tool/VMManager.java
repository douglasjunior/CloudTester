package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 */
public interface VMManager extends ServiceManager {

    public IPAddress request(StartVM startVM);

}
