package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.CityDTOAssembler;
import co.edu.uco.nose.business.business.impl.CityBusinessImpl;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.business.facade.CityFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.CityDTO;

import java.util.List;
import java.util.UUID;

public final class CityFacadeImpl implements CityFacade {

    @Override
    public List<CityDTO> findAllCities() {

        var daoFactory = DAOFactory.getFactory();
        var business = new CityBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            List<CityDomain> domainList = business.findAllCities();

            return CityDTOAssembler.getCityDTOAssembler().toDTO(domainList);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Error al consultar la información de las ciudades.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar las ciudades: "
                    + exception.getMessage();
            throw  NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<CityDTO> findCitiesByFilter(CityDTO cityFilters) {

        var daoFactory = DAOFactory.getFactory();
        var business = new CityBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var filterDomain = CityDTOAssembler.getCityDTOAssembler().toDomain(cityFilters);

            List<CityDomain> domainList = business.findCitiesByFilter(filterDomain);

            return CityDTOAssembler.getCityDTOAssembler().toDTO(domainList);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Error al consultar la información de las ciudades con los filtros suministrados.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar las ciudades con los filtros suministrados: "
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }

    }

    @Override
    public CityDTO findSpecificCity(UUID id) {

        var daoFactory = DAOFactory.getFactory();
        var business = new CityBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            CityDomain domain = business.findSpecificCity(id);

            return CityDTOAssembler.getCityDTOAssembler().toDTO(domain);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Error al consultar la información de la ciudad específica.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar de la ciudad específica: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }
}
