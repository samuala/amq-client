package amq;

import org.apache.activemq.ActiveMQConnectionFactory;
import pojo.ParamBean;

import javax.jms.*;

/**
 * Created by sammy on 2015/11/17.
 */
public class TopicListener implements Runnable {
    private ParamBean param;

    public TopicListener(ParamBean param) {
        this.param = param;
    }

    @Override
    public void run() {
        try {
            this.receive(param);
        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void receive(ParamBean param) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(param.getMqServerLoc());
        Connection connection = connectionFactory.createConnection();


        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        javax.jms.Topic topic = session.createTopic("Test.Sam.Foo.Topic");

        MessageConsumer consumer = session.createConsumer(topic);
        MyMsgListener myMsgListener = new MyMsgListener(0);
        consumer.setMessageListener(myMsgListener);

        connection.start();
    }

    class MyMsgListener implements  MessageListener {
        private int count;
        private Long startTime;
        private Long pre;

        public MyMsgListener(int count) {
            this.count = count;
            startTime = System.currentTimeMillis();
            pre = startTime;
        }

        @Override
        public void onMessage(Message message) {
            try {
                if(message instanceof TextMessage) {
                    count++;
                    if(count % 1000 == 0) {
                        Long now = System.currentTimeMillis();
                        System.out.println(String.format("1000 messages cost %s ms, tps is %s.", now - pre, (double)1000/(now - pre)));
                        pre = now;
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
