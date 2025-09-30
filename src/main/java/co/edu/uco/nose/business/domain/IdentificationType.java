package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class IdentificationType extends Domain {

	private String nombre;
	
	public IdentificationType() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
	}
	
	public IdentificationType(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
	}
	
	public IdentificationType(final UUID id, final String nombre) {
		super(id);
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = TextHelper.getDefault(nombre);
	}
	
	public static IdentificationType createDefault() {
		return new IdentificationType();
	}
}
