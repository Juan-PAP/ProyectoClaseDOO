package co.edu.uco.nose.crosscuting.messagescatalog.business.rule.city;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnumCityRule {

    CITY_EXISTS_BY_ID_RULE_DATA_IS_NULL(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            "No se recibieron los parámetros requeridos para ejecutar la regla CityExistsByIdRule."
    ),
    CITY_EXISTS_BY_ID_RULE_DATA_LENGTH_INVALID(
            "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada.",
            // Corregí el typo "paramentros"
            "Se requerían 2 parámetros y llegó una cantidad menor para ejecutar la regla CityExistsByIdRule."
    ),
    CITY_EXISTS_BY_ID_RULE_CITY_NOT_FOUND(
            "La ciudad deseada no existe...",
            "La ciudad con id [{0}] no existe ..."
    );

    private String title;
    private String content;

    private MessagesEnumCityRule(final String title, final String content) {
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