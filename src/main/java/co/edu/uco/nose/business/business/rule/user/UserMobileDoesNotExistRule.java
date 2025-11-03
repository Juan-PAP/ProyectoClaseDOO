package co.edu.uco.nose.business.business.rule.user;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.UserEntity;

import java.util.List;

public class UserMobileDoesNotExistRule implements Rule {

    private static final Rule instance = new UserMobileDoesNotExistRule();

    private UserMobileDoesNotExistRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var technicalMessage = "No se recibieron los parámetros requeridos para ejecutar la regla UserMobileDoesNotExistRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var technicalMessage = "Se requerían 2 parámetros y llegó una cantidad menor para ejecutar la regla UserMobileDoesNotExistRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        var mobile = (String) data[0];
        var daoFactory = (DAOFactory) data[1];

        var userFilter = new UserEntity();

        userFilter.setMobileNumber(mobile);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(userFilter);

        var user = results.stream()
                .findFirst()
                .orElse(new UserEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(user.getId())) {
            var userMessage = "Ya existe un usuario registrado con el número de teléfono ingresado.";
            var technicalMessage = "La regla UserMobileDoesNotExistRule falló porque ya existe un usuario con el teléfono: "
                    .concat(mobile);
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}