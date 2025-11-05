package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.business.rule.generics.MessagesEnumGenericRule;

public final class StringLengthValuesIsValidRule implements Rule {

    private static final Rule instance = new StringLengthValuesIsValidRule();

    private StringLengthValuesIsValidRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)){
            var userMessage = MessagesEnumGenericRule.STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumGenericRule.STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_IS_NULL.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
        if (data.length < 5){
            var userMessage = MessagesEnumGenericRule.STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumGenericRule.STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        var StringData = (String) data[0];
        var dataName = (String) data[1];
        int minLength = (Integer) data[2];
        int maxLength = (Integer) data[3];
        boolean mustApplyTrim = (Boolean) data[4];

        if (!TextHelper.lengthIsValid(StringData,minLength, maxLength, mustApplyTrim)) {

            var minLengthStr = String.valueOf(minLength);
            var maxLengthStr = String.valueOf(maxLength);

            var userMessage = TextHelper.format(
                    MessagesEnumGenericRule.STRING_LENGTH_VALUES_IS_VALID_RULE_LENGTH_IS_INVALID.getTitle(),
                    dataName, minLengthStr, maxLengthStr
            );
            var technicalMessage = TextHelper.format(
                    MessagesEnumGenericRule.STRING_LENGTH_VALUES_IS_VALID_RULE_LENGTH_IS_INVALID.getContent(),
                    dataName, minLengthStr, maxLengthStr
            );
            throw NoseException.create(userMessage, technicalMessage);
        };
    }
}