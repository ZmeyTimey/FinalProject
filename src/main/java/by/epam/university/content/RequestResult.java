package by.epam.university.content;

import java.util.Objects;

/**
 * Contains the page to which {@code sendRedirect()}
 * or {@code forward()} will be performed,
 * the latter is defined by {@code navigationType}.
 *
 */
public class RequestResult {

    /**
     * The page which the app navigate to.
     */
    private String page;
    /**
     * A type of navigation to the page.
     */
    private NavigationType navigationType;

    /**
     * Instantiates a new RequestResult instance.
     * @param type navigation type.
     * @param pg page we should navigate to.
     */
    public RequestResult(final NavigationType type,
                         final String pg) {
        navigationType = type;
        page = pg;
    }

    /**
     * Gets navigation type.
     * @return navigation type.
     */
    public NavigationType getNavigationType() {
        return navigationType;
    }

    /**
     * Sets navigation type.
     * @param type navigation type.
     */
    public void setNavigationType(final NavigationType type) {
        navigationType = type;
    }

    /**
     * Gets page.
     * @return page.
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets page.
     * @param pg page.
     */
    public void setPage(final String pg) {
        page = pg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        RequestResult result = (RequestResult) obj;
        return Objects.equals(page, result.page)
                && Objects.equals(navigationType, result.navigationType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(page, navigationType);
    }
}
