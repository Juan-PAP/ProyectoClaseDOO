package co.edu.uco.nose.business.business.rule.user;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.UserEntity;
import co.edu.uco.nose.crosscuting.messagescatalog.business.rule.user.MessagesEnumUserRule;

import java.util.List;
import java.util.UUID;

public class UserDoesNotExistWithSameIdNumberAndIdTypeRule implements Rule {

    private static final Rule instance = new UserDoesNotExistWithSameIdNumberAndIdTypeRule();

    private UserDoesNotExistWithSameIdNumberAndIdTypeRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }

    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnumUserRule.USER_DOES_NOT_EXIST_WITH_SAME_ID_NUMBER_AND_ID_TYPE_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumUserRule.USER_DOES_NOT_EXIST_WITH_SAME_ID_NUMBER_AND_ID_TYPE_RULE_DATA_IS_NULL.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length < 3) {
            var userMessage = MessagesEnumUserRule.USER_DOES_NOT_EXIST_WITH_SAME_ID_NUMBER_AND_ID_TYPE_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumUserRule.USER_DOES_NOT_EXIST_WITH_SAME_ID_NUMBER_AND_ID_TYPE_RULE_DATA_LENGTH_INVALID.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        var idNumber = (String) data[0];
        var idTypeUuid = (UUID) data[1];
        var daoFactory = (DAOFactory) data[2];

        var idTypeFilter = new IdTypeEntity();
        idTypeFilter.setId(idTypeUuid);

        var userFilter = new UserEntity();
        userFilter.setIdNumber(idNumber);
        userFilter.setIdType(idTypeFilter);

        List<UserEntity> results = daoFactory.getUserDAO().findByFilter(userFilter);

        var user = results.stream()
                .findFirst()
                .orElse(new UserEntity());

        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(user.getId())) {
            var userMessage = MessagesEnumUserRule.USER_DOES_NOT_EXIST_WITH_SAME_ID_NUMBER_AND_ID_TYPE_RULE_USER_ALREADY_EXISTS.getTitle();
            var technicalMessage = TextHelper.format(
                    MessagesEnumUserRule.USER_DOES_NOT_EXIST_WITH_SAME_ID_NUMBER_AND_ID_TYPE_RULE_USER_ALREADY_EXISTS.getContent(),
                    idNumber, idTypeUuid.toString()
            );
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}