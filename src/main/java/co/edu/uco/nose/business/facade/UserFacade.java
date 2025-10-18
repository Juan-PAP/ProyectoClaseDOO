package co.edu.uco.nose.business.facade;

import co.edu.uco.nose.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserFacade {

    void registerNewUserInformation (UserDTO userDomain);

    void dropUserInformation (UserDTO userDomain);

    void dropUserInformation (UUID id);

    void updateUserInformation (UUID id, UserDTO userDomain);

    List<UserDTO> findAllUsers ();

    List <UserDTO> findUsersByFilter (UserDTO userDomain);

    UserDTO findSpecificUser (UUID id);

    void confirmEmail(UUID id, int confirmationCode);

    void sendMobileNumberConfirmation(UUID id);

    void sendEmailConfirmation(UUID id);
}
