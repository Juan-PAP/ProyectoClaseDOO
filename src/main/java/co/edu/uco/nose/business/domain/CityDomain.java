package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class CityDomain extends Domain{
	
	private String name;
	private DepartmentDomain department;
	
	public CityDomain() {
		super(UUIDHelper.getUUIDHelper().getDefault());
		setDepartment(DepartmentDomain.createDefault());
		setName(TextHelper.getDefault());
	}
	
	public CityDomain(final UUID id) {
		super(id);
		setDepartment(DepartmentDomain.createDefault());
		setName(TextHelper.getDefault());
	}
	
	public CityDomain(final UUID id, final DepartmentDomain department, final String name) {
		super(id);
		setDepartment(department);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

	public DepartmentDomain getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDomain department) {
		this.department = ObjectHelper.getDefault(department, DepartmentDomain.createDefault());
	}
	
	public static CityDomain createDefault() {
		return new CityDomain();
	}

}
