package by.epam.university.dao;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.Certificate;
import by.epam.university.model.ExamGrade;

/**
 * Defines methods for working with certificate.
 */
public interface CertificateDAO {

    /**
     * Adds entrant's grades to data base.
     * @param userId id of entrant whose certificate
     *               should be added to the data base
     * @param grade {@link ExamGrade} instance containing
     *                               entrant's grade for exam
     * @return the id of added certificate
     * @throws DAOException
     *             the exception during getting connection with data base
     *             or during working with data base
     */
    int addData(int userId, ExamGrade grade) throws DAOException;

    /**
     * Sets the value of user's certificate grade in the data base.
     * @param id id or certificate
     * @param grade the grade value
     * @throws DAOException
     *             the exception during getting connection with data base
     *             or during working with data base
     */
    void setGrade(int id, int grade) throws DAOException;

    /**
     * Gets user's exam certificate by user's id and put it
     * into {@link Certificate} instance.
     * @param certificate input {@link Certificate} instance
     *                    that contains user id
     * @throws DAOException
     *             the exception during getting connection with data base
     *             or during working with data base
     */
    void putExamGrades(Certificate certificate) throws DAOException;
}
