package br.edu.utfpr.cp.cloudtester.tool;

import java.net.URI;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public interface ResourceMetadata {

    public String getEtag();

    public String getName();

    public String getContainerName();

    public URI getUri();

    public Date getCreationDate();

    public Date getLastModified();

    public long getContentLenght();
}
