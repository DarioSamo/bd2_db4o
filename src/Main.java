import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import java.io.File;
import java.util.ArrayList;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

public class Main {
    static final String NombreAlumnoA = "Wenceslao";
    static final String NombreAlumnoB = "Dario";
    
    public static void generarDB() {
        String Filename = "Ingenieria.bin";
        File file = new File(Filename);
        file.delete();
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), Filename);
        
        // Llenar con datos de prueba.
        Facultad ingenieria = new Facultad("Ingenieria");
        ingenieria.AddMateria("Programacion 3");
        ingenieria.AddMateria("Fisica 2");
        ingenieria.AddMateria("Base de Datos");
        ingenieria.AddMateria("Analisis Matematico C");
        ingenieria.AddMateria("Teoria de la Informacion");
        
        Alumno alumnoA = new Alumno(NombreAlumnoA, 28, new ArrayList<MateriaCursada>());
        alumnoA.agregarMateria("Programacion 3", 8);
        alumnoA.agregarMateria("Fisica 2", 6);
        alumnoA.agregarMateria("Base de Datos", 2);
        
        Alumno alumnoB = new Alumno(NombreAlumnoB, 27, new ArrayList<MateriaCursada>());
        alumnoB.agregarMateria("Analisis Matematico C", 4);
        alumnoB.agregarMateria("Programacion 3", 10);
        alumnoB.agregarMateria("Teoria de la Informacion", 7);
        
        ingenieria.AddAlumno(alumnoA);
        ingenieria.AddAlumno(alumnoB);
        
        // Persistir y cerrar.
        db.store(ingenieria);
        db.close();
    }
    
    public static void consultarQueryByExample(ObjectContainer db) {
        System.out.println("\nConsultando por QBE...");
        
        // Consulta 1
        System.out.println("Consulta 1:");
        Alumno ejemplo = new Alumno(NombreAlumnoA, 0, null);
        ObjectSet<Alumno> resultados = db.queryByExample(ejemplo);
        if (resultados.hasNext()) {
            resultados.next().print();
        }
        else {
            System.out.println("No se encontró al alumno " + NombreAlumnoA + ".");
        }
        
        // Consulta 2
        System.out.println("Consulta 2:");
        resultados = db.query(Alumno.class);
        while (resultados.hasNext()) {
            Alumno alumno = resultados.next();
            if (alumno.Promedio() > 6) {
                alumno.print();
            }
        }
    }
    
    public static void consultarNativeQuery(ObjectContainer db) {
        System.out.println("\nConsultando por NQ...");
        
        // Consulta 1
        System.out.println("Consulta 1:");
        ObjectSet<Alumno> resultados = db.query(new Predicate<Alumno>() {
            public boolean match(Alumno alumno) {
                return alumno.getNombre().equals(NombreAlumnoA);
            }
        });
        
        if (resultados.hasNext()) {
            resultados.next().print();
        }
        else {
            System.out.println("No se encontró al alumno " + NombreAlumnoA + ".");
        }
        
        // Consulta 2
        System.out.println("Consulta 2:");
        resultados = db.query(new Predicate<Alumno>() {
            public boolean match(Alumno alumno) {
                return alumno.Promedio() > 6;
            }
        });
        
        while (resultados.hasNext()) {
            resultados.next().print();
        }
    }
    
    static private class EvaluacionPromedio implements Evaluation {
        public void evaluate(Candidate candidato) {
            Alumno alumno = (Alumno)(candidato.getObject());
            candidato.include(alumno.Promedio() > 6);
        }
    }
    
    public static void consultarSODAQueryAPI(ObjectContainer db) {
        System.out.println("\nConsultando por SODA...");
        
        // Consulta 1
        System.out.println("Consulta 1:");
        Query query = db.query();
        query.constrain(Alumno.class);
        query.descend("nombre").constrain(NombreAlumnoA);
        ObjectSet<Alumno> resultados = query.execute();
        if (resultados.hasNext()) {
            resultados.next().print();
        }
        else {
            System.out.println("No se encontró al alumno " + NombreAlumnoA + ".");
        }
        
        // Consulta 2
        System.out.println("Consulta 2:");
        query = db.query();
        query.constrain(Alumno.class);
        query.constrain(new EvaluacionPromedio());
        resultados = query.execute();
        while (resultados.hasNext()) {
            Alumno alumno = resultados.next();
            if (alumno.Promedio() > 6) {
                alumno.print();
            }
        }
    }
    
    public static void consultarDB() {
        // Cargar de disco.
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Ingenieria.bin");
        
        // Ejecutar consultas en cada modo.
        consultarQueryByExample(db);
        consultarNativeQuery(db);
        consultarSODAQueryAPI(db);
        
        // Cerrar.
        db.close();
    }
    
    public static void main(String[] args) {
        // Generar los datos.
        generarDB();
        
        // Ejecutar las consultas.
        consultarDB();
    }
}
