package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public final class UserBusinessImpl implements UserBusiness {

    private DAOFactory daoFactory;

    public UserBusinessImpl (final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registerNewUserInformation(UserDomain userDomain) {

        //1. Validar que la información sea consistente a nivel de Tipo de Dato,
        //longitud, obligatoriedad, formato, rango, reglas propias del objeto

        //2. Validar que no exista previamente otro usuario tipo y numero de identificacion

        //3. Validar que no exista otro usuario con el mismo correo electronico

        //4. Validar que no exista previamente otro usuario con el mismo numero de telefono

        //5. Generar el nuevo identificador unico para el usuario

        var id = UUIDHelper.getUUIDHelper().generateNewUUID();
        var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);

        userEntity.setId(id);

        daoFactory.getUserDAO().create(userEntity);

    }

    @Override
    public void dropUserInformation(UserDomain userDomain) {

    }

    @Override
    public void dropUserInformation(UUID id) {

    }

    @Override
    public void updateUserInformation(UUID id, UserDomain userDomain) {

    }

    @Override
    public List<UserDomain> findAllUsers() {
        return List.of();
    }

    @Override
    public List<UserDomain> findUsersByFilter(UserDomain userDomain) {
        return List.of();
    }

    @Override
    public UserDomain findSpecificUser(UUID id) {
        return null;
    }
}
