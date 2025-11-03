package co.edu.uco.nose.business.business.rule.validator.user;

import co.edu.uco.nose.business.business.rule.user.UserDoesNotExistWithSameIdNumberAndIdTypeRule; // Importamos la regla
import co.edu.uco.nose.business.business.rule.validator.Validator;

public class ValidateUserDoesNotExistWithSameIdNumberAndIdType implements Validator {

    private static final Validator instance = new ValidateUserDoesNotExistWithSameIdNumberAndIdType();

    private ValidateUserDoesNotExistWithSameIdNumberAndIdType() {
    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {
        UserDoesNotExistWithSameIdNumberAndIdTypeRule.executeRule(data);
    }
}