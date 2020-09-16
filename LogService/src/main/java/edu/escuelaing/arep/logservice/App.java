/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.logservice;

import edu.escuelaing.arep.logservice.Persistence.Conection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class App {
    private static Conection Con;
    public static void main(String[] args){
        App.Con = new Conection();
        try {
            conection();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        App.Con.Close();
    }
    
    public static void conection() throws IOException{
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in .readLine()) != null) {
            System.out.println("Mensaje:" +inputLine);
            outputLine = "Respuesta" + inputLine;
            out.println(outputLine);
            if (outputLine.equals("Respuestas: Bye.")) break;
        }
        out.close(); in .close();
        clientSocket.close();
        serverSocket.close();
    }
 }