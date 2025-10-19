package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.IdTypeDomain;
import co.edu.uco.nose.entity.IdTypeEntity;

import java.util.List;

public final class IdTypeEntityAssembler implements EntityAssembler<IdTypeEntity, IdTypeDomain> {

    private static final EntityAssembler<IdTypeEntity, IdTypeDomain> instance =
            new IdTypeEntityAssembler();

    private IdTypeEntityAssembler() {

    }

    public static EntityAssembler<IdTypeEntity, IdTypeDomain> getIdTypeEntityAssembler() {
        return instance;
    }
    @Override
    public IdTypeEntity toEntity(final IdTypeDomain domain) {
        return null;
    }

    @Override
    public IdTypeDomain toDomain(final IdTypeEntity entity) {
        return null;
    }

    @Override
    public List<IdTypeEntity> toDTO(List<IdTypeDomain> domainList) {
        return List.of();
    }
}
