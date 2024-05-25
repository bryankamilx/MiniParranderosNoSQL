package uniandes.edu.co.demo;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import uniandes.edu.co.demo.modelo.Oficina;
import uniandes.edu.co.demo.modelo.PuntoAtencion;
import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.repository.OficinaRepository;
import uniandes.edu.co.demo.repository.PuntoAtencionRepository;
import uniandes.edu.co.demo.repository.UsuarioRepository;

@ComponentScan({"uniandes.edu.co.demo.repository"})
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    @Autowired
    private PuntoAtencionRepository puntoAtencionRepository;

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
            System.out.println("9) LISTAR USUARIOS");
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
                case 9:
                    listarUsuarios();
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

    public void crearUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- CREAR USUARIO -----");
        
        // Solicitar los datos del nuevo usuario
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Palabra clave: ");
        String palabraClave = scanner.nextLine();

        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();

        System.out.print("Código postal: ");
        String codigoPostal = scanner.nextLine();

        System.out.print("Rol: ");
        String rol = scanner.nextLine();

        // Crear una instancia de Usuario con los datos ingresados
        Usuario nuevoUsuario = new Usuario(id, nombre, login, palabraClave, nacionalidad, direccion, telefono, ciudad, codigoPostal, rol);

        // Guardar el nuevo usuario en la base de datos
        usuarioRepository.save(nuevoUsuario);

        System.out.println("Usuario creado exitosamente.");
    }

    public void crearOficina() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- CREAR OFICINA -----");

        // Solicitar los datos de la nueva oficina
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Número de puntos de atención posibles: ");
        int puntosDeAtencionPosibles = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        System.out.print("ID del gerente (debe ser un usuario registrado): ");
        int gerenteId = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        // Verificar que el gerente exista
        Optional<Usuario> usuarioGerente = usuarioRepository.findById(gerenteId);
        if (usuarioGerente.isEmpty()) {
            System.out.println("Gerente no encontrado. Asegúrese de que el usuario esté registrado antes de asignarlo como gerente.");
            return;
        }

        // Crear una instancia de Oficina con los datos ingresados
        Oficina nuevaOficina = new Oficina(nombre, direccion, puntosDeAtencionPosibles, String.valueOf(gerenteId));

        // Guardar la nueva oficina en la base de datos
        oficinaRepository.save(nuevaOficina);

        System.out.println("Oficina creada exitosamente.");
    }

    public void listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        System.out.println("----- LISTA DE USUARIOS -----");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public void crearYBorrarPuntoDeAtencion() {
        manejarPuntosDeAtencion();
    }

    public void manejarPuntosDeAtencion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- MANEJAR PUNTOS DE ATENCIÓN -----");
        System.out.println("1) CREAR PUNTO DE ATENCIÓN");
        System.out.println("2) ELIMINAR PUNTO DE ATENCIÓN");
        System.out.println("0) VOLVER AL MENÚ PRINCIPAL");
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consume la nueva línea pendiente

        switch (opcion) {
            case 1:
                crearPuntoDeAtencion();
                break;
            case 2:
                eliminarPuntoDeAtencion();
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public void crearPuntoDeAtencion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- CREAR PUNTO DE ATENCIÓN -----");

        // Solicitar los datos del nuevo punto de atención
        System.out.print("Tipo de punto (personalizada, cajero automático, digital): ");
        String tipoPunto = scanner.nextLine();

        System.out.print("Ubicación: ");
        String ubicacion = scanner.nextLine();

        String oficina = null;
        if (!tipoPunto.equalsIgnoreCase("digital")) {
            System.out.print("ID de la oficina (debe ser una oficina registrada): ");
            oficina = scanner.nextLine();

            // Verificar que la oficina exista
            Optional<Oficina> oficinaExistente = oficinaRepository.findById(oficina);
            if (oficinaExistente.isEmpty()) {
                System.out.println("Oficina no encontrada. Asegúrese de que la oficina esté registrada antes de asignarla al punto de atención.");
                return;
            }
        }

        // Crear una instancia de PuntoAtencion con los datos ingresados
        PuntoAtencion nuevoPuntoAtencion = new PuntoAtencion(tipoPunto, ubicacion, oficina);

        // Guardar el nuevo punto de atención en la base de datos
        puntoAtencionRepository.save(nuevoPuntoAtencion);

        System.out.println("Punto de atención creado exitosamente.");
    }

    public void eliminarPuntoDeAtencion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- ELIMINAR PUNTO DE ATENCIÓN -----");

        // Solicitar el ID del punto de atención a eliminar
        System.out.print("ID del punto de atención a eliminar: ");
        String id = scanner.nextLine();

        // Verificar que el punto de atención exista
        Optional<PuntoAtencion> puntoAtencionExistente = puntoAtencionRepository.findById(id);
        if (puntoAtencionExistente.isEmpty()) {
            System.out.println("Punto de atención no encontrado.");
            return;
        }

        // Eliminar el punto de atención
        puntoAtencionRepository.deleteById(id);

        System.out.println("Punto de atención eliminado exitosamente.");
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

