package co.edu.uco.nose.data.dao.factory.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PruebaConnecion {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/apiNose";
        String user = "postgres";
        String password = "root";

        try {
            Class.forName("org.postgresql.Driver");

            try (Connection connection = DriverManager.getConnection(url, user, password)) {

                if (connection != null && !connection.isClosed()) {
                    System.out.println("✅ Conexión establecida correctamente con PostgreSQL");
                }

                // ------------------------------------------------------
                // 1️⃣ INSERTAR DATOS DE PRUEBA
                // ------------------------------------------------------
                // --------------------- Inserciones usando PreparedStatement ---------------------
                UUID paisId = UUID.randomUUID();
                UUID departamentoId = UUID.randomUUID();
                UUID ciudadId = UUID.randomUUID();
                UUID tipoId = UUID.randomUUID();
                UUID usuarioId = UUID.randomUUID();

                String insertPais = "INSERT INTO Pais (id, nombre) VALUES (?, ?)";
                String insertDepartamento = "INSERT INTO Departamento (id, pais, nombre) VALUES (?, ?, ?)";
                String insertCiudad = "INSERT INTO Ciudad (id, departamento, nombre) VALUES (?, ?, ?)";
                String insertTipo = "INSERT INTO TipoIdentificacion (id, nombre) VALUES (?, ?)";
                String insertUsuario = "INSERT INTO Usuario (id, tipoIdentificacion, numeroIdentificacion, primerNombre, segundoNombre, "
                        + "primerApellido, segundoApellido, ciudadResidencia, correoElectronico, numeroTelefonoMovil, "
                        + "correoElectronicoConfirmado, numeroTelefonoMovilConfirmado) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (
                        PreparedStatement psPais = connection.prepareStatement(insertPais);
                        PreparedStatement psDepartamento = connection.prepareStatement(insertDepartamento);
                        PreparedStatement psCiudad = connection.prepareStatement(insertCiudad);
                        PreparedStatement psTipo = connection.prepareStatement(insertTipo);
                        PreparedStatement psUsuario = connection.prepareStatement(insertUsuario)
                ) {
                    // Pais
                    psPais.setObject(1, paisId);
                    psPais.setString(2, "Colombia");
                    psPais.executeUpdate();

                    // Departamento
                    psDepartamento.setObject(1, departamentoId);
                    psDepartamento.setObject(2, paisId);
                    psDepartamento.setString(3, "Antioquia");
                    psDepartamento.executeUpdate();

                    // Ciudad
                    psCiudad.setObject(1, ciudadId);
                    psCiudad.setObject(2, departamentoId);
                    psCiudad.setString(3, "Medellín");
                    psCiudad.executeUpdate();

                    // TipoIdentificacion
                    psTipo.setObject(1, tipoId);
                    psTipo.setString(2, "Cédula de ciudadanía");
                    psTipo.executeUpdate();

                    // Usuario
                    psUsuario.setObject(1, usuarioId);
                    psUsuario.setObject(2, tipoId);
                    psUsuario.setString(3, "123456789");
                    psUsuario.setString(4, "Juan");
                    psUsuario.setString(5, "Pablo");
                    psUsuario.setString(6, "Alzate");
                    psUsuario.setString(7, "Pulgarín"); // acentos manejados por UTF-8
                    psUsuario.setObject(8, ciudadId);
                    psUsuario.setString(9, "juanpablo@example.com");
                    psUsuario.setString(10, "3001234567");
                    psUsuario.setBoolean(11, true);   // correoElectronicoConfirmado
                    psUsuario.setBoolean(12, false);  // numeroTelefonoMovilConfirmado
                    psUsuario.executeUpdate();
                }

                System.out.println("✅ Datos insertados correctamente (con PreparedStatement)");


                System.out.println("✅ Datos insertados correctamente");

                // ------------------------------------------------------
                // 2️⃣ CONSULTAR EL USUARIO (query que me mostraste)
                // ------------------------------------------------------
                String sql = """
                SELECT u.id, 
                       ti.id AS idTipoIdentificacion, 
                       ti.nombre AS nombreTipoIdentificacion, 
                       u.numeroIdentificacion, 
                       u.primerNombre, 
                       u.segundoNombre, 
                       u.primerApellido, 
                       u.segundoApellido, 
                       c.id AS idCiudadResidencia, 
                       c.nombre AS nombreCiudadResidencia, 
                       d.id AS idDepartamentoCiudadResidencia, 
                       d.nombre AS nombreDepartamentoCiudadResidencia, 
                       p.id AS idPaisDepartamentoCiudadResidencia, 
                       p.nombre AS nombrePaisDepartamentoCiudadResidencia, 
                       u.correoElectronico, 
                       u.numeroTelefonoMovil, 
                       u.correoElectronicoConfirmado, 
                       u.numeroTelefonoMovilConfirmado
                FROM Usuario AS u
                INNER JOIN TipoIdentificacion AS ti ON u.tipoIdentificacion = ti.id
                INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id
                INNER JOIN Departamento AS d ON c.departamento = d.id
                INNER JOIN Pais AS p ON d.pais = p.id
                WHERE u.id = ?;
                """;

                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setObject(1, usuarioId);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("\n🔍 Resultado de la consulta:");
                        System.out.println("Usuario: " + rs.getString("primerNombre") + " " + rs.getString("primerApellido"));
                        System.out.println("Tipo ID: " + rs.getString("nombreTipoIdentificacion"));
                        System.out.println("Ciudad: " + rs.getString("nombreCiudadResidencia"));
                        System.out.println("Departamento: " + rs.getString("nombreDepartamentoCiudadResidencia"));
                        System.out.println("País: " + rs.getString("nombrePaisDepartamentoCiudadResidencia"));
                        System.out.println("Correo: " + rs.getString("correoElectronico"));
                    }
                }

                // ------------------------------------------------------
                // 3️⃣ ACTUALIZAR EL USUARIO
                // ------------------------------------------------------
                try (PreparedStatement ps = connection.prepareStatement(
                        "UPDATE Usuario SET correoElectronicoConfirmado = true WHERE id = ?")) {
                    ps.setObject(1, usuarioId);
                    int rows = ps.executeUpdate();
                    System.out.println("✏️ Usuario actualizado: " + rows + " fila(s) afectadas");
                }

                // ------------------------------------------------------
                // 4️⃣ ELIMINAR EL USUARIO
                // ------------------------------------------------------
                try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Usuario WHERE id = ?")) {
                    ps.setObject(1, usuarioId);
                    int rows = ps.executeUpdate();
                    System.out.println("🗑️ Usuario eliminado: " + rows + " fila(s) afectadas");
                }

            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver JDBC de PostgreSQL no encontrado.");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Error SQL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}





