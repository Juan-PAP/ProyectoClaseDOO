package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public final class UserBusinessImpl implements UserBusiness {

    private DAOFactory daoFactory;

    public UserBusinessImpl (final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registerNewUserInformation(UserDomain userDomain) {

        package co.edu.uco.pizzaporcion.businesslogic.businesslogic.impl.customer.impl;

import co.edu.uco.pizzaporcion.businesslogic.businesslogic.assembler.customer.entity.CustomerEntityAssembler;
import co.edu.uco.pizzaporcion.businesslogic.businesslogic.domain.CustomerDomain;
import co.edu.uco.pizzaporcion.businesslogic.businesslogic.impl.customer.CustomerBusinessLogic;
import co.edu.uco.pizzaporcion.crosscutting.exceptions.BusinessLogicPizzaPorcionException;
import co.edu.uco.pizzaporcion.crosscutting.exceptions.PizzaPorcionException;
import co.edu.uco.pizzaporcion.crosscutting.utils.UtilText;
import co.edu.uco.pizzaporcion.crosscutting.utils.UtilUUID;
import co.edu.uco.pizzaporcion.data.dao.factory.DAOFactory;
import co.edu.uco.pizzaporcion.entity.CustomerEntity;

import java.util.List;
import java.util.UUID;

        public class CustomerBusinessLogicImpl implements CustomerBusinessLogic {

            private DAOFactory factory;

            public CustomerBusinessLogicImpl(DAOFactory factory) {
                this.factory = factory;
            }

            @Override
            public void registerNewCustomer(CustomerDomain customer) throws PizzaPorcionException {
                validateIntegrityInformationToRegisterNewCustomer(customer);
                validateCustomerWithSameIdentificationNumberDoesNotExist(customer.getIdentificationNumber());
                validateCustomerWithSamePhoneNumberDoesNotExist(customer.getPhoneNumber());

                var id = generateIdNewCustomer();

                var customerDomainToCreate = new CustomerDomain(
                        id,
                        customer.getIdentificationNumber(),
                        customer.getName(),
                        customer.getLastname(),
                        customer.getPhoneNumber()
                );
                CustomerEntity customerEntity = CustomerEntityAssembler.getInstance().toEntity(customerDomainToCreate);
                factory.getCustomerDAO().create(customerEntity);
            }

            private void validateIntegrityInformationToRegisterNewCustomer(CustomerDomain customer) throws PizzaPorcionException {
                validateIntegrityCustomerIdentificationNumber(customer.getIdentificationNumber());
                validateIntegrityCustomerName(customer.getName());
                validateIntegrityCustomerLastName(customer.getLastname());
                validateIntegrityCustomerPhoneNumber(customer.getPhoneNumber());
            }

            private void validateIntegrityCustomerName(String name) throws PizzaPorcionException {
                var cleanName = UtilText.getInstance().removeWhiteSpacesStartEnd(name);

                if (UtilText.getInstance().isEmpty(name)) {
                    throw BusinessLogicPizzaPorcionException.report("El nombre no puede estar vacío.");
                }

                if (cleanName.length() < 3) {
                    throw BusinessLogicPizzaPorcionException.report("El nombre debe tener al menos 3 caracteres.");
                }

                if (cleanName.length() > 15) {
                    throw BusinessLogicPizzaPorcionException.report("El nombre no puede tener más de 15 caracteres.");
                }

                if (!UtilText.getInstance().containsOnlyLettersSpaces(name)) {
                    throw BusinessLogicPizzaPorcionException.report("El nombre solo puede contener letras y espacios.");
                }
            }

            private void validateIntegrityCustomerLastName(String lastName) throws PizzaPorcionException {
                var cleanLastName = UtilText.getInstance().removeWhiteSpacesStartEnd(lastName);

                if (UtilText.getInstance().isEmpty(lastName)) {
                    throw BusinessLogicPizzaPorcionException.report("El apellido no puede estar vacío.");
                }

                if (cleanLastName.length() < 3) {
                    throw BusinessLogicPizzaPorcionException.report("El apellido debe tener al menos 3 caracteres.");
                }

                if (cleanLastName.length() > 30) {
                    throw BusinessLogicPizzaPorcionException.report("El apellido no puede tener más de 30 caracteres.");
                }

                if (!UtilText.getInstance().containsOnlyLettersSpaces(lastName)) {
                    throw BusinessLogicPizzaPorcionException.report("El apellido solo puede contener letras y espacios.");
                }
            }

            private void validateIntegrityCustomerIdentificationNumber(String identificationNumber) throws PizzaPorcionException {
                var cleanId = UtilText.getInstance().removeWhiteSpacesStartEnd(identificationNumber);

                if (UtilText.getInstance().isEmpty(identificationNumber)) {
                    throw BusinessLogicPizzaPorcionException.report("El número de identificación no puede estar vacío.");
                }

                if (cleanId.length() < 6) {
                    throw BusinessLogicPizzaPorcionException.report("El número de identificación debe tener al menos 6 caracteres.");
                }

                if (cleanId.length() > 15) {
                    throw BusinessLogicPizzaPorcionException.report("El número de identificación no puede tener más de 15 caracteres.");
                }

                if (!UtilText.getInstance().containsOnlyNumbers(identificationNumber)) {
                    throw BusinessLogicPizzaPorcionException.report("El número de identificación solo puede contener números.");
                }
            }

            private void validateIntegrityCustomerPhoneNumber(String phoneNumber) throws PizzaPorcionException {
                var cleanPhone = UtilText.getInstance().removeWhiteSpacesStartEnd(phoneNumber);

                if (UtilText.getInstance().isEmpty(phoneNumber)) {
                    throw BusinessLogicPizzaPorcionException.report("El número de teléfono no puede estar vacío.");
                }

                if (cleanPhone.length() < 10) {
                    throw BusinessLogicPizzaPorcionException.report("El número de teléfono debe tener al menos 10 caracteres.");
                }

                if (cleanPhone.length() > 15) {
                    throw BusinessLogicPizzaPorcionException.report("El número de teléfono no puede tener más de 15 caracteres.");
                }

                if (!UtilText.getInstance().containsOnlyNumbers(phoneNumber)) {
                    throw BusinessLogicPizzaPorcionException.report("El número de teléfono solo puede contener números.");
                }
            }

            private void validateCustomerWithSameIdentificationNumberDoesNotExist(String identificationNumber) throws PizzaPorcionException {
                var filter = new CustomerEntity();
                filter.setIdentificationNumber(identificationNumber);

                var resultsList = factory.getCustomerDAO().listByFilter(filter);

                if (!resultsList.isEmpty()) {
                    throw BusinessLogicPizzaPorcionException.report("Ya existe un cliente con el número de identificación: " + identificationNumber);
                }
            }

            private void validateCustomerWithSamePhoneNumberDoesNotExist(String phoneNumber) throws PizzaPorcionException {
                var filter = new CustomerEntity();
                filter.setPhoneNumber(phoneNumber);

                var resultsList = factory.getCustomerDAO().listByFilter(filter);

                if (!resultsList.isEmpty()) {
                    throw BusinessLogicPizzaPorcionException.report("Ya existe un cliente con el número de télefono: " + phoneNumber);
                }
            }

            private UUID generateIdNewCustomer() throws PizzaPorcionException {
                UUID newId;
                boolean existId;

                do {
                    newId = UtilUUID.generateNewUUID();
                    var customer = factory.getCustomerDAO().listById(newId);
                    existId = !UtilUUID.isDefaultValue(customer.getId());
                } while (existId);

                return newId;
            }

            @Override
            public void updateExistingCustomer(UUID id, CustomerDomain customer) throws PizzaPorcionException {
                validateIntegrityInformationToUpdateCustomer(customer);
                validateCustomerWithSamePhoneNumberDoesNotExist(customer.getPhoneNumber());

                var customerEntity = CustomerEntityAssembler.getInstance().toEntity(customer);
                factory.getCustomerDAO().update(id, customerEntity);
            }

            private void validateIntegrityInformationToUpdateCustomer(CustomerDomain customer) throws PizzaPorcionException {
                validateIntegrityCustomerName(customer.getName());
                validateIntegrityCustomerLastName(customer.getLastname());
                validateIntegrityCustomerPhoneNumber(customer.getPhoneNumber());
            }

            @Override
            public CustomerDomain getCustomerById(UUID id) throws PizzaPorcionException {
                var customerEntity = factory.getCustomerDAO().listById(id);
                return CustomerEntityAssembler.getInstance().toDomain(customerEntity);
            }

            @Override
            public List<CustomerDomain> getCustomerByFilter(CustomerDomain customer) throws PizzaPorcionException {
                CustomerEntity filter = new CustomerEntity();
                filter = CustomerEntityAssembler.getInstance().toEntity(customer);

                var resultsList = factory.getCustomerDAO().listByFilter(filter);

                if (resultsList.isEmpty()) {
                    return null;
                }

                return CustomerEntityAssembler.getInstance().toDomain(resultsList);
            }

            @Override
            public List<CustomerDomain> getCustomers() throws PizzaPorcionException {
                var customersEntities = factory.getCustomerDAO().listAll();
                return CustomerEntityAssembler.getInstance().toDomain(customersEntities);
            }
        }

        //1. Validar que la información sea consistente a nivel de Tipo de Dato,
        //longitud, obligatoriedad, formato, rango, reglas propias del objeto

        //2. Validar que no exista previamente otro usuario con el mismo tipo y número
        // de identificación

        //3. Validar que no exista previamente otro usuario con el mismo correo electronico

        //4. Validar que no exista previamente otro usuario con el mismo número de telefono celular

        //5. Generar el nuevo identificador unico para el usuario, asegurando que no exista

        var id = UUIDHelper.getUUIDHelper().generateNewUUID();
        var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);

        userEntity.setId(id);

        //6. Registrar la información del nuevo usuario

        daoFactory.getUserDAO().create(userEntity);

    }

    @Override
    public void dropUserInformation(UUID id) {

    }

    @Override
    public void updateUserInformation(UUID id, UserDomain userDomain) {

    }

    @Override
    public List<UserDomain> findAllUsers() {
        return List.of();
    }

    @Override
    public List<UserDomain> findUsersByFilter(UserDomain userFilters) {
        return List.of();
    }

    @Override
    public UserDomain findSpecificUser(UUID id) {
        return null;
    }

    @Override
    public void confirmMobileNumber(UUID id, int confirmationCode) {

    }

    @Override
    public void confirmEmail(UUID id) {

    }

    @Override
    public void sendMobileNumberConfirmation(UUID id) {

    }

    @Override
    public void sendEmailConfirmation(UUID id) {

    }
}
