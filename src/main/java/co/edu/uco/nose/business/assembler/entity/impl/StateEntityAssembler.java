package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.StateDomain;
import co.edu.uco.nose.entity.StateEntity;

import java.util.List;

public final class StateEntityAssembler implements EntityAssembler<StateEntity, StateDomain> {

    private static final EntityAssembler<StateEntity, StateDomain> instance =
            new StateEntityAssembler();

    private StateEntityAssembler() {

    }

    public static EntityAssembler<StateEntity, StateDomain> getStateEntityAssembler() {
        return instance;
    }
    @Override
    public StateEntity toEntity(final StateDomain domain) {
        return null;
    }

    @Override
    public StateDomain toDomain(final StateEntity entity) {
        return null;
    }

    @Override
    public List<StateEntity> toDTO(List<StateDomain> domainList) {
        return List.of();
    }
}
