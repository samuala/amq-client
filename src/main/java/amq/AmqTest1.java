package amq;

import pojo.ParamBean;
import thread.ThreadPool;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;

/**
 * Created by sammy on 2015/11/10.
 */
public class AmqTest1 {

    public static void testF(ParamBean b) {
        //b= new ParamBean();
        b.setMsgNumer(3);
    }
    /**
     * persist 1:yes 0:no
     *
     * @param args
     */
    //TODO support multi-thread
    public static void main(String[] args) throws Exception {
        /*ParamBean pb = new ParamBean();
        testF(pb);
        System.exit(1);*/

        if(args.length % 2 != 0) {
            if(args.length > 0 && args[0].equals("-h") == false)
                System.out.println("wrong params");
            printUsage();
            System.exit(0);
        }

        System.out.println(Arrays.toString(args));

        ParamBean para = new ParamBean();
        for(int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "-h":
                    printUsage();
                    System.exit(0);
                    break;
                case "-m":
                    para.setMode(Integer.valueOf(args[i+1]));
                    break;
                case "-p":
                    para.setPersistence(args[i+1].equals("1") ? true : false);
                    break;
                case "-s":
                    para.setMqServerLoc(args[i+1]);
                    break;
                case "-l":
                    para.setMsgSize(Integer.valueOf(args[i+1]));
                    break;
                case "-q":
                    para.setQueueName(args[i+1]);
                    break;
                case "-n":
                    para.setMsgNumer(Integer.valueOf(args[i+1]));
                    break;
                case "-t":
                    para.setMultiThreadNum(Integer.valueOf(args[i+1]));
                    break;
                default:
                    System.out.println("unknown param");
                    printUsage();
                    System.exit(0);
            }
            i++;
        }

        if(para.getMode() == 1) {
            if(para.getMultiThreadNum() == 1) {
                Publisher publisher = new Publisher(para);
                publisher.run();
            }
            else {
                int threadNum = para.getMultiThreadNum();// > 16 ? 16 : para.getMultiThreadNum();
                ExecutorService service = ThreadPool.getInstance();
                for(int i = 0; i < threadNum; ++i) {
                    service.submit(new Publisher(para));
                }
            }

        }
        else if(para.getMode() == 2) {
            if(para.getMultiThreadNum() == 1) {
                Listener listener = new Listener(para);
                listener.run();
            }
            else {
                int threadNum = para.getMultiThreadNum();// > 16 ? 16 : para.getMultiThreadNum();
                ExecutorService service = ThreadPool.getInstance();
                for(int i = 0; i < threadNum; ++i) {
                    service.submit(new Listener(para));
                }
            }
        }
        else {
            System.out.println("stupid guy, check your input again!");
        }
    }

    public static void printUsage() {
        System.out.println("**************active mq benchmark V1.0**************");
        System.out.println("parametres:");
        System.out.println("-m\tmode, 1:sender, 2:receiver, default is 1");
        System.out.println("-p\twhether mq use persistent mode or not, 1:yes 0:no. default is 0. e.g. -p 1");
        System.out.println("-s\tmq server location, default is tcp://172.30.11.240:61616");
        System.out.println("-l\tmq msg length, default is 1024B");
        System.out.println("-q\tmq queue name, default is Test.Sam.Foo");
        System.out.println("-n\tmq msg number you want to send or receive, default is 100000");
        System.out.println("-t\tmulti thread running mode. default thread number is 1. MAX is 16. Note: if you set msg num to 1000, then every thread will send 1000 msgs");
        System.out.println("");
        System.out.println("example:");
        System.out.println("java -jar jarname -m 1 -p 1 -n 1000000");
        System.out.println("");
        System.out.println("enjoy it - Sammy");
    }
}
