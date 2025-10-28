package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class IdTypeDTO extends DTO{
	
	private String name;
	
	public IdTypeDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}
	
	public IdTypeDTO(final UUID id) {
		super(id);
		setName(TextHelper.getDefault());
	}
	
	public IdTypeDTO(final UUID id, final String nombre) {
		super(id);
		setName(nombre);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.getDefault(name);
	}
	
	public static IdTypeDTO createDefault() {
		return new IdTypeDTO();
	}

}
