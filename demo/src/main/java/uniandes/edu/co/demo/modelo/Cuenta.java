package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="cuentas")
@ToString

public class Cuenta {

    @Id
    private Integer id;
    private String tipo;
    private String estado;
    private int saldo;
    private int cliente_id;
    private int oficina;

    public Cuenta(Integer id, String tipo, String estado, int saldo, int cliente_id, int oficina) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.saldo = saldo;
        this.cliente_id = cliente_id;
        this.oficina = oficina;
    }

    public Cuenta() {
        // Constructor vacío requerido por JPA
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getOficina() {
        return oficina;
    }

    public void setOficina(int oficina) {
        this.oficina = oficina;
    }
}
