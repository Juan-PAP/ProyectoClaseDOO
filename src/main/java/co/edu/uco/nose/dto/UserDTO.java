package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.BooleanHelper;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class UserDTO extends DTO{
	
	private IdTypeDTO identificationType;
	private String identificationNumber;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private CityDTO cityResidence;
	private String email;
	private String mobilePhoneNumber;
	private Boolean confirmedEmail;
	private Boolean confirmedMobilePhoneNumber;
	
	public UserDTO() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setIdentificationType(IdTypeDTO.createDefault());
		setIdentificationNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstSurname(TextHelper.getDefault());
		setSecondSurname(TextHelper.getDefault());
		setCityDTO(CityDTO.createDefault());
		setEmail(TextHelper.getDefault());
		setMobilePhoneNumber(TextHelper.getDefault());
		setConfirmedEmail(BooleanHelper.getDefault());
		setConfirmedMobilePhoneNumber(BooleanHelper.getDefault());
	}
	
	public UserDTO(final UUID id) {
		super(id);
		setIdentificationType(IdTypeDTO.createDefault());
		setIdentificationNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstSurname(TextHelper.getDefault());
		setSecondSurname(TextHelper.getDefault());
		setCityDTO(CityDTO.createDefault());
		setEmail(TextHelper.getDefault());
		setMobilePhoneNumber(TextHelper.getDefault());
		setConfirmedEmail(BooleanHelper.getDefault());
	}
	
	public UserDTO(final UUID id, final IdTypeDTO identificationType, final String identificationNumber, final String firstName, 
			final String secondName, final String firstSurname, final String secondSurname, final CityDTO cityResidence, final String email, 
			final String mobilePhoneNumber, final Boolean confirmedEmail, final Boolean confirmedMobilePhoneNumber) {
		super(id);
		setIdentificationType(identificationType);
		setIdentificationNumber(identificationNumber);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstSurname(firstSurname);
		setSecondSurname(secondSurname);
		setCityDTO(cityResidence);
		setEmail(email);
		setMobilePhoneNumber(mobilePhoneNumber);
		setConfirmedEmail(confirmedEmail);
		setConfirmedMobilePhoneNumber(confirmedMobilePhoneNumber);
	}
	
	public IdTypeDTO getIdentificationType() {
		return identificationType;
	}
	
	public void setIdentificationType(IdTypeDTO identificationType) {
		this.identificationType = ObjectHelper.getDefault(identificationType, IdTypeDTO.createDefault());
	}
	
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = TextHelper.getDefaultWithTrim(firstName);
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public void setSecondName(String secondName) {
		this.secondName = TextHelper.getDefaultWithTrim(secondName);
	}
	
	public String getFirstSurname() {
		return firstSurname;
	}
	
	public void setFirstSurname(String firstSurname) {
		this.firstSurname = TextHelper.getDefaultWithTrim(firstSurname);
	}
	
	public String getSecondSurname() {
		return secondSurname;
	}
	
	public void setSecondSurname(String secondSurname) {
		this.secondSurname = TextHelper.getDefaultWithTrim(secondSurname);
	}
	
	public CityDTO getCityDTO() {
		return cityResidence;
	}
	
	public void setCityDTO(CityDTO cityResidence) {
		this.cityResidence = ObjectHelper.getDefault(cityResidence, CityDTO.createDefault());
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = TextHelper.getDefaultWithTrim(email);
	}
	
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}
	
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = TextHelper.getDefaultWithTrim(mobilePhoneNumber);
	}
	
	public Boolean getConfirmedEmail() {
		return confirmedEmail;
	}
	
	public void setConfirmedEmail(Boolean confirmedEmail) {
		this.confirmedEmail = BooleanHelper.getDefault(confirmedEmail);
	}
	
	public Boolean getConfirmedMobilePhoneNumber() {
		return confirmedMobilePhoneNumber;
	}
	
	public void setConfirmedMobilePhoneNumber(Boolean confirmedMobilePhoneNumber) {
		this.confirmedMobilePhoneNumber = BooleanHelper.getDefault(confirmedMobilePhoneNumber);
	}	

}
