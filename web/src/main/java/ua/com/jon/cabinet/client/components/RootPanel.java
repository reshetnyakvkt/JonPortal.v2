package ua.com.jon.cabinet.client.components;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TabLink;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import ua.com.jon.cabinet.shared.GroupEvent;
import ua.com.jon.cabinet.shared.GroupEventHandler;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/1/13
 */
public class RootPanel extends Composite {
    @UiTemplate("RootPanel.ui.xml")
    interface RootPanelUiBinder extends UiBinder<Widget, RootPanel> {
    }

    @UiTemplate("UserTasksTabPanel.ui.xml")
    interface UserTasksTabPanelUiBinder extends UiBinder<Widget, UserTasksTabPanel> {
    }

    @UiTemplate("GroupTasksTabPanel.ui.xml")
    interface GroupTasksTabPanelUiBinder extends UiBinder<Widget, GroupTasksTabPanel> {
    }

    @UiTemplate("GroupInfoTabPanel.ui.xml")
    interface GroupInfoTabPanelUiBinder extends UiBinder<Widget, GroupInfoTabPanel> {
    }

    @UiField
    TabLink admin;

    @UiField
    com.google.gwt.user.client.ui.FlowPanel userTasksHolderPanel;

    @UiField
    com.google.gwt.user.client.ui.FlowPanel groupTasksHolderPanel;

    @UiField
    com.google.gwt.user.client.ui.FlowPanel groupInfoHolderPanel;

    private static RootPanelUiBinder rootUIBinder = GWT.create(RootPanelUiBinder.class);

    private static UserTasksTabPanelUiBinder userTasksUIBinder = GWT.create(UserTasksTabPanelUiBinder.class);
    private static GroupTasksTabPanelUiBinder groupTasksUIBinder = GWT.create(GroupTasksTabPanelUiBinder.class);
    private static GroupInfoTabPanelUiBinder groupInfoUIBinder = GWT.create(GroupInfoTabPanelUiBinder.class);
    public static EventBus CABINET_EVENT_BUS = GWT.create(SimpleEventBus.class);

    public RootPanel() {
        initWidget(rootUIBinder.createAndBindUi(this));

        UserTasksTabPanel userTasksTabPanel = new UserTasksTabPanel(userTasksUIBinder);
        GroupTasksTabPanel groupTasksTabPanel = new GroupTasksTabPanel(groupTasksUIBinder, userTasksTabPanel);
        GroupInfoTabPanel groupInfoTabPanel  = new GroupInfoTabPanel(groupInfoUIBinder, userTasksTabPanel);

        userTasksHolderPanel.add(userTasksTabPanel);
        groupTasksHolderPanel.add(groupTasksTabPanel);
        groupInfoHolderPanel.add(groupInfoTabPanel);

        // TODO deep remove
        CABINET_EVENT_BUS.addHandler(GroupEvent.TYPE, new GroupEventHandler()     {
            @Override
            public void onGroupChanged(GroupEvent groupEvent) {

            }
        });
    }
    // TODO add method check if admin
    @UiHandler("admin")
    public void onClick(ClickEvent e) {
//        Window.alert("lll");
        Window.Location.replace("/admin/index.html");
//        result.setText("");
//        taskText.setText("");
//        loadSprintsAndTasks();
    }

}
