import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
public class InitializeFile implements Runnable{
    public void run(){
        StringBuilder stringBuilder= new StringBuilder() ;
        FileWriter fw = null;
        FileReader reader = null;
        File tempFile = null;
        try{
            tempFile = new File("..//logs//logs.txt");
            reader = new FileReader(tempFile);
            if(tempFile.exists()){
                int ch;
                while((ch = reader.read() )!= -1){
                    stringBuilder.append((char)ch);
                }
            }
            String str = stringBuilder.toString();
            int index = str.lastIndexOf("EOFH");
            if(index != -1)
            fw = new FileWriter("..//logs//logs.txt");
            fw.write(str.substring(index+4));
            fw.flush();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                reader.close();
                fw.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}