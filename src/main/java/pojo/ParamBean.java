package pojo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sammy on 2015/11/10.
 */
public class ParamBean {
    private int mode;
    private boolean persistence;
    private String mqServerLoc;
    private int msgSize;
    private String queueName;
    private int msgNumer;
    private int logPointNum;
    private int samplesNum;
    private int multiThreadNum;
    private String data;

    public ParamBean() {
        //failover:tcp://host1:port1,tcp://host2:port2
        mqServerLoc ="failover:(tcp://172.16.5.241:61616,tcp://172.16.5.241:61617,tcp://172.16.5.242:61616,tcp://172.16.5.242:61617)?initialReconnectDelay=1000";
                //"failover:tcp://172.30.11.240:61616,tcp://172.30.11.77:61616,tcp://172.30.0.21:61616";

        //"failover:(tcp://172.16.5.241:61616,tcp://172.16.5.241:61617,tcp://172.16.5.242:61616,tcp://172.16.5.242:61617)?initialReconnectDelay=1000";


                //"failover:(tcp://172.16.5.241:61656,tcp://172.16.5.241:61666,tcp://172.16.5.242:61656,tcp://172.16.5.242:61666)?initialReconnectDelay=1000";
                //"failover:tcp://172.30.11.240:61616,tcp://172.30.11.77:61616,tcp://172.30.0.21:61616";
                // "failover:(tcp://10.10.8.50:61616)?initialReconnectDelay=1000";
        // "failover:(tcp://172.16.3.147:61616)?initialReconnectDelay=1000";
                //"failover:(tcp://172.30.11.240:61616,tcp://172.30.11.77:61616,tcp://172.30.0.21:61616)?initialReconnectDelay=1000";
                //"failover:(tcp://172.16.5.241:61616,tcp://172.16.5.241:61617,tcp://172.16.5.242:61616,tcp://172.16.5.242:61617)?initialReconnectDelay=1000";
        //failover:(tcp://172.16.5.241:61616,tcp://172.16.5.241:61617,tcp://172.16.5.242:61616,tcp://172.16.5.242:61617)?initialReconnectDelay=1000
        //failover:(tcp://172.30.11.240:61616,tcp://172.30.11.77:61616,tcp://172.30.0.21:61616)?initialReconnectDelay=1000
                //"failover:tcp://172.30.11.240:61616,tcp://172.30.11.77:61616,tcp://172.30.0.21:61616";
        msgSize = 1024;
        queueName = "Test.Sam.Foo";
        msgNumer = 100_000;
        mode = 1;
        persistence = false;
        logPointNum = 10000;
        multiThreadNum = 1;
        data = "";
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getMultiThreadNum() {
        return multiThreadNum;
    }

    public void setMultiThreadNum(int multiThreadNum) {
        this.multiThreadNum = multiThreadNum;
    }

    public int getSamplesNum() {
        return samplesNum;
    }

    public void setSamplesNum(int samplesNum) {
        this.samplesNum = samplesNum;
    }

    public int getLogPointNum() {
        return logPointNum;
    }

    public void setLogPointNum(int logPointNum) {
        this.logPointNum = logPointNum;
    }

    public boolean isPersistence() {
        return persistence;
    }

    public void setPersistence(boolean persistence) {
        this.persistence = persistence;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getMqServerLoc() {
        return mqServerLoc;
    }

    public void setMqServerLoc(String mqServerLoc) {
        this.mqServerLoc = mqServerLoc;
    }

    public int getMsgSize() {
        return msgSize;
    }

    public void setMsgSize(int msgSize) {
        this.msgSize = msgSize;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public int getMsgNumer() {
        return msgNumer;
    }

    public void setMsgNumer(int msgNumer) {
        this.msgNumer = msgNumer;
    }

    @Override
    public String toString() {
        return "ParamBean{" +
                "mode=" + mode +
                ", persistence=" + persistence +
                ", mqServerLoc='" + mqServerLoc + '\'' +
                ", msgSize=" + msgSize +
                ", queueName='" + queueName + '\'' +
                ", msgNumer=" + msgNumer +
                ", logPointNum=" + logPointNum +
                ", samplesNum=" + samplesNum +
                ", multiThreadNum=" + multiThreadNum +
                ", data='" + data + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<String> l = Arrays.asList("123", "qasd");
        Long w = new Long(12323);
        String v = String.valueOf(w);
        System.out.println(v);
    }
}
