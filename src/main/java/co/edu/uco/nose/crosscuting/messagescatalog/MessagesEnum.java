package co.edu.uco.nose.crosscuting.messagescatalog;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnum {

	//SqlConnectionHelper
	
	USER_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada vacia",
			"La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada está vacia. "
			+ "Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
	
	TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY("Conexion contra la fuente de informacion deseada nula",
			"La conexion requerida para llevar a cabo la operacion contra la base de datos llegó nula."
			+ "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),
	
	USER_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada",
			"La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada está cerrada. "
			+ "Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
	
	TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED("Conexion contra la fuente de informacion deseada cerrada",
			"La conexion requerida para llevar a cabo la operacion contra la base de datos llegó cerrada."
			+ "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),
	
	USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Problema inesperado contra la fuente de informacion deseada vacia",
			"La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada está vacia. "
			+ "Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion"),
	
	TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_CONNECTION_STATUS("Problema inesperado contra la fuente de informacion deseada vacia" ,
			"La conexion requerida para llevar a cabo la operacion contra la fuente de informacion deseada está vacia."
			+ "Por favor intenta de nuevo y si el problema persiste, contacte al administrador de la aplicación"),
	
	TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Error técnico inesperado al validar el estado de la conexión",
			"Se presentó un error técnico inesperado al intentar validar el estado de la conexión contra la base de datos. "
			+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación"),
	
	USER_ERROR_TRANSACTION_IS_NOT_STARTED("Transacción no iniciada",
			"La operación no puede completarse porque la transacción requerida no ha sido iniciada. "
			+ "Por favor inicie la transacción e intente nuevamente. Si el problema persiste, contacte al administrador de la aplicación."),
	
	TECHNICAL_ERROR_TRANSACTION_IS_NOT_STARTED("Transacción no iniciada en la base de datos",
			"La operación no puede completarse porque la transacción requerida no fue iniciada correctamente en la base de datos. "
			+ "Por favor revise la lógica de inicio de transacciones y si el problema persiste, contacte al administrador de la aplicación."),
	
	USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error inesperado al validar el inicio de la transacción",
			"Se presentó un problema inesperado al validar el estado de la transacción. "
			+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),
	
	TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED("Error SQL al validar el inicio de la transacción",
			"Se produjo una excepción SQL al intentar validar el estado de la transacción. "
			+ "Por favor revise la conexión con la base de datos y si el problema persiste, contacte al administrador de la aplicación."),
	
	TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error técnico inesperado al validar el inicio de la transacción",
			"Se presentó un error técnico inesperado al intentar validar el estado de la transacción. "
			+ "Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_TRANSACTION_IS_STARTED("Transacción ya iniciada",
			"La operación no puede completarse porque la transacción ya se encuentra iniciada. "
					+ "Por favor verifique el estado de la transacción e intente nuevamente. Si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_TRANSACTION_IS_STARTED("Transacción ya iniciada en la base de datos",
			"La operación no puede completarse porque la transacción ya se encontraba iniciada en la base de datos. "
					+ "Por favor revise la lógica de control de transacciones y si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED("Error inesperado al validar que la transacción no esté iniciada",
			"Se presentó un problema inesperado al validar que la transacción no se encontrara iniciada. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_NOT_STARTED("Error SQL al validar que la transacción no esté iniciada",
			"Se produjo una excepción SQL al intentar validar que la transacción no se encontrara iniciada. "
					+ "Por favor revise la conexión con la base de datos y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED("Error técnico inesperado al validar que la transacción no esté iniciada",
			"Se presentó un error técnico inesperado al intentar validar que la transacción no estuviera iniciada. "
					+ "Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),

	//SqlServerDAOFactory

	USER_ERROR_SQL_CANNOT_OPEN_CONNECTION("No fue posible establecer conexión con la base de datos",
			"Se presentó un error al intentar conectarse con la base de datos PostgreSQL. " +
					"Por favor verifique los datos de conexión (URL, usuario y contraseña) e intente nuevamente. " +
			    	"Si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_CANNOT_OPEN_CONNECTION("Error SQL al intentar abrir la conexión con PostgreSQL",
			"Ocurrió una excepción SQL al ejecutar DriverManager.getConnection(). "
				   + "Verifique que el servicio de base de datos esté disponible, las credenciales sean correctas y la URL sea válida. "
				   + "Si el problema persiste, contacte al administrador de la aplicación."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION("Error inesperado al intentar abrir la conexión",
		   "Se presentó un problema inesperado al intentar establecer la conexión con la base de datos. "
				   + "Por favor intente nuevamente y si el problema persiste, contacte al administrador de la aplicación."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_OPENING_CONNECTION("Error técnico inesperado al intentar abrir la conexión",
			"Se presentó un error técnico no controlado al intentar establecer la conexión con la base de datos. "
					+ "Por favor revise los registros del sistema y si el problema persiste, contacte al administrador de la aplicación."),

	//UserSqlServerDAO

	//Insertar
	USER_ERROR_SQL_INSERT_USER("Error al registrar la información del nuevo usuario",
			"Se ha presentado un problema tratando de registrar la información del nuevo usuario. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_INSERT_USER("Error técnico al registrar la información del nuevo usuario",
			"Se ha presentado un problema al tratar de ejecutar el proceso de creación de un nuevo usuario en la base de datos. "
					+ "Por favor valide que la base de datos esté funcionando correctamente. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_INSERT_USER("Error inesperado al registrar la información del nuevo usuario",
			"Se ha presentado un problema inesperado tratando de registrar la información del nuevo usuario. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_USER("Error técnico inesperado al registrar la información del nuevo usuario",
			"Se ha presentado un problema técnico inesperado al tratar de ejecutar el proceso de creación de un nuevo usuario. "
					+ "Por favor valide que la base de datos esté funcionando correctamente y revise los registros del sistema. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	//Consultar todos


	//Consultar por filtro


	//Consultar por Id

	USER_ERROR_SQL_FIND_BY_ID_USER("Error al consultar la información del usuario deseado",
			"Se ha presentado un problema tratando de consultar la información del usuario deseado. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_FIND_BY_ID_USER("Error técnico al consultar la información del usuario deseado",
			"Se ha presentado un problema al tratar de ejecutar el proceso de consulta del usuario deseado en la base de datos. "
					+ "Por favor valide que la base de datos esté funcionando correctamente. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_USER("Error inesperado al consultar la información del usuario deseado",
			"Se ha presentado un problema inesperado tratando de consultar la información del usuario deseado. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_FIND_BY_ID_USER("Error técnico inesperado al consultar la información del usuario deseado",
			"Se ha presentado un problema técnico inesperado al tratar de ejecutar el proceso de consulta del usuario deseado. "
					+ "Por favor valide que la base de datos esté funcionando correctamente y revise los registros del sistema. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	//Actualizar

	USER_ERROR_SQL_UPDATE_USER("Error al modificar la información del usuario",
			"Se ha presentado un problema tratando de modificar la información del usuario. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UPDATE_USER("Error técnico al modificar la información del usuario",
			"Se ha presentado un problema al tratar de ejecutar el proceso de modificación del usuario en la base de datos. "
					+ "Por favor valide que la base de datos esté funcionando correctamente. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_USER("Error inesperado al modificar la información del usuario",
			"Se ha presentado un problema inesperado tratando de modificar la información del usuario. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_USER("Error técnico inesperado al modificar la información del usuario",
			"Se ha presentado un problema técnico inesperado al tratar de ejecutar el proceso de modificación del usuario. "
					+ "Por favor valide que la base de datos esté funcionando correctamente y revise los registros del sistema. "
					+ "Si el problema persiste, contacte al administrador del sistema."),


	//Eliminar

	USER_ERROR_SQL_DELETE_USER("Error al eliminar la información del usuario",
			"Se ha presentado un problema tratando de eliminar la información del usuario. "
					+ "Por favor intente de nuevo y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_DELETE_USER("Error técnico al eliminar la información del usuario",
			"Se ha presentado un problema al tratar de ejecutar el proceso de eliminación del usuario en la base de datos. "
					+ "Por favor valide que la base de datos esté funcionando correctamente. "
					+ "Si el problema persiste, contacte al administrador del sistema."),

	USER_ERROR_SQL_UNEXPECTED_ERROR_DELETE_USER("Error inesperado al eliminar la información del usuario",
			"Se ha presentado un problema inesperado tratando de eliminar la información del usuario. "
					+ "Por favor intente nuevamente y si el problema persiste, contacte al administrador del sistema."),

	TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_USER("Error técnico inesperado al eliminar la información del usuario",
			"Se ha presentado un problema técnico inesperado al tratar de ejecutar el proceso de eliminación del usuario. "
					+ "Por favor valide que la base de datos esté funcionando correctamente y revise los registros del sistema. "
					+ "Si el problema persiste, contacte al administrador del sistema.");







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
