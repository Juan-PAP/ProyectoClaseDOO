package co.edu.uco.nose.crosscuting.messagescatalog.business.rule.user;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnumUserRule {

    // --- MENSAJES PARA UserDoesNotExistWithSameIdNumberAndIdTypeRule ---
    USER_DOES_NOT_EXIST_WITH_SAME_ID_NUMBER_AND_ID_TYPE_RULE_DATA_IS_NULL(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "No se recibieron los parámetros requeridos para ejecutar la regla UserDoesNotExistWithSameIdNumberAndIdTypeRule."
    ),
    USER_DOES_NOT_EXIST_WITH_SAME_ID_NUMBER_AND_ID_TYPE_RULE_DATA_LENGTH_INVALID(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "Se requerían 3 parámetros y llegó una cantidad menor para ejecutar la regla UserDoesNotExistWithSameIdNumberAndIdTypeRule."
    ),
    USER_DOES_NOT_EXIST_WITH_SAME_ID_NUMBER_AND_ID_TYPE_RULE_USER_ALREADY_EXISTS(
            "Ya existe un usuario registrado con el mismo tipo y número de identificación.",
            "La regla UserDoesNotExistWithSameIdNumberAndIdTypeRule falló porque ya existe un usuario con IdNumber: {0} e IdType: {1}"
    ),


    // --- MENSAJES PARA UserEmailDoesNotExistRule ---
    USER_EMAIL_DOES_NOT_EXIST_RULE_DATA_IS_NULL(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "No se recibieron los parámetros requeridos para ejecutar la regla UserEmailDoesNotExistRule."
    ),
    USER_EMAIL_DOES_NOT_EXIST_RULE_DATA_LENGTH_INVALID(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "Se requerían 2 parámetros (String email, DAOFactory) y llegó una cantidad menor para ejecutar la regla UserEmailDoesNotExistRule."
    ),
    USER_EMAIL_DOES_NOT_EXIST_RULE_USER_ALREADY_EXISTS(
            "Ya existe un usuario registrado con el correo electrónico ingresado.",
            "La regla UserEmailDoesNotExistRule falló porque ya existe un usuario con el email: {0}"
    ),


    // --- MENSAJES PARA UserExistsByIdRule ---
    USER_EXISTS_BY_ID_RULE_DATA_IS_NULL(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "No se recibieron los parámetros requeridos para ejecutar la regla UserExistsByIdRule."
    ),
    USER_EXISTS_BY_ID_RULE_DATA_LENGTH_INVALID(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "Se requerían 2 parámetros y llegó una cantidad menor para ejecutar la regla UserExistsByIdRule."
    ),
    USER_EXISTS_BY_ID_RULE_USER_NOT_FOUND(
            "El usuario deseado no existe...",
            "El usuario con id [{0}] no existe ..."
    ),


    // --- MENSAJES PARA UserMobileDoesNotExistRule ---
    USER_MOBILE_DOES_NOT_EXIST_RULE_DATA_IS_NULL(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
                    "No se recibieron los parámetros requeridos para ejecutar la regla UserMobileDoesNotExistRule."
    ),
    USER_MOBILE_DOES_NOT_EXIST_RULE_DATA_LENGTH_INVALID(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
                    "Se requerían 2 parámetros y llegó una cantidad menor para ejecutar la regla UserMobileDoesNotExistRule."
    ),
    USER_MOBILE_DOES_NOT_EXIST_RULE_USER_ALREADY_EXISTS(
            "Ya existe un usuario registrado con el número de teléfono ingresado.",
                    "La regla UserMobileDoesNotExistRule falló porque ya existe un usuario con el teléfono: {0}"
    );

    private String title;
    private String content;

    private MessagesEnumUserRule(final String title, final String content) {
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