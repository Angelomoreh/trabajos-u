/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exp6_s7_angelo_silva;

/**
 *
 * @author angel
 */
import java.util.ArrayList; 
import java.util.Scanner;
public class EXP6_S7_angelo_silva {

    /**
     * @param args the command line arguments
     */
    // Variables de instancia
    private ArrayList<String> ubicaciones = new ArrayList<>();
    private ArrayList<Double> preciosFinales = new ArrayList<>();
    private ArrayList<Double> descuentosAplicados = new ArrayList<>();

    // Variables estáticas
    static int totalEntradasVendidas = 0;
    static double ingresosTotales = 0;
    static String nombreTeatro = "Teatro Moro";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EXP6_S7_angelo_silva teatro = new EXP6_S7_angelo_silva();

        // Variables locales
        int opcion;

        do {
            System.out.println("Bienvenido al " + nombreTeatro);
            System.out.println("1. para comprar tu entrada");
            System.out.println("2. para visualizar resumen de ventas");
            System.out.println("3. para generar boleta");
            System.out.println("4. para calcular ingresos totales");
            System.out.println("5. para salir");
            System.out.print("porfavor, Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    boolean seguirComprando = true;
                    while (seguirComprando) {
                        sc.nextLine(); // Limpiar buffer
                        System.out.println("porfavor dinos donde te gustaria ubicarte: VIP, Platea, Balcón");
                        String ubicacion = sc.nextLine();
                        double precioBase = 0;

                        if (ubicacion.equalsIgnoreCase("VIP")) {
                            precioBase = 30000;
                        } else if (ubicacion.equalsIgnoreCase("Platea")) {
                            precioBase = 20000;
                        } else if (ubicacion.equalsIgnoreCase("Balcón")) {
                            precioBase = 15000;
                        } else {
                            System.out.println("Ubicación no válida. Venta cancelada.");
                            break;
                        }

                        System.out.println("Es estudiante? (si/no)");
                        String esEstudiante = sc.nextLine();
                        System.out.println("Es persona de tercera edad? (si/no)");
                        String esTerceraEdad = sc.nextLine();

                        double descuento = 0;
                        if (esEstudiante.equalsIgnoreCase("si")) {
                            descuento = 0.10 * precioBase;
                        } else if (esTerceraEdad.equalsIgnoreCase("si")) {
                            descuento = 0.15 * precioBase;
                        }

                        double precioFinal = precioBase - descuento;

                        System.out.println("------ Detalle de su compra -----");
                        System.out.println("Ubicación: " + ubicacion);
                        System.out.println("Precio Base: $" + precioBase);
                        System.out.println("Descuento: $" + descuento);
                        System.out.println("Total a pagar: $" + precioFinal);
                        System.out.print("¿Desea confirmar esta compra? (si/no): ");
                        String confirmar = sc.nextLine();

                        if (confirmar.equalsIgnoreCase("si")) {
                            teatro.ubicaciones.add(ubicacion);
                            teatro.preciosFinales.add(precioFinal);
                            teatro.descuentosAplicados.add(descuento);
                            totalEntradasVendidas++;
                            ingresosTotales += precioFinal;
                            System.out.println("Compra confirmada exitosamente!");
                        } else {
                            System.out.println("Compra cancelada.");
                        }

                        System.out.println("¿Qué desea hacer ahora?");
                        System.out.println("1. si quiero comprar otra entrada");
                        System.out.println("2. si quiero volver al menú principal");
                        System.out.println("3. si quiero salir del programa");
                        System.out.print("porfavor, Seleccione una opción: ");
                        int siguienteAccion = sc.nextInt();
                        if (siguienteAccion == 1) {
                            seguirComprando = true;
                        } else if (siguienteAccion == 2) {
                            seguirComprando = false;
                        } else if (siguienteAccion == 3) {
                            System.out.println("muchas gracias por su compra. Vuelva pronto!");
                            sc.close();
                            System.exit(0);
                        } else {
                            System.out.println("Opción no válida. Volviendo al menú principal.");
                            seguirComprando = false;
                        }
                    }
                    break;

                case 2:
                    System.out.println("Resumen de Ventas:");
                    for (int i = 0; i < teatro.ubicaciones.size(); i++) {
                        System.out.println("Ubicación: " + teatro.ubicaciones.get(i)
                                + ", Precio Final: $" + teatro.preciosFinales.get(i)
                                + ", Descuento aplicado: $" + teatro.descuentosAplicados.get(i));
                    }
                    break;

                case 3:
                    System.out.println("Generación de Boletas:");
                    for (int i = 0; i < teatro.ubicaciones.size(); i++) {
                        System.out.println("------- Boleta -------");
                        System.out.println("Teatro: " + nombreTeatro);
                        System.out.println("Ubicación: " + teatro.ubicaciones.get(i));
                        System.out.println("Precio Base: $" + (teatro.preciosFinales.get(i) + teatro.descuentosAplicados.get(i)));
                        System.out.println("Descuento: $" + teatro.descuentosAplicados.get(i));
                        System.out.println("Precio Final: $" + teatro.preciosFinales.get(i));
                        System.out.println("Gracias por su compra!");
                    }
                    break;

                case 4:
                    System.out.println("Total ingresos acumulados: $" + ingresosTotales);
                    System.out.println("Total entradas vendidas: " + totalEntradasVendidas);
                    break;

                case 5:
                    System.out.println("Gracias por su compra. Vuelva pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);

        sc.close();
    }
}
