package co.edu.uco.nose.business.business.rule.validator.user;

import co.edu.uco.nose.business.business.rule.user.UserEmailDoesNotExistRule;
import co.edu.uco.nose.business.business.rule.validator.Validator;

public class ValidateDataUserEmailDoesNotExist implements Validator {

    private static final Validator instance = new ValidateDataUserEmailDoesNotExist();

    private ValidateDataUserEmailDoesNotExist() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {

        UserEmailDoesNotExistRule.executeRule(data);

    }
}
