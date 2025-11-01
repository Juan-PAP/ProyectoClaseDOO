package co.edu.uco.nose.business.business.rule.idtype;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.business.business.rule.generics.StringLengthValuesIsValidRule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
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

        //...Mismas validaciones de las demás reglas
        //... que data no llegue nulo
        //... que data no tenga menos de 2 parametros

        var id = (UUID) data[0];
        var daoFactory = (DAOFactory) data[1];

        var idType = daoFactory.getIdTypeDAO().findById(id);

        if (UUIDHelper.getUUIDHelper().isDefaultUUID(idType.getId())) {
            var userMessage = "El tipo de identificación deseado no existe...";
            var technicalMessage = "El tipo de identificación con id ["
                    .concat(id.toString()).concat("] no existe ...");
            throw NoseException.create(userMessage,technicalMessage);

        }

    }
}
