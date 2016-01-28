package ua.com.jon.admin.client.components;

import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import ua.com.jon.admin.client.AdminService;
import ua.com.jon.admin.client.AdminServiceAsync;
import ua.com.jon.admin.shared.AdminNotificationEvent;
import ua.com.jon.admin.shared.SpaceDTO;
import ua.com.jon.admin.shared.UserDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/2/13
 */
public class GroupCreationTabPanel extends Composite {
/*    interface UserTabPanelUiBinder extends UiBinder<Widget, GroupCreationTabPanel> {
    }

    private static UserTabPanelUiBinder uiBinder = GWT.create(UserTabPanelUiBinder.class);*/

    @UiField
    Button refreshSpacesBtn = new Button();

    @UiField
    Button createGroupBtn = new Button();

    @UiField
    WellNavList spaceUsers = new WellNavList();

    @UiField
    WellNavList groupUsers = new WellNavList();

    @UiField
    TextBox repoName;

    @UiField
    TextBox groupName;

    @UiField
    TextBox groupCode;

    @UiField
    ProgressBar spacesProgress;

    private AdminServiceAsync adminService = GWT.create(AdminService.class);

    private GlobalData globalData;

    @UiField(provided = true)
    ValueListBox<SpaceDTO> spacesListBox = new ValueListBox<SpaceDTO>(new AbstractRenderer<SpaceDTO>() {
        @Override
        public String render(SpaceDTO spaceDTO) {
            if (spaceDTO == null) {
                return "";
            } else {
                return spaceDTO.getName();
            }
        }
    });

    public GroupCreationTabPanel(final UiBinder<Widget, GroupCreationTabPanel> binder, GlobalData globalData) {
        this.globalData = globalData;
        initWidget(binder.createAndBindUi(this));
        loadSpaces();
    }

    @UiHandler("spacesListBox")
    public void onChangeSprintPosition(ValueChangeEvent<SpaceDTO> sprint) {
        clearSprintTasksList();
        //Window.alert(sprint.getValue().toString());
        addTasksToSprintNavList(sprint.getValue().getUsers());
        groupName.setText(sprint.getValue().getName());
    }

    private void clearSprintTasksList() {
        spaceUsers.clear();
        spaceUsers.add(new NavHeader("Пользователи в области"));
    }

    private void clearGroupUsersList() {
        groupUsers.clear();
        groupUsers.add(new NavHeader("Пользователи в группе"));
    }

    private void addTasksToSprintNavList(ArrayList<UserDTO> users) {
        if (users != null) {
            for (UserDTO userDTO : users) {
                NavLink userNL = new NavLink(userDTO.getName());
                userNL.addClickHandler(handler);
                spaceUsers.add(userNL);
            }
        }
    }

    private ClickHandler handler = new ClickHandler() {
        @Override
        public void onClick(ClickEvent clickEvent) {

            Widget icon = (Widget) clickEvent.getSource();
            NavLink navLink = (NavLink) icon.getParent();
            if (navLink.isActive()) {
                navLink.setActive(false);
            } else {
                navLink.setActive(true);
            }
        }
    };

    private void loadSpaces() {
        final AsyncCallback<ArrayList<SpaceDTO>> groupCallback = new AsyncCallback<ArrayList<SpaceDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                spacesProgress.setVisible(false);
                spacesListBox.setVisible(true);
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(ArrayList<SpaceDTO> spaceDTOs) {
                spacesProgress.setVisible(false);
                spacesListBox.setVisible(true);
                spacesListBox.setAcceptableValues(spaceDTOs);
                globalData.setSpacesDtos(spaceDTOs);
/*                Window.alert("fireEvent");*/
                RootPanel.ADMIN_EVENT_BUS.fireEvent(new AdminNotificationEvent());
//                for (SpaceDTO spaceDTO : spaceDTOs) {
//                    addTasksToSprintNavList(spaceDTO.getUsers());
//                    spacesListBox.setValue(spaceDTO);
//                }
                if (spaceDTOs.size() > 0) {
                    SpaceDTO spaceDTO = spaceDTOs.get(0);
//                    Window.alert(spaceDTO.toString());
                    addTasksToSprintNavList(spaceDTO.getUsers());
                    spacesListBox.setValue(spaceDTO);
                }
            }
        };

