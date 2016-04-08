package br.edu.utfpr.cp.cloudtester.tool;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Douglas
 */
public interface Resource {

    public InputStream getInputStream() throws IOException;

    public String getName();

    public long getLength();

}
