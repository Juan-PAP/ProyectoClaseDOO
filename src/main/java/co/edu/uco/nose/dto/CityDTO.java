package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class CityDTO extends DTO{
	private String name;
	private StateDTO state;
	
	public CityDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setState(StateDTO.createDefault());
		setName(TextHelper.getDefault());
	}
	
	public CityDTO(final UUID id) {
		super(id);
		setState(StateDTO.createDefault());
		setName(TextHelper.getDefault());
	}
	
	public CityDTO(final UUID id, final StateDTO state, final String name) {
		super(id);
		setState(state);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

	public StateDTO getState() {
		return state;
	}

	public void setState(StateDTO state) {
		this.state = ObjectHelper.getDefault(state, StateDTO.createDefault());
	}
	
	public static CityDTO createDefault() {
		return new CityDTO();
	}


}
