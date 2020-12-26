import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Facultad {
    private String nombre;
    private List<Alumno> alumnos;
    private List<String> materias;

    public Facultad(String nombre) {
        this.nombre = nombre;
        this.alumnos = new ArrayList<Alumno>();
        this.materias = new ArrayList<String>();
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public List<String> getMaterias() {
        return materias;
    }

    public void setMaterias(List<String> materias) {
        this.materias = materias;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void AddAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }
    
    public void AddMateria(String materia) {
        this.materias.add(materia);
    }
    
    public int PromedioPorMateria(String materia) {
        int sumaTotal = 0;
        int notasContadas = 0;
        if (this.materias.contains(materia)) {
            Iterator<Alumno> itAlumnos = this.alumnos.iterator();
            Iterator<MateriaCursada> itMaterias;
            MateriaCursada mt;
            while (itAlumnos.hasNext()) {
                itMaterias = itAlumnos.next().getMaterias().iterator();
                while (itMaterias.hasNext()) {
                    mt = itMaterias.next();
                    if (mt.getNombre() == materia) {
                        sumaTotal += mt.getNota();
                        notasContadas++;
                    }
                }
            }
        }
        if (notasContadas > 0) {
            return sumaTotal / notasContadas;
        }
        else {
            return 0;
        }
    }
}
