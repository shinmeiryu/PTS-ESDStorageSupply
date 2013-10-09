/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

/**
 *
 * @author Jeff
 */
public class JmsClient implements MessageListener, ExceptionListener{
        
    @Override
    public void onMessage(Message message)
    {
       TextMessage msg = (TextMessage) message;
       try {
          System.out.println("received: " + msg.getText());
       } catch (JMSException ex) {
           System.out.println("JMS is excepting");
       }
    }
    
    public static void main(String[] args) throws Exception
    {
       // get the initial context
       InitialContext ctx = new InitialContext();
                                                                          
       // lookup the queue object
       Queue queue = (Queue) ctx.lookup("/GCQueue");
                                                                          
       // lookup the queue connection factory
       QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.
           lookup("/gcConnectionFactory");
                                                                          
       // create a queue connection
       QueueConnection queueConn = connFactory.createQueueConnection();
                                                                          
       // create a queue session
       QueueSession queueSession = queueConn.createQueueSession(false,
           Session.AUTO_ACKNOWLEDGE);
                                                                          
       // create a queue receiver
       QueueReceiver queueReceiver = queueSession.createReceiver(queue);
                                                                          
       // set an asynchronous message listener
       JmsClient asyncReceiver = new JmsClient();
       queueReceiver.setMessageListener(asyncReceiver);
                                                                          
       // set an asynchronous exception listener on the connection
       queueConn.setExceptionListener(asyncReceiver);
                                                                          
       // start the connection
       queueConn.start();
                                                                          
       // wait for messages
       System.out.print("waiting for messages");
//       for (int i = 0; i < 10; i++) {
//          Thread.sleep(1000);
//          System.out.print(".");
//       }
                                                                          
       // close the queue connection
       queueConn.close();
    }

    @Override
    public void onException(JMSException exception) {
        System.err.println("an error occurred: " + exception);
    }
}
