package ua.com.jon.cabinet.client.components;

import com.github.gwtbootstrap.client.ui.*;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.client.TasksServiceAsync;
import ua.com.jon.cabinet.shared.GroupDTO;
import ua.com.jon.cabinet.shared.GroupEvent;
import ua.com.jon.cabinet.shared.NotificationEvent;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UserTasksTabPanel extends Composite {
    @UiField(provided = true)
    CellTable<TaskDTO> cellTable = new CellTable<TaskDTO>(25, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));//new CellTable<TaskDTO>();

    final SingleSelectionModel<TaskDTO> selectionModel = new SingleSelectionModel<TaskDTO>();

    private Long selectedTaskTemplateId;
    private String userName;
    private SprintDTO selectedSprint;
    private GroupDTO selectedGroup;

    @UiField
    TextArea result;

    @UiField
    TextArea taskText;

    @UiField
    TextArea code;

    @UiField
    Strong sprintRate;

    @UiField
    Strong courseRate;

    @UiField
    Button serverAvailBtn;

    @UiField
    Heading user;

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
    ValueListBox<GroupDTO> groupsListBox = new ValueListBox<GroupDTO>(new AbstractRenderer<GroupDTO>() {
        @Override
        public String render(GroupDTO sprintDTO) {
            if (sprintDTO == null) {
                return "";
            } else {
                return sprintDTO.getName();
            }
        }
    });

    Column<TaskDTO, String> statusCol;

    ListDataProvider<TaskDTO> dataProvider = new ListDataProvider<TaskDTO>();

    public SprintDTO getSelectedSprint() {
        return selectedSprint;
    }

    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, UserTasksTabPanel> {
    }

