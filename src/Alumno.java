import java.util.List;

public class Alumno {
    private String nombre;
    private int edad;
    private List<MateriaCursadas> materias;

    public Alumno(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
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

    public List<MateriaCursadas> getMaterias() {
        return materias;
    }

    public void setMaterias(List<MateriaCursadas> materias) {
        this.materias = materias;
    }
}
