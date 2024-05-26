package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Operacion;

public interface OperacionRepository extends MongoRepository<Operacion, String> {

    // Búsqueda de todas las operaciones
    List<Operacion> findAll();

    // Búsqueda por tipo de operación
    @Query("{tipo: ?0}")
    List<Operacion> findByTipo(String tipo);

    // Búsqueda por cliente
    @Query("{cliente: ?0}")
    List<Operacion> findByCliente(String cliente);

    // Búsqueda por producto
    @Query("{producto: ?0}")
    List<Operacion> findByProducto(String producto);

    // Búsqueda por cuenta
    @Query("{cuenta: ?0}")
    List<Operacion> findByCuenta(String cuenta);

    // Agregación: Contar operaciones por tipo
    @Aggregation(pipeline = {
        "{$group: { _id: '$tipo', count: { $sum: 1 } }}",
        "{$project: { tipo: '$_id', count: 1 }}"
    })
    List<ContarOperacionesPorTipo> contarOperacionesPorTipo();

    // Delete by id de operación
    @DeleteQuery("{id: ?0}")
    void deleteById(String id);

    public static class ContarOperacionesPorTipo {
        private String tipo;
        private int count;

        public ContarOperacionesPorTipo(String tipo, int count) {
            this.tipo = tipo;
            this.count = count;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
