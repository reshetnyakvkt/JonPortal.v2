package ua.com.jon.admin.client.components;

import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
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
import ua.com.jon.admin.shared.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/1/13
 */
public class TasksOverviewTabPanel extends Composite {
/*    interface TasksTabPanelUiBinder extends UiBinder<Widget, TasksTabPanel> {
    }

    private static TasksTabPanelUiBinder uiBinder = GWT.create(TasksTabPanelUiBinder.class);*/

//    @UiField
//    com.google.gwt.user.client.ui.HTMLPanel tasksHolderPanel;

    private ArrayList<Long> taskNames = new ArrayList<Long>();
    private ListDataProvider<TaskDTO> dataProvider = new ListDataProvider<TaskDTO>();

/*    @UiField
    WellNavList availableTasks = new WellNavList();

    @UiField
    WellNavList tasksToSend = new WellNavList();

    @UiField
    WellNavList groupTasks = new WellNavList();*/

/*    @UiField
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
    Column column2;
*/
    @UiField
    Column column3;

/*    @UiField
    ListBox tasksList;

    @UiField
    ListBox control;
*/
    @UiField
    CodeBlock code;

    @UiField
    TextArea text;

    @UiField
    TextArea result;

    @UiField
    SimplePager pager;

    @UiField(provided = true)
    CellTable<TaskDTO> cellTable = new CellTable<TaskDTO>(25, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));

    final SingleSelectionModel<TaskDTO> selectionModel = new SingleSelectionModel<TaskDTO>();
//    @UiField
//    DropdownButton groupsListBox = new DropdownButton();

    @UiField(provided = true)
    ValueListBox<GroupAndSprintsDTO> groupsListBox = new ValueListBox<GroupAndSprintsDTO>(new AbstractRenderer<GroupAndSprintsDTO>() {
        @Override
        public String render(GroupAndSprintsDTO groupDTO) {
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

    @UiField(provided = true)
    ValueListBox<TaskTemplateDTO> tasksListBox = new ValueListBox<TaskTemplateDTO>(new AbstractRenderer<TaskTemplateDTO>() {
        @Override
        public String render(TaskTemplateDTO sprintDTO) {
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

    public TasksOverviewTabPanel(final UiBinder<Widget, TasksOverviewTabPanel> binder) {
        initWidget(binder.createAndBindUi(this));
        buildTable();
        loadGroupsAndSprints();
        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        TaskDTO selected = selectionModel.getSelectedObject();
                        setTaskInfo(selected);
                    }
                }
        );

//        fillCellTable(sprintsListBox.getValue(), tasksListBox.getValue());
/*        if (groupsListBox.getValue() != null) {
            Long groupId = groupsListBox.getValue().getGroupId();
            Long id = sprintsListBox.getValue().getId();
            loadUsersAndTasks(groupId, id);
        }*/
//        control.setVisible(false);
//        column2.setSize(1);
//        column3.setSize(8);
    }

    private void setTaskInfo(TaskDTO selected) {
        if (selected != null) {
            text.setText(selected.getText());
	        code.setLinenums(true);
            code.setText(selected.getCode());
            if (selected.getResult() != null) {
                code.setText(selected.getCode());
                result.setText(selected.getResult());
            } else {
                code.setText(selected.getMaterial());
            }
        }
    }

    public void buildTable() {

        cellTable.setEmptyTableWidget(new Label("Please add data"));
        dataProvider.addDataDisplay(cellTable);
//        cellTable.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.BOUND_TO_SELECTION);
        pager.setDisplay(cellTable);

/*        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                if (taskDTO.getGname() == null) {
                    return "null";
                }
                return String.valueOf(taskDTO.getGname());
            }
        }, "Название");*/

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                if (taskDTO.getUserName() == null) {
                    return "null";
                }
                return String.valueOf(taskDTO.getUserName());
            }
        }, "Студент");

        TextColumn<TaskDTO> nameCol = new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                String result = taskDTO.getResult();
                if (result == null) {
                    return "null";
                }
                int newLinePos = result.indexOf('\n') < 0 ? result.length() : result.indexOf('\n');
                return String.valueOf(taskDTO.getResult().substring(0, newLinePos));
            }
        };
        cellTable.setColumnWidth(nameCol, 4.0, Style.Unit.PCT);
        cellTable.addColumn(nameCol, "Оценка");

