package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.business.rule.validator.idtype.ValidateIdTypeExistsById;
import co.edu.uco.nose.business.business.rule.validator.user.ValidateDataUserConsistencyForRegisterNewInformation;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UserBusinessImpl implements UserBusiness {

    private DAOFactory daoFactory;

    public UserBusinessImpl (final DAOFactory daoFactory) {
        if (daoFactory == null) {
            throw NoseException.create("El DAOFactory no puede ser nulo.");
        }
        this.daoFactory = daoFactory;
    }



    @Override
    public void registerNewUserInformation(UserDomain userDomain) {

        // 1. Validar que la información sea consistente a nivel de Tipo de Dato,
        // longitud, obligatoriedad, formato, rango, reglas propias del objeto
        ValidateDataUserConsistencyForRegisterNewInformation.executeValidation(userDomain);

        // 2. Validar que no exista previamente otro usuario con el mismo tipo y número
        // de identificación
        ValidateIdTypeExistsById.executeValidation(userDomain.getIdType().getId(),  daoFactory);


        //7. Ensamblar objeto como Entity
        var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);

        //8. Generar ID
        userEntity.setId(generateId());

        // 9. Registrar la información del nuevo usuario
        daoFactory.getUserDAO().create(userEntity);
    }

    private UUID generateId() {

        var id = UUIDHelper.getUUIDHelper().generateNewUUID();
        var userEntity = daoFactory.getUserDAO().findById(id);

        while (!UUIDHelper.getUUIDHelper().isDefaultUUID(userEntity.getId())) {
            id = UUIDHelper.getUUIDHelper().generateNewUUID();
            userEntity = daoFactory.getUserDAO().findById(id);
        }

        return id;
    }

    @Override
    public void dropUserInformation(UUID id) {

    }

    @Override
    public void updateUserInformation(UUID id, UserDomain userDomain) {

    }

    @Override
    public List<UserDomain> findAllUsers() {

        try {
            List<UserEntity> entityList = daoFactory.getUserDAO().findAll();

            List<UserDomain> domainList = new ArrayList<>();

            for (UserEntity entity : entityList) {
                domainList.add(UserEntityAssembler.getUserEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;

        } catch (final Exception exception) {
            throw NoseException.create(exception, "Error inesperado al consultar todos los usuarios.",
                    "Capa de Negocio");
        }
    }

    @Override
    public List<UserDomain> findUsersByFilter(UserDomain userFilters) {

        try {

            UserEntity filterEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userFilters);

            List<UserEntity> entityList = daoFactory.getUserDAO().findByFilter(filterEntity);

            List<UserDomain> domainList = new ArrayList<>();

            for (UserEntity entity : entityList) {
                domainList.add(UserEntityAssembler.getUserEntityAssembler().toDomain(entity));
            }
            return domainList;

        } catch (final NoseException exception) {
            throw exception;

        } catch (final Exception exception) {
            throw NoseException.create(exception, "Error inesperado al consultar los usuarios por filtro.",
                    "Capa de Negocio");
        }
    }

    @Override
    public UserDomain findSpecificUser(UUID id) {
        try {

            UserEntity filter = new UserEntity();
            filter.setId(id);
            List<UserEntity> entityList = daoFactory.getUserDAO().findByFilter(filter);

            if (entityList.isEmpty()) {
                throw NoseException.create("El usuario con el ID " + id + " no fue encontrado.", "Capa de Negocio");
            }

            return UserEntityAssembler.getUserEntityAssembler().toDomain(entityList.get(0));

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(exception, "Error inesperado al buscar un usuario específico.",
                    "Capa de Negocio");
        }
    }

    @Override
    public void confirmMobileNumber(UUID id, int confirmationCode) {

    }

    @Override
    public void confirmEmail(UUID id) {

    }

    @Override
    public void sendMobileNumberConfirmation(UUID id) {

    }

    @Override
    public void sendEmailConfirmation(UUID id) {

    }
}
