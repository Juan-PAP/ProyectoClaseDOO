package co.edu.uco.nose.data.dao.factory.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlPruebaConnecion {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/apiNose";
        String user = "postgres";
        String password = "root";

        try {

            Class.forName("org.postgresql.Driver");

            try (Connection connection = DriverManager.getConnection(url, user, password)) {

                if (connection != null && !connection.isClosed()) {
                    System.out.println("✅ Conexión establecida correctamente con PostgreSQL");
                } else {
                    System.out.println("❌ No se pudo establecer la conexión");
                }

            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ No se encontró el driver JDBC de PostgreSQL. "
                    + "Asegúrate de tener el archivo .jar o la dependencia Maven.");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Error al intentar conectarse: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



