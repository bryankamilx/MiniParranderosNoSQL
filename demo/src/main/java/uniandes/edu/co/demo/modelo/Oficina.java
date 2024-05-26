package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "oficinas")
public class Oficina {
    
    @Id
    private String id;
    
    private String nombre;
    private String direccion;
    private int puntosDeAtencionPosibles;
    private String gerente;

    public Oficina() {
    }

    public Oficina(String nombre, String direccion, int puntosDeAtencionPosibles, String gerente) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.puntosDeAtencionPosibles = puntosDeAtencionPosibles;
        this.gerente = gerente;
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPuntosDeAtencionPosibles() {
        return puntosDeAtencionPosibles;
    }

    public void setPuntosDeAtencionPosibles(int puntosDeAtencionPosibles) {
        this.puntosDeAtencionPosibles = puntosDeAtencionPosibles;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }
}
