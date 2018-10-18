package by.epam.university.dao.impl;

import by.epam.university.dao.AbstractDAO;
import by.epam.university.dao.AuxiliaryDAO;
import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SubjectDAO;
import by.epam.university.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for working with 'subject' data base.
 */
public class SubjectDAOImpl extends AbstractDAO implements SubjectDAO {

    /**
     * {@link Logger} instance for logging.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(CertificateDAOImpl.class);

////////////////////////////////////////////////////////////////////////////////

    private static final String GET_SUBJECT_NAME
            = "SELECT `subject_name` FROM `subject` WHERE `id`=?";

    private static final String GET_SUBJECT_ID
            = "SELECT `id` FROM `subject` WHERE `subject_name`=?";

////////////////////////////////////////////////////////////////////////////////

    /**
     * The name of entity 'Subject'.
     */
    private static final String SUBJECT = "subject";

    /**
     * {@inheritDoc}
     */
    public int getIdByName(final String name) throws DAOException {

        AuxiliaryDAO auxiliaryDAO
            = DAOFactory.getInstance().getAuxiliaryDAO();

        return auxiliaryDAO.getIdByName(name, GET_SUBJECT_ID, SUBJECT);
    }

    /**
     * {@inheritDoc}
     */
    public String getNameById(final int id) throws DAOException {

        AuxiliaryDAO auxiliaryDAO
                = DAOFactory.getInstance().getAuxiliaryDAO();

        return auxiliaryDAO.getNameById(id, GET_SUBJECT_NAME, SUBJECT);
    }
}
