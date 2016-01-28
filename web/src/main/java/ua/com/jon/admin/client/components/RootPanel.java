package ua.com.jon.admin.client.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/1/13
 */
public class RootPanel extends Composite {
    @UiTemplate("RootPanel.ui.xml")
    interface RootPanelUiBinder extends UiBinder<Widget, RootPanel> {
    }

    @UiTemplate("TasksTabPanel.ui.xml")
    interface TasksTabPanelUiBinder extends UiBinder<Widget, TasksTabPanel> {
    }

    @UiTemplate("GroupCreationTabPanel.ui.xml")
    interface UsersTabPanelUiBinder extends UiBinder<Widget, GroupCreationTabPanel> {
    }

    @UiTemplate("TasksManageTabPanel.ui.xml")
    interface TasksManageTabPanelUiBinder extends UiBinder<Widget, TasksManageTabPanel> {
    }

    @UiTemplate("SprintsTabPanel.ui.xml")
    interface SprintsTabPanelUiBinder extends UiBinder<Widget, SprintsTabPanel> {
    }

    @UiTemplate("GroupsManageTabPanel.ui.xml")
    interface GroupsManageTabPanelUiBinder extends UiBinder<Widget, GroupsManageTabPanel> {
    }

    @UiTemplate("TasksOverviewTabPanel.ui.xml")
    interface TasksOverviewTabPanelUiBinder extends UiBinder<Widget, TasksOverviewTabPanel> {
    }

    @UiField
    com.google.gwt.user.client.ui.FlowPanel tasksHolderPanel;

    @UiField
    com.google.gwt.user.client.ui.FlowPanel usersHolderPanel;

    @UiField
    com.google.gwt.user.client.ui.FlowPanel tasksManageHolderPanel;

    @UiField
    com.google.gwt.user.client.ui.FlowPanel sprintsHolderPanel;

    @UiField
    com.google.gwt.user.client.ui.FlowPanel groupsManageHolderPanel;

    @UiField
    com.google.gwt.user.client.ui.FlowPanel tasksOverviewHolderPanel;

    private static RootPanelUiBinder rootUIBinder = GWT.create(RootPanelUiBinder.class);

    private static TasksTabPanelUiBinder tasksUIBinder = GWT.create(TasksTabPanelUiBinder.class);
    private static UsersTabPanelUiBinder usersUIBinder = GWT.create(UsersTabPanelUiBinder.class);
    private static TasksManageTabPanelUiBinder tasksManageUIBinder = GWT.create(TasksManageTabPanelUiBinder.class);
    private static SprintsTabPanelUiBinder sprintsUIBinder = GWT.create(SprintsTabPanelUiBinder.class);
    private static GroupsManageTabPanelUiBinder groupsManageUIBinder = GWT.create(GroupsManageTabPanelUiBinder.class);
    private static TasksOverviewTabPanelUiBinder tasksOverviewUIBinder = GWT.create(TasksOverviewTabPanelUiBinder.class);
    private static GlobalData globalData = new GlobalData();
    public static EventBus ADMIN_EVENT_BUS = GWT.create(SimpleEventBus.class);

    public RootPanel() {
        initWidget(rootUIBinder.createAndBindUi(this));

        TasksTabPanel tasksTabPanel = new TasksTabPanel(tasksUIBinder);
        GroupCreationTabPanel groupCreationTabPanel = new GroupCreationTabPanel(usersUIBinder, globalData);
        TasksManageTabPanel tasksManageTabPanel = new TasksManageTabPanel(tasksManageUIBinder);
        SprintsTabPanel sprintsTabPanel = new SprintsTabPanel(sprintsUIBinder);
        GroupsManageTabPanel groupsManageTabPanel = new GroupsManageTabPanel(groupsManageUIBinder, globalData);
        TasksOverviewTabPanel tasksOverviewTabPanel = new TasksOverviewTabPanel(tasksOverviewUIBinder);

        tasksHolderPanel.add(tasksTabPanel);
        usersHolderPanel.add(groupCreationTabPanel);
        tasksManageHolderPanel.add(tasksManageTabPanel);
        sprintsHolderPanel.add(sprintsTabPanel);
        groupsManageHolderPanel.add(groupsManageTabPanel);
        tasksOverviewHolderPanel.add(tasksOverviewTabPanel);
    }
}
