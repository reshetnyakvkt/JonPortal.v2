package ua.com.jon.admin.client.components;

import com.github.gwtbootstrap.client.ui.*;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.admin.client.AdminService;
import ua.com.jon.admin.client.AdminServiceAsync;
import ua.com.jon.admin.shared.GroupDTO;
import ua.com.jon.admin.shared.SprintDTO;
import ua.com.jon.admin.shared.TaskDTO;
import ua.com.jon.admin.shared.TaskTemplateDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/1/13
 */
public class TasksTabPanel extends Composite {
/*    interface TasksTabPanelUiBinder extends UiBinder<Widget, TasksTabPanel> {
    }

    private static TasksTabPanelUiBinder uiBinder = GWT.create(TasksTabPanelUiBinder.class);*/

//    @UiField
//    com.google.gwt.user.client.ui.HTMLPanel tasksHolderPanel;

    private ArrayList<Long> taskNames = new ArrayList<Long>();
    private ListDataProvider<TaskDTO> dataProvider = new ListDataProvider<TaskDTO>();

    @UiField
    WellNavList availableTasks = new WellNavList();

    @UiField
    WellNavList tasksToSend = new WellNavList();

    @UiField
    WellNavList groupTasks = new WellNavList();

    @UiField
    Button add = new Button();

    @UiField
    Button remove = new Button();

    @UiField
    Button sendTasks = new Button();

    @UiField
    Button close = new Button();

    @UiField
    Modal sendConfirm = new Modal();

    @UiField
    TextBox sprint;

    @UiField
    TextBox group;

    @UiField
    CellTable<TaskDTO> cellTable = new CellTable<TaskDTO>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));
//    @UiField
//    DropdownButton groupsListBox = new DropdownButton();

    @UiField(provided = true)
    ValueListBox<GroupDTO> groupsListBox = new ValueListBox<GroupDTO>(new AbstractRenderer<GroupDTO>() {
        @Override
        public String render(GroupDTO groupDTO) {
            if (groupDTO == null) {
                return "";
            } else {
                return groupDTO.getName();
            }
        }
    });

    @UiField(provided = true)
    ValueListBox<SprintDTO> sprintsListBox = new ValueListBox<SprintDTO>(new AbstractRenderer<SprintDTO>() {
        @Override
        public String render(SprintDTO sprintDTO) {
            if (sprintDTO == null) {
                return "";
            } else {
                return sprintDTO.getName();
            }
        }
    });

    private AdminServiceAsync adminService = GWT.create(AdminService.class);

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

