package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Cuenta;

public interface CuentaRepository extends MongoRepository<Cuenta, Integer> {
    
    class RespuestaGrupo {
        String tipo;
        int cantidad;
        double saldoPromedio;

        // Constructor, getters y setters
        public RespuestaGrupo(String tipo, int cantidad, double saldoPromedio) {
            this.tipo = tipo;
            this.cantidad = cantidad;
            this.saldoPromedio = saldoPromedio;
        }

        // Getters y setters
        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getSaldoPromedio() {
            return saldoPromedio;
        }

        public void setSaldoPromedio(double saldoPromedio) {
            this.saldoPromedio = saldoPromedio;
        }
    }

    @Aggregation(pipeline = {"{$group:{_id:'$tipo', cantidad:{$sum:1}, saldoPromedio:{$avg:'$saldo'}}}",
            "{$project:{'tipo':'$_id', cantidad:1, saldoPromedio:1}}"})
    List<RespuestaGrupo> contarPorTipoCuenta();
}
