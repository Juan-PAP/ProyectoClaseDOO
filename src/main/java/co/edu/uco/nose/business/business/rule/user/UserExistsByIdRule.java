// Archivo: co/edu/uco/nose/business/business/rule/user/UserExistsByIdRule.java
package co.edu.uco.nose.business.business.rule.user;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.crosscuting.messagescatalog.business.rule.user.MessagesEnumUserRule;

import java.util.UUID;

public class UserExistsByIdRule implements Rule {

    private static final Rule instance = new UserExistsByIdRule();

    private UserExistsByIdRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }


    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)){
            var userMessage = MessagesEnumUserRule.USER_EXISTS_BY_ID_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumUserRule.USER_EXISTS_BY_ID_RULE_DATA_IS_NULL.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
        if (data.length < 2){
            var userMessage = MessagesEnumUserRule.USER_EXISTS_BY_ID_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumUserRule.USER_EXISTS_BY_ID_RULE_DATA_LENGTH_INVALID.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        var id = (UUID) data[0];
        var daoFactory = (DAOFactory) data[1];

        var user = daoFactory.getUserDAO().findById(id);

        if (ObjectHelper.isNull(user) || UUIDHelper.getUUIDHelper().isDefaultUUID(user.getId())) {

            var userMessage = MessagesEnumUserRule.USER_EXISTS_BY_ID_RULE_USER_NOT_FOUND.getTitle();
            var technicalMessage = TextHelper.format(
                    MessagesEnumUserRule.USER_EXISTS_BY_ID_RULE_USER_NOT_FOUND.getContent(),
                    id.toString()
            );
            throw NoseException.create(userMessage,technicalMessage);
        }


    }
}