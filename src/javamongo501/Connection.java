
package javamongo501;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;

import java.util.logging.Level;
import java.util.logging.Logger ;
/**
 *
 * @author Mayo
 */
public class Connection{
   DB BaseDatos;
    DBCollection coleccion;
    BasicDBObject document = new BasicDBObject();
    
    public Connection(){
        MongoClient mongo = new MongoClient("localhost",27017);
        BaseDatos = mongo.getDB("bd501");
        coleccion = BaseDatos.getCollection("coleccion501");
        System.out.println("Conexion Exitosa");
        }
    
    
    public boolean Insertar(String accion){
        document.put("accion", accion);
        coleccion.insert(document);
        return true;
    }
    
   
    public void Mostrar(){
        DBCursor cursor = coleccion.find();
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }
    
   
    public boolean Actualizar(String accionVieja, String accionNueva){
        document.put("accionNueva", accionNueva);
        BasicDBObject documentoNuevo = new BasicDBObject();
        documentoNuevo.put("accion",accionNueva);
        coleccion.findAndModify(document, documentoNuevo);
        return true;
    }
    
   
    public boolean Eliminar(String accion){
        document.put("accion", accion);
        coleccion.remove(document);
        return true;
    }
}
