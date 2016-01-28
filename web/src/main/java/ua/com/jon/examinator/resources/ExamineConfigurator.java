package ua.com.jon.examinator.resources;

import com.github.gwtbootstrap.client.ui.config.Configurator;
import com.github.gwtbootstrap.client.ui.resources.Resources;
import com.google.gwt.core.client.GWT;

public class ExamineConfigurator implements Configurator {
    public Resources getResources() {
        return GWT.create(ExamineResources.class);
    }

    public boolean hasResponsiveDesign() {
        return false;
    }
}
