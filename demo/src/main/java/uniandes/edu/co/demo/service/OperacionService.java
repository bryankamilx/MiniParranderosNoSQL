package uniandes.edu.co.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.Operacion;
import uniandes.edu.co.demo.repository.CuentaRepository;
import uniandes.edu.co.demo.repository.OperacionRepository;
import uniandes.edu.co.demo.repository.OperacionRepository.ExtractoCuenta;

@Service
public class OperacionService {

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int idCounter = 1;

    private final MongoTemplate mongoTemplate;

    public OperacionService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public int getIdCounter() {
        return idCounter;
    }

    public void initializeIdCounter() {
        this.idCounter = (int)operacionRepository.count();
    }

    public Operacion consignar(int numeroCuenta, int idUsuario, int valor) {
        String fechaHora = LocalDateTime.now().format(formatter);
        initializeIdCounter();
        Operacion operacion = new Operacion(getIdCounter()+1,numeroCuenta, "consignación", idUsuario, valor, fechaHora);
        operacionRepository.save(operacion);
        actualizarSaldo(numeroCuenta, valor);
        return operacion;
    }

    public Operacion retirar(int numeroCuenta, int idUsuario, int valor) {
        if (verificarFondos(numeroCuenta, valor)) {
            String fechaHora = LocalDateTime.now().format(formatter);
            initializeIdCounter();
            Operacion operacion = new Operacion(getIdCounter()+1,numeroCuenta, "retiro", idUsuario, valor, fechaHora);
            operacionRepository.save(operacion);
            actualizarSaldo(numeroCuenta, -valor);
            return operacion;
        } else {
            throw new RuntimeException("Fondos insuficientes en la cuenta");
        }
    }

    public Operacion transferir(int cuentaOrigen, int cuentaDestino, int idUsuario, int valor) {
        if (verificarFondos(cuentaOrigen, valor)) {
            String fechaHora = LocalDateTime.now().format(formatter);
            initializeIdCounter();
            Operacion operacion = new Operacion(getIdCounter()+1,cuentaOrigen, "transferencia", idUsuario, valor, fechaHora);
            operacionRepository.save(operacion);
            actualizarSaldo(cuentaOrigen, -valor);
            actualizarSaldo(cuentaDestino, valor);
            return operacion;
        } else {
            throw new RuntimeException("Fondos insuficientes en la cuenta de origen");
        }
    }

    private void actualizarSaldo(int numeroCuenta, int valor) {
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setSaldo(cuenta.getSaldo() + valor);
        cuentaRepository.save(cuenta);
    }

    private boolean verificarFondos(int numeroCuenta, int valor) {
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta).orElse(null);
        return cuenta != null && cuenta.getSaldo() >= valor;
    }

    public void generarYMostrarExtracto(int numeroCuenta, String mes) {
        List<Operacion> extracto = operacionRepository.findOperacionesConCuenta(numeroCuenta);
        // Ahora puedes imprimir o procesar el extracto como desees
        System.out.println("Extracto para la cuenta " + numeroCuenta + " en el mes " + mes + ":");
        for (Operacion operacion : extracto) {
            System.out.println(operacion.toString());
        }
    }
    
}
