package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.business.rule.generics.MessagesEnumGenericRule;

public final class StringFormatValuesIsValidRule implements Rule {

    private static final Rule instance = new StringFormatValuesIsValidRule();

    private StringFormatValuesIsValidRule() {
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGenericRule.STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumGenericRule.STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_IS_NULL.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length < 4) {
            var userMessage = MessagesEnumGenericRule.STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumGenericRule.STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        var stringData = (String) data[0];
        var dataName = (String) data[1];
        var regex = (String) data[2];
        var mustApplyTrim = (Boolean) data[3];

        var dataToValidate = (mustApplyTrim)
                ? TextHelper.getDefaultWithTrim(stringData)
                : stringData;

        if (!TextHelper.isEmpty(dataToValidate)) {

            if (!TextHelper.matchesRegex(dataToValidate, regex)) {
                var userMessage = TextHelper.format(
                        MessagesEnumGenericRule.STRING_FORMAT_VALUES_IS_VALID_RULE_FORMAT_IS_INVALID.getTitle(),
                        dataName
                );
                var technicalMessage = TextHelper.format(
                        MessagesEnumGenericRule.STRING_FORMAT_VALUES_IS_VALID_RULE_FORMAT_IS_INVALID.getContent(),
                        dataName, regex
                );
                throw NoseException.create(userMessage, technicalMessage);
            }
        }
    }
}