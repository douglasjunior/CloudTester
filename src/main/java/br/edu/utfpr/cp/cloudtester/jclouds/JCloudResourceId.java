/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cp.cloudtester.jclouds;

import br.edu.utfpr.cp.cloudtester.tool.ResourceId;

/**
 *
 * @author Douglas
 */
public class JCloudResourceId implements ResourceId {

    private final String etags;
    private final String containerName;
    private final String name;

    JCloudResourceId(String name, String containerName, String etags) {
        this.etags = etags;
        this.containerName = containerName;
        this.name = name;
    }

    @Override
    public String getId() {
        return etags;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getContainerName() {
        return containerName;
    }

}
