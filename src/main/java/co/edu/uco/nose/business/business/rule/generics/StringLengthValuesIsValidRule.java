package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public class StringLengthValuesIsValidRule implements Rule {

    private static final Rule instance = new StringLengthValuesIsValidRule();

    private StringLengthValuesIsValidRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)){
            var UserMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var TechnicalMessage = "No se recibieron los parámetros requeridos para ejecutar la regla StringValuelsPresentRule.";
            throw NoseException.create(UserMessage, TechnicalMessage);
        }
        if (data.length < 5){
            var UserMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var TechnicalMessage = "Se requerian cinco paramentros y llegó una cantidad menor a esta requeridos para ejecutar la regla StringValuelsPresentRule.";
            throw NoseException.create(UserMessage, TechnicalMessage);
        }

        var StringData = (String) data[0];
        var dataName = (String) data[1];
        int minLength = (Integer) data[2];
        int maxLength = (Integer) data[3];
        boolean mustApplyTrim = (Boolean) data[4];

        if (!TextHelper.lengthIsValid(StringData,minLength, maxLength, mustApplyTrim)) {
            var UserMessage = "El dato [" .concat(dataName).concat("] no tiene una longitud entre " .concat(String.valueOf(minLength)).concat(" y ").concat(String.valueOf(maxLength)).concat(" ..."));
            var TechnicalMessage = "La regla StringValuelsPresentRule falló porque el dato [".concat(dataName)
                    .concat(("] para llevar a cabo la operacion, " + "no tiene una longitud entre entre ")
                    .concat(String.valueOf(minLength)).concat(" y ").concat(String.valueOf(maxLength)).concat(" caracteres."));
            throw NoseException.create(UserMessage, TechnicalMessage);
        };
    }
}
