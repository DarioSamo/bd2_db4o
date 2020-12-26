import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Alumno {
    private String nombre;
    private int edad;
    private List<MateriaCursada> materias;

    public Alumno(String nombre, int edad, List<MateriaCursada> materias) {
        this.nombre = nombre;
        this.edad = edad;
        this.materias = materias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<MateriaCursada> getMaterias() {
        return materias;
    }

    public void setMaterias(List<MateriaCursada> materias) {
        this.materias = materias;
    }
    
    public void agregarMateria(String materia, int nota) {
        MateriaCursada nueva = new MateriaCursada(materia, nota, new Date());
        this.materias.add(nueva);
    }
    
    public int Promedio() {
        int sumaTotal = 0;
        int notasContadas = 0;
        Iterator<MateriaCursada> it = this.materias.iterator();
        while (it.hasNext()) {
            sumaTotal += it.next().getNota();
            notasContadas++;
        }
        
        if (notasContadas > 0) {
            return sumaTotal / notasContadas;
        }
        else {
            return 0;
        }
    }
    
    public int PromedioSinAplazos() {
        int sumaTotal = 0;
        int nota = 0;
        int notasContadas = 0;
        Iterator<MateriaCursada> it = this.materias.iterator();
        while (it.hasNext()) {
            nota = it.next().getNota();
            if (nota >= 4) {
                sumaTotal += nota;
                notasContadas++;
            }
        }
        
        if (notasContadas > 0) {
            return sumaTotal / notasContadas;
        }
        else {
            return 0;
        }
    }
    
    public int CantidaddeMaterias() {
        return this.materias.size();
    }
    
    void print() {
        System.out.println("Nombre: " + nombre + ", Edad: " + edad + ", Materias: " + materias.size());
    }
}
