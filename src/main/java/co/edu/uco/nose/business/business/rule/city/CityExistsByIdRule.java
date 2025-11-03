package co.edu.uco.nose.business.business.rule.city;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.crosscuting.messagescatalog.business.rule.city.MessagesEnumCityRule;

import java.util.UUID;

public class CityExistsByIdRule implements Rule {

    private static final Rule instance = new CityExistsByIdRule();

    private CityExistsByIdRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }


    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)){
            var userMessage = MessagesEnumCityRule.CITY_EXISTS_BY_ID_RULE_DATA_IS_NULL.getTitle();
            var technicalMessage = MessagesEnumCityRule.CITY_EXISTS_BY_ID_RULE_DATA_IS_NULL.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
        if (data.length < 2){
            var userMessage = MessagesEnumCityRule.CITY_EXISTS_BY_ID_RULE_DATA_LENGTH_INVALID.getTitle();
            var technicalMessage = MessagesEnumCityRule.CITY_EXISTS_BY_ID_RULE_DATA_LENGTH_INVALID.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }

        var id = (UUID) data[0];
        var daoFactory = (DAOFactory) data[1];

        var city = daoFactory.getCityDAO().findById(id);

        if (ObjectHelper.isNull(city) ||UUIDHelper.getUUIDHelper().isDefaultUUID(city.getId())) {
            var userMessage = MessagesEnumCityRule.CITY_EXISTS_BY_ID_RULE_CITY_NOT_FOUND.getTitle();
            var technicalMessage = TextHelper.format(
                    MessagesEnumCityRule.CITY_EXISTS_BY_ID_RULE_CITY_NOT_FOUND.getContent(),
                    id.toString()
            );
            throw NoseException.create(userMessage,technicalMessage);
        }

    }

}