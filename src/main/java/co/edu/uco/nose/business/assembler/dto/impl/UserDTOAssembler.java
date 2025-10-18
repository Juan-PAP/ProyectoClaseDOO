package co.edu.uco.nose.business.assembler.dto.impl;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDTOAssembler implements DTOAssembler <UserDTO, UserDomain> {

    private static final DTOAssembler<UserDTO, UserDomain> instance = new UserDTOAssembler();

    private UserDTOAssembler() {

    }

    public static DTOAssembler<UserDTO, UserDomain> getUserDTOAssembler() {
        return instance;
    }

    @Override
    public UserDTO toDTO(final UserDomain domain) {
        return null;
    }

    @Override
    public UserDomain toDomain(final UserDTO dto) {
        return null;
    }

    @Override
    public List<UserDTO> toDTO(final List<UserDomain> domainList) {
        var userDtoList = new ArrayList<UserDTO>();

        for (var userDomain : domainList) {
            userDtoList.add(toDTO(userDomain));
        }

        return userDtoList;
    }
}
