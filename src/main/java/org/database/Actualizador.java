package org.database;
import java.sql.SQLException;
import java.sql.*;

public class Actualizador {
    private final Connection conexion;

    public Actualizador(Connection conexion) {
        this.conexion = conexion;

    }
    public int getUpdate()  {
        try {
            String querySelect = "SELECT valor FROM Movimiento";
            // Crear el objeto PreparedStatement para la consulta SELECT
            PreparedStatement ps = conexion.prepareStatement(querySelect);



            // Ejecutar la consulta SELECT y leer los resultados
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Leer los valores de las columnas de cada fila del resultado
                int valor = rs.getInt("valor");

                return valor;
            }
        } catch(SQLException sql){
            System.out.println("ERROR SELECT ");

            //System.out.println(sql);
        }
        return 0;


    }
}
