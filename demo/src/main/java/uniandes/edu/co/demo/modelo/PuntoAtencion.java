package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "puntos_atencion")
public class PuntoAtencion {

    @Id
    private String id;

    private String tipoPunto;
    private String ubicacion;
    private String oficina; // Puede ser null para puntos de atención digitales

    public PuntoAtencion() {
    }

    public PuntoAtencion(String tipoPunto, String ubicacion, String oficina) {
        this.tipoPunto = tipoPunto;
        this.ubicacion = ubicacion;
        this.oficina = oficina;
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoPunto() {
        return tipoPunto;
    }

    public void setTipoPunto(String tipoPunto) {
        this.tipoPunto = tipoPunto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }
}

