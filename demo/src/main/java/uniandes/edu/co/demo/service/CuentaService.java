package uniandes.edu.co.demo.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;
import uniandes.edu.co.demo.modelo.Cuenta;

import java.util.List;

@Service
public class CuentaService {

    private final MongoTemplate mongoTemplate;

    public CuentaService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void consultarPorTipoCuenta() {
        // Definir la operación de agrupación por tipo de cuenta
        GroupOperation groupByTipoCuenta = Aggregation.group("tipo").count().as("cantidad");

        // Definir la operación de agregación
        TypedAggregation<Cuenta> aggregation = Aggregation.newAggregation(Cuenta.class, groupByTipoCuenta);

        // Ejecutar la operación de agregación
        AggregationResults<Cuenta> result = mongoTemplate.aggregate(aggregation, Cuenta.class);

        // Obtener los resultados
        List<Cuenta> cuentasPorTipo = result.getMappedResults();

        // Mostrar resultados
        System.out.println("Cuentas agrupadas por tipo de cuenta:");
        for (Cuenta cuenta : cuentasPorTipo) {
            System.out.println("Tipo de cuenta: " + cuenta.getTipo() + ", Cantidad: " + cuenta.getSaldo());
        }
    }
}

