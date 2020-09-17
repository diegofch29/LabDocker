/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.logservice.Persistence;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;



/**
 *
 * @author diego
 */
public class Conection {
    private final MongoClient mongoCl ;
    private final DB database;
    
    
    public Conection() {
        mongoCl = new MongoClient(new MongoClientURI("mongodb://192.168.0.7:27017"));
        database = mongoCl.getDB("DockerData");
        if (mongoCl!=null){
            System.out.println("OK");
        }
        else{
            System.out.println("FUCK");
        }
    }
    
    public void insert(String name,String temp,String date){
        DBCollection collection = database.getCollection(name);
	BasicDBObject document = new BasicDBObject();
        document.put("log", temp);
	document.put("Date", date);
	collection.insert(document);
    }
    
    public void Close(){
        mongoCl.close();
    }
    
    public String getData(String name){
        String Data = "";
        DBCollection collection = database.getCollection(name);
	Iterator<DBObject> cursor = collection.find().iterator();
        DBObject it ;
        if (!(cursor.hasNext())){
            LocalDate date = LocalDate.now();
            insert("log","hello ", date.toString());
            cursor = collection.find().iterator();
        }
        while (cursor.hasNext()){
            Data+=cursor.next().toString().replace("{", "").replace("}", "").replace("\"", "");
            if (cursor.hasNext()){
                Data+=";";
            }
        }
        return Data;
    }
    
}
