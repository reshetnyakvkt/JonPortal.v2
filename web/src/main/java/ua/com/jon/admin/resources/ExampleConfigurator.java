package ua.com.jon.admin.resources;

import com.github.gwtbootstrap.client.ui.config.Configurator;
import com.github.gwtbootstrap.client.ui.resources.Resources;
import com.google.gwt.core.client.GWT;
import ua.com.jon.admin.resources.ExampleResources;

public class ExampleConfigurator implements Configurator {
    public Resources getResources() {
        return GWT.create(ExampleResources.class);
    }

    public boolean hasResponsiveDesign() {
        return false;
    }
}
