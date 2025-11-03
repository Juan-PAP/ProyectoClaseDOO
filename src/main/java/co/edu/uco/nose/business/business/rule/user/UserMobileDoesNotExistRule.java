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

public class UserMobileDoesNotExistRule implements Rule {

    private static final Rule instance = new UserMobileDoesNotExistRule();

    private UserMobileDoesNotExistRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumUserRule.USER_MOBILE_DOES_NOT_EXIST_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumUserRule.USER_MOBILE_DOES_NOT_EXIST_RULE_DATA_IS_NULL.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length < 2) {
            var userMessage = MessagesEnumUserRule.USER_MOBILE_DOES_NOT_EXIST_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumUserRule.USER_MOBILE_DOES_NOT_EXIST_RULE_DATA_LENGTH_INVALID.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        var mobile = (String) data[0];
        var daoFactory = (DAOFactory) data[1];

        var userFilter = new UserEntity();

        userFilter.setMobileNumber(mobile);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(userFilter);

        var user = results.stream()
                .findFirst()
                .orElse(new UserEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(user.getId())) {
            var userMessage = MessagesEnumUserRule.USER_MOBILE_DOES_NOT_EXIST_RULE_USER_ALREADY_EXISTS.getTitle();
            var technicalMessage = TextHelper.format(
                    MessagesEnumUserRule.USER_MOBILE_DOES_NOT_EXIST_RULE_USER_ALREADY_EXISTS.getContent(),
                    mobile
            );
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}