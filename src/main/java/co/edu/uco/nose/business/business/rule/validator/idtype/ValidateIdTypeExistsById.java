package co.edu.uco.nose.business.business.rule.validator.idtype;

import co.edu.uco.nose.business.business.rule.idtype.IdTypeExistsByIdRule;
import co.edu.uco.nose.business.business.rule.validator.Validator;

public class ValidateIdTypeExistsById implements Validator {

    private static final Validator instance = new ValidateIdTypeExistsById();

    private ValidateIdTypeExistsById() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }

    @Override
    public void validate(Object... data) {

        IdTypeExistsByIdRule.executeRule(data);

    }
}
