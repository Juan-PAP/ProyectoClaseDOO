package co.edu.uco.nose.data.dao.factory.sqlserver;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.CitySqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.CountrySqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.IdTypeSqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.StateSqlServerDAO;
import co.edu.uco.nose.data.dao.entity.sqlserver.UserSqlServerDAO;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public final class SqlServerDAOFactory extends DAOFactory {
	
	@Override
	protected void openConnection() {
		
			String url = "jdbc:postgresql://localhost:5432/apiNose";
		    String user = "postgres";
		    String password = "root";
		 
		try {

			Class.forName("org.postgresql.Driver");
			
			this.connection = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException exception) {

			var userMassage = MessagesEnum.USER_ERROR_SQL_CANNOT_OPEN_CONNECTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CANNOT_OPEN_CONNECTION.getContent();
			throw NoseException.create(exception, userMassage, technicalMessage);
			
		} catch (Exception exception) {

			var userMassage = MessagesEnum.USER_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION.getContent();
			throw NoseException.create(exception, userMassage, technicalMessage);
		} 
		
	}

	@Override
	public CityDAO getCityDAO() {
		return new CitySqlServerDAO(connection);
	}

	@Override
	public CountryDAO getCountryDAO() {
		return new CountrySqlServerDAO(connection);
	}

	@Override
	public IdTypeDAO getIdTypeDAO() {
		return new IdTypeSqlServerDAO(connection);
	}

	@Override
	public StateDAO getStateDAO() {
		return new StateSqlServerDAO(connection);
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserSqlServerDAO(connection);
	}

}
