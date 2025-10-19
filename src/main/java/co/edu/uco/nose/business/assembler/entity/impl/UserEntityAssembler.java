package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.dto.UserDTO;
import co.edu.uco.nose.entity.UserEntity;

import java.util.List;

public final class UserEntityAssembler implements EntityAssembler<UserEntity, UserDomain> {

    private final static EntityAssembler<UserEntity, UserDomain> instance = new UserEntityAssembler();

    private UserEntityAssembler() {
    }

    public static EntityAssembler<UserEntity, UserDomain> getUserEntityAssembler() {
        return instance;
    }

    @Override
    public UserEntity toEntity(final UserDomain domain) {
        return null;
    }

    @Override
    public UserDomain toDomain(final UserEntity entity) {
        return null;
    }

    @Override
    public List<UserEntity> toDTO(List<UserDomain> domainList) {
        return List.of();
    }
}
