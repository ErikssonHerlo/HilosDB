package org.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Decrementador implements Runnable {
    private final Connection conexion;
    private final int decremento;
    private final int tiempo;
    private Actualizador actualizador;
    public Decrementador(Connection conexion, int decremento, int tiempo, Actualizador actualizador) {
        this.conexion = conexion;
        this.decremento = decremento;
        this.tiempo = tiempo;
        this.actualizador = actualizador;
    }

    @Override
    public void run() {
        try {
            Statement statement = conexion.createStatement();
            long tiempoInicio = System.currentTimeMillis();

            while (System.currentTimeMillis() - tiempoInicio < tiempo) {
                statement.executeUpdate("UPDATE Movimiento SET valor = valor - " + decremento);
                System.out.println("***DECREMENTO***");
                System.out.println("Valor del Incremento: "+ decremento);
                
                System.out.println("Valor: "+ actualizador.getUpdate());
                Thread.sleep(1000);
            }

            statement.close();
            conexion.close();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
