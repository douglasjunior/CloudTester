/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cp.cloudtester.tool;

import br.edu.utfpr.cp.cloudtester.tool.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Douglas
 */
public class ResourceByteArray implements Resource {

    private final byte[] bytes;
    private final String name;

    public ResourceByteArray(byte[] bytes, String name) {
        this.bytes = bytes;
        this.name = name;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getLength() {
        return bytes.length;
    }

}
