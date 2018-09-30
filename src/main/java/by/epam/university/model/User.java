package by.epam.university.model;

/**
 * Entity-class containing user information.
 */
public class User {

    /**
     * User's id.
     */
    private int id;

    /**
     * User's login.
     */
    private String login;
    /**
     * User's password.
     */
    private String password;

    /**
     * User's name.
     */
    private String name;

    /**
     * User's middlename.
     */
    private String middlename;

    /**
     * User's surname.
     */
    private String surname;

    /**
     * User's email address.
     */
    private String email;

    /**
     * User's phone number.
     */
    private String phone;

    /**
     * User's role.
     */
    private Role role;
    /**
     * This variable shows is an entrant enlisted.
     */
    private boolean isEnlisted;

    /**
     * Average score of the entrant's school certificate, multiplied by 10.
     */
    private int schoolCertificate;
    /**
     * Id of the speciality specialty for which the antrant is applying.
     */
    private String specialityId;

    /**
     * Instantiates a new User instance.
     */
    public User() {
    }

    /**
     * Instantiates a new User instance.
     * @param userId    user's id.
     * @param log       user's login.
     * @param pWord     user's password.
     * @param nm        user's name.
     * @param midName   user's middlename.
     * @param sName     user's surname.
     * @param mail      user's email address.
     * @param tel       user's phone number.
     * @param rl        user's role.
     */
    public User(final int userId,
                final String log,
                final String pWord,
                final String nm,
                final String midName,
                final String sName,
                final String mail,
                final String tel,
                final Role rl) {
        id = userId;
        login = log;
        password = pWord;
        name = nm;
        middlename = midName;
        surname = sName;
        email = mail;
        phone = tel;
        role = rl;
    }

    /**
     * Gets user's id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets user's id.
     * @param userId user's id.
     */
    public void setId(final int userId) {
        id = userId;
    }

    /**
     * Gets user's login.
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets user's login.
     * @param log user's login.
     */
    public void setLogin(final String log) {
        login = log;
    }

    /**
     * Gets user's password.
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user's password.
     * @param pWord is user's password.
     */
    public void setPassword(final String pWord) {
        password = pWord;
    }

    /**
     * Gets user's name.
     * @return user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets user's name.
     * @param nm user's name.
     */
    public void setName(final String nm) {
        name = nm;
    }

    /**
     * Gets user's middlename.
     * @return user's middlename.
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * Sets user's middlename.
     * @param midName user's middlename.
     */
    public void setMiddlename(final String midName) {
        middlename = midName;
    }

    /**
     * Gets user's surname.
     * @return user's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets user's surname.
     * @param sName user's surname.
     */
    public void setSurname(final String sName) {
        surname = sName;
    }

    /**
     * Gets user's email.
     * @return user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets user's email.
     * @param mail user's email.
     */
    public void setEmail(final String mail) {
        email = mail;
    }

    /**
     * Gets user's phone number.
     * @return user's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets user's phone number.
     * @param tel user's phone number.
     */
    public void setPhone(final String tel) {
        phone = tel;
    }

    /**
     * Gets user's role.
     * @return user's role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets user's role.
     * @param rl user's role.
     */
    public void setRole(final Role rl) {
        role = rl;
    }

    /**
     * Gets isEnlisted variable.
     * @return isEnlisted.
     */
    public boolean isEnlisted() {
        return isEnlisted;
    }

    /**
     * Sets isEnlisted variable.
     * @param enlisted isEnlisted variable.
     */
    public void setEnlisted(final boolean enlisted) {
        isEnlisted = enlisted;
    }

    /**
     * Gets an average score of the entrant's school certificate.
     * @return schoolCertificate.
     */
    public int getSchoolCertificate() {
        return schoolCertificate;
    }

    /**
     * Sets school certificate value.
     * @param certificate an average score
     *                          of the entrant's school certificate.
     */
    public void setSchoolCertificate(final int certificate) {
        schoolCertificate = certificate;
    }

    /**
     * Gets id of the faculty where the entrant is registered.
     * @return specialityId.
     */
    public String getSpecialityId() {
        return specialityId;
    }

    /**
     * Sets id of the faculty.
     * @param userId id of the faculty where the entrant is registered.
     */
    public void setSpecialityId(final String userId) {
        this.specialityId = specialityId;
    }
}

