import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.concurrent.locks.ReentrantLock;
public class InitializeFile implements Runnable{
    ReentrantLock re;
    InitializeFile(ReentrantLock re){
        this.re = re;
    }
    public void run(){
        re.lock();
        StringBuilder stringBuilder= null ;
        BufferedReader reader = null;
        FileOutputStream outs = null;
        try{
            File tempFile = new File("..//logs//logs.txt");
            if(tempFile.exists()){
                reader = new BufferedReader(new FileReader (tempFile));
                String line = null;
                stringBuilder = new StringBuilder();
                String ls = System.getProperty("line.separator");
                while((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }
            }
            String str = stringBuilder.toString();
            int index = str.lastIndexOf("EOFH");
            System.out.println(index);
            if(index != -1)
                outs = new FileOutputStream(tempFile);  
                for(int i=index+4; i<=str.length()-1; i++){
                          outs.write((byte)str.charAt(i));
                }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                reader.close();
                outs.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}