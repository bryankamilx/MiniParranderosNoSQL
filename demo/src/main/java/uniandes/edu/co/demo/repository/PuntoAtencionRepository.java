package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.PuntoAtencion;

public interface PuntoAtencionRepository extends MongoRepository<PuntoAtencion, String> {
    
    // Búsqueda de todos los puntos de atención
    List<PuntoAtencion> findAll();

    // Búsqueda por tipo de punto
    @Query("{tipoPunto: ?0}")
    List<PuntoAtencion> findByTipoPunto(String tipoPunto);

    // Búsqueda por oficina
    @Query("{oficina: ?0}")
    List<PuntoAtencion> findByOficina(String oficina);

    // Búsqueda por ubicación
    @Query("{ubicacion: ?0}")
    List<PuntoAtencion> findByUbicacion(String ubicacion);

    // Agregación: Contar puntos de atención por tipo
    @Aggregation(pipeline = {
        "{$group: { _id: '$tipoPunto', count: { $sum: 1 } }}",
        "{$project: { tipoPunto: '$_id', count: 1 }}"
    })
    List<ContarPuntosAtencionPorTipo> contarPuntosAtencionPorTipo();

    // Delete by id de punto de atención
    @DeleteQuery("{id: ?0}")
    void deleteById(String id);


    public static class ContarPuntosAtencionPorTipo {
        private String tipoPunto;
        private int count;

        public ContarPuntosAtencionPorTipo(String tipoPunto, int count) {
            this.tipoPunto = tipoPunto;
            this.count = count;
        }

        public String getTipoPunto() {
            return tipoPunto;
        }

        public void setTipoPunto(String tipoPunto) {
            this.tipoPunto = tipoPunto;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}

