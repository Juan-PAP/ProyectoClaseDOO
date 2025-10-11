package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.StateEntity;
import co.edu.uco.nose.entity.UserEntity;

public class UserSqlServerDAO extends SqlConnection implements UserDAO {

	public UserSqlServerDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(UserEntity entity) {
		
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("INSERT INTO Usario(id, tipoIdentificacion, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoNombre,"
				+ "correoElectronico, numeroTelefonoMovil, correoElectronicoConfirmado, numeroTelefonoMovilConfirmado)");
		
		sql.append("SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
		
		try(var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
			
			preparedStatement.setObject(1, entity.getId());
			preparedStatement.setObject(2, entity.getIdentificationType().getId());
			preparedStatement.setString(3, entity.getIdentificationNumber());
			preparedStatement.setString(4, entity.getFirstName());
			preparedStatement.setString(5, entity.getSecondName());
			preparedStatement.setString(6, entity.getFirstSurname());
			preparedStatement.setString(7, entity.getSecondSurname());
			preparedStatement.setObject(8, entity.getCityEntity().getId());
			preparedStatement.setString(9, entity.getEmail());
			preparedStatement.setString(10, entity.getMobilePhoneNumber());
			preparedStatement.setBoolean(11, entity.getConfirmedEmail());
			preparedStatement.setBoolean(12, entity.getConfirmedMobilePhoneNumber());
			
			preparedStatement.executeUpdate();
		
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de registrar la información";
			var technicalMessage = "Se ha presentado un problema inesperado al tratar de ejecutar el proceso" + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		} catch (final Exception exception) {
		var userMessage = "Se ha presentado un problema INESPERADO tratando de registrar la información";
		var technicalMessage = "Se ha presentado un problema INESPERADO inesperado al tratar de ejecutar el proceso" + exception.getMessage();
		throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}	

	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserEntity> findByFilter(UserEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity findById(final UUID id) {

	    SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

	    var user = new UserEntity();
	    final var sql = new StringBuilder();

	    sql.append("SELECT  u.id, ");
	    sql.append("        ti.id AS idTipoIdentificacion, ");
	    sql.append("        ti.nombre AS nombreTipoIdentificacion, ");
	    sql.append("        u.numeroIdentificacion, ");
	    sql.append("        u.primerNombre, ");
	    sql.append("        u.segundoNombre, ");
	    sql.append("        u.primerApellido, ");
	    sql.append("        u.segundoApellido, ");
	    sql.append("        c.id AS idCiudadResidencia, ");
	    sql.append("        c.nombre AS nombreCiudadResidencia, ");
	    sql.append("        d.id AS idDepartamento, ");
	    sql.append("        d.nombre AS nombreDepartamento, ");
	    sql.append("        p.id AS idPais, ");
	    sql.append("        p.nombre AS nombrePais, ");
	    sql.append("        u.correoElectronico, ");
	    sql.append("        u.numeroTelefonoMovil, ");
	    sql.append("        u.correoElectronicoConfirmado, ");
	    sql.append("        u.numeroTelefonoMovilConfirmado ");
	    sql.append("FROM Usuario AS u ");
	    sql.append("INNER JOIN TipoIdentificacion AS ti ON u.tipoIdentificacion = ti.id ");
	    sql.append("INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id ");
	    sql.append("INNER JOIN Departamento AS d ON c.departamento = d.id ");
	    sql.append("INNER JOIN Pais AS p ON d.pais = p.id ");
	    sql.append("WHERE u.id = ?;");

	    try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

	        preparedStatement.setObject(1, id);

	        try (var resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {

	                var idType = new IdTypeEntity();
	                idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTipoIdentificacion")));
	                idType.setNombre(resultSet.getString("nombreTipoIdentificacion"));

	                var country = new CountryEntity();
	                country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPais")));
	                country.setName(resultSet.getString("nombrePais"));

	                var state = new StateEntity();
	                state.setCountry(country);
	                state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamento")));
	                state.setName(resultSet.getString("nombreDepartamento"));

	                var city = new CityEntity();
	                city.setState(state);
	                city.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
	                city.setName(resultSet.getString("nombreCiudadResidencia"));

	                user.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
	                user.setIdentificationType(idType);
	                user.setIdentificationNumber(resultSet.getString("numeroIdentificacion"));
	                user.setFirstName(resultSet.getString("primerNombre"));
	                user.setSecondName(resultSet.getString("segundoNombre"));
	                user.setFirstSurname(resultSet.getString("primerApellido"));
	                user.setSecondSurname(resultSet.getString("segundoApellido"));
	                user.setCityEntity(city);
	                user.setEmail(resultSet.getString("correoElectronico"));
	                user.setMobilePhoneNumber(resultSet.getString("numeroTelefonoMovil"));
	                user.setConfirmedEmail(resultSet.getBoolean("correoElectronicoConfirmado"));
	                user.setConfirmedMobilePhoneNumber(resultSet.getBoolean("numeroTelefonoMovilConfirmado"));
	            }
	        }

	    } catch (final SQLException exception) {
	        var userMessage = "Error al consultar la información del usuario deseado.";
	        var technicalMessage = "Problema SQL al ejecutar el proceso: " + exception.getMessage();
	        throw NoseException.create(exception, userMessage, technicalMessage);

	    } catch (final Exception exception) {
	        var userMessage = "Error inesperado al consultar la información del usuario.";
	        var technicalMessage = "Excepción inesperada: " + exception.getMessage();
	        throw NoseException.create(exception, userMessage, technicalMessage);
	    }

	    return user;
	}


	@Override
	public void update(final UserEntity entity) {
		
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		
		final var sql = new StringBuilder();
		
	    sql.append("UPDATE Usuario ");
	    sql.append("SET tipoIdentificacion = ?, ");
	    sql.append("    numeroIdentificacion = ?, ");
	    sql.append("    primerNombre = ?, ");
	    sql.append("    segundoNombre = ?, ");
	    sql.append("    primerApellido = ?, ");
	    sql.append("    segundoApellido = ?, ");
	    sql.append("    correoElectronico = ?, ");
	    sql.append("    numeroTelefonoMovil = ?, ");
	    sql.append("    correoElectronicoConfirmado = ?, ");
	    sql.append("    numeroTelefonoMovilConfirmado = ? ");
	    sql.append("WHERE id = ?");
		
		
		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
			
			preparedStatement.setObject(1, entity.getIdentificationType().getId());
	        preparedStatement.setString(2, entity.getIdentificationNumber());
	        preparedStatement.setString(3, entity.getFirstName());
	        preparedStatement.setString(4, entity.getSecondName());
	        preparedStatement.setString(5, entity.getFirstSurname());
	        preparedStatement.setString(6, entity.getSecondSurname());
	        preparedStatement.setString(7, entity.getEmail());
	        preparedStatement.setString(8, entity.getMobilePhoneNumber());
	        preparedStatement.setBoolean(9, entity.getConfirmedEmail());
	        preparedStatement.setBoolean(10, entity.getConfirmedMobilePhoneNumber());
	        preparedStatement.setObject(11, entity.getId());

	        preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
	        var userMessage = "Se presentó un problema al actualizar la información del usuario.";
	        var technicalMessage = "Error SQL: " + exception.getMessage();
	        throw NoseException.create(exception, userMessage, technicalMessage);

	    } catch (final Exception exception) {
	        var userMessage = "Error inesperado al actualizar la información del usuario.";
	        var technicalMessage = "Detalles: " + exception.getMessage();
	        throw NoseException.create(exception, userMessage, technicalMessage);
	    }
		}



	@Override
	public void delete(UUID id) {
		
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
	    
	    final var sql = new StringBuilder();
	    sql.append("DELETE FROM Usuario WHERE id = ?");

	    try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
	        
	        preparedStatement.setObject(1, id);
	        preparedStatement.executeUpdate();
	        
	    } catch (final SQLException exception) {
	        var userMessage = "Se presento un problema al eliminar la información del usuario.";
	        var technicalMessage = "Error SQL: " + exception.getMessage();
	        throw NoseException.create(exception, userMessage, technicalMessage);

	    } catch (final Exception exception) {
	        var userMessage = "Error inesperado al eliminar la información del usuario.";
	        var technicalMessage = "Detalles: " + exception.getMessage();
	        throw NoseException.create(exception, userMessage, technicalMessage);
	    }
		
	}

}