/*    public TasksTabPanel() {
        initWidget(uiBinder.createAndBindUi(this));
    }*/

    public TasksTabPanel(final UiBinder<Widget, TasksTabPanel> binder) {
        initWidget(binder.createAndBindUi(this));
        buildTable();
        loadGroupsAndTasks();
        loadSprintsAndTasks();
    }

    public void buildTable() {

        cellTable.setEmptyTableWidget(new Label("Please add data"));
        dataProvider.addDataDisplay(cellTable);

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                if (taskDTO.getName() == null) {
                    return "null";
                }
                return String.valueOf(taskDTO.getName());
            }
        }, "Название");

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                if (taskDTO.getUserName() == null) {
                    return "null";
                }
                return String.valueOf(taskDTO.getUserName());
            }
        }, "Студент");

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                String result = taskDTO.getResult();
                if (result == null) {
                    return "null";
                }
                int newLinePos = result.indexOf('\n');
                return String.valueOf(taskDTO.getResult().substring(0, newLinePos));
            }
        }, "Оценка");

        com.google.gwt.user.cellview.client.Column<TaskDTO, String> buttonDelCol = new com.google.gwt.user.cellview.client.Column<TaskDTO, String>(new ButtonCell(IconType.REMOVE, ButtonType.DANGER)) {
            @Override
            public String getValue(TaskDTO object) {
                return "";
            }
        };

        buttonDelCol.setFieldUpdater(new FieldUpdater<TaskDTO, String>() {
            @Override
            public void update(int index, TaskDTO object, String value) {
                dataProvider.getList().remove(object);
                dataProvider.flush();
                dataProvider.refresh();
            }
        });
        cellTable.addColumn(buttonDelCol);


        final SingleSelectionModel<TaskDTO> selectionModel = new SingleSelectionModel<TaskDTO>();
        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        TaskDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            String text = selected.getText();
                            //textArea.setText(text);
                        }
                    }
                });
    }

    public void addWidgetToList(WellNavList list, Widget widget) {
        NavLink navLink = ((NavLink) widget);
        navLink.addClickHandler(handler);
        list.add(navLink);
    }

    @UiHandler("sprintsListBox")
    public void onChangeSprintPosition(ValueChangeEvent<SprintDTO> sprint) {
        clearSprintTasksList();
        addTasksToSprintNavList(sprint.getValue().getTasks());
        //fillCellTable(groupsListBox.getValue().getId(), sprint.getValue().getId());

    }

    private void clearSprintTasksList() {
        availableTasks.clear();
        availableTasks.add(new NavHeader("Доступные задания"));
    }

    @UiHandler("groupsListBox")
    public void onChangeGroupPosition(ValueChangeEvent<GroupDTO> group) {
        clearGroupTasksList();
        //addTaskToGroupNavList(group.getValue().getTasks());
        fillCellTable(group.getValue().getId(), sprintsListBox.getValue().getId());
    }

    private void clearGroupTasksList() {
        groupTasks.clear();
        groupTasks.add(new NavHeader("Задания для группы"));
    }

    private void addTaskToGroupNavList(ArrayList<TaskTemplateDTO> tasks) {
        for (TaskTemplateDTO taskTemplateDTO : tasks) {
            NavLink taskNL = new NavLink(taskTemplateDTO.getName());
            taskNL.addClickHandler(handler);
            groupTasks.add(taskNL);
        }
    }

    private boolean addTasksToSprintNavList(List<TaskTemplateDTO> tasks) {
        boolean hasUnchecked = false;
        if (tasks != null) {
            for (TaskTemplateDTO taskTemplateDTO : tasks) {
                NavLink taskNL = new NavLink(taskTemplateDTO.getId() + "-" + taskTemplateDTO.getName());
                if (taskTemplateDTO.getTestName().equals("AnyTest") || taskTemplateDTO.getTestName() == null) {
                    taskNL.setStyleName("alert-info");
                    hasUnchecked = true;
                }
                taskNL.addClickHandler(handler);
                availableTasks.add(taskNL);
            }
        }
        return hasUnchecked;
    }

    private void clearSprints() {
        availableTasks.clear();
        availableTasks.add(new NavHeader("Доступные задания"));
    }

    @UiHandler("add")
    void handleAddClick(ClickEvent e) {

        Widget widget = availableTasks.getWidget(0);
        List<Widget> children = getChildren(widget);

        for (Widget child : children) {
            if (child instanceof NavLink) {
                NavLink navLink = (NavLink) child;
                if (navLink.isActive()) {
                    navLink.setActive(false);
                    String taskText = navLink.getText();
                    NavLink newNavLink = new NavLink(taskText);
                    if (navLink.getStyleName().equals("alert-info")) {
                        Window.alert("Вы выбрали непроверяемое задание");
                        newNavLink.setStyleName("alert-info");
                    }
                    addWidgetToList(groupTasks, newNavLink);
                    groupsListBox.getValue().getTasks().add(new TaskTemplateDTO(taskText));
                }
            }
        }
    }

    @UiHandler("remove")
    void handleRemoveClick(ClickEvent e) {
        getActiveElementsFromNavList(groupTasks, true);
    }

    private ArrayList<NavLink> getActiveElementsFromNavList(WellNavList navList, boolean isRemove) {
        ArrayList<NavLink> elements = new ArrayList<NavLink>();
        Widget widget = navList.getWidget(0);
        if (widget instanceof NavList) {
            NavList list = (NavList) widget;
            Iterator<Widget> itr = list.iterator();
            while (itr.hasNext()) {
                Widget el = itr.next();
                if (el instanceof NavLink) {
                    NavLink navLink = (NavLink) el;
                    elements.add(navLink);
                    if (isRemove) {
                        itr.remove();
                    }
                }
            }
        }
        return elements;
    }


    @UiHandler("close")
    void close(ClickEvent e) {
        sendConfirm.hide();
    }

    @UiHandler("refreshAllBtn")
    void refresh(ClickEvent e) {
        clearSprints();
        loadGroupsAndTasks();
        loadSprintsAndTasks();
    }

    @UiHandler("sendTasksBtn")
    void saveChanges(ClickEvent e) {
        Widget widget = groupTasks.getWidget(0);
        List<Widget> children = getChildren(widget);

        if (children.size() <= 1) {
            Window.alert("Нет выдаваемых заданий");
            return;
        }
        ArrayList<NavLink> links = getActiveElementsFromNavList(groupTasks, false);
        taskNames.clear();
        for (NavLink link : links) {
            String name = link.getText();
            Long val;
            try {
                String[] array = name.split("-");
                val = Long.valueOf(array[0].trim());
            } catch (Exception ex) {
                continue;
            }
            taskNames.add(val);
        }

        boolean hasDublicates = false;
        tasksToSend.clear();
        tasksToSend.add(new NavHeader("ВЫДАВАЕМЫЕ ЗАДАНИЯ"));
        for (Widget child : children) {
            if (child instanceof NavLink) {
                NavLink navLink = (NavLink) child;
//                if (navLink.isActive()) {
//                    navLink.setActive(false);
                String taskText = navLink.getText();
                NavLink newNavLink = new NavLink(taskText);
                Long id = Long.parseLong(taskText.substring(0, taskText.indexOf("-")).trim());
                if (isPosted(id)) {
                    newNavLink.setStyleName("alert-error");
                    hasDublicates = true;
                }
//                    NavLink navLink = ((NavLink) widget);
//                    navLink.addClickHandler(handler);
//                    list.add(navLink);

//                    addWidgetToList(groupTasks, newNavLink);
                tasksToSend.add(newNavLink);
//                }
            }
        }
        if (hasDublicates) {
            Window.alert("Вы пытаетесь выдать уже выданное задание");
            sendTasks.setEnabled(false);
        } else {
            sendTasks.setEnabled(true);
        }
        sprint.setText(sprintsListBox.getValue().getName());
        group.setText(groupsListBox.getValue().getName());
        sendConfirm.show();

    }

    private boolean isPosted(Long id) {
        ArrayList<Long> elements = new ArrayList<>();
        List<TaskDTO> tasks = dataProvider.getList();
//        Window.alert("" + tasks.size());
        for (TaskDTO taskDTO : tasks) {
//            Window.alert(taskDTO.getTaskTemplateId() + ", " + id);
            elements.add(taskDTO.getTaskTemplateId());
            if (taskDTO.getTaskTemplateId().equals(id)) {
                return true;
            }
        }
//        Window.alert(elements.toString());
/*
        if (tasks instanceof NavList) {
            NavList list = (NavList) tasks;
            Iterator<Widget> itr = list.iterator();
            if (itr.hasNext()) {
                Widget el = itr.next();
                if (el instanceof NavLink) {
                    NavLink navLink = (NavLink) el;
                    String taskText = navLink.getText();
                    Long navId = Long.parseLong(taskText.substring(0, taskText.indexOf("-")).trim());
                    Window.alert(taskText + ", " + id);
                    if (navId.equals(id)) {
                        return true;
                    }
                }
            }
        }
*/
        return false;
    }

    @UiHandler("sendTasks")
    void sendTask(ClickEvent e) {
        sendConfirm.hide();
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("!!! Error async save tasks" + throwable);
            }

            @Override
            public void onSuccess(Void aVoid) {
                //loadGroupsAndTasks();
                Window.alert("CabinetMain tasks posted successfully");
                clearGroupTasksList();
                loadGroupsAndTasks();
            }
        };
        GroupDTO groupDTO = groupsListBox.getValue();
        SprintDTO sprintDTO = sprintsListBox.getValue();
        // TODO: replace taskNames by tasks
        adminService.postTasksByNames(groupDTO, taskNames, sprintDTO, callback);

    }

    @UiHandler("saveGroupsBtn")
    void saveGroups(ClickEvent e) {

        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("!!! Error async save groups" + throwable);
            }

            @Override
            public void onSuccess(Void aVoid) {
                Window.alert("Groups are saved successfully");
            }
        };
        //groupsListBox.getValue().setTasks(dataProvider.getList());

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

    private void loadSprintsAndTasks() {
        final AsyncCallback<ArrayList<SprintDTO>> callback = new AsyncCallback<ArrayList<SprintDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error async get sprints");
            }

            @Override
            public void onSuccess(ArrayList<SprintDTO> sprints) {
//                Window.alert("sprints " + sprints);
                sprintsListBox.setAcceptableValues(sprints);
                for (SprintDTO sprint : sprints) {
                    if (sprint.isActive()) {
                        addTasksToSprintNavList(sprint.getTasks());
                        sprintsListBox.setValue(sprint);
                        break;
                    }
//                    Window.alert("Added sprint " + sprint.getName());
                }
            }
        };

        adminService.getSprintsAndTasks(callback);
    }

    private void loadGroupsAndTasks() {

        final AsyncCallback<List<GroupDTO>> groupCallback = new AsyncCallback<List<GroupDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(List<GroupDTO> groupDTOs) {
                //Window.alert("groups: " + groupDTOs);

                groupsListBox.setValue(null);
                groupsListBox.setAcceptableValues(groupDTOs);
                if (groupDTOs.size() > 0) {
                    GroupDTO group = groupDTOs.get(0);
                    //addTaskToGroupNavList(groupTextBox.getTasks());
                    groupsListBox.setValue(group);
                    fillCellTable(group.getId(), sprintsListBox.getValue().getId());
                }
            }
        };

        adminService.getGroupsAndTasks(groupCallback);
    }

    private void createGetTasksCallback() {
        final AsyncCallback<List<TaskTemplateDTO>> callback = new AsyncCallback<List<TaskTemplateDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error callback");
            }

            @Override
            public void onSuccess(List<TaskTemplateDTO> tasksTemplateDTOs) {

                for (TaskTemplateDTO taskTemplateDTO : tasksTemplateDTOs) {
                    NavLink taskNL = new NavLink(taskTemplateDTO.getName());
                    taskNL.addClickHandler(handler);
                    availableTasks.add(taskNL);
                }
            }
        };

        adminService.getTaskTemplates(callback);
    }

    public void fillCellTable(Long groupId, Long sprintId) {

        final AsyncCallback<List<TaskDTO>> callback = new AsyncCallback<List<TaskDTO>>() {

            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("!! Error async save tasks" + throwable);
            }

            @Override
            public void onSuccess(List<TaskDTO> taskDTOs) {
                dataProvider.getList().clear();
                for (TaskDTO taskDto : taskDTOs) {
                    dataProvider.getList().add(taskDto);
                }
                dataProvider.flush();
                dataProvider.refresh();
            }
        };

        adminService.getTasksByGroup(groupId, sprintId, callback);
    }
}
