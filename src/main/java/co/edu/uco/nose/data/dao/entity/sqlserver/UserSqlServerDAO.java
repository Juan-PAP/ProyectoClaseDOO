package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
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
	public void create(final UserEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted (getConnection());
		
		final var sql = new StringBuilder();
		sql.append(" INSERT INTO Usuario (id, tipoIdentificacion, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido,"
				+ " ciudadResidencia, correoElectronico, numeroTelefonoMovil, correoElectronicoConfirmado, numeroTelefonoMovilConfirmado) ");
		
		sql.append("SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ");

		try(var preparedStatement = this.getConnection().prepareStatement(sql.toString())){
			
			preparedStatement.setObject(1, entity.getId());
			preparedStatement.setObject(2, entity.getIdType().getId());
			preparedStatement.setString(3, entity.getIdNumber());
			preparedStatement.setString(4, entity.getFirstName());
			preparedStatement.setString(5, entity.getSecondName());
			preparedStatement.setString(6, entity.getFirstSurname());
			preparedStatement.setString(7, entity.getSecondSurname());
			preparedStatement.setObject(8, entity.getHomeCity().getId());
			preparedStatement.setString(9, entity.getEmail());
			preparedStatement.setString(10, entity.getMobileNumber());
			preparedStatement.setBoolean(11, entity.isEmailConfirmed());
			preparedStatement.setBoolean(12, entity.isMobileNumberConfirmed());
			
			preparedStatement.executeUpdate();
		
			
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_INSERT_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_INSERT_USER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		} catch (final Exception exception) {

			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_INSERT_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_USER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	//Se realiza un Data mapper para separar la logica que mapea el ResultSet, que seria la base de datos
	//hacia los objetos de dominio, estando esto dentro de un metodo privado y que puede ser utilizado en todas las consultas
	private void mapResultSetToUser(final java.sql.ResultSet resultSet, final UserEntity user) {
		try {
			var idType = new IdTypeEntity();
			idType.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTipoIdentificacion")));
			idType.setNombre(resultSet.getString("nombreTipoIdentificacion"));

			var country = new CountryEntity();
			country.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
			country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));

			var state = new StateEntity();
			state.setCountry(country);
			state.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamentoCiudadResidencia")));
			state.setName(resultSet.getString("nombreDepartamentoCiudadResidencia"));

			var city = new CityEntity();
			city.setState(state);
			city.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
			city.setName(resultSet.getString("nombreCiudadResidencia"));

			user.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
			user.setIdType(idType);
			user.setIdNumber(resultSet.getString("numeroIdentificacion"));
			user.setFirstName(resultSet.getString("primerNombre"));
			user.setSecondName(resultSet.getString("segundoNombre"));
			user.setFirstSurname(resultSet.getString("primerApellido"));
			user.setSecondSurname(resultSet.getString("segundoApellido"));
			user.setHomeCity(city);
			user.setEmail(resultSet.getString("correoElectronico"));
			user.setMobileNumber(resultSet.getString("numeroTelefonoMovil"));
			user.setEmailConfirmed(resultSet.getBoolean("correoElectronicoConfirmado"));
			user.setMobileNumberConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmado"));

		} catch (final SQLException exception) {

			var userMessage = MessagesEnum.USER_ERROR_SQL_MAPPING_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_USER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {

			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_MAPPING_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_USER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<UserEntity> findAll() {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

		final List<UserEntity> users = new java.util.ArrayList<>();
		final var sql = new StringBuilder();

		sql.append("SELECT     u.id, ");
		sql.append("           ti.id AS idTipoIdentificacion, ");
		sql.append("           ti.nombre AS nombreTipoIdentificacion, ");
		sql.append("           u.numeroIdentificacion, ");
		sql.append("           u.primerNombre, ");
		sql.append("           u.segundoNombre, ");
		sql.append("           u.primerApellido, ");
		sql.append("           u.segundoApellido, ");
		sql.append("           c.id AS idCiudadResidencia, ");
		sql.append("           c.nombre AS nombreCiudadResidencia, ");
		sql.append("           d.id AS idDepartamentoCiudadResidencia, ");
		sql.append("           d.nombre AS nombreDepartamentoCiudadResidencia, ");
		sql.append("           p.id AS idPaisDepartamentoCiudadResidencia, ");
		sql.append("           p.nombre AS nombrePaisDepartamentoCiudadResidencia, ");
		sql.append("           u.correoElectronico, ");
		sql.append("           u.numeroTelefonoMovil, ");
		sql.append("           u.correoElectronicoConfirmado, ");
		sql.append("           u.numeroTelefonoMovilConfirmado ");
		sql.append("FROM       Usuario AS u ");
		sql.append("INNER JOIN TipoIdentificacion AS ti ");
		sql.append("ON         u.tipoIdentificacion = ti.id ");
		sql.append("INNER JOIN Ciudad AS c ");
		sql.append("ON         u.ciudadResidencia = c.id ");
		sql.append("INNER JOIN Departamento AS d ");
		sql.append("ON         c.departamento = d.id ");
		sql.append("INNER JOIN Pais AS p ");
		sql.append("ON         d.pais = p.id ");

		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString());
			 var resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				var user = new UserEntity();
				mapResultSetToUser(resultSet, user);
				users.add(user);
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_ALL_USER.getContent(); // Crear este mensaje
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_ALL_USER.getContent(); // Crear este mensaje
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_USER.getContent(); // Crear este mensaje
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_USER.getContent(); // Crear este mensaje
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return users;
	}

	@Override
	public List<UserEntity> findByFilter(UserEntity filterEntity) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

		final List<UserEntity> users = new java.util.ArrayList<>();

		final var sql = new StringBuilder();
		final var parameters = new java.util.ArrayList<Object>();

		sql.append("SELECT     u.id, ");
		sql.append("           ti.id AS idTipoIdentificacion, ");
		sql.append("           ti.nombre AS nombreTipoIdentificacion, ");
		sql.append("           u.numeroIdentificacion, ");
		sql.append("           u.primerNombre, ");
		sql.append("           u.segundoNombre, ");
		sql.append("           u.primerApellido, ");
		sql.append("           u.segundoApellido, ");
		sql.append("           c.id AS idCiudadResidencia, ");
		sql.append("           c.nombre AS nombreCiudadResidencia, ");
		sql.append("           d.id AS idDepartamentoCiudadResidencia, ");
		sql.append("           d.nombre AS nombreDepartamentoCiudadResidencia, ");
		sql.append("           p.id AS idPaisDepartamentoCiudadResidencia, ");
		sql.append("           p.nombre AS nombrePaisDepartamentoCiudadResidencia, ");
		sql.append("           u.correoElectronico, ");
		sql.append("           u.numeroTelefonoMovil, ");
		sql.append("           u.correoElectronicoConfirmado, ");
		sql.append("           u.numeroTelefonoMovilConfirmado ");
		sql.append("FROM       Usuario AS u ");
		sql.append("INNER JOIN TipoIdentificacion AS ti ");
		sql.append("ON         u.tipoIdentificacion = ti.id ");
		sql.append("INNER JOIN Ciudad AS c ");
		sql.append("ON         u.ciudadResidencia = c.id ");
		sql.append("INNER JOIN Departamento AS d ");
		sql.append("ON         c.departamento = d.id ");
		sql.append("INNER JOIN Pais AS p ");
		sql.append("ON         d.pais = p.id ");
		sql.append("WHERE 1=1 ");

		if (filterEntity.getId() != null && !UUIDHelper.getUUIDHelper().getDefault().equals(filterEntity.getId())) { // <-- CORRECCIÓN AQUÍ
			sql.append("AND u.id = ? ");
			parameters.add(filterEntity.getId());
		}
		if (filterEntity.getIdType() != null && filterEntity.getIdType().getId() != null && !UUIDHelper.getUUIDHelper().getDefault().equals(filterEntity.getIdType().getId())) { // <-- CORRECCIÓN AQUÍ
			sql.append("AND u.tipoIdentificacion = ? ");
			parameters.add(filterEntity.getIdType().getId());
		}
		if (filterEntity.getIdNumber() != null && !filterEntity.getIdNumber().trim().isEmpty()) {
			sql.append("AND u.numeroIdentificacion = ? ");
			parameters.add(filterEntity.getIdNumber());
		}
		if (filterEntity.getFirstName() != null && !filterEntity.getFirstName().trim().isEmpty()) {
			sql.append("AND u.primerNombre LIKE ? ");
			parameters.add("%" + filterEntity.getFirstName().trim() + "%");
		}
		if (filterEntity.getSecondName() != null && !filterEntity.getSecondName().trim().isEmpty()) {
			sql.append("AND u.segundoNombre LIKE ? ");
			parameters.add("%" + filterEntity.getSecondName().trim() + "%");
		}
		if (filterEntity.getFirstSurname() != null && !filterEntity.getFirstSurname().trim().isEmpty()) {
			sql.append("AND u.primerApellido LIKE ? ");
			parameters.add("%" + filterEntity.getFirstSurname().trim() + "%");
		}
		if (filterEntity.getSecondSurname() != null && !filterEntity.getSecondSurname().trim().isEmpty()) {
			sql.append("AND u.segundoApellido LIKE ? ");
			parameters.add("%" + filterEntity.getSecondSurname().trim() + "%");
		}
		if (filterEntity.getHomeCity() != null && filterEntity.getHomeCity().getId() != null && !UUIDHelper.getUUIDHelper().getDefault().equals(filterEntity.getHomeCity().getId())) { // <-- CORRECCIÓN AQUÍ
			sql.append("AND u.ciudadResidencia = ? ");
			parameters.add(filterEntity.getHomeCity().getId());
		}
		if (filterEntity.getEmail() != null && !filterEntity.getEmail().trim().isEmpty()) {
			sql.append("AND u.correoElectronico = ? ");
			parameters.add(filterEntity.getEmail());
		}
		if (filterEntity.getMobileNumber() != null && !filterEntity.getMobileNumber().trim().isEmpty()) {
			sql.append("AND u.numeroTelefonoMovil = ? ");
			parameters.add(filterEntity.getMobileNumber());
		}
		if (!filterEntity.isEmailConfirmedIsDefaultValue()) {
			sql.append("AND u.correoElectronicoConfirmado = ? ");
			parameters.add(filterEntity.isEmailConfirmed());
		}
		if (!filterEntity.isMobileNumberIsDefualtValue()) {
			sql.append("AND u.numeroTelefonoMovilConfirmado = ? ");
			parameters.add(filterEntity.isMobileNumberConfirmed());
		}

		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

			for (int i = 0; i < parameters.size(); i++) {
				preparedStatement.setObject(i + 1, parameters.get(i));
			}

			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					var user = new UserEntity();
					mapResultSetToUser(resultSet, user);
					users.add(user);
				}
			}

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_USER.getContent(); // Crear este mensaje
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_USER.getContent(); // Crear este mensaje
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_USER.getContent(); // Crear este mensaje
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_USER.getContent(); // Crear este mensaje
			throw NoseException.create(exception, userMessage, technicalMessage);
		}

		return users;
	}

	@Override
	public UserEntity findById(final UUID id) {

	    SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

	    var user = new UserEntity();
	    final var sql = new StringBuilder();

	    sql.append("SELECT     u.id, ");
	    sql.append("           ti.id AS idTipoIdentificacion, ");
	    sql.append("           ti.nombre AS nombreTipoIdentificacion, ");
	    sql.append("           u.numeroIdentificacion, ");
	    sql.append("           u.primerNombre, ");
	    sql.append("           u.segundoNombre, ");
	    sql.append("           u.primerApellido, ");
	    sql.append("           u.segundoApellido, ");
	    sql.append("           c.id AS idCiudadResidencia, ");
	    sql.append("           c.nombre AS nombreCiudadResidencia, ");
	    sql.append("           d.id AS idDepartamentoCiudadResidencia, ");
	    sql.append("           d.nombre AS nombreDepartamentoCiudadResidencia, ");
	    sql.append("           p.id AS idPaisDepartamentoCiudadResidencia, ");
	    sql.append("           p.nombre AS nombrePaisDepartamentoCiudadResidencia, ");
	    sql.append("           u.correoElectronico, ");
	    sql.append("           u.numeroTelefonoMovil, ");
	    sql.append("           u.correoElectronicoConfirmado, ");
	    sql.append("           u.numeroTelefonoMovilConfirmado ");
	    sql.append("FROM       Usuario AS u ");
	    sql.append("INNER JOIN TipoIdentificacion AS ti ");
		sql.append("ON         u.tipoIdentificacion = ti.id ");
	    sql.append("INNER JOIN Ciudad AS c ");
		sql.append("ON         u.ciudadResidencia = c.id ");
	    sql.append("INNER JOIN Departamento AS d ");
		sql.append("ON         c.departamento = d.id ");
	    sql.append("INNER JOIN Pais AS p ");
		sql.append("ON         d.pais = p.id ");
	    sql.append("WHERE      u.id = ?;");

		try {

			try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

				preparedStatement.setObject(1, id);

				try (var resultSet = preparedStatement.executeQuery()) {

					if (resultSet.next()) {
						mapResultSetToUser(resultSet, user);
					}

				} catch (final SQLException exception) {
					// Error al ejecutar la consulta
					var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_ID_USER.getContent();
					var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_ID_USER.getContent();
					throw NoseException.create(exception, userMessage, technicalMessage);
				}

			} catch (final SQLException exception) {
				// Error al preparar la sentencia
				var userMessage = MessagesEnum.USER_ERROR_SQL_PREPARING_FIND_BY_ID_USER.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_PREPARING_FIND_BY_ID_USER.getContent();
				throw NoseException.create(exception, userMessage, technicalMessage);
			}

		} catch (final Exception exception) {

			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_USER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}

		return user;
	}

	@Override
	public void update(final UserEntity entity) {

		SqlConnectionHelper.ensureTransactionIsStarted (getConnection());

		final var sql = new StringBuilder();
		
	    sql.append("UPDATE Usuario ");
		sql.append("SET tipoIdentificacion = ?, ");
		sql.append("    numeroIdentificacion = ?, ");
		sql.append("    primerNombre = ?, ");
		sql.append("    segundoNombre = ?, ");
		sql.append("    primerApellido = ?, ");
		sql.append("    segundoApellido = ?, ");
		sql.append("    ciudadResidencia = ?, ");
		sql.append("    correoElectronico = ?, ");
		sql.append("    numeroTelefonoMovil = ?, ");
		sql.append("    correoElectronicoConfirmado = ?, ");
		sql.append("    numeroTelefonoMovilConfirmado = ? ");
	    sql.append("WHERE id = ?");
		
		
		try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
			
			preparedStatement.setObject(1, entity.getIdType().getId());
	        preparedStatement.setString(2, entity.getIdNumber());
	        preparedStatement.setString(3, entity.getFirstName());
	        preparedStatement.setString(4, entity.getSecondName());
	        preparedStatement.setString(5, entity.getFirstSurname());
	        preparedStatement.setString(6, entity.getSecondSurname());
			preparedStatement.setObject(7, entity.getHomeCity().getId());
	        preparedStatement.setString(8, entity.getEmail());
	        preparedStatement.setString(9, entity.getMobileNumber());
	        preparedStatement.setBoolean(10, entity.isEmailConfirmed());
	        preparedStatement.setBoolean(11, entity.isMobileNumberConfirmed());
	        preparedStatement.setObject(12, entity.getId());

	        preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UPDATE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UPDATE_USER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);

	    } catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_USER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
	    }
	}

	@Override
	public void delete(final UUID id) {

		SqlConnectionHelper.ensureTransactionIsStarted (getConnection());
	    
	    final var sql = new StringBuilder();
	    sql.append("DELETE ");
		sql.append("FROM   Usuario ");
		sql.append("WHERE  id = ?");

	    try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
	        
	        preparedStatement.setObject(1, id);
	        preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_DELETE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_DELETE_USER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_DELETE_USER.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_USER.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

}
