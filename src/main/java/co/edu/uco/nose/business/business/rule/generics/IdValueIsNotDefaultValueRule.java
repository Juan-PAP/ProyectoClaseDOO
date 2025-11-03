package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.business.rule.generics.MessagesEnumGenericRule;

import java.util.UUID;

public class IdValueIsNotDefaultValueRule implements Rule {

    private static final Rule instance = new IdValueIsNotDefaultValueRule();

    private IdValueIsNotDefaultValueRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumGenericRule.ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumGenericRule.ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_IS_NULL.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = MessagesEnumGenericRule.ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_LENGHT_INVALID.getTitle();
            var technicalMessage = MessagesEnumGenericRule.ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_LENGHT_INVALID.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        var uuid = (UUID) data[0];
        var dataName = (String) data[1];

        if (ObjectHelper.isNull(uuid)) {
            var userMessage = TextHelper.format(
                    MessagesEnumGenericRule.ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_NULL.getTitle(),
                    dataName
            );
            var technicalMessage = TextHelper.format(
                    MessagesEnumGenericRule.ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_NULL.getContent(),
                    dataName
            );
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (UUIDHelper.getUUIDHelper().isDefaultUUID(uuid)) {
            var userMessage = TextHelper.format(
                    MessagesEnumGenericRule.ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_DEFAULT.getTitle(),
                    dataName
            );
            var technicalMessage = TextHelper.format(
                    MessagesEnumGenericRule.ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_DEFAULT.getContent(),
                    dataName
            );
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}