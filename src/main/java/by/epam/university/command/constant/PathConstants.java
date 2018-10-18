package by.epam.university.command.constant;

/**
 * Stores the names of paths to jsp-files.
 */
public final class PathConstants {

    public static final String MAIN_PAGE = "path.page.main";
    public static final String LOGIN_PAGE = "path.page.login";
    public static final String ADMIN = "path.page.admin";
    public static final String ENTRANT = "path.page.entrant";
    public static final String ERROR_PAGE = "path.page.error";
    public static final String REGISTRATION = "path.page.registration";
    public static final String SUCCESS_PAGE = "path.page.success-page";
    public static final String EDIT_PERSONAL_DATA = "path.page.edit-personal-info";
    public static final String SET_APPLICATION_DATA = "path.page.fill-out-application";
    public static final String MANAGE_APPLICATIONS = "path.page.manage-applications";
    public static final String VIEW_CERTIFICATE = "path.page.view-certificate";
    public static final String COMPETITION_MENU = "path.page.competition-menu";

    public static final String TEST = "path.page.test";

    /**
     * Prevents getting an instance of this class.
     */
    private PathConstants() {
        throw new AssertionError(
                "Getting instance of this class is not allowed!");
    }
}
