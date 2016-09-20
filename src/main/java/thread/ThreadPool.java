package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sammy on 2015/11/10.
 */
public class ThreadPool {
    private static ExecutorService threadExecutor;
    private static Object obj = new Object();
    private ThreadPool() {}

    public static ExecutorService getInstance() {
        if(threadExecutor == null) {
            synchronized (obj) {
                if (threadExecutor == null) {
                    threadExecutor = Executors.newFixedThreadPool(128);
                }
            }
        }

        return threadExecutor;
    }
}
