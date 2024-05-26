package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="operaciones")
@ToString
public class Operacion {
    @Id
    private int id;
    private int cuenta;
    private String tipo;
    private int id_usuario;
    private int valor;
    private String fecha_hora;
    private List<Cuenta> cuenta_info;

    public Operacion(int id, int cuenta, String tipo, int id_usuario, int valor, String fecha_hora) {
        super();
        this.id = id;
        this.cuenta = cuenta;
        this.tipo = tipo;
        this.id_usuario = id_usuario;
        this.valor = valor;
        this.fecha_hora = fecha_hora;
    }

    public Operacion() {}

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cuenta> getCuenta_info() {
        return cuenta_info;
    }

    public void setCuenta_info(List<Cuenta> cuenta_info) {
        this.cuenta_info = cuenta_info;
    }

    

    
    
    
}
