package ca.ryerson.scs.cscu.display;

/**
 * Created by mitchellmohorovich on 15-08-29.
 * Since JSF doesn't allow iterating over maps with <ui:repeat>.
 */
public class Breadcrumb {
    private String name;
    private String url;
    private boolean active;

    Breadcrumb() {
        name = null;
        url = null;
        active = false;
    }

    Breadcrumb(String name, String url) {
        this();
        this.name = name;
        this.url = url;
    }

    Breadcrumb(String name, String url, Boolean active) {
        this(name, url);
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActiveClass() {
        return (this.isActive()) ? "active" : "";
    }
}