//    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);
    private TasksServiceAsync tasksService = GWT.create(TasksService.class);

    public UserTasksTabPanel(final UiBinder<Widget, UserTasksTabPanel> binder) {
        initWidget(binder.createAndBindUi(this));
        buildTable();
        loadGroups();

        // Create a new timer that calls Window.alert().
        Timer t = new Timer() {
            @Override
            public void run() {
                List<Long> ids = new ArrayList<Long>();
                for (TaskDTO taskDTO : dataProvider.getList()) {
                    if (taskDTO.getType().equals(TaskType.SVN.name())) {
                        ids.add(taskDTO.getId());
                    }
                }
                if (!ids.isEmpty()) {
                    refreshTasks(ids);
                }
            }
        };

        t.scheduleRepeating(5000);
    }

    private void refreshTasks(List<Long> ids) {

        final AsyncCallback<List<TaskDTO>> tasksCallback = new AsyncCallback<List<TaskDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                serverAvailBtn.setText("Сервер недоступен");
                serverAvailBtn.setType(ButtonType.DEFAULT);
                serverAvailBtn.setEnabled(false);
//                Window.alert("Ошибка получения имени пользователя с сервера ");
            }

            @Override
            public void onSuccess(List<TaskDTO> tasks) {
                serverAvailBtn.setText("Сервер доступен");
                serverAvailBtn.setType(ButtonType.SUCCESS);
                serverAvailBtn.setEnabled(true);
                boolean changed = false;
                for (TaskDTO taskDTO : dataProvider.getList()) {
                    for (TaskDTO serverTask : tasks) {
                        if (taskDTO.equals(serverTask) && !taskDTO.getStatus().equals(serverTask.getStatus())) {
//                            Window.alert(String.valueOf("Changed: " + taskDTO.getName() + taskDTO.getStatus()));
                            changed = true;
                            taskDTO.setStatus(serverTask.getStatus());
                            taskDTO.setResult(serverTask.getResult());
                        }
                    }
                }
//                Window.alert(String.valueOf("Changed: " + changed));
                if (changed) {
                    result.setText(selectionModel.getSelectedObject().getResult());
                    cellTable.redraw();
                }
            }
        };
        tasksService.refreshTasks(ids, tasksCallback);
    }

    private void loadGroups() {
        final AsyncCallback<List<GroupDTO>> callback = new AsyncCallback<List<GroupDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка загрузки групп с сервера ");
                Window.alert(caught.getClass().getName());
                Throwable cause = caught.getCause();
                String errorMessage = "";
                if (cause == null) {
                    errorMessage = caught.getCause().getMessage();
                }
                Window.alert("Ошибка загрузки групп с сервера " + errorMessage);
                Window.Location.reload();
            }

            @Override
            public void onSuccess(List<GroupDTO> groups) {
                GroupDTO currentGroup = null;
                groupsListBox.setAcceptableValues(groups);

                for (GroupDTO group : groups) {
                    currentGroup = group;
                }
                groupsListBox.setValue(currentGroup);
                selectedGroup = currentGroup;
                getUserName();
                RootPanel.CABINET_EVENT_BUS.fireEvent(new GroupEvent());
            }
        };

        tasksService.getUserGroups(callback);
    }

    private void getUserName() {
        final AsyncCallback<String> userCallback = new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка получения имени пользователя с сервера ");
//                Window.alert(caught.getClass().getName());
                Throwable cause = caught.getCause();
                String errorMessage = "";
                if (cause == null) {
                    errorMessage = caught.getCause().getMessage();
                }
                Window.alert("Ошибка получения имени пользователя с сервера " + errorMessage);
                Window.Location.reload();
            }

            @Override
            public void onSuccess(String userName) {
                if (userName == null) {
                    Window.alert("Ошибка: Пользователь не определен");
                    return;
                }
                UserTasksTabPanel.this.userName = userName;
                user.setText('[' + userName + ']' + " - ваши задания");
                loadSprintsAndTasks();
            }
        };

        tasksService.getSpringUserName(userCallback);
    }

    private void updateCourseRate() {
        final AsyncCallback<Double> courseRateCallback = new AsyncCallback<Double>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка получения общего рейтинга с сервера ");
//                Window.alert(caught.getClass().getName());
                Throwable cause = caught.getCause();
                String errorMessage = "";
                if (cause == null) {
                    errorMessage = caught.getCause().getMessage();
                }
                Window.alert("Ошибка получения общего рейтинга с сервера " + errorMessage);
                Window.Location.reload();
            }

            @Override
            public void onSuccess(Double rate) {
                if (rate == null) {
                    Window.alert("Ошибка получения общего рейтинга пользователя");
                    return;
                }
                if (rate < 50) {
                    courseRate.setStyleName("alert");
                } else if (rate > 50 && rate < 70) {
                    courseRate.setStyleName("muted");
                } else {
                    courseRate.setStyleName("success");
                }
                courseRate.setText(String.valueOf(rate));
            }
        };

        tasksService.getCourseRate(selectedGroup.getId(), UserTasksTabPanel.this.userName, courseRateCallback);
    }

    private void updateSprintRate() {
        final AsyncCallback<Double> sprintRateCallback = new AsyncCallback<Double>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка получения недельного рейтинга с сервера ");
//                Window.alert(caught.getClass().getName());
                Throwable cause = caught.getCause();
                String errorMessage = "";
                if (cause == null) {
                    errorMessage = caught.getCause().getMessage();
                }
                Window.alert("Ошибка получения недельного рейтинга с сервера " + errorMessage);
                Window.Location.reload();
            }

            @Override
            public void onSuccess(Double rate) {
                if (rate == null) {
                    Window.alert("Ошибка получения недельного рейтинга пользователя");
                    return;
                }
                if (rate < 50) {
                    sprintRate.setStyleName("alert");
                } else if (rate > 50 && rate < 70) {
                    sprintRate.setStyleName("muted");
                } else {
                    sprintRate.setStyleName("success");
                }
                sprintRate.setText(String.valueOf(rate));
            }
        };
        tasksService.getSprintRate(selectedGroup.getId(), selectedSprint.getId(),
                UserTasksTabPanel.this.userName, sprintRateCallback);
    }

    @UiHandler("refreshTasksBtn")
    public void onClick(ClickEvent e) {
        result.setText("");
//        taskText.setText("");
        loadSprintsAndTasks();
    }

    @UiHandler("sprintsListBox")
    public void onChangeTabPosition(ValueChangeEvent<SprintDTO> sprint) {
        result.setText("");
        taskText.setText("");
        selectedSprint = sprint.getValue();
        addTasksToTable(sprint.getValue().getTasks(), true);
        updateSprintRate();
    }

    @UiHandler("groupsListBox")
    public void onChangeGroupPosition(ValueChangeEvent<GroupDTO> group) {
        result.setText("");
        taskText.setText("");
        selectedGroup = group.getValue();
        loadSprintsAndTasks();
    }

    private void loadSprintsAndTasks() {
        final AsyncCallback<ArrayList<SprintDTO>> callback = new AsyncCallback<ArrayList<SprintDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка загрузки этапов с сервера ");
//                Window.alert(caught.getClass().getName());
                Throwable cause = caught.getCause();
                String errorMessage = "";
                if (cause == null) {
                    errorMessage = caught.getCause().getMessage();
                }
                Window.alert("Ошибка загрузки этапов с сервера " + errorMessage);
                Window.Location.reload();
            }

            @Override
            public void onSuccess(ArrayList<SprintDTO> sprints) {
                SprintDTO currentSprint;
                sprintsListBox.setAcceptableValues(sprints);
                Iterator<SprintDTO> iterator = sprints.iterator();
                if (iterator.hasNext()) {
                    currentSprint = iterator.next();
                    addTasksToTable(currentSprint.getTasks(), true);
                    sprintsListBox.setValue(currentSprint);
                    selectedSprint = currentSprint;
                }
                updateCourseRate();
                updateSprintRate();
            }
        };

        tasksService.getSprints(selectedGroup.getId(), callback);
    }

    private void addTasksToTable(List<TaskDTO> tasks, boolean isSelectLast) {
        final List<TaskDTO> list = dataProvider.getList();
        list.clear();
        TaskDTO last = null;
        for (TaskDTO task : tasks) {
            list.add(task);
            last = task;
        }
        if (isSelectLast && last != null) {
            selectionModel.setSelected(last, true);
        }
    }

    public void buildTable() {
        dataProvider.addDataDisplay(cellTable);
        TextColumn<TaskDTO> nameColumn = new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO contact) {
                return contact.getName();
            }
        };
        // Make the name column sortable
        nameColumn.setSortable(true);

        TextColumn<TaskDTO> textColumn = new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO contact) {
                if (contact.getText() != null) {
                    return contact.getText().substring(0, 51);
                }
                return "";
            }
        };

        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        TaskDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            //restructureTable(selected.getName());
                            result.setText(selected.getResult());
                            taskText.setText(selected.getText());
                            //Window.alert("Select: " + selected);
                            if (selected.getResult() != null && !selected.getResult().isEmpty()) {
                                code.setText(selected.getCode());
                            } else {
                                code.setText(selected.getMaterial());
                            }
                            selectedTaskTemplateId = selected.getTaskTemplateId();
                            RootPanel.CABINET_EVENT_BUS.fireEvent(new NotificationEvent());
                        }
                    }
                }
        );

        // Add the columns.
        cellTable.addColumn(nameColumn, "Название");
        cellTable.addColumn(textColumn, "Текст задания");
        final SelectionCell cell = new SelectionCell(getAcceptableValues()) {

            @Override
            public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {

                super.onBrowserEvent(context, parent, value, event, valueUpdater);

                if (BrowserEvents.CHANGE.equals(event.getType())) {
//                    Window.alert(event.getEventTarget().toString());
//                    Window.alert(event.getCurrentEventTarget().toString());
//                    Window.alert(event.getRelatedEventTarget().toString());

/*                    SelectElement select = parent.getFirstChild().cast();
                    String newValue = select.getValue();

                    final TaskDTO dto = selectionModel.getSelectedObject();
                    if(dto == null){
                        Window.alert("Не выбрано ни одного задания!");
                        return;
                    }
                    if(!dto.getType().equals(TaskType.SVN.name())) {
                        Window.alert("Для проверки измените статус задания на \"TEST\"");
                        return;
                    }
                    dto.setStatus(newValue);*/

                    /*tasksService.taskStatusChanged(dto, new AsyncCallback<Void>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            Window.alert("Status changed with error");
                        }

                        @Override
                        public void onSuccess(Void resTaskDto) {
                            //Window.alert("Status changed successfully");
                        }
                    });*/
                }
            }
        };

        statusCol = new Column<TaskDTO, String>(cell) {

            @Override
            public String getValue(TaskDTO taskDto) {
//                Window.alert(taskDto.toString());
                return taskDto.getStatus();
            }
        };
        statusCol.setFieldUpdater(new FieldUpdater<TaskDTO, String>() {
            @Override
            public void update(int i, final TaskDTO taskDTO, String status) {
//                Window.alert("dispatchTaskChecking " + taskDTO);
                taskDTO.setStatus(status);
                //taskDTO.setText("");
                taskDTO.setResult("");
                if (taskDTO.getType().equals(TaskType.CLASS.name())) {
                    taskDTO.setCode(code.getText());
                }
//                Window.alert("dispatchTaskChecking " + taskDTO);
                tasksService.dispatchTaskChecking(taskDTO, null);
            }
        });
        cellTable.addColumn(statusCol, "Статус");

