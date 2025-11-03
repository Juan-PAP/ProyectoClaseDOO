package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public class UUIDValueIsPresentRule implements Rule {

    private static final Rule instance = new UUIDValueIsPresentRule();

    private UUIDValueIsPresentRule() {
        super();
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var technicalMessage = "No se recibieron los parámetros requeridos para ejecutar la regla UUIDValueIsPresentRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.";
            var technicalMessage = "Se requerían dos parámetros y llegó una cantidad menor para ejecutar la regla UUIDValueIsPresentRule.";
            throw NoseException.create(userMessage, technicalMessage);
        }

        var uuid = (UUID) data[0];
        var dataName = (String) data[1];

        if (ObjectHelper.isNull(uuid)) {
            var userMessage = "El dato [" .concat(dataName).concat("] es requerido para llevar a cabo la operación.");
            var technicalMessage = "La regla UUIDValueIsPresentRule falló porque el dato [".concat(dataName).concat("] requerido esta nulo.");
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (UUIDHelper.getUUIDHelper().isDefaultUUID(uuid)) {
            var userMessage = "El dato [" .concat(dataName).concat("] no puede ser el valor por defecto.");
            var technicalMessage = "La regla UUIDValueIsPresentRule falló porque el dato [".concat(dataName).concat("] tiene el valor por defecto.");
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}
