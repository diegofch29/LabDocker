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
        mongoCl = new MongoClient();
        database = mongoCl.getDB("DockerData");
        if (mongoCl!=null){
            System.out.println("OK");
        }
        else{
            System.out.println("FUCK");
        }
    }
    
    public void insert(String name,String date,String temp){
        DBCollection collection = database.getCollection(name);
	BasicDBObject document = new BasicDBObject();
	document.put("Date", date);
	document.put("log", temp);
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
        while (cursor.hasNext()){
            Data+=cursor.next().toString().replace("{", "").replace("}", "").replace(" ", "").replace("\"", "")+";";
        }
        return Data;
    }
    
}
