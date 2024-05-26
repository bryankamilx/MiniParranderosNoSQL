package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Oficina;

public interface OficinaRepository extends MongoRepository<Oficina, String> {
    
    // Búsqueda de todas las oficinas
    List<Oficina> findAll();


    @Query("{nombre: ?0}")
    List<Oficina> findByNombre(String nombre);

    
    @Query("{gerente: ?0}")
    List<Oficina> findByGerente(String gerente);

    
    @Query("{direccion: ?0}")
    List<Oficina> findByDireccion(String direccion);

    @Aggregation(pipeline = {
        "{$group: { _id: '$gerente', count: { $sum: 1 } }}",
        "{$project: { gerente: '$_id', count: 1 }}"
    })
    List<ContarOficinasPorGerente> contarOficinasPorGerente();

    public static class ContarOficinasPorGerente {
        private String gerente;
        private int count;

        public ContarOficinasPorGerente(String gerente, int count) {
            this.gerente = gerente;
            this.count = count;
        }

        public String getGerente() {
            return gerente;
        }

        public void setGerente(String gerente) {
            this.gerente = gerente;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}


