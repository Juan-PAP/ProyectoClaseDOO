package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public class StringFormatValuesIsValidRule implements Rule {

    private static final Rule instance = new StringFormatValuesIsValidRule();

    private StringFormatValuesIsValidRule() {
        super();
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var technicalMessage = "No se recibieron los parámetros requeridos para ejecutar la regla StringFormatValuesIsValidRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length < 4) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var technicalMessage = "Se requerían cuatro parámetros y llegó una cantidad menor para ejecutar la regla StringFormatValuesIsValidRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        var stringData = (String) data[0];
        var dataName = (String) data[1];
        var regex = (String) data[2];
        var mustApplyTrim = (Boolean) data[3];

        var dataToValidate = (mustApplyTrim)
                ? TextHelper.getDefaultWithTrim(stringData)
                : stringData;

        if (!TextHelper.isEmpty(dataToValidate)) {

            if (!TextHelper.matchesRegex(dataToValidate, regex)) {
                var userMessage = "El dato [" .concat(dataName).concat("] no cumple con el formato esperado.");
                var technicalMessage = "La regla StringFormatValuesIsValidRule falló porque el dato [".concat(dataName).concat("] no cumple con el formato (Regex): ").concat(regex);
                throw NoseException.create(userMessage, technicalMessage);
            }
        }
    }
}