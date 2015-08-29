package ca.ryerson.scs.cscu.display;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mitchellmohorovich on 15-08-28.
 */
@Named("breadcrumbBean")
@RequestScoped
public class BreadcrumbBean {
    List<Breadcrumb> breadcrumbs = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.addBreadcrumb(new Breadcrumb("Home", "/index.xhtml"));
    }

    public void addBreadcrumb(Breadcrumb breadcrumb) {
        this.breadcrumbs.add(breadcrumb);
    }

    public void addBreadcrumb(String name, String url) {
        this.breadcrumbs.add(new Breadcrumb(name, url));
    }

    public void addBreadcrumb(String name, String url, boolean active) {
        this.breadcrumbs.add(new Breadcrumb(name, url, active));
    }

    public void addActiveBreadcrumb(String name) {
        this.breadcrumbs.add(new Breadcrumb(name, null, true));
    }

    public void addBreadcrumbs(List<Breadcrumb> breadcrumbs) {
        breadcrumbs.forEach(this::addBreadcrumb);
    }

    public List<Breadcrumb> getBreadcrumbs() {
        return this.breadcrumbs;
    }

    public boolean hasBreadcrumbs() {
        return (this.getBreadcrumbs().size() > 1);
    }

}
