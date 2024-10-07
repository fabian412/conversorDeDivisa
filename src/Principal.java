import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("*************************************");
            System.out.println("**      Conversor de Monedas       **");
            System.out.println("*************************************");
            System.out.println("  1)   Dólar => Sol peruano");
            System.out.println("  2)   Sol peruano => Dólar");
            System.out.println("  3)   Dólar => Peso argentino");
            System.out.println("  4)   Peso argentino => Dólar");
            System.out.println("  5)   Dólar => Real brasileño");
            System.out.println("  6)   Real brasileño => Dólar");
            System.out.println("  7)   Dólar => Peso colombiano");
            System.out.println("  8)   Peso colombiano => Dólar");
            System.out.println("  9)   Dólar => Bs venezolano");
            System.out.println(" 10)   Bs venezolano => Dólar");
            System.out.println(" 11)   Salir");
            System.out.print("Elija una opción válida: ");

            //Qué opcion escoge el usuario
            int opcion = Integer.parseInt(scanner.nextLine());
            //Opción de salida
            if (opcion == 11) {
                continuar = false;
                int hora = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY);
                if (hora < 12) {
                    System.out.println("¡Buenos días!");
                } else {
                    System.out.println("¡Buenas tardes!");
                }
                break;
            }

            // Crea instancia ApiConsulta para obtener tasas de cambio
            ConsultaValor apiConsulta = new ConsultaValor();
            Divisa[] monedas = apiConsulta.obtenerMonedas();

            // Crea instancia de Conversor
            ConversorDivisa conversor = new ConversorDivisa(monedas);
            conversor.conversion(opcion);

            //Desea continuar? Si no, se despide...
            System.out.print("¿Desea hacer otra conversión? (si/no): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("si")) {
                continuar = false;
                int hora = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY);
                if (hora < 12) {
                    System.out.println("¡Buenos días!");
                } else {
                    System.out.println("¡Buenas tardes!");
                }
            }
        }
        //Cierra el scanner
        scanner.close();
    }
}
