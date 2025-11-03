package co.edu.uco.nose.business.business.rule.validator.user;

import co.edu.uco.nose.business.business.rule.user.UserMobileDoesNotExistRule;
import co.edu.uco.nose.business.business.rule.validator.Validator;

public class ValidateDataUserMobileDoesNotExist implements Validator {

    private static final Validator instance = new ValidateDataUserMobileDoesNotExist();

    private ValidateDataUserMobileDoesNotExist() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {
        UserMobileDoesNotExistRule.executeRule(data);
    }
}
