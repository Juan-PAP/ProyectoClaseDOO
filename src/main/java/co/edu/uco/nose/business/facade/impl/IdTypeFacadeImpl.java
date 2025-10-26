package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.IdTypeDTOAssembler;
import co.edu.uco.nose.business.business.impl.IdTypeBusinessImpl;
import co.edu.uco.nose.business.domain.IdTypeDomain;
import co.edu.uco.nose.business.facade.IdTypeFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.IdTypeDTO;

import java.util.List;
import java.util.UUID;

public final class IdTypeFacadeImpl implements IdTypeFacade {

    @Override
    public List<IdTypeDTO> findAllIdTypes() {

        var daoFactory = DAOFactory.getFactory();
        var business = new IdTypeBusinessImpl(daoFactory);

        try {

            List<IdTypeDomain> domainList = business.findAllIdTypes();

            return IdTypeDTOAssembler.getIdTypeDTOAssembler().toDTO(domainList);

        } catch (final NoseException exception) {
            throw exception;

        } catch (final Exception exception) {
            var userMessage = "Error al consultar la información de los tipos de identificación.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar los tipos de identificación: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<IdTypeDTO> findIdTypesByFilter(IdTypeDTO idTypeFilters) {

        var daoFactory = DAOFactory.getFactory();
        var business = new IdTypeBusinessImpl(daoFactory);

        try {

            var filterDomain = IdTypeDTOAssembler.getIdTypeDTOAssembler().toDomain(idTypeFilters);

            List<IdTypeDomain> domainList = business.findIdTypesByFilter(filterDomain);

            return IdTypeDTOAssembler.getIdTypeDTOAssembler().toDTO(domainList);

        } catch (final NoseException exception) {
            throw exception;

        } catch (final Exception exception) {
            var userMessage = "Error al consultar la información de los tipos de identificación con los filtros suministrados.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar los tipos de identificación con los filtros suministrados: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public IdTypeDTO findSpecificIdType(UUID id) {

        var daoFactory = DAOFactory.getFactory();
        var business = new IdTypeBusinessImpl(daoFactory);

        try {

            IdTypeDomain domain = business.findSpecificIdType(id);

            return IdTypeDTOAssembler.getIdTypeDTOAssembler().toDTO(domain);

        } catch (final NoseException exception) {
            throw exception;

        } catch (final Exception exception) {
            var userMessage = "Error al consultar la información del tipo de identificación específico.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar el tipo de identificación específico: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }
}
