package co.edu.uco.nose.controller;

import co.edu.uco.nose.business.facade.UserFacade;
import co.edu.uco.nose.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.controller.dto.Response;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.dto.UserDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    public ResponseEntity<Response<UserDTO>> findAllUsers(){

        Response<UserDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {

            var facade = new UserFacadeImpl();
            responseObjectData.setData(facade.findAllUsers());
            responseObjectData.addMessage("All users filtered successfully");

        } catch (final NoseException exception) {

            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();

        } catch (final Exception exception) {
            var userMessage = "Unexpected error";
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }
        return new ResponseEntity<Response<UserDTO>>(responseObjectData, responseStatusCode);
    }

    @PostMapping
    public ResponseEntity<Response<UserDTO>> registerNewUserInformation(@RequestBody UserDTO user) {

        Response<UserDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.CREATED;

        try {
            var facade = new UserFacadeImpl();

            facade.registerNewUserInformation(user);
            responseObjectData.addMessage("New user registered successfully");

        } catch (final NoseException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();

        } catch (final Exception exception) {
            var userMessage = "Unexpected error";
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }
        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }

    @PutMapping("/{id}")
    public String updateUserInformation(@PathVariable UUID id, @RequestBody UserDTO user) {
        return "PUT: User information updated!";
    }

    @DeleteMapping("/{id}")
    public String dropUserInformation(@PathVariable UUID id) {
        return "DELETE: User information deleted!";
    }
}
