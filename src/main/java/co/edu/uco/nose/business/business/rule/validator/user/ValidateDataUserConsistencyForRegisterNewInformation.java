package co.edu.uco.nose.business.business.rule.validator.user;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.business.business.rule.generics.StringLengthValuesIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringValuelsPresentRule;
import co.edu.uco.nose.business.business.rule.validator.Validator;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public class ValidateDataUserConsistencyForRegisterNewInformation implements Validator {

    private static final Validator instance = new ValidateDataUserConsistencyForRegisterNewInformation();

    private ValidateDataUserConsistencyForRegisterNewInformation() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data) {

        //Validaciones del objeto data
        var userDomainData = (UserDomain) data [0];

        //Valid empty data
        validatEmptyData(userDomainData);

        //Valid data length
        validatDataLength(userDomainData);

        //Valid data format
        //Valid data valid range
    }

    private void validatEmptyData (final UserDomain data) {

        StringValuelsPresentRule.executeRule(data.getIdNumber(), "número de identificación", true);
        StringValuelsPresentRule.executeRule(data.getFirstName(), "primer nombre", true);
        StringValuelsPresentRule.executeRule(data.getFirstSurname(), "primer apellido", true);

        //Continue with other sttributes validation
    }

    private void validatDataLength (final UserDomain data) {

        StringLengthValuesIsValidRule.executeRule(data.getIdNumber(), "número de identificación", 1, 50, true);
        StringLengthValuesIsValidRule.executeRule(data.getFirstName(), "primer nombre", 1, 100, true);

        if (TextHelper.isEmptyWithTrim(data.getSecondName())){
            StringLengthValuesIsValidRule.executeRule(data.getSecondName(), "segundo nombre", 1, 100, false);
        }

        StringLengthValuesIsValidRule.executeRule(data.getFirstSurname(), "primer apellido", 1, 100, true);

        if (TextHelper.isEmptyWithTrim(data.getSecondSurname())) {
            StringLengthValuesIsValidRule.executeRule(data.getSecondSurname(), "segundo apellido", 1, 100, false);
        }

        //Continue with other sttributes validation
    }
}
