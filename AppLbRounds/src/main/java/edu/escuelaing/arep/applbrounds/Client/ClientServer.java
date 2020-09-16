/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.applbrounds.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author diego
 */
public class ClientServer {
    public ClientServer(){
        
    }
    public  String  Server(String userInput,String host,String port) throws IOException{
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            echoSocket = new Socket(host, Integer.parseInt(port));
            out = new PrintWriter(echoSocket.getOutputStream(), true); in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            
        } catch (UnknownHostException e) {
            System.err.println("Don’t know about host!.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn’t get I/O for " + "the connection to: localhost.");
            System.exit(1);
        }
        
        if (userInput != null) {
            out.println(userInput);
            userInput=in.readLine();
        }
        echoSocket.close();  
        
        return userInput;
    }
}
