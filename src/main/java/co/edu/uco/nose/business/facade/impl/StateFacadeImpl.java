package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.StateDTOAssembler;
import co.edu.uco.nose.business.business.impl.StateBusinessImpl;
import co.edu.uco.nose.business.domain.StateDomain;
import co.edu.uco.nose.business.facade.StateFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.StateDTO;

import java.util.List;
import java.util.UUID;

public final class StateFacadeImpl implements StateFacade {

    @Override
    public List<StateDTO> findAllStates() {

        var daoFactory = DAOFactory.getFactory();
        var business = new StateBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            List<StateDomain> domainList = business.findAllStates();

            return StateDTOAssembler.getStateDTOAssembler().toDTO(domainList);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        }
        catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Error al consultar la información de los estados.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar los estados: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<StateDTO> findStatesByFilter(StateDTO stateFilters) {

        var daoFactory = DAOFactory.getFactory();
        var business = new StateBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            var filterDomain = StateDTOAssembler.getStateDTOAssembler().toDomain(stateFilters);

            List<StateDomain> domainList = business.findStatesByFilter(filterDomain);

            return StateDTOAssembler.getStateDTOAssembler().toDTO(domainList);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        }
        catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Error al consultar la información de los estados con los filtros suministrados.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar los estados con los filtros suministrados: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public StateDTO findSpecificState(UUID id) {

        var daoFactory = DAOFactory.getFactory();
        var business = new StateBusinessImpl(daoFactory);

        try {

            daoFactory.initTransaction();

            StateDomain domain = business.findSpecificState(id);

            return StateDTOAssembler.getStateDTOAssembler().toDTO(domain);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        }
        catch (final Exception exception) {
            daoFactory.rollbackTransaction();
            var userMessage = "Error al consultar la información del estado específico.";
            var technicalMessage = "Se ha presentado un error inesperado al consultar el estado específico: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }
}
