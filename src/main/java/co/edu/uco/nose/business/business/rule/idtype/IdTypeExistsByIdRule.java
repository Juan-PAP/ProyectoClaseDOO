package co.edu.uco.nose.business.business.rule.idtype;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

import java.util.UUID;

public final class IdTypeExistsByIdRule implements Rule {

    private static final Rule instance = new IdTypeExistsByIdRule();

    private IdTypeExistsByIdRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)){
            var UserMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var TechnicalMessage = "No se recibieron los parámetros requeridos para ejecutar la regla IdTypeExistsByIdRule.";
            throw NoseException.create(UserMessage, TechnicalMessage);
        }
        if (data.length < 2){
            var UserMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var TechnicalMessage = "Se requerian 2 paramentros y llegó una cantidad menor a esta requeridos para ejecutar la regla IdTypeExistsByIdRule.";
            throw NoseException.create(UserMessage, TechnicalMessage);
        }

        var id = (UUID) data[0];
        var daoFactory = (DAOFactory) data[1];

        var idType = daoFactory.getIdTypeDAO().findById(id);

        if (ObjectHelper.isNull(idType) || UUIDHelper.getUUIDHelper().isDefaultUUID(idType.getId())) {
            var userMessage = "El tipo de identificación deseado no existe...";
            var technicalMessage = "El tipo de identificación con id ["
                    .concat(id.toString()).concat("] no existe ...");
            throw NoseException.create(userMessage,technicalMessage);
        }

    }
}
