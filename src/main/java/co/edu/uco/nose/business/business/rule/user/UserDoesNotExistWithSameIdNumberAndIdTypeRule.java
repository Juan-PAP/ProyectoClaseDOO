package co.edu.uco.nose.business.business.rule.user;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public class UserDoesNotExistWithSameIdNumberAndIdTypeRule implements Rule {

    private static final Rule instance = new UserDoesNotExistWithSameIdNumberAndIdTypeRule();

    private UserDoesNotExistWithSameIdNumberAndIdTypeRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var technicalMessage = "No se recibieron los parámetros requeridos para ejecutar la regla UserDoesNotExistWithSameIdNumberAndIdTypeRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length < 3) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var technicalMessage = "Se requerían 3 parámetros y llegó una cantidad menor para ejecutar la regla UserDoesNotExistWithSameIdNumberAndIdTypeRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        var idNumber = (String) data[0];
        var idTypeUuid = (UUID) data[1];
        var daoFactory = (DAOFactory) data[2];

        var idTypeFilter = new IdTypeEntity();
        idTypeFilter.setId(idTypeUuid);

        var userFilter = new UserEntity();
        userFilter.setIdNumber(idNumber);
        userFilter.setIdType(idTypeFilter);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(userFilter);

        var user = results.stream()
                .findFirst()
                .orElse(new UserEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(user.getId())) {
            var userMessage = "Ya existe un usuario registrado con el mismo tipo y número de identificación.";
            var technicalMessage = "La regla UserDoesNotExistWithSameIdNumberAndIdTypeRule falló porque ya existe un usuario con IdNumber: "
                    .concat(idNumber).concat(" e IdType: ").concat(idTypeUuid.toString());
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}