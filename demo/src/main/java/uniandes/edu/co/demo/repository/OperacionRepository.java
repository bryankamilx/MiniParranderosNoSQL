package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.demo.modelo.Operacion;

public interface OperacionRepository extends MongoRepository<Operacion, Integer>{
        class ExtractoCuenta {
        private int saldoInicial;
        private List<Operacion> operaciones;
        private int saldoFinal;

        // Constructor, getters y setters
        public ExtractoCuenta(int saldoInicial, List<Operacion> operaciones, int saldoFinal) {
            this.saldoInicial = saldoInicial;
            this.operaciones = operaciones;
            this.saldoFinal = saldoFinal;
        }

        // Getters y setters
        public int getSaldoInicial() {
            return saldoInicial;
        }

        public void setSaldoInicial(int saldoInicial) {
            this.saldoInicial = saldoInicial;
        }

        public List<Operacion> getOperaciones() {
            return operaciones;
        }

        public void setOperaciones(List<Operacion> operaciones) {
            this.operaciones = operaciones;
        }

        public int getSaldoFinal() {
            return saldoFinal;
        }

        public void setSaldoFinal(int saldoFinal) {
            this.saldoFinal = saldoFinal;
        }
    }
    @Aggregation(pipeline = {
        "{ '$match': { 'cuenta': ?0 } }",
        "{ '$lookup': { 'from': 'cuentas', 'localField': 'cuenta', 'foreignField': '_id', 'as': 'cuenta_info' } }"
    })
    List<Operacion> findOperacionesConCuenta(int numeroCuenta);
}
