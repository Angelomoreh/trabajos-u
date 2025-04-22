/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exp5_s6_angelo_silva;

/**
 *
 * @author angel
 */
import java.util.*;
public class Exp5_s6_angelo_silva {

    /**
     * @param args the command line arguments
     */
 static int totalEntradasVendidas = 0;
    static double ingresosTotales = 0;
    static int contadorEntradas = 1;

    static ArrayList<Entrada> entradasVendidas = new ArrayList<>();
    static ArrayList<Entrada> reservas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombreTeatro = "Teatro Moro";
        int capacidadSala = 100;
        int entradasDisponibles = capacidadSala;

        int opcion;
        do {
            System.out.println("===== " + nombreTeatro + " =====");
            System.out.println("1. Reservar entrada");
            System.out.println("2. Comprar entrada");
            System.out.println("3. Modificar venta");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> reservarEntrada(sc);
                case 2 -> comprarEntrada(sc);
                case 3 -> modificarVenta(sc);
                case 4 -> imprimirBoleta(sc);
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        System.out.println("Total de ingresos: $" + ingresosTotales);
        System.out.println("Entradas vendidas: " + totalEntradasVendidas);
        sc.close();
    }

    static class Entrada {
        int numero;
        String ubicacion;
        String tipoCliente;
        int cantidad;
        double precioFinal;

        Entrada(int numero, String ubicacion, String tipoCliente, int cantidad, double precioFinal) {
            this.numero = numero;
            this.ubicacion = ubicacion;
            this.tipoCliente = tipoCliente;
            this.cantidad = cantidad;
            this.precioFinal = precioFinal;
        }
    }

    public static void reservarEntrada(Scanner sc) {
        System.out.println("--- Reservar Entrada ---");
        System.out.print("porfavor, describre que ubicasion te gustaria tomar (VIP, Platea, General): ");
        String ubicacion = sc.nextLine();

        System.out.print("para aplicacion de descuentos ¿Es estudiante o tercera edad? (estudiante/general/tercera): ");
        String tipoCliente = sc.nextLine().toLowerCase();

        System.out.print("inrese la cantidad de entradas que desea reservar: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        double precioBase = 20000;
        double descuento = 0;
        if (tipoCliente.equals("estudiante")) {
            descuento = 0.10;
        } else if (tipoCliente.equals("tercera")) {
            descuento = 0.15;
        }

        double precioFinal = (precioBase - (precioBase * descuento)) * cantidad;

        Entrada nuevaReserva = new Entrada(contadorEntradas++, ubicacion, tipoCliente, cantidad, precioFinal);
        reservas.add(nuevaReserva);

        System.out.println("muchas grascias su reserva a sido realizada con exito. Su número de reserva es: " + nuevaReserva.numero);
    }

    public static void comprarEntrada(Scanner sc) {
        System.out.println("--- Comprar Entrada ---");
        System.out.print("poravor ngrese el número asociado a su reserva: ");
        int numero = sc.nextInt();
        sc.nextLine();

        Entrada reservaEncontrada = null;
        for (Entrada r : reservas) {
            if (r.numero == numero) {
                reservaEncontrada = r;
                break;
            }
        }

        if (reservaEncontrada != null) {
            reservas.remove(reservaEncontrada);
            entradasVendidas.add(reservaEncontrada);
            ingresosTotales += reservaEncontrada.precioFinal;
            totalEntradasVendidas += reservaEncontrada.cantidad;
            System.out.println("Compra realizada con éxito que disfrute su funcion.");
        } else {
            System.out.println("Reserva no encontrada.");
        }
    }

    public static void modificarVenta(Scanner sc) {
        System.out.println("--- Modificar Venta ---");
        System.out.print("porfavor ingrese el númeroasociado a su entrada vendida: ");
        int numero = sc.nextInt();
        sc.nextLine();

        for (Entrada e : entradasVendidas) {
            if (e.numero == numero) {
                System.out.print("Nueva cantidad: ");
                int nuevaCantidad = sc.nextInt();
                sc.nextLine();

                double precioBase = 20000;
                double descuento = 0;
                if (e.tipoCliente.equals("estudiante")) {
                    descuento = 0.10;
                } else if (e.tipoCliente.equals("tercera")) {
                    descuento = 0.15;
                }

                ingresosTotales -= e.precioFinal;
                totalEntradasVendidas -= e.cantidad;

                e.cantidad = nuevaCantidad;
                e.precioFinal = (precioBase - (precioBase * descuento)) * nuevaCantidad;

                ingresosTotales += e.precioFinal;
                totalEntradasVendidas += nuevaCantidad;

                System.out.println("Venta modificada correctamente.");
                return;
            }
        }

        System.out.println("Venta no encontrada.");
    }

    public static void imprimirBoleta(Scanner sc) {
        System.out.println("--- Imprimir Boleta ---");
        System.out.print("Ingrese el número de entrada: ");
        int numero = sc.nextInt();
        sc.nextLine();

        for (Entrada e : entradasVendidas) {
            if (e.numero == numero) {
                System.out.println("--- BOLETA ---");
                System.out.println("Número de entrada: " + e.numero);
                System.out.println("Ubicación: " + e.ubicacion);
                System.out.println("Tipo de cliente: " + e.tipoCliente);
                System.out.println("Cantidad: " + e.cantidad);
                System.out.println("Total pagado: $" + e.precioFinal);
                return;
            }
        }

        System.out.println("Entrada no encontrada.");
    }
}
