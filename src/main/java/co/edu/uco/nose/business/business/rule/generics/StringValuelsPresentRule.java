package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.business.rule.generics.MessagesEnumGenericRule;

public final class StringValuelsPresentRule implements Rule {

    private static final Rule instance = new StringValuelsPresentRule();

    private StringValuelsPresentRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data) {
        if (ObjectHelper.isNull(data)){
            var userMessage = MessagesEnumGenericRule.STRING_VALUELS_PRESENT_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumGenericRule.STRING_VALUELS_PRESENT_RULE_DATA_IS_NULL.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
        if (data.length < 3){
            var userMessage = MessagesEnumGenericRule.STRING_VALUELS_PRESENT_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumGenericRule.STRING_VALUELS_PRESENT_RULE_DATA_LENGTH_INVALID.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        var StringData = (String) data[0];
        var dataName = (String) data[1];
        boolean mustApplyTrim = (Boolean) data[2];

        if ((mustApplyTrim)
                ? TextHelper.isEmptyWithTrim(StringData) : TextHelper.isEmpty(StringData)) {
            var userMessage = TextHelper.format(
                    MessagesEnumGenericRule.STRING_VALUELS_PRESENT_RULE_DATA_IS_EMPTY.getTitle(),
                    dataName
            );
            var technicalMessage = TextHelper.format(
                    MessagesEnumGenericRule.STRING_VALUELS_PRESENT_RULE_DATA_IS_EMPTY.getContent(),
                    dataName
            );
            throw NoseException.create(userMessage, technicalMessage);
        };
    }
}