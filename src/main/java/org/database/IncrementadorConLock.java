package org.database;
import java.sql.SQLException;
import java.sql.*;
class IncrementadorConLock implements Runnable {
    private final Connection conexion;
    private final int incremento;
    private final int tiempo;
    private Actualizador actualizador;

    public IncrementadorConLock(Connection conexion, int incremento, int tiempo, Actualizador actualizador) {
        this.conexion = conexion;
        this.incremento = incremento;
        this.tiempo = tiempo;
        this.actualizador = actualizador;
    }

    @Override
    public void run() {
        try {
            long tiempoInicio = System.currentTimeMillis();
            Statement statement = conexion.createStatement();
            while (System.currentTimeMillis() - tiempoInicio < tiempo) {
                synchronized (statement.getConnection()) {
                    statement.executeUpdate("UPDATE Movimiento SET valor = valor + " + incremento);
                }
                System.out.println("---INCREMENTO CON LOCK---");
                System.out.println("Valor del Incremento: "+ incremento);
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