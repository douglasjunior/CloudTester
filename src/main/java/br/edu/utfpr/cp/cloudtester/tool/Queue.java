package br.edu.utfpr.cp.cloudtester.tool;

import java.util.List;

/**
 *
 * @author Douglas
 */
public interface Queue {

    public String getName();

    public void createIfNotExists();

    public void deleteIfExists();

    public void clear();

    public QueueMessage addMessage(String message);

    public QueueMessage addMessage(byte[] message);

    public void deleteMessage(QueueMessage message);

    /**
     * Retorna sem remover a mensagem na frente da fila.
     *
     * @return
     */
    public QueueMessage peekMessage();

    /**
     * Retorna a primeira mensagem visível na fila torando-a invisível para os
     * demais.
     *
     * @param visibilityTimeoutInSeconds Tempo em segundos que a mensagem ficará visível
     * após ser retornada.
     * @return
     */
    public QueueMessage retrieveMessage(int visibilityTimeoutInSeconds);

    /**
     * Retorna as primeiras mensagens visíveis na fila torando-as invisíveis
     * para os demais.
     *
     * @param maxMessages Quantidade máxima de mensagens a serem retornadas.
     * @return
     */
    public List<QueueMessage> retrieveMessages(int maxMessages);

    /**
     * Aproximado pois o número de mensagens pode mudar a todo momento.
     *
     * @return
     */
    public long getApproximateMessageCount();

}
