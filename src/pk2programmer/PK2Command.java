/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pk2programmer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.exec.*;
/**
 *
 * @author genm1023
 */
public class PK2Command {
    private static final String PK2CMD = "/usr/local/bin/pk2cmd";
    private CommandLine cmdLine;
    
    private int returnCode; //TODO xmlでエラーコード取得

    PK2Command(){
        
        
    }
    
    private void sendCommand(CommandLine cmdl){
        DefaultExecutor executor = new DefaultExecutor();

        System.out.println(cmdl.toString());
        try {
            ExecuteWatchdog watchDog = new ExecuteWatchdog(10000);
            executor.setWatchdog(watchDog);
            //PipedOutputStream os = new PipedOutputStream();
            //PumpStreamHandler streamHandler = new PumpStreamHandler(os);
            //executor.setStreamHandler(streamHandler);
        
            executor.setExitValue(0);
            //DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            executor.execute(cmdl);//,resultHandler);
            
            //resultHandler.waitFor();
            //PipedInputStream is = new PipedInputStream(os);
            //BufferedReader br = new BufferedReader(new InputStreamReader(is));

            //System.out.println("ReturnCode=" + br.readLine());
        }catch(Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }
    public String autoDetect(){
        cmdLine = CommandLine.parse(PK2CMD);
        cmdLine.addArgument("-P");
        sendCommand(cmdLine);
        //Auto-Detect: Found part PICXXX
        return "";
    }
    public void writeHex(String hex){
        cmdLine = CommandLine.parse(PK2CMD);
        cmdLine.addArgument("-P");
        cmdLine.addArgument("-F" + hex);
        sendCommand(cmdLine);
    }
    public void WriteEEPROM(String ep){
        cmdLine = CommandLine.parse(PK2CMD);
        cmdLine.addArgument("-P");
        cmdLine.addArgument("-ME");
        
        sendCommand(cmdLine);        
    }
    public void readHex(){
        cmdLine = CommandLine.parse(PK2CMD);
        cmdLine.addArgument("");
        
        sendCommand(cmdLine);
    }
    public void readEEPROM(){
        cmdLine = CommandLine.parse(PK2CMD);
        cmdLine.addArgument("-P24LC16B");
        cmdLine.addArgument("-GP0-3FF");
        sendCommand(cmdLine);

    }
    public void EraceHex(){
        cmdLine = CommandLine.parse(PK2CMD);
        cmdLine.addArgument("-P");
        cmdLine.addArgument("-E");
        sendCommand(cmdLine);

    }
    public void powerOn(String vol){
        cmdLine = CommandLine.parse(PK2CMD);
        cmdLine.addArgument("-P");
        cmdLine.addArgument("PIC16F84A");
        cmdLine.addArgument("-T");
        cmdLine.addArgument("-A");
        cmdLine.addArgument(vol);
        sendCommand(cmdLine);
        
    }
    public void powerOFF(){
        cmdLine = CommandLine.parse(PK2CMD);
        cmdLine.addArgument("-P");
        cmdLine.addArgument("PIC16F84A");
        sendCommand(cmdLine);
    }
    


    
}
