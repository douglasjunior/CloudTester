package br.edu.utfpr.cp.cloudtester.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author Douglas
 */
public class ResourceFile implements Resource {

    private final File file;

    public ResourceFile(String filePath) {
        this(new File(filePath));
    }

    public ResourceFile(File file) {
        this.file = file;
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public long getLength() {
        return file.length();
    }
}
