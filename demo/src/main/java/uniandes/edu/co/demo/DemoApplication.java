package uniandes.edu.co.demo;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.repository.UsuarioRepository;
import uniandes.edu.co.demo.repository.UsuarioRepository.ContarUsuariosPorCiudad;

@ComponentScan({"uniandes.edu.co.demo.repository"})
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("----- MENU -----");
            System.out.println("1) CREAR USUARIO");
            System.out.println("2) CREAR OFICINA");
            System.out.println("3) CREAR Y BORRAR PUNTO DE ATENCIÓN");
            System.out.println("4) CREAR CUENTA");
            System.out.println("5) CAMBIAR ESTADO DE CUENTA A CERRADA O DESACTIVADA");
            System.out.println("6) REGISTRAR OPERACIÓN SOBRE CUENTA");
            System.out.println("7) CONSULTAR LAS CUENTAS EN BANCANDES");
            System.out.println("8) EXTRACTO BANCARIO PARA UNA CUENTA");
            System.out.println("0) SALIR");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea pendiente

            // Ejecutar acción basada en la opción seleccionada
            switch (opcion) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    crearOficina();
                    break;
                case 3:
                    crearYBorrarPuntoDeAtencion();
                    break;
                case 4:
                    crearCuenta();
                    break;
                case 5:
                    cambiarEstadoCuenta();
                    break;
                case 6:
                    registrarOperacionSobreCuenta();
                    break;
                case 7:
                    consultarCuentasBancandes();
                    break;
                case 8:
                    obtenerExtractoBancario();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                    break;
            }
        } while (opcion != 0);
        scanner.close();
    }

    // Implementa los métodos para cada opción del menú
    public void crearUsuario() {
        System.out.println("Hola");
    }

    public void crearOficina() {
        // Lógica para crear una oficina
    }

    public void crearYBorrarPuntoDeAtencion() {
        // Lógica para crear y borrar un punto de atención
    }

    public void crearCuenta() {
        // Lógica para crear una cuenta
    }

    public void cambiarEstadoCuenta() {
        // Lógica para cambiar el estado de una cuenta
    }

    public void registrarOperacionSobreCuenta() {
        // Lógica para registrar una operación sobre una cuenta
    }

    public void consultarCuentasBancandes() {
        // Lógica para consultar las cuentas en Bancandes
    }

    public void obtenerExtractoBancario() {
        // Lógica para obtener el extracto bancario para una cuenta
    }

    // Método run requerido por CommandLineRunner
    @Override
    public void run(String... args) throws Exception {
        // Llama al método para mostrar el menú
        mostrarMenu();
    }

    // Método main
    public static void main(String[] args) {
        // Inicia la aplicación Spring Boot
        SpringApplication.run(DemoApplication.class, args);
    }
}
