package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.dto.impl.UserDTOAssembler;
import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.business.rule.validator.city.ValidateCityExistsById;
import co.edu.uco.nose.business.business.rule.validator.idtype.ValidateIdTypeExistsById;
import co.edu.uco.nose.business.business.rule.validator.user.ValidateDataUserConsistencyForRegisterNewInformation;
import co.edu.uco.nose.business.business.rule.validator.user.ValidateDataUserEmailDoesNotExist;
import co.edu.uco.nose.business.business.rule.validator.user.ValidateDataUserMobileDoesNotExist;
import co.edu.uco.nose.business.business.rule.validator.user.ValidateUserDoesNotExistWithSameIdNumberAndIdType;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
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

        // 1. Validar que la información sea consistente a nivel de Tipo de Dato,
        // longitud, obligatoriedad, formato, rango, reglas propias del objeto
        ValidateDataUserConsistencyForRegisterNewInformation.executeValidation(userDomain);

        // 2. Validar que el Tipo de Identificación exista
        ValidateIdTypeExistsById.executeValidation(userDomain.getIdType().getId(),  daoFactory);

        // 3. Validad que exista la ciudad de recidencia
        ValidateCityExistsById.executeValidation(userDomain.getHomeCity().getId(), daoFactory);

        // 4. Validar que no exista previamente otro usuario con el mismo tipo y número
        // de identificación
        ValidateUserDoesNotExistWithSameIdNumberAndIdType.executeValidation(
                userDomain.getIdNumber(),userDomain.getIdType().getId(), daoFactory);

        //
        // 5. Validar que no exista previamente otro usuario con el mismo correo
        // electrónico
        ValidateDataUserEmailDoesNotExist.executeValidation(userDomain.getEmail(), daoFactory);

        // 6. Validar que no exista previamente otro usuario con el mismo número de
        // teléfono celular
        ValidateDataUserMobileDoesNotExist.executeValidation(userDomain.getMobileNumber(), daoFactory);

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

        List<UserEntity> entityList = daoFactory.getUserDAO().findAll();

        return UserEntityAssembler.getUserEntityAssembler().toDomain(entityList);

    }

    @Override
    public List<UserDomain> findUsersByFilter(UserDomain userFilters) {

        var validatedFilterDomain = ObjectHelper.getDefault(userFilters, new UserDomain());

        UserEntity userEntityFilter = UserEntityAssembler.getUserEntityAssembler()
                .toEntity(validatedFilterDomain);

        List<UserEntity> entityListResult = daoFactory.getUserDAO().findByFilter(userEntityFilter);

        return UserEntityAssembler.getUserEntityAssembler().toDomain(entityListResult);
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
