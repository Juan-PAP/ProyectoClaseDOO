package co.edu.uco.nose.data.dao.entity.sqlserver;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.entity.IdTypeEntity;

public class IdTypeSqlServerDAO extends SqlConnection implements IdTypeDAO{

	protected IdTypeSqlServerDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IdTypeEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdTypeEntity> findByFilter(IdTypeEntity filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdTypeEntity findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
