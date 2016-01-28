package ua.com.jon.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
//import ua.com.jon.admin.client.components.AdminUiBinder;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
public class Console implements EntryPoint {
/*    private AdminServiceAsync adminService = GWT.create(AdminService.class);
    private TextBox nameTextBox = new TextBox();
    private Label greetingLabel = new Label("Hello, GWT!");*/

    @Override
    public void onModuleLoad() {

//        RootPanel.get("slot1").add(new AdminUiBinder());
        RootPanel.get("slot1").add(new ua.com.jon.admin.client.components.RootPanel());
    }
}
