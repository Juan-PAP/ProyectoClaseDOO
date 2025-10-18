package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.CountryDomain;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.dto.CountryDTO;
import co.edu.uco.nose.dto.UserDTO;
import co.edu.uco.nose.entity.UserEntity;
import org.apache.catalina.User;

public final class UserEntityAssembler implements EntityAssembler<UserEntity, UserDTO> {

    private final static EntityAssembler<UserEntity, UserDTO> instance = new UserEntityAssembler();

    private UserEntityAssembler() {
    }

    public static EntityAssembler<UserEntity, UserDTO> getUserEntityAssembler() {
        return instance;
    }

    @Override
    public UserEntity toEntity(UserDTO domain) {
        var userTmp = ObjectHelper.getDefault(domain, new UserDTO(UUIDHelper.getUUIDHelper().getDefault()));
        return new UserEntity();
    }

    @Override
    public UserDTO toDomain(UserEntity domain) {
        var dtoTmp = ObjectHelper.getDefault(domain, new UserDTO());
        return new UserDTO();
    }
}
