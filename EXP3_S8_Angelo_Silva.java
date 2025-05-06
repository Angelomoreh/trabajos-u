/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exp3_s8_angelo_silva;

/**
 *
 * @author angel
 */
import java.util.*; 
public class EXP3_S8_Angelo_Silva {

    /**
     * @param args the command line arguments
     */
static Venta[] ventas = new Venta[100];
    static int contadorVentas = 0;

    static Cliente[] clientes = new Cliente[100];
    static int contadorClientes = 0;

    static boolean[] asientos = new boolean[100]; // false = libre, true = ocupado

    static Map<String, Double> descuentos = new HashMap<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        descuentos.put("estudiante", 0.10);
        descuentos.put("tercera", 0.15);

        System.out.println("Sistema de Ventas del Teatro Moro inicializado.\n");

        int opcion;
        do {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Reservar entrada");
            System.out.println("2. Comprar entrada");
            System.out.println("3. Modificar venta existente");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    reservarEntrada(sc);
                    break;
                case 2:
                    comprarEntrada(sc);
                    break;
                case 3:
                    modificarVenta(sc);
                    break;
                case 4:
                    imprimirBoleta(sc);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema. ¡Gracias por usar el sistema del Teatro Moro!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);

        sc.close();
    }

    static void reservarEntrada(Scanner sc) {
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese tipo de cliente (normal, estudiante, tercera): ");
        String tipo = sc.nextLine();
        int idCliente = contadorClientes++;
        clientes[idCliente] = new Cliente(idCliente, nombre, tipo);

        System.out.print("Seleccione número de asiento (0-99): ");
        int asiento = sc.nextInt();
        sc.nextLine();

        if (asientos[asiento]) {
            System.out.println("Asiento no disponible.");
            return;
        }

        double precio = 10000;
        double descuento = descuentos.getOrDefault(tipo, 0.0);
        double precioFinal = precio - (precio * descuento);

        int idReserva = reservas.size();
        reservas.add(new Reserva(idReserva, idCliente, "A" + asiento, precioFinal));
        asientos[asiento] = true;

        System.out.println("Reserva realizada con éxito. ID de reserva: " + idReserva);
    }

    static void comprarEntrada(Scanner sc) {
        System.out.print("Ingrese el ID de reserva: ");
        int idReserva = sc.nextInt();
        sc.nextLine();

        if (idReserva >= reservas.size()) {
            System.out.println("Reserva no encontrada.");
            return;
        }

        Reserva reserva = reservas.get(idReserva);
        int idVenta = contadorVentas++;
        ventas[idVenta] = new Venta(idVenta, reserva.idCliente, reserva.idAsiento, reserva.precioReservado);

        System.out.println("Compra realizada con éxito. ID de venta: " + idVenta);
    }

    static void modificarVenta(Scanner sc) {
        System.out.print("Ingrese ID de venta a modificar: ");
        int idVenta = sc.nextInt();
        sc.nextLine();

        if (idVenta >= contadorVentas || ventas[idVenta] == null) {
            System.out.println("Venta no encontrada.");
            return;
        }

        System.out.print("Ingrese nuevo asiento (0-99): ");
        int nuevoAsiento = sc.nextInt();
        sc.nextLine();

        if (asientos[nuevoAsiento]) {
            System.out.println("Asiento ya ocupado.");
            return;
        }

        asientos[Integer.parseInt(ventas[idVenta].idAsiento.substring(1))] = false;
        ventas[idVenta].idAsiento = "A" + nuevoAsiento;
        asientos[nuevoAsiento] = true;

        System.out.println("Venta modificada con éxito.");
    }

    static void imprimirBoleta(Scanner sc) {
        System.out.print("Ingrese ID de venta para imprimir boleta: ");
        int idVenta = sc.nextInt();
        sc.nextLine();

        if (idVenta >= contadorVentas || ventas[idVenta] == null) {
            System.out.println("Venta no encontrada.");
            return;
        }

        Venta venta = ventas[idVenta];
        Cliente cliente = clientes[venta.idCliente];

        System.out.println("----- Boleta -----");
        System.out.println("Cliente: " + cliente.nombre);
        System.out.println("Tipo: " + cliente.tipo);
        System.out.println("Asiento: " + venta.idAsiento);
        System.out.println("Precio Final: $" + venta.precioFinal);
        System.out.println("------------------");
    }

    static class Venta {
        int idVenta;
        int idCliente;
        String idAsiento;
        double precioFinal;

        Venta(int idVenta, int idCliente, String idAsiento, double precioFinal) {
            this.idVenta = idVenta;
            this.idCliente = idCliente;
            this.idAsiento = idAsiento;
            this.precioFinal = precioFinal;
        }
    }

    static class Cliente {
        int idCliente;
        String nombre;
        String tipo;

        Cliente(int idCliente, String nombre, String tipo) {
            this.idCliente = idCliente;
            this.nombre = nombre;
            this.tipo = tipo;
        }
    }

    static class Reserva {
        int idReserva;
        int idCliente;
        String idAsiento;
        double precioReservado;

        Reserva(int idReserva, int idCliente, String idAsiento, double precioReservado) {
            this.idReserva = idReserva;
            this.idCliente = idCliente;
            this.idAsiento = idAsiento;
            this.precioReservado = precioReservado;
        }
    }
}
