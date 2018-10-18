package by.epam.university.service;

import by.epam.university.model.Faculty;
import by.epam.university.service.exception.ServiceException;

import java.util.List;

/**
 * Works with faculty operations.
 */
public interface FacultyService {

    /**
     * Gets the names of subjects that are submitted.
     * for admission to the faculty and specialities
     * of the faculty and put them into {@link Faculty} instance
     * @param id faculty id
     * @return {@link Faculty} instance containing all the necessary data
     * @throws ServiceException
     *             the service layer exception
     */
    Faculty fillFacultyInfo(String id) throws ServiceException;

    /**
     * Gets the recruitment plan of the faculty
     * and the number of submitted applications
     * for the faculty and put them into {@link Faculty} instance.
     * @param id faculty id
     * @throws ServiceException
     *             the service layer exception
     */
    Faculty fillFacultyRecruitmentInfo(String id)
            throws ServiceException;

    /**
     * Creates a list of the ids of all the faculties.
     * @return list of faculty ids
     * @throws ServiceException
     *             the service layer exception
     */
    List<String> createListOfIds() throws ServiceException;
}