/*        com.google.gwt.user.cellview.client.Column<TaskDTO, String> buttonDelCol = new com.google.gwt.user.cellview.client.Column<TaskDTO, String>(new ButtonCell(IconType.REMOVE, ButtonType.DANGER)) {
            @Override
            public String getValue(TaskDTO object) {
                return "";
            }
        };*/

/*        buttonDelCol.setFieldUpdater(new FieldUpdater<TaskDTO, String>() {
            @Override
            public void update(int index, TaskDTO object, String value) {
                dataProvider.getList().remove(object);
                dataProvider.flush();
                dataProvider.refresh();
            }
        });
        cellTable.addColumn(buttonDelCol);*/


/*        final SingleSelectionModel<TaskDTO> selectionModel = new SingleSelectionModel<TaskDTO>();
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
                });*/
    }

    public void addWidgetToList(WellNavList list, Widget widget) {
        NavLink navLink = ((NavLink) widget);
        navLink.addClickHandler(handler);
        list.add(navLink);
    }

    @UiHandler("groupsListBox")
    public void onChangeGroupPosition(ValueChangeEvent<GroupAndSprintsDTO> group) {
//        clearGroupTasksList();
//        fillCellTable(group.getValue().getId(), sprintsListBox.getValue().getId());
        addSprintsListBox(group.getValue());
    }

    private void addSprintsListBox(GroupAndSprintsDTO group) {
        sprintsListBox.setValue(null);
        sprintsListBox.setAcceptableValues(group.getSprints());
        if (group.getSprints().size() > 0) {
            SprintDTO sprint = group.getSprints().get(0);
            sprintsListBox.setValue(sprint);
            addTemplatesToListBox(sprint);
        }
    }

    private void addTemplatesToListBox(SprintDTO sprintDTO) {
        tasksListBox.setValue(null);
        tasksListBox.setAcceptableValues(sprintDTO.getTasks());

        if (sprintDTO.getTasks().size() > 0) {
            TaskTemplateDTO templateDTO = sprintDTO.getTasks().get(0);
            tasksListBox.setValue(templateDTO);
//            loadUsersAndTasks(templateDTO.getId(), sprintDTO.getId());
            fillCellTable(templateDTO);
            TaskDTO task = null;
            for (TaskDTO taskDTO : templateDTO.getTasks()) {
                if (taskDTO.getResult() != null && !taskDTO.getResult().isEmpty()) {
                    task = taskDTO;
                }
            }
            setTaskInfo(task);
        }
    }

    @UiHandler("sprintsListBox")
    public void onChangeSprintPosition(ValueChangeEvent<SprintDTO> sprint) {
//        clearSprintTasksList();
//        addTasksToSprintNavList(sprint.getValue().getSprints());
        addTemplatesToListBox(sprint.getValue());
    }


    @UiHandler("tasksListBox")
    public void onChangeTasksPosition(ValueChangeEvent<TaskTemplateDTO> taskTemplate) {
//        clearSprintTasksList();
        fillCellTable(taskTemplate.getValue());
//        addTemplatesToListBox(sprint.getValue());
    }

    private void fillCellTable(TaskTemplateDTO taskTemplate) {
        dataProvider.getList().clear();
        for (TaskDTO taskDto : taskTemplate.getTasks()) {
            dataProvider.getList().add(taskDto);
        }
        dataProvider.flush();
        dataProvider.refresh();

/*
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

        adminService.getTasksBySprintAndTemplate(sprint.getId(), taskTemplate.getId(), callback);
*/

    }

/*    private void clearSprintTasksList() {
        availableTasks.clear();
        availableTasks.add(new NavHeader("Доступные задания"));
    }

    private void clearGroupTasksList() {
        groupTasks.clear();
        groupTasks.add(new NavHeader("Задания для группы"));
    }

    private void addTaskToGroupNavList(ArrayList<TaskTemplateDTO> tasks) {
        for (TaskTemplateDTO taskTemplateDTO : tasks) {
            NavLink taskNL = new NavLink(taskTemplateDTO.getGname());
            taskNL.addClickHandler(handler);
            groupTasks.add(taskNL);
        }
    }*/
