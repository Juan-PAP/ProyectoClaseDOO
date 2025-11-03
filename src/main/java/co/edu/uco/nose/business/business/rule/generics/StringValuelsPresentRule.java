package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public final class StringValuelsPresentRule implements Rule {

    private static final Rule instance = new StringValuelsPresentRule();

    private StringValuelsPresentRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {
        if (ObjectHelper.isNull(data)){
            var UserMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var TechnicalMessage = "No se recibieron los parámetros requeridos para ejecutar la regla StringValuelsPresentRule.";
            throw NoseException.create(UserMessage, TechnicalMessage);
        }
        if (data.length < 3){
            var UserMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var TechnicalMessage = "Se requerian tres paramentros y llegó una cantidad menor a esta requeridos para ejecutar la regla StringValuelsPresentRule.";
            throw NoseException.create(UserMessage, TechnicalMessage);
        }

        var StringData = (String) data[0];
        var dataName = (String) data[1];
        boolean mustApplyTrim = (Boolean) data[2];

        if ((mustApplyTrim)
                ? TextHelper.isEmptyWithTrim(StringData) : TextHelper.isEmpty(StringData)) {
            var UserMessage = "El dato [" .concat(dataName).concat("] es requerido para llevar a cabo la operación.");
            var TechnicalMessage = "La regla StringValuelsPresentRule falló porque el dato [".concat(dataName).concat("] requerido para llevar a cabo la operación esta vació");
            throw NoseException.create(UserMessage, TechnicalMessage);
        };
    }
}
