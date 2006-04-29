package org.seasar.jms.blank;

public class SendPage {

    protected String message;
    protected SendService service;

    public SendPage() {
    }

    public String send() {
        service.send(message);
        message = null;
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setService(SendService service) {
        this.service = service;
    }
}
