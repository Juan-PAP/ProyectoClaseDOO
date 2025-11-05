package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.business.rule.generics.MessagesEnumGenericRule;

import java.util.UUID;

public final class UUIDValueIsPresentRule implements Rule {

    private static final Rule instance = new UUIDValueIsPresentRule();

    private UUIDValueIsPresentRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGenericRule.UUID_VALUE_IS_PRESENT_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumGenericRule.UUID_VALUE_IS_PRESENT_RULE_DATA_IS_NULL.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = MessagesEnumGenericRule.UUID_VALUE_IS_PRESENT_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumGenericRule.UUID_VALUE_IS_PRESENT_RULE_DATA_LENGTH_INVALID.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        var uuid = (UUID) data[0];
        var dataName = (String) data[1];

        if (ObjectHelper.isNull(uuid)) {
            var userMessage = TextHelper.format(
                    MessagesEnumGenericRule.UUID_VALUE_IS_PRESENT_RULE_UUID_IS_NULL.getTitle(),
                    dataName
            );
            var technicalMessage = TextHelper.format(
                    MessagesEnumGenericRule.UUID_VALUE_IS_PRESENT_RULE_UUID_IS_NULL.getContent(),
                    dataName
            );
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (UUIDHelper.getUUIDHelper().isDefaultUUID(uuid)) {
            var userMessage = TextHelper.format(
                    MessagesEnumGenericRule.UUID_VALUE_IS_PRESENT_RULE_UUID_IS_DEFAULT.getTitle(),
                    dataName
            );
            var technicalMessage = TextHelper.format(
                    MessagesEnumGenericRule.UUID_VALUE_IS_PRESENT_RULE_UUID_IS_DEFAULT.getContent(),
                    dataName
            );
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}