package br.edu.utfpr.cp.cloudtester.tool;

/**
 *
 * @author Douglas
 */
public interface QueueMessage {

    public byte[] getContentAsByte();

    public String getContentAsString();

    public void setMessageContent(byte[] content);

    public void setMessageContent(String content);

    /**
     * Utilizado como indeiticador para ações como excluir a mensagem da fila.
     *
     * @return
     */
    public Object getIdentifier();
}