/*        Column<TaskDTO, String> buttonTestCol = new Column<TaskDTO, String>(new ButtonCell(ButtonType.WARNING)) {
            @Override
            public String getValue(TaskDTO object) {
                return "Проверить";
            }
        };

        buttonTestCol.setFieldUpdater(new FieldUpdater<TaskDTO, String>() {
            @Override
            public void update(int index, final TaskDTO taskDTO, String value) {
                if(!taskDTO.getType().equals(TaskType.CLASS.name())) {
                    Window.alert("Для проверки нажмите кнопку \"Проверить\"");
                    return;
                }

                taskDTO.setCode(code.getText());
                final AsyncCallback<String> callback = new AsyncCallback<String>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(String testResult) {
                       // Window.alert(testResult.toString());
                        taskDTO.setResult(testResult);
                        result.setText(testResult);
                        //dataProvider.flush();
                        dataProvider.refresh();
                        restructureTable(null);
                    }
                };
                taskDTO.setText("");
                taskDTO.setResult("");

                tasksService.postForTest(taskDTO, callback);
            }
        });

        cellTable.addColumn(buttonTestCol);*/

        TextColumn<TaskDTO> resultColumn = new TextColumn<TaskDTO>() {

            @Override
            public String getValue(TaskDTO taskDto) {
                if (taskDto == null) {
                    return "null";
                }
                int newLineMarkIdx = taskDto.getResult().indexOf('\n');
                String resStr;
                if (newLineMarkIdx < 0) {
                    resStr = taskDto.getResult();
                } else {
                    resStr = taskDto.getResult().substring(0, newLineMarkIdx);
                }
                //  Window.alert("!!! "+resStr + taskDto);
                return resStr;//taskDto.getResult().substring(0, newLineMarkIdx);
            }
        };

        cellTable.addColumn(resultColumn, "Результат");
    }

    private void restructureTable(String taskName) {
        final int TEST_COLUMN = 3;
        final int STATUS_COLUMN = 2;
        for (int i = 0; i < cellTable.getRowCount(); i++) {
            String nameText = cellTable.getRowElement(i).getInnerText();
            if (taskName == null || nameText.contains(taskName)) {
                int columnIndex = TEST_COLUMN;
                TaskDTO taskDTO = cellTable.getVisibleItem(i);
                //Window.alert("taskDTO = "+taskDTO.toString());
                if (TaskType.CLASS.name().equals(taskDTO.getType())) {
                    columnIndex = STATUS_COLUMN;
                }
                cellTable.getRowElement(i).deleteCell(columnIndex);
                cellTable.getRowElement(i).insertCell(columnIndex);
            }
        }
    }

    private List<String> getAcceptableValues() {
        return Arrays.asList("NEW", "IN_PROGRESS", "TEST");
    }

    public enum TaskType {
        SVN, CLASS
    }

    public Long getSelectedTaskTemplateId() {
        return selectedTaskTemplateId;
    }

    public GroupDTO getSelectedGroup() {
        return selectedGroup;
    }
}

