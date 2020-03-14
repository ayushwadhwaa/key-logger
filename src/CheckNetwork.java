import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.locks.ReentrantLock;
public class CheckNetwork implements Runnable {
    ReentrantLock re;
    CheckNetwork(ReentrantLock re){
        this.re = re;
    }
    public void run(){
        URL url;
        URLConnection connection = null;
        while(true){
            try{
                url = new URL("http://www.google.com");
                connection  = url.openConnection();
                connection.connect();
                SendLogs sl = new SendLogs(re);
                Thread t4 = new Thread(sl);
                sl.start();
            }
            catch(Exception e){
            }
            try{
                Thread.currentThread().sleep(500);
            }
           catch(Exception e){
           }
        }
    }
}