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
        Thread t4 = null;
        SendLogs sl = null;
        while(true){
            try{
                url = new URL("http://www.google.com");
                connection  = url.openConnection();
                connection.connect();
                System.out.println("Connected");
                sl = new SendLogs(re);
                t4 = new Thread(sl);
                t4.start();
            } catch(Exception e){
            }
            try{
                Thread.currentThread().sleep(500);
            }catch(Exception e){
               System.out.println(e);
           }
        }
    }
}