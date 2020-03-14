import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.locks.ReentrantLock;
public class Main{
    public static void main(String[] args) {
        ReentrantLock re = new ReentrantLock();
        InitializeFile infObj = new InitializeFile(re);
        Thread t1 = new Thread(infObj);
        t1.start();
    }
}