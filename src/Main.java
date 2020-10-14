import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.File;
import java.util.List;

public class Main {
    public static void generarDB() {
        String Filename = "Ingenieria.bin";
        File file = new File(Filename);
        file.delete();
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), Filename);
        Facultad ingenieria = new Facultad("Ingenieria");
        db.store(ingenieria);
        db.close();
    }
    
    public static void consultarDB() {
        System.out.println("Consultando DB...");
        
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Ingenieria.bin");
        List<Facultad> facultades = db.queryByExample(new Facultad(null));
        for (Facultad f : facultades) {
            System.out.println(f.getNombre());
        }
        
        db.close();
    }
    
    public static void main(String[] args) {
        generarDB();
        consultarDB();
    }
}
