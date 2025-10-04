package co.edu.uco.nose.crosscuting.messagescatalog;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnum {
	
	USER_ERROR_SQL_CONNECTION_IS_EMPTY("Conexión contra la fuente de información vacía",
			"La conexión requerida para llevar a cabo la operación contra la fuente de informacion esta vacía. "
			+ "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),
	
	TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY("Conexión contra la fuente de información vacía",
			"La conexión requerida para llevar a cabo la operación contra la fuente de informacion está vacía. "
			+ "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),
	
	USER_ERROR_SQL_CONNECTION_IS_CLOSED("Conexión contra la fuente deseada cerrada",
			"La conexión requerida para llevar a cabo la operación contra la fuente de informacion esta vacía. "
			+ "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),
	
	TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED("Conexión contra la fuente deseada cerrada",
			"La conexión requerida para llevar a cabo la operación contra la fuente de informacion está vacía. "
			+ "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),
	USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONECTION_STATUS("Problema inesperado, validando el estado de la conección contra la fuente de datos deseada",
			"La conexión requerida para llevar a cabo la operación contra la fuente de informacion esta vacía. "
			+ "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),
	
	TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONECTION_STATUS("Error inesperado validando si la información contra la base de datos estaba abierta" ,
			"Se presento un error de tipo SQLException al validar si la conección contra la base de datos esta cerrada o abierta. Por favor valide la consola de erroes."
			+ "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación");

	private String title;
	private String content;
	
	private MessagesEnum(final String title, final String content) {
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
