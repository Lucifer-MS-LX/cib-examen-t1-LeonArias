package entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Proyecto {
    private int id;
    private String nombre;
    private String descripcionTecnica;
    private LocalDate fechaInicio;
    private LocalDate fechaCulminacion;
    private String asesorTecnico;
    private BigDecimal presupuesto;

    public Proyecto() {
    }

    public Proyecto(String nombre, String descripcionTecnica, LocalDate fechaInicio,
                    LocalDate fechaCulminacion, String asesorTecnico, BigDecimal presupuesto) {
        this.nombre = nombre;
        this.descripcionTecnica = descripcionTecnica;
        this.fechaInicio = fechaInicio;
        this.fechaCulminacion = fechaCulminacion;
        this.asesorTecnico = asesorTecnico;
        this.presupuesto = presupuesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionTecnica() {
        return descripcionTecnica;
    }

    public void setDescripcionTecnica(String descripcionTecnica) {
        this.descripcionTecnica = descripcionTecnica;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaCulminacion() {
        return fechaCulminacion;
    }

    public void setFechaCulminacion(LocalDate fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }

    public String getAsesorTecnico() {
        return asesorTecnico;
    }

    public void setAsesorTecnico(String asesorTecnico) {
        this.asesorTecnico = asesorTecnico;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }
}
