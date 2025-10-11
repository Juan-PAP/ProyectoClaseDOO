package co.edu.uco.nose.data.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.SqlConnectionHelper;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.factory.sqlserver.SqlServerDAOFactory;

public abstract class DAOFactory {
	
	protected Connection connection;
	protected static FactoryEnum factory = FactoryEnum.POSTGRESQL;
	
	public static DAOFactory getFactory() {
		
		switch (factory) {
		
		case POSTGRESQL: {
			
			return new SqlServerDAOFactory();
		}
		
		default:
			var userMessage = "Factoría no iniciada";
			var technicalMessage = "Factoría no válida";
			throw NoseException.create(userMessage, technicalMessage);
		}
	}
	
	public abstract CityDAO getCityDAO();
	
	public abstract CountryDAO getCountryDAO();
	
	public abstract IdTypeDAO getIdTypeDAO();
	
	public abstract StateDAO getStateDAO();
	
	public abstract UserDAO getUserDAO();
	
	protected abstract void openConnection ();
	
	protected final void initTransaction () {
		SqlConnectionHelper.ensureTransactionIsNotStarted(connection);
		
		try {
			connection.commit();
			
		} catch (final SQLException exception) {
			var userMassage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMassage, technicalMessage);
			
		} catch (Exception exception) {
			var userMassage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMassage, technicalMessage);
		}
		
		
	}
	
	protected final void commitTransaction () {
		SqlConnectionHelper.ensureTransactionIsStarted(connection);
		
		try {
			connection.commit();
			
		} catch (final SQLException exception) {
			var userMassage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMassage, technicalMessage);
			
		}catch (Exception exception) {
			var userMassage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMassage, technicalMessage);
		}
		
	}
	
	protected final void rollbackTransaction () {
		SqlConnectionHelper.ensureTransactionIsStarted(connection);
		
		try {
			connection.rollback();
			
		} catch (final SQLException exception) {
			var userMassage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMassage, technicalMessage);
			
		}catch (Exception exception) {
			var userMassage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMassage, technicalMessage);
		}
		
	}
	
	protected final void closeTransaction () {
		SqlConnectionHelper.ensureTransactionIsNotStarted(connection);
		
		try {
			connection.close();
			
		} catch (final SQLException exception) {
			var userMassage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMassage, technicalMessage);
			
		}catch (Exception exception) {
			var userMassage = "";
			var technicalMessage = "";
			throw NoseException.create(exception, userMassage, technicalMessage);
		}
		
	}

}
