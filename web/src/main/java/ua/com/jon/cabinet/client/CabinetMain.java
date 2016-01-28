package ua.com.jon.cabinet.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
public class CabinetMain implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get("slot1").add(new ua.com.jon.cabinet.client.components.RootPanel());
    }
}
