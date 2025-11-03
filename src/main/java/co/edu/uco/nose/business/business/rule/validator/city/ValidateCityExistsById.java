package co.edu.uco.nose.business.business.rule.validator.city;

import co.edu.uco.nose.business.business.rule.city.CityExistsByIdRule;
import co.edu.uco.nose.business.business.rule.validator.Validator;

public class ValidateCityExistsById implements Validator {

    private static final Validator instance = new ValidateCityExistsById();

    private ValidateCityExistsById() {

    }

    public static void executeValidation(final Object... data) {
        instance.validate(data);
    }


    @Override
    public void validate(Object... data) {

        CityExistsByIdRule.executeRule(data);

    }
}
