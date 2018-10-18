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
    private Boolean isEnlisted;

    /**
     * This variable shows is user sent an application
     * for admission to the university.
     */
    private Boolean isApplicationSent;

    /**
     * This variable shows is user's application confirmed by administrator.
     */
    private Boolean isApplicationConfirmed;

    /**
     * Id of the faculty to which the user is registered.
     */
    private String facultyId;

    /**
     * The specialty for which the entrant is applying.
     */
    private Speciality speciality;

    /**
     * Certificate which stores entrant's grades.
     */
    private Certificate certificate;

    /**
     * Instantiates a new User instance.
     */
    public User() {
    }

    /**
     * Instantiates a new User instance.
     * @param userId    user's id
     * @param log       user's login
     * @param pWord     user's password
     * @param nm        user's name
     * @param midName   user's middlename
     * @param sName     user's surname
     * @param mail      user's email address
     * @param tel       user's phone number
     * @param rl        user's role
     * @param flt       faculty id
     * @param spec      speciality
     * @param cert      user's certificate
     */
    public User(final int userId,
                final String log,
                final String pWord,
                final String nm,
                final String midName,
                final String sName,
                final String mail,
                final String tel,
                final Role rl,
                final String flt,
                final Speciality spec,
                final Certificate cert) {
        id = userId;
        login = log;
        password = pWord;
        name = nm;
        middlename = midName;
        surname = sName;
        email = mail;
        phone = tel;
        role = rl;
        facultyId = flt;
        speciality = spec;
        certificate = cert;
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
     * Gets {@code isEnlisted}.
     * @return boolean value of the variable.
     */
    public Boolean isEnlisted() {
        return isEnlisted;
    }

    /**
     * Sets {@code isEnlisted}.
     * @param enlisted input boolean value.
     */
    public void setEnlisted(final Boolean enlisted) {
        isEnlisted = enlisted;
    }

    /**
     * Gets {@code isApplicationSent}.
     * @return boolean value of the variable
     */
    public Boolean isApplicationSent() {
        return isApplicationSent;
    }

    /**
     * Sets {@code isApplicationSent}.
     * @param applSent input boolean value
     */
    public void setApplicationSent(final Boolean applSent) {
        isApplicationSent = applSent;
    }

    /**
     * Gets {@code isApplicationConfirmed}.
     * @return boolean value of the variable
     */
    public Boolean getIsApplicationConfirmed() {
        return isApplicationConfirmed;
    }

    /**
     * Sets {@code isApplicationConfirmed}.
     * @param applConf input boolean value
     */
    public void setApplicationConfirmed(final Boolean applConf) {
        isApplicationConfirmed = applConf;
    }

    /**
     * Gets user's certificate.
     * @return certificate.
     */
    public Certificate getCertificate() {
        return certificate;
    }

    /**
     * Sets user's certificate.
     * @param cert certificate which contains user's grades.
     */
    public void setCertificate(final Certificate cert) {
        certificate = cert;
    }

    /**
     * Gets the id of the faculty where the entrant is registered.
     * @return facultyId.
     */
    public String getFacultyId() {
        return facultyId;
    }

    /**
     * Sets the id of the faculty.
     * @param flt id the faculty where the entrant is registered.
     */
    public void setFacultyId(final String flt) {
        facultyId = flt;
    }

    /**
     * Gets the speciality chosen by the entrant.
     * @return speciality.
     */
    public Speciality getSpeciality() {
        return speciality;
    }

    /**
     * Sets the speciality.
     * @param spec of the speciality chosen by the entrant.
     */
    public void setSpeciality(final Speciality spec) {
        speciality = spec;
    }
}

