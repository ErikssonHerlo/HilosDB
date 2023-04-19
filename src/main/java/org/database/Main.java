package org.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/hilos_db";
    private static final String USER = "erikssonherlo";
    private static final String PASSWORD = "201830459";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        int valorIncrementadorX = 0;
        int valorDecrementadorY = 0;
        int tiempoEjecucion = 0;
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Escenario 1");
            System.out.println("2. Escenario 2");
            System.out.println("0. Salir");
            opcion = scanner.nextInt();
            System.out.println("Ingrese el valor del Incrementador:");
            valorIncrementadorX = scanner.nextInt();
            System.out.println("Ingrese el valor del Decrementador");
            valorDecrementadorY = scanner.nextInt();
            System.out.println("Ingrese el valor del tiempo (segundos)");
            tiempoEjecucion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ejecutarEscenario1(valorIncrementadorX, valorDecrementadorY, tiempoEjecucion*1000);
                    break;
                case 2:
                    ejecutarEscenario2(valorIncrementadorX, valorDecrementadorY, tiempoEjecucion*1000);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void ejecutarEscenario1(int valorIncrementadorX, int valorDecrementadorY, int tiempoEjecucion) {

        try {
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Actualizador actualizador = new Actualizador(conexion);
            Thread incrementador = new Thread(new Incrementador(conexion, valorIncrementadorX, tiempoEjecucion, actualizador));
            Thread decrementador = new Thread(new Decrementador(conexion, valorDecrementadorY, tiempoEjecucion, actualizador));
            incrementador.start();
            decrementador.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void ejecutarEscenario2(int valorIncrementadorX, int valorDecrementadorY, int tiempoEjecucion) {

        try {
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Actualizador actualizador = new Actualizador(conexion);


            Thread incrementadorLock = new Thread(new IncrementadorConLock(conexion, valorIncrementadorX, tiempoEjecucion, actualizador));
            Thread decrementadorLock = new Thread(new DecrementadorConLock(conexion, valorDecrementadorY, tiempoEjecucion, actualizador));
            incrementadorLock.start();
            decrementadorLock.start();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}