/*
    private boolean addTasksToSprintNavList(List<TaskTemplateDTO> tasks) {
        boolean hasUnchecked = false;
        if (tasks != null) {
            for (TaskTemplateDTO taskTemplateDTO : tasks) {
                NavLink taskNL = new NavLink(taskTemplateDTO.getId() + "-" + taskTemplateDTO.getGname());
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
    }*/

/*    @UiHandler("add")
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
    }*/

/*    @UiHandler("remove")
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
    }*/


/*    @UiHandler("close")
    void close(ClickEvent e) {
        sendConfirm.hide();
    }*/

    @UiHandler("refreshAllBtn")
    void refresh(ClickEvent e) {
//        control.setVisible(false);
//        column2.setSize(1);
//        column3.setSize(8);
//        tasksList.setVisible(false);
//        loadGroupsAndSprints();
//        loadUsersAndTasks(sprintsListBox.getValue().getId(), sprintsListBox.getValue().getId());
    }

/*
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
        sprint.setText(sprintsListBox.getValue().getGname());
        group.setText(groupsListBox.getValue().getGname());
        sendConfirm.show();

    }
*/

/*
    private boolean isPosted(Long id) {
        ArrayList<Long> elements = new ArrayList<>();
        List<TaskDTO> tasks = dataProvider.getList();
        for (TaskDTO taskDTO : tasks) {
            elements.add(taskDTO.getTaskTemplateId());
            if (taskDTO.getTaskTemplateId().equals(id)) {
                return true;
            }
        }

        return false;
    }
*/

/*    @UiHandler("sendTasks")
    void sendTask(ClickEvent e) {
        sendConfirm.hide();
        final AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("!!! Error async save tasks" + throwable);
            }

            @Override
            public void onSuccess(Void aVoid) {
                //loadGroupsAndSprints();
                Window.alert("CabinetMain tasks posted successfully");
                clearGroupTasksList();
                loadGroupsAndSprints();
            }
        };
        GroupDTO groupDTO = groupsListBox.getValue();
        SprintDTO sprintDTO = sprintsListBox.getValue();
        // TODO: replace taskNames by tasks
        adminService.postTasksByNames(groupDTO, taskNames, sprintDTO, callback);

    }*/

/*    @UiHandler("saveGroupsBtn")
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
        //groupsListBox.getValue().setSprints(dataProvider.getList());

    }*/

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

/*    private void loadUsersAndTasks(Long groupId, Long sprintId) {
        final AsyncCallback<List<TaskDTO>> tasksCallback = new AsyncCallback<List<TaskDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error callback tasksListBox");
            }

            @Override
            public void onSuccess(List<TaskDTO> groupDTOs) {
//                Window.alert("groups: " + groupDTOs);

                tasksList.clear();
                for (int i = 0; i < groupDTOs.size(); i++) {
                    tasksList.insertItem(groupDTOs.get(i).getName(), i);
                    if (i == 0) {
                        TaskDTO group = groupDTOs.get(0);
                        code.setLinenums(true);
                        code.setText(group.getCode());
                    }

                }

            }
        };
        adminService.getTasksBySprintAndTemplate(groupId, sprintId, tasksCallback);
    }*/

    private void loadGroupsAndSprints() {

        final AsyncCallback<List<GroupAndSprintsDTO>> groupCallback = new AsyncCallback<List<GroupAndSprintsDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(List<GroupAndSprintsDTO> groupDTOs) {
//                Window.alert("groups: " + groupDTOs);

                groupsListBox.setValue(null);
                groupsListBox.setAcceptableValues(groupDTOs);

                if (groupDTOs.size() > 0) {
                    GroupAndSprintsDTO group = groupDTOs.get(0);
                    groupsListBox.setValue(group);
                    addSprintsListBox(group);
                }
            }
        };

        adminService.getGroupsAndSprints(groupCallback);
    }

/*
    private void createGetTasksCallback() {
        final AsyncCallback<List<TaskTemplateDTO>> callback = new AsyncCallback<List<TaskTemplateDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error callback");
            }

            @Override
            public void onSuccess(List<TaskTemplateDTO> tasksTemplateDTOs) {

                for (TaskTemplateDTO taskTemplateDTO : tasksTemplateDTOs) {
                    NavLink taskNL = new NavLink(taskTemplateDTO.getGname());
                    taskNL.addClickHandler(handler);
                    availableTasks.add(taskNL);
                }
            }
        };

        adminService.getTaskTemplates(callback);
    }
*/

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
