package co.edu.uco.nose.business.business.rule.validator.user;

import co.edu.uco.nose.business.business.rule.generics.IdValueIsNotDefaultValueRule;
import co.edu.uco.nose.business.business.rule.generics.StringFormatValuesIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringLengthValuesIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.nose.business.business.rule.validator.Validator;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public class ValidateDataUserConsistencyForRegisterNewInformation implements Validator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String MOBILE_PHONE_REGEX = "^[0-9]{1,20}$";

    private static final Validator instance = new ValidateDataUserConsistencyForRegisterNewInformation();

    private ValidateDataUserConsistencyForRegisterNewInformation() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data) {

        var userDomainData = (UserDomain) data [0];

        validatEmptyData(userDomainData);

        validatDataLength(userDomainData);

        validateDataFormat(userDomainData);

        validateDomainObjects(userDomainData);
    }

    private void validatEmptyData (final UserDomain data) {

        StringValueIsPresentRule.executeRule(data.getIdNumber(), "número de identificación", true);
        StringValueIsPresentRule.executeRule(data.getFirstName(), "primer nombre", true);
        StringValueIsPresentRule.executeRule(data.getFirstSurname(), "primer apellido", true);
        StringValueIsPresentRule.executeRule(data.getEmail(), "email", true);
        StringValueIsPresentRule.executeRule(data.getMobileNumber(), "teléfono movil", true);
    }

    private void validatDataLength (final UserDomain data) {

        StringLengthValuesIsValidRule.executeRule(data.getIdNumber(), "número de identificación", 1, 25, true);
        StringLengthValuesIsValidRule.executeRule(data.getFirstName(), "primer nombre", 1, 20, true);

        if (!TextHelper.isEmptyWithTrim(data.getSecondName())){
            StringLengthValuesIsValidRule.executeRule(data.getSecondName(), "segundo nombre", 1, 20, true);
        }

        StringLengthValuesIsValidRule.executeRule(data.getFirstSurname(), "primer apellido", 1, 20, true);

        if (!TextHelper.isEmptyWithTrim(data.getSecondSurname())) {
            StringLengthValuesIsValidRule.executeRule(data.getSecondSurname(), "segundo apellido", 1, 20, true);
        }

        StringLengthValuesIsValidRule.executeRule(data.getEmail(), "email", 1, 250, true);
        StringLengthValuesIsValidRule.executeRule(data.getMobileNumber(), "teléfono movil", 1, 20, true);
    }

    private void validateDataFormat(final UserDomain data) {
        StringFormatValuesIsValidRule.executeRule(data.getEmail(), "email", EMAIL_REGEX, true);
        StringFormatValuesIsValidRule.executeRule(data.getMobileNumber(), "teléfono movil", MOBILE_PHONE_REGEX, true);
    }

    private void validateDomainObjects(final UserDomain data) {
        IdValueIsNotDefaultValueRule.executeRule(data.getIdType().getId(), "Tipo de Identificación");
        IdValueIsNotDefaultValueRule.executeRule(data.getHomeCity().getId(), "Ciudad de Residencia");
    }
}
