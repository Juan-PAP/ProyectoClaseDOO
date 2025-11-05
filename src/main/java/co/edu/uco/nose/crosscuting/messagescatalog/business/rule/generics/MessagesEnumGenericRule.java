package co.edu.uco.nose.crosscuting.messagescatalog.business.rule.generics;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnumGenericRule {

    // --- MENSAJES PARA IdValueIsNotDefaultValueRule ---
    ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_IS_NULL(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "No se recibieron los parámetros requeridos para ejecutar la regla IdValueIsNotDefaultValueRule."
    ),
    ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_LENGHT_INVALID(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "Se requerían dos parámetros y llegó una cantidad menor para ejecutar la regla IdValueIsNotDefaultValueRule."
    ),
    ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_NULL(
            "El dato [{0}] es requerido para llevar a cabo la operación.",
            "La regla IdValueIsNotDefaultValueRule falló porque el dato [{0}] requerido esta nulo."
    ),
    ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_DEFAULT(
            "El dato [{0}] no puede ser el valor por defecto.",
            "La regla IdValueIsNotDefaultValueRule falló porque el dato [{0}] tiene el valor por defecto."
    ),



    // --- MENSAJES PARA StringFormatValuesIsValidRule ---
    STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_IS_NULL(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
                    "No se recibieron los parámetros requeridos para ejecutar la regla StringFormatValuesIsValidRule."
    ),
    STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
                    "Se requerían cuatro parámetros y llegó una cantidad menor para ejecutar la regla StringFormatValuesIsValidRule."
    ),
    STRING_FORMAT_VALUES_IS_VALID_RULE_FORMAT_IS_INVALID(
            "El dato [{0}] no cumple con el formato esperado.",
                    "La regla StringFormatValuesIsValidRule falló porque el dato [{0}] no cumple con el formato (Regex): {1}"
    ),



    // --- MENSAJES PARA StringLengthValuesIsValidRule ---
    STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_IS_NULL(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
                    // Corregimos el nombre de la regla que estaba mal en tu original
                    "No se recibieron los parámetros requeridos para ejecutar la regla StringLengthValuesIsValidRule."
    ),
    STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
                    // Corregimos el nombre y el número
                    "Se requerían cinco parámetros y llegó una cantidad menor para ejecutar la regla StringLengthValuesIsValidRule."
    ),
    STRING_LENGTH_VALUES_IS_VALID_RULE_LENGTH_IS_INVALID(
            // Usamos {0}, {1} y {2} como placeholders
            "El dato [{0}] no tiene una longitud entre {1} y {2} caracteres.",
                    "La regla StringLengthValuesIsValidRule falló porque el dato [{0}] no tiene una longitud entre {1} y {2} caracteres."
    ),

    // --- MENSAJES PARA StringValueIsPresentRule ---
    STRING_VALUELS_PRESENT_RULE_DATA_IS_NULL(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
                    "No se recibieron los parámetros requeridos para ejecutar la regla StringValueIsPresentRule."
    ),
    STRING_VALUELS_PRESENT_RULE_DATA_LENGTH_INVALID(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
                    // Corregimos el typo "paramentros"
                    "Se requerían tres parámetros y llegó una cantidad menor para ejecutar la regla StringValueIsPresentRule."
    ),
    STRING_VALUELS_PRESENT_RULE_DATA_IS_EMPTY(
            "El dato [{0}] es requerido para llevar a cabo la operación.",
                    // Corregimos el typo "vació"
                    "La regla StringValueIsPresentRule falló porque el dato [{0}] requerido para llevar a cabo la operación esta vacío."
    ),


    // --- MENSAJES PARA UUIDValueIsPresentRule ---
    UUID_VALUE_IS_PRESENT_RULE_DATA_IS_NULL(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "No se recibieron los parámetros requeridos para ejecutar la regla UUIDValueIsPresentRule."
    ),
    UUID_VALUE_IS_PRESENT_RULE_DATA_LENGTH_INVALID(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "Se requerían dos parámetros y llegó una cantidad menor para ejecutar la regla UUIDValueIsPresentRule."
    ),
    UUID_VALUE_IS_PRESENT_RULE_UUID_IS_NULL(
            "El dato [{0}] es requerido para llevar a cabo la operación.",
            "La regla UUIDValueIsPresentRule falló porque el dato [{0}] requerido esta nulo."
    ),
    UUID_VALUE_IS_PRESENT_RULE_UUID_IS_DEFAULT(
            "El dato [{0}] no puede ser el valor por defecto.",
            "La regla UUIDValueIsPresentRule falló porque el dato [{0}] tiene el valor por defecto."
    );


    private String title;
    private String content;

    private MessagesEnumGenericRule(final String title, final String content) {
        setTitle(title);
        setContent(content);
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = TextHelper.getDefaultWithTrim(title);
    }

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = TextHelper.getDefaultWithTrim(content);
    }
}