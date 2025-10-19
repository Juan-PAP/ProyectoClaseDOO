package co.edu.uco.nose.business.assembler.dto.impl;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.CountryDomain;
import co.edu.uco.nose.business.domain.IdTypeDomain;
import co.edu.uco.nose.dto.CountryDTO;
import co.edu.uco.nose.dto.IdTypeDTO;

import java.util.List;

public final class IdTypeDTOAssembler implements DTOAssembler <IdTypeDTO, IdTypeDomain> {

    private static final DTOAssembler <IdTypeDTO, IdTypeDomain> instance =
            new IdTypeDTOAssembler();

    private IdTypeDTOAssembler() {

    }

    public static DTOAssembler <IdTypeDTO, IdTypeDomain> getCountryDTOAssembler() {
        return instance;
    }
    @Override
    public IdTypeDTO toDTO(final IdTypeDomain domain) {
        return null;
    }

    @Override
    public IdTypeDomain toDomain(final IdTypeDTO dto) {
        return null;
    }

    @Override
    public List<IdTypeDTO> toDTO(List<IdTypeDomain> domainList) {
        return List.of();
    }
}
