package ua.com.jon.cabinet.resources;

import com.github.gwtbootstrap.client.ui.resources.Resources;
import com.google.gwt.resources.client.TextResource;

public interface ExampleResources extends Resources {
    @Source("css/bootstrap.min.css")
    TextResource bootstrapCss();
    @Source("css/addition.min.css")
    TextResource additionCss();
}
