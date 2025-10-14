package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.entity.CountryEntity;

public final class CountrySqlServerDAO extends SqlConnection implements CountryDAO {

	public CountrySqlServerDAO(final Connection connection) {
		super(connection);
	}

	private void mapResultSetToCountry(final java.sql.ResultSet resultSet, final CountryEntity entity) {
		try {
			entity.setId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
			entity.setName(resultSet.getString("nombre"));
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_MAPPING_COUNTRY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_MAPPING_COUNTRY.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
	}

	@Override
	public List<CountryEntity> findAll() {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		final List<CountryEntity> countries = new ArrayList<>();
		final String sql = "SELECT id, nombre FROM Pais";

		try (var preparedStatement = getConnection().prepareStatement(sql);
			 var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				var country = new CountryEntity();
				mapResultSetToCountry(resultSet, country);
				countries.add(country);
			}
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_ALL_COUNTRY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_ALL_COUNTRY.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_COUNTRY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_ALL_COUNTRY.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return countries;
	}

	@Override
	public List<CountryEntity> findByFilter(final CountryEntity filterEntity) {

		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());

		final List<CountryEntity> countries = new ArrayList<>();
		final var sql = new StringBuilder("SELECT id, nombre FROM Pais WHERE 1=1 ");
		final var parameters = new ArrayList<Object>();

		if (filterEntity.getId() != null && !UUIDHelper.getUUIDHelper().getDefault().equals(filterEntity.getId())) {
			sql.append("AND id = ? ");
			parameters.add(filterEntity.getId());
		}
		if (filterEntity.getName() != null && !filterEntity.getName().trim().isEmpty()) {
			sql.append("AND nombre LIKE ? ");
			parameters.add("%" + filterEntity.getName().trim() + "%");
		}

		try (var preparedStatement = getConnection().prepareStatement(sql.toString())) {
			for (int i = 0; i < parameters.size(); i++) {
				preparedStatement.setObject(i + 1, parameters.get(i));
			}
			try (var resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					var country = new CountryEntity();
					mapResultSetToCountry(resultSet, country);
					countries.add(country);
				}
			}
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_FILTER_COUNTRY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_COUNTRY.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_COUNTRY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_FILTER_COUNTRY.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return countries;
	}

	@Override
	public CountryEntity findById(final UUID id) {
		SqlConnectionHelper.ensureConnectionIsNotNull(getConnection());
		CountryEntity country = null;
		final String sql = "SELECT id, nombre FROM Pais WHERE id = ?";

		try (var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setObject(1, id);
			try (var resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					country = new CountryEntity();
					mapResultSetToCountry(resultSet, country);
				}
			}
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_EXECUTING_FIND_BY_ID_COUNTRY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_ID_COUNTRY.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);

		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_COUNTRY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_COUNTRY.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		return country;
	}
}