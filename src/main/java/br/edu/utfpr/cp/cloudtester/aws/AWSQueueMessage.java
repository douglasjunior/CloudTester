package br.edu.utfpr.cp.cloudtester.aws;

import br.edu.utfpr.cp.cloudtester.tool.QueueMessage;

/**
 *
 * @author Douglas
 */
public class AWSQueueMessage implements QueueMessage {

    private final String content;
    private final String messageId;
    private final String receiptHandle;

    AWSQueueMessage(String messageId, String content, String receiptHandle) {
        this.messageId = messageId;
        this.content = content;
        this.receiptHandle = receiptHandle;
    }

    @Override
    public byte[] getContentAsByte() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String getContentAsString() {
       return content;
    }

    @Override
    public void setMessageContent(byte[] content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMessageContent(String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getIdentifier() {
        return receiptHandle;
    }
    
}
