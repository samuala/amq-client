package amq;

import org.apache.activemq.ActiveMQConnectionFactory;
import pojo.ParamBean;

import javax.jms.*;

/**
 * Created by sammy on 2015/11/17.
 */
public class TopicPublisher implements Runnable {
    private ParamBean param;

    public TopicPublisher(ParamBean param) {
        this.param = param;
    }

    @Override
    public void run() {


    }

    public void send(ParamBean param) throws JMSException {
        System.out.println("begin publish thread " + Thread.currentThread().getId());
        String body = "";
        String template = "abcdefghijklmnopqrstuvwxyz";

        for(int i =0; i<param.getMsgSize(); ++i) {
            body = body + template.charAt(i%26);
        }

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(param.getMqServerLoc());
        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        javax.jms.Topic topic = session.createTopic("Test.Sam.Foo.Topic");

        MessageProducer messageProducer = session.createProducer(topic);
        messageProducer.setDeliveryMode(param.isPersistence() ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);

        for(int i=0; i<param.getMsgNumer(); ++i) {
            TextMessage message = session.createTextMessage();
            message.setText(body);

            messageProducer.send(message);
        }


    }
}
