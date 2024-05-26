package uniandes.edu.co.demo.modelo;

public class Operacion {
    // Atributos
    private String id;
    private String tipo;
    private String cliente;
    private String producto;
    private String cuenta;
    private int valor;
    private String puestoAtencion;
    private String hora;
    private String fecha;
    
    // Constructor
    public Operacion(String id, String tipo, String cliente, String producto, String cuenta, int valor, String puestoAtencion, String hora, String fecha) {
        this.id = id;
        this.tipo = tipo;
        this.cliente = cliente;
        this.producto = producto;
        this.cuenta = cuenta;
        this.valor = valor;
        this.puestoAtencion = puestoAtencion;
        this.hora = hora;
        this.fecha = fecha;
    }
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getCliente() {
        return cliente;
    }
    
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public String getProducto() {
        return producto;
    }
    
    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    public String getCuenta() {
        return cuenta;
    }
    
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    
    public int getValor() {
        return valor;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public String getPuestoAtencion() {
        return puestoAtencion;
    }
    
    public void setPuestoAtencion(String puestoAtencion) {
        this.puestoAtencion = puestoAtencion;
    }
    
    public String getHora() {
        return hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