        adminService.getSpaces(groupCallback);
    }

    private void loadGitHubRepos() {
        final AsyncCallback<ArrayList<SpaceDTO>> groupCallback = new AsyncCallback<ArrayList<SpaceDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                spacesProgress.setVisible(false);
                spacesListBox.setVisible(true);
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(ArrayList<SpaceDTO> spaceDTOs) {
                spacesProgress.setVisible(false);
                spacesListBox.setVisible(true);
//                Window.alert(spaceDTOs.toString());
                spacesListBox.setAcceptableValues(spaceDTOs);
                globalData.setSpacesDtos(spaceDTOs);
/*                Window.alert("fireEvent");*/
                RootPanel.ADMIN_EVENT_BUS.fireEvent(new AdminNotificationEvent());
//                for (SpaceDTO spaceDTO : spaceDTOs) {
//                    addTasksToSprintNavList(spaceDTO.getUsers());
//                    spacesListBox.setValue(spaceDTO);
//                }
                if (spaceDTOs.size() > 0) {
                    SpaceDTO spaceDTO = spaceDTOs.get(0);
//                    Window.alert(spaceDTO.toString());
                    addTasksToSprintNavList(spaceDTO.getUsers());
                    spacesListBox.setValue(spaceDTO);
                }
            }
        };

        adminService.getGitHubRepos(groupCallback);
    }

    @UiHandler("add")
    void handleAddClick(ClickEvent e) {

        Widget widget = spaceUsers.getWidget(0);
        List<Widget> children = getChildren(widget);

        for (Widget child : children) {
            if (child instanceof NavLink) {
                NavLink navLink = (NavLink) child;
                if (navLink.isActive()) {
                    navLink.setActive(false);
                    String taskText = navLink.getText();
                    NavLink newNavLink = new NavLink(taskText);
                    addWidgetToList(groupUsers, newNavLink);
//                    groupsListBox.getValue().getTasks().add(new UserDTO(taskText));
                }
            }
        }
    }

    @UiHandler("remove")
    void handleRemoveClick(ClickEvent e) {
        getActiveElementsFromNavList(groupUsers, true);
    }

    List<Widget> getChildren(Widget parent) {

        List<Widget> result = new ArrayList<Widget>();
        if (parent instanceof HasWidgets) {
            Iterator<Widget> iter = ((NavList) parent).iterator();
            while (iter.hasNext()) {
                Widget next = iter.next();
//                Window.alert(next.toString());
                result.add(next);
            }
        }

        return result;
    }

    public void addWidgetToList(WellNavList list, Widget widget) {
        NavLink navLink = ((NavLink) widget);
        navLink.addClickHandler(handler);
        list.add(navLink);
    }

    private ArrayList<NavLink> getActiveElementsFromNavList(WellNavList navList, boolean isRemove) {
        ArrayList<NavLink> elements = new ArrayList<NavLink>();
        Widget widget = navList.getWidget(0);
        if (widget instanceof NavList) {
            NavList list = (NavList) widget;
//            Window.alert("widget " + widget);
            Iterator<Widget> itr = list.iterator();
            while (itr.hasNext()) {
                Widget el = itr.next();
                if (el instanceof NavLink) {
                    NavLink navLink = (NavLink) el;
//                    Window.alert("Element " + navLink + ", class " + navLink.getClass().getName() + ", " + navLink.isActive());
                    if (navLink.isActive()) {
                        elements.add(navLink);
                        if (isRemove) {
                            itr.remove();
                        }
//                        Window.alert("Added " + el);
                    }
                }
            }
        }
        return elements;
    }

    @UiHandler("markAllUsersBtn")
    public void handleSelectUsersClick(ClickEvent e) {
        Widget widget = spaceUsers.getWidget(0);
        List<Widget> children = getChildren(widget);

        for (Widget child : children) {
            if (child instanceof NavLink) {
                NavLink navLink = (NavLink) child;
                navLink.setActive(true);
            }
        }
    }

    @UiHandler("refreshSpacesBtn")
    public void handleRefreshSpaces(ClickEvent e) {
        spacesProgress.setVisible(true);
        spacesListBox.setVisible(false);
        clearSprintTasksList();
        loadSpaces();
    }

    @UiHandler("createGroupBtn")
    public void handleCreateGroup(ClickEvent e) {
        final SpaceDTO group = new SpaceDTO(null, groupName.getText(), getUsersFromList(), repoName.getText());
        if(groupName.getText().isEmpty()) {
             Window.alert("Group name can't be empty");
            return;
        }
        if(repoName.getText().isEmpty()) {
             Window.alert("Repo can't be empty");
            return;
        }
        final AsyncCallback<String> groupCallback = new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable caught) {
                spacesProgress.setVisible(false);
                spacesListBox.setVisible(true);
                Window.alert("Error callback groupsListBox " + caught);
            }

            @Override
            public void onSuccess(String code) {
                clearGroupUsersList();
                groupName.setText("");
                groupCode.setText(code);
//                Window.alert("Code of group: " + code);
            }
        };


        adminService.createGroup(group, groupCallback);
    }

    private ArrayList<UserDTO> getUsersFromList() {
        Widget widget = groupUsers.getWidget(0);
        List<Widget> children = getChildren(widget);
        ArrayList<UserDTO> users = new ArrayList<UserDTO>(children.size());

        for (Widget child : children) {
            if (child instanceof NavLink) {
                users.add(new UserDTO(null, ((NavLink) child).getText()));
            }
        }
        return users;
    }
}
