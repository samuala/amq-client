package amq; /**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.activemq.ActiveMQConnectionFactory;
import pojo.ParamBean;

import javax.jms.*;
import java.io.File;

public class Listener implements Runnable{

    private ParamBean p;

    public Listener(ParamBean p) {
        this.p = p;
    }

    public void receive(ParamBean param)  throws  Exception {
        //ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://172.30.11.77:61616");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(param.getMqServerLoc());
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //connection.setExceptionListener(this);
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination1 = session.createQueue(param.getQueueName());
        MessageConsumer consumer = session.createConsumer(destination1);

        /*StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
        factory.setBrokerURI("tcp://" + host + ":" + port);

        Connection connection = factory.createConnection(user, password);
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination dest = new StompJmsDestination(destination);

        MessageConsumer consumer = session.createConsumer(dest);*/
        long start = System.currentTimeMillis();
        long count = 1;
        long pre = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        long myCount = 0;
        System.out.println("Waiting for messages...");
        while (true) {
            {
                Message msg = consumer.receive();
                System.out.println(Thread.currentThread().getId() + "*****************");
                myCount++;
                if (msg instanceof TextMessage) {
                    String body = ((TextMessage) msg).getText();
                    //System.out.println(body);
                    if ("SHUTDOWN".equals(body)) {
                        long diff = System.currentTimeMillis() - start;
                        System.out.println(String.format("Received %d in %.2f seconds", count, (1.0 * diff / 1000.0)));
                        //break;
                    } else {
                        //Thread.currentThread().sleep(15);//simulate execution process
                    /*if( count != msg.getIntProperty("id") ) {
                        System.out.println("mismatch: "+count+"!="+msg.getIntProperty("id"));
                    }
                    count = msg.getIntProperty("id");

                    if( count == 0 ) {
                        start = System.currentTimeMillis();
                    }

                    count ++;*/
                    }

                    msg.acknowledge();

                } else {
                    System.out.println("Unexpected message type: " + msg.getClass());
                }

                if (myCount % 10 == 0) {
                    now = System.currentTimeMillis();
                    double rate = 10000 * 1000 / 1;
                    double time = (now - start) / 1000;
                    System.out.println(String.format("Received %d messages. Rate %s tps, time cost %s ms, " +
                            "accumulated time is %s, accumulated rate is %s tps", myCount, rate, now - pre, time, myCount / time));
                    pre = now;
                }
            }
        }
            //connection.close();
        }



    public static void main(String []args) throws Exception {
        File a = new File("D:\\tmp\\group_avg_performance_allcompany_201602.csv");
        System.out.println(a.getPath());
    }

    private static String env(String key, String defaultValue) {
        String rc = System.getenv(key);
        if( rc== null )
            return defaultValue;
        return rc;
    }

    private static String arg(String []args, int index, String defaultValue) {
        if( index < args.length )
            return args[index];
        else
            return defaultValue;
    }

    @Override
    public void run() {
        try {
            receive(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}