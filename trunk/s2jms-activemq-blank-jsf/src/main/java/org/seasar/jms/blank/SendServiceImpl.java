package org.seasar.jms.blank;

import org.seasar.jms.core.MessageSender;

public class SendServiceImpl implements SendService {

    protected MessageSender sender;

    public SendServiceImpl() {
    }

    public void send(String message) {
        sender.send(message);
    }

    public void setSender(MessageSender sender) {
        this.sender = sender;
    }
}
