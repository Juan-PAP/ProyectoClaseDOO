package co.edu.uco.nose.business.business.rule.user;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.UserEntity;
import co.edu.uco.nose.crosscuting.messagescatalog.business.rule.user.MessagesEnumUserRule;

import java.util.List;

public class UserEmailDoesNotExistRule implements Rule {

    private static final Rule instance = new UserEmailDoesNotExistRule();

    private UserEmailDoesNotExistRule() {
    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumUserRule.USER_EMAIL_DOES_NOT_EXIST_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumUserRule.USER_EMAIL_DOES_NOT_EXIST_RULE_DATA_IS_NULL.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = MessagesEnumUserRule.USER_EMAIL_DOES_NOT_EXIST_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumUserRule.USER_EMAIL_DOES_NOT_EXIST_RULE_DATA_LENGTH_INVALID.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        var email = (String) data[0];
        var daoFactory = (DAOFactory) data[1];

        var userFilter = new UserEntity();
        userFilter.setEmail(email);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(userFilter);

        var user = results.stream()
                .findFirst()
                .orElse(new UserEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(user.getId())) {
            var userMessage = MessagesEnumUserRule.USER_EMAIL_DOES_NOT_EXIST_RULE_USER_ALREADY_EXISTS.getTitle();
            var technicalMessage = TextHelper.format(
                    MessagesEnumUserRule.USER_EMAIL_DOES_NOT_EXIST_RULE_USER_ALREADY_EXISTS.getContent(),
                    email
            );
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}