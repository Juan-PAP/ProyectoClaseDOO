package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class IdTypeEntity extends Entity{
	
private String nombre;
	
	public IdTypeEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setNombre(TextHelper.getDefault());
	}
	
	public IdTypeEntity(final UUID id) {
		super(id);
		setNombre(TextHelper.getDefault());
	}
	
	public IdTypeEntity(final UUID id, final String nombre) {
		super(id);
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = TextHelper.getDefault(nombre);
	}
	
	public static IdTypeEntity createDefault() {
		return new IdTypeEntity();
	}

}
