package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.CountryDTOAssembler;
import co.edu.uco.nose.business.business.impl.CountryBusinessImpl;
import co.edu.uco.nose.business.domain.CountryDomain;
import co.edu.uco.nose.business.facade.CountryFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.CountryDTO;

import java.util.List;
import java.util.UUID;

public final class CountryFacadeImpl implements CountryFacade {

    @Override
    public List<CountryDTO> findAllCountries() {

        var daoFactory = DAOFactory.getFactory();
        var business = new CountryBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            List<CountryDomain> domainList = business.findAllCountries();

            return CountryDTOAssembler.getCountryDTOAssembler().toDTO(domainList);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Error al consultar la información de los países.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar los países: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<CountryDTO> findCountriesByFilter(CountryDTO countryFilters) {

        var daoFactory = DAOFactory.getFactory();
        var business = new CountryBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var filterDomain = CountryDTOAssembler.getCountryDTOAssembler().toDomain(countryFilters);

            List<CountryDomain> domainList = business.findCountriesByFilter(filterDomain);

            return CountryDTOAssembler.getCountryDTOAssembler().toDTO(domainList);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Error al consultar la información de los países con los filtros suministrados.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar los países con los filtros suministrados: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public CountryDTO findSpecificCountry(UUID id) {

        var daoFactory = DAOFactory.getFactory();
        var business = new CountryBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            CountryDomain domain = business.findSpecificCountry(id);

            return CountryDTOAssembler.getCountryDTOAssembler().toDTO(domain);

        } catch (final NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Error al consultar la información del país solicitado.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar el país solicitado: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }
}
