import java.util.Date;

class MateriaCursadas {
    private String nombre;
    private int nota;
    private Date fecha;

    public MateriaCursadas(String nombre, int nota, Date fecha) {
        this.nombre = nombre;
        this.nota = nota;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
