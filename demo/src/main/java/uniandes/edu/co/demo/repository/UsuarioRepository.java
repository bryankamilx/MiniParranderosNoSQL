package uniandes.edu.co.demo.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.demo.modelo.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Integer> {

    // Búsqueda por login
    @Query("{login: ?0}")
    Usuario findByLogin(String login);

    // Búsqueda por nacionalidad
    List<Usuario> findByNacionalidad(String nacionalidad);

    // Búsqueda por ciudad
    List<Usuario> findByCiudad(String ciudad);

    // Búsqueda por rol
    List<Usuario> findByRol(String rol);

    // Búsqueda por nombre
    List<Usuario> findByNombre(String nombre);

    // Agregación: Contar usuarios por ciudad
    @Aggregation(pipeline = {
        "{$group: { _id: '$ciudad', count: { $sum: 1 } }}",
        "{$project: { ciudad: '$_id', count: 1 }}"
    })
    List<ContarUsuariosPorCiudad> contarUsuariosPorCiudad();

    // Clase interna para la respuesta de agregación
    public static class ContarUsuariosPorCiudad {
        private String ciudad;
        private int count;

        public ContarUsuariosPorCiudad(String ciudad, int count) {
            this.ciudad = ciudad;
            this.count = count;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
