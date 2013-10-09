/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Jeff
 */
@MessageDriven(mappedName = "GCQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MDBean implements MessageListener {
    
    public MDBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
       try {
          System.out.println("received: " + msg.getText());
       } catch (JMSException ex) {
           System.out.println("JMS is excepting");
       }
    }
}
