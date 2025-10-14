package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.BooleanHelper;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class UserEntity extends Entity {
	
	private IdTypeEntity idType;
	private String identificationNumber;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private CityEntity homeCity;
	private String email;
	private String mobilePhoneNumber;
	private Boolean confirmedEmail;
	private Boolean confirmedMobilePhoneNumber;
	
	public UserEntity() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setIdType(IdTypeEntity.createDefault());
		setIdNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstSurname(TextHelper.getDefault());
		setSecondSurname(TextHelper.getDefault());
		setHomeCity(CityEntity.createDefault());
		setEmail(TextHelper.getDefault());
		setMobileNumber(TextHelper.getDefault());
		setConfirmedEmail(BooleanHelper.getDefault());
		setMobileNumberConfirmed(BooleanHelper.getDefault());
	}
	
	public UserEntity(final UUID id) {
		super(id);
		setIdType(IdTypeEntity.createDefault());
		setIdNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstSurname(TextHelper.getDefault());
		setSecondSurname(TextHelper.getDefault());
		setHomeCity(CityEntity.createDefault());
		setEmail(TextHelper.getDefault());
		setMobileNumber(TextHelper.getDefault());
		setConfirmedEmail(BooleanHelper.getDefault());
	}
	
	public UserEntity(final UUID id, final IdTypeEntity identificationType, final String identificationNumber, final String firstName, 
			final String secondName, final String firstSurname, final String secondSurname, final CityEntity cityResidence, final String email, 
			final String mobilePhoneNumber, final Boolean confirmedEmail, final Boolean confirmedMobilePhoneNumber) {
		super(id);
		setIdType(identificationType);
		setIdNumber(identificationNumber);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstSurname(firstSurname);
		setSecondSurname(secondSurname);
		setHomeCity(cityResidence);
		setEmail(email);
		setMobileNumber(mobilePhoneNumber);
		setConfirmedEmail(confirmedEmail);
		setMobileNumberConfirmed(confirmedMobilePhoneNumber);
	}
	
	public IdTypeEntity getIdType() {
		return idType;
	}
	
	public void setIdType(IdTypeEntity idType) {
		this.idType = ObjectHelper.getDefault(idType, IdTypeEntity.createDefault());
	}
	
	public String getIdNumber() {
		return identificationNumber;
	}
	
	public void setIdNumber(String identificationNumber) {
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
	
	public CityEntity getHomeCity() {
		return homeCity;
	}
	
	public void setHomeCity(CityEntity cityResidence) {
		this.homeCity = ObjectHelper.getDefault(cityResidence, CityEntity.createDefault());
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = TextHelper.getDefaultWithTrim(email);
	}
	
	public String getMobileNumber() {
		return mobilePhoneNumber;
	}
	
	public void setMobileNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = TextHelper.getDefaultWithTrim(mobilePhoneNumber);
	}
	
	public Boolean getConfirmedEmail() {
		return confirmedEmail;
	}
	
	public void setConfirmedEmail(Boolean confirmedEmail) {
		this.confirmedEmail = BooleanHelper.getDefault(confirmedEmail);
	}

	public Boolean getMobileNumberConfirmed() {
		return confirmedMobilePhoneNumber;
	}

	public void setMobileNumberConfirmed(Boolean confirmedMobilePhoneNumber) {
		this.confirmedMobilePhoneNumber = BooleanHelper.getDefault(confirmedMobilePhoneNumber);
	}	

}
