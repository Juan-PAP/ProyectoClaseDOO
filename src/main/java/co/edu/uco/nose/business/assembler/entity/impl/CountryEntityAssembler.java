package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.dto.CountryDTO;
import co.edu.uco.nose.entity.CountryEntity;

public final class CountryEntityAssembler implements EntityAssembler<CountryEntity,CountryDTO> {

    private static final EntityAssembler<CountryEntity,CountryDTO> instance = new CountryEntityAssembler();

    private CountryEntityAssembler() {

    }
    public static EntityAssembler<CountryEntity,CountryDTO> getCountryEntityAssembler() {
        return instance;
    }


}
