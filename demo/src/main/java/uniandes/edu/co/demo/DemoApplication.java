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
import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.repository.CuentaRepository;
import uniandes.edu.co.demo.repository.UsuarioRepository;

@ComponentScan({"uniandes.edu.co.demo.repository"})
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    


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

        Cuenta nuevaCuenta = new Cuenta(12, "Ahorros", "Activa", 0, 1, 1);
        cuentaRepository.save(nuevaCuenta);



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
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("----- CREAR CUENTA -----");
    
        // Solicitar los datos de la nueva cuenta
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente
        
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
    
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
    
        System.out.print("Saldo: ");
        int saldo = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente
        
        System.out.print("ID del cliente: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente
        
        System.out.print("ID de la oficina: ");
        int oficina = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente
    
        // Crear una instancia de Cuenta con los datos ingresados
        Cuenta nuevaCuenta = new Cuenta(id, tipo, estado, saldo, clienteId, oficina);
    
        // Guardar la nueva cuenta en el repositorio
        cuentaRepository.save(nuevaCuenta);
    
        // Mostrar mensaje de confirmación
        System.out.println("Cuenta creada exitosamente.");
    }

    public void cambiarEstadoCuenta() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("----- CAMBIAR ESTADO DE CUENTA -----");
    
        // Solicitar el ID de la cuenta cuyo estado se quiere cambiar
        System.out.print("Ingrese el ID de la cuenta: ");
        int idCuenta = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente
    
        // Verificar si la cuenta existe en la base de datos
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(idCuenta);
        if (cuentaOptional.isPresent()) {
            // La cuenta existe, solicitar el nuevo estado
            System.out.print("Ingrese el nuevo estado (Activa/Inactiva/Cerrada): ");
            String nuevoEstado = scanner.nextLine();
    
            // Obtener la cuenta de la base de datos
            Cuenta cuenta = cuentaOptional.get();
    
            // Actualizar el estado de la cuenta
            cuenta.setEstado(nuevoEstado);
    
            // Guardar los cambios en la base de datos
            cuentaRepository.save(cuenta);
    
            System.out.println("Estado de la cuenta actualizado exitosamente.");
        } else {
            // La cuenta no existe
            System.out.println("La cuenta con ID " + idCuenta + " no existe.");
        }
    }
    

    public void registrarOperacionSobreCuenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- REGISTRAR OPERACION SOBRE CUENTA EN BANCANDES -----");
        System.out.println("Seleccione el criterio de agrupación:");
        System.out.println("1) Por tipo de cuenta");
        System.out.println("2) Por rango de saldos");
        System.out.println("3) Por ID de cliente");
        System.out.print("Ingrese su opción: ");
    }

    public void consultarCuentasBancandes() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("----- CONSULTAR CUENTAS EN BANCANDES -----");
        System.out.println("Seleccione el criterio de agrupación:");
        System.out.println("1) Por tipo de cuenta");
        System.out.println("2) Por rango de saldos");
        System.out.println("3) Por ID de cliente");
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente
    
        switch (opcion) {
            case 1:
                consultarPorTipoCuenta();
                break;
            case 2:
                consultarPorRangoSaldos();
                break;
            case 3:
                consultarPorIdCliente();
                break;
            default:
                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                break;
        }
    }

    public void obtenerExtractoBancario() {
        // Lógica para obtener el extracto bancario para una cuenta
    }

    private void consultarPorTipoCuenta() {
        // Realizar la consulta por tipo de cuenta y mostrar los resultados
        List<CuentaRepository.RespuestaGrupo> cuentasPorTipo = cuentaRepository.contarPorTipoCuenta();
        
        // Mostrar resultados
        System.out.println("Cuentas agrupadas por tipo de cuenta:");
        for (CuentaRepository.RespuestaGrupo grupo : cuentasPorTipo) {
            System.out.println("Tipo de cuenta: " + grupo.getTipo() +
                               ", Cantidad: " + grupo.getCantidad() +
                               ", Saldo promedio: " + grupo.getSaldoPromedio());
        }
    }
    
    private void consultarPorRangoSaldos() {
        // Realizar consulta por rango de saldos y mostrar los resultados
        List<Cuenta> cuentasPorRangoSaldos = cuentaRepository.findAll(); // Aquí debes implementar la consulta adecuada
        // Mostrar resultados
        System.out.println("Cuentas agrupadas por rango de saldos:");
        // Aquí debes mostrar los resultados de la consulta
    }
    
    private void consultarPorIdCliente() {
        // Realizar consulta por ID de cliente y mostrar los resultados
        List<Cuenta> cuentasPorIdCliente = cuentaRepository.findAll(); // Aquí debes implementar la consulta adecuada
        // Mostrar resultados
        System.out.println("Cuentas agrupadas por ID de cliente:");
        // Aquí debes mostrar los resultados de la consulta
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

