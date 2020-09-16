/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.applbrounds;

/**
 *
 * @author diego
 */
import edu.escuelaing.arep.applbrounds.Client.ClientServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 *
 * @author diego
 */
public class SparckWeb {
    private static String[][] sockets ={{"127.0.0.1","34000"},{"127.0.0.1","34001"},{"127.0.0.1","34002"}};
    private static int count = 0;
    private static ClientServer client;
    public static void main(String[] args){
        SparckWeb.client = new ClientServer();
        port(getPort());
        get("/data", (req, res) -> inputData(req, res));
    }
    
    
    private static String inputData(Request req,Response res){
        String resp = ((req.queryParams("cadena"))==null)?"consulta DB":req.queryParams("cadena");
        String page="<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>WALL :D</h2>"
                + "<form action=\"/data\">"
                + "  Valores:<br>"
                + "  <input type=\"text\" name=\"Valores\" value=\"your message\">"
                + "  <br><br>"
                + "  <input type=\"submit\" name=\"cadena\" value=\"Subir\">"
                + "</form>";
        page = createHtmlTable(page);
        try {
            //String Data = SparckWeb.client.Server(sockets[(count)%4-1][0],sockets[(count)%4-1][1]);
            String Dataa = SparckWeb.client.Server(resp,"127.0.0.1","34000");
            System.out.println(Dataa);
            page+=FillHtmlTable(page,Dataa);
        } catch (IOException ex) {
            Logger.getLogger(SparckWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        page+= "</body>"
                + "</html>";
        count+=1;
        //System.out.println(page);
        return page;
    }
 
    
    
    private static String createHtmlTable(String outputLine) {
        outputLine = outputLine 
                + "<style>"
                + "table, th, td {"
                + "border: 1px solid black;"
                + "border-collapse: collapse;"
                + "}"
                + "th, td {padding: 15px;"
                + "text-align: left;"
                + "}"
                + "#t01 {"
                + "width: 100%;"
                + "background-color: #f1f1c1;"
                +"}"
                +"</style>";
        return outputLine;
    }
    
    
    private static String FillHtmlTable(String outputLine, String datos){
        System.out.println(datos);
        String col ="<table style=\"width:100%\">"
                + "<caption>Temperaturas</caption>"
                +"<tr>"
                    + "<th>Date</th>"
                    + "<th>Mesagge</th>"
                    + "</tr>";
        String[] data = datos.split(";");
        for (String i : data){
            System.out.println(i.split(",")[1].split(":")[1]+" "+i.split(",")[2].split(":")[1]+ "------------------");
            col+="<tr>"
                    + "<th>"+i.split(",")[1].split(":")[1]+"</th>"
                    + "<th>"+i.split(",")[2].split(":")[1]+"</th>"
                    + "</tr>";
        }
        return outputLine+col;       
    }
 

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    
}