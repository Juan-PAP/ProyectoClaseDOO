package co.edu.uco.nose.business.assembler.dto.impl;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.dto.CityDTO;

import java.util.List;

public final class CityDTOAssembler implements DTOAssembler<CityDTO, CityDomain> {
    @Override
    public CityDTO toDTO(final CityDomain domain) {
        return null;
    }

    @Override
    public CityDomain toDomain(final CityDTO dto) {
        return null;
    }

    @Override
    public List<CityDTO> toDTO(List<CityDomain> domainList) {
        return List.of();
    }
}
