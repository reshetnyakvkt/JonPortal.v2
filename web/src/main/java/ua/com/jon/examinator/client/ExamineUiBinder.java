package ua.com.jon.examinator.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.CodeBlock;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.ProgressBar;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.examinator.shared.SprintDTO;
import ua.com.jon.examinator.shared.TaskDTO;
import ua.com.jon.examinator.shared.TaskHistoryDto;

import java.util.ArrayList;
import java.util.List;

public class ExamineUiBinder extends Composite {
    @UiField
    CellTable<TaskDTO> cellTable = new CellTable<TaskDTO>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));//new CellTable<TaskDTO>();

    final SingleSelectionModel<TaskDTO> selectionModel = new SingleSelectionModel<TaskDTO>();
//    @UiField
//    DropdownButton sprintBtn = new DropdownButton();

    @UiField
    TextArea result;

    @UiField
    TextArea taskText;

    @UiField
    TextArea code;

    @UiField
    TextBox userName;

    @UiField
    TextBox hash;

    @UiField(provided=true)
    ValueListBox<SprintDTO> sprintsListBox = new ValueListBox<SprintDTO>(new AbstractRenderer<SprintDTO>() {
        @Override
        public String render(SprintDTO sprintDTO) {
            if(sprintDTO == null) {
                return "";
            } else {
                return sprintDTO.getName();
            }
        }
    });

    @UiField
    Modal findTaskModal = new Modal();

    @UiField
    Button findTaskBtn = new Button();

    @UiField
    CodeBlock codeBlock = new CodeBlock();

    @UiField
    ProgressBar taskFindProgress = new ProgressBar();

    private boolean isTestButtonsDisabled;

    interface ExampleUiBinderUiBinder extends UiBinder<HTMLPanel, ExamineUiBinder> {
    }

    private static ExampleUiBinderUiBinder ourUiBinder = GWT.create(ExampleUiBinderUiBinder.class);
    private ua.com.jon.examinator.client.ExamineServiceAsync tasksService = GWT.create(ExamineService.class);
    private ListDataProvider<TaskDTO> dataProvider = new ListDataProvider<TaskDTO>();

    public ExamineUiBinder() {
        initWidget(ourUiBinder.createAndBindUi(this));
        buildTable();
        loadSprintsAndTasks();
        hash.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event_) {
                boolean enterPressed = KeyCodes.KEY_ENTER == event_
                        .getNativeEvent().getKeyCode();
                if (enterPressed) {
                    findTaskHandler(null);
                }
            }
        });
//        selectFirstTaskIfExists();
//        loadTasksToTable();
//        alert.setVisible(false);
    }

    private void selectFirstTaskIfExists() {
        for (TaskDTO task : selectionModel.getSelectedSet()) {
            Window.alert("selected task: " + task);
            selectionModel.setSelected(task, true);
            break;
        }
    }

//    }

    //    @UiHandler("submit")
//    void handleClick(ClickEvent e) {
//        Window.alert("Hello, UiBinder");
//    }
//    @UiHandler("sprintBtn")
//    void handleClick(ClickEvent e) {
//
    @UiHandler("refreshTasksBtn")
    public void onClick(ClickEvent e) {
        result.setText("");
        taskText.setText("");
        loadSprintsAndTasks();
    }

    @UiHandler("sprintsListBox")
    public void onChangeTabPosition(ValueChangeEvent<SprintDTO> sprint) {
        result.setText("");
        taskText.setText("");
        addTasksToTable(sprint.getValue().getTasks(), true);
//        selectFirstTaskIfExists();
    }

    @UiHandler("showTaskBtn")
    public void showTaskFindModalHandler(ClickEvent e) {
        taskFindProgress.setVisible(false);
        codeBlock.setVisible(false);
        hash.setText("");
        findTaskModal.show();
    }

    @UiHandler("findTaskBtn")
    public void findTaskHandler(ClickEvent e) {
        if (!hash.getText().isEmpty()) {
            final AsyncCallback<TaskHistoryDto> callback = new AsyncCallback<TaskHistoryDto>() {

                @Override
                public void onFailure(Throwable caught) {
                    taskFindProgress.setVisible(false);
                    codeBlock.setVisible(false);
                    Window.alert("Ошибка получения решения по коду");
                }

                @Override
                public void onSuccess(TaskHistoryDto task) {
                    taskFindProgress.setVisible(false);
                    codeBlock.setVisible(true);
                    codeBlock.setText(task.getCode());
                }
            };

            taskFindProgress.setVisible(true);
            tasksService.getTaskHistoryByHash(hash.getText().trim(), callback);
        } else {
            Window.alert("Введите код решения");
        }
    }

    private void loadSprintsAndTasks() {
        final AsyncCallback<ArrayList<SprintDTO>> callback = new AsyncCallback<ArrayList<SprintDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка загрузки задач с сервера");
            }

            @Override
            public void onSuccess(ArrayList<SprintDTO> sprints) {
                sprintsListBox.setAcceptableValues(sprints);

//                SprintDTO lastSprint = null;
                if (sprints.size() >= 1 && sprints.get(0) != null) {
                    sprintsListBox.setValue(sprints.get(0));
                    addTasksToTable(sprints.get(0).getTasks(), true);
                }
/*
                for (int i = 0; i < sprints.size(); i++) {
                    lastSprint = sprints.get(i);
                }
                if (lastSprint != null) {
                    sprintsListBox.setValue(lastSprint);
                    addTasksToTable(lastSprint.getTasks(), true);
                }
*/
            }
        };

        tasksService.getSprints(callback);

    }

    private void loadTasksToTable() {
        final AsyncCallback<ArrayList<TaskDTO>> callback = new AsyncCallback<ArrayList<TaskDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error callback");
            }

            @Override
            public void onSuccess(ArrayList<TaskDTO> tasks) {
                addTasksToTable(tasks, true);
            }
        };

        tasksService.getUserTasks(callback);
    }

    private void addTasksToTable(List<TaskDTO> tasks, boolean isSelectFirst) {
        dataProvider.setList(tasks);
        cellTable.setRowData(tasks);


        // Connect the table to the data provider.
        dataProvider.addDataDisplay(cellTable);
        final List<TaskDTO> list = dataProvider.getList();
        TaskDTO first = null;
/*        for (TaskDTO task : tasks) {
            list.add(task);
            last = task;
        }*/
        if (isSelectFirst && tasks.iterator().hasNext() &&
                (first = tasks.iterator().next()) != null) {
            selectionModel.setSelected(first, true);
        }

/*        if(isSelectFirst && first != null) {
            selectionModel.setSelected(first, true);
        }*/
    }

    public void buildTable() {
//        String jSessionId= Cookies.getCookie("JSESSIONID");
//        Window.alert("" + Cookies.getCookieNames());
//        userName.setText(jSessionId);
        TextColumn<TaskDTO> nameColumn = new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO contact) {
                return contact.getName();
            }
        };
        // Make the name column sortable.
        nameColumn.setSortable(true);

        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        TaskDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            result.setText(selected.getResult());
                            taskText.setText(selected.getText());
                        }
                    }
                });

        // Add the columns.
        cellTable.addColumn(nameColumn, "Название");

/*        TextColumn<TaskDTO> addressColumn = new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO contact) {
                return contact.getText();
            }
        };
        cellTable.addColumn(addressColumn, "Текст задания");*/

        final StyledButtonCell styledButtonCell = new StyledButtonCell();
        Column<TaskDTO, String> buttonTestCol = new Column<TaskDTO, String>(styledButtonCell) {
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

                if (code.getText().isEmpty()) {
                    String testResult = "0\nОшибка компиляции: Тело класса не может быть пустым";
                    taskDTO.setResult(testResult);
                    result.setText(testResult);
                    styledButtonCell.setDisabled(false);
                    cellTable.redraw();
                    return;
                }

                if (userName.getText().isEmpty()) {
                    userName.setFocus(true);
                    result.setText("Проверка не выполена, не введено имя пользователя");
                    cellTable.redraw();
                    return;
                }

                if (userName.getText().length() >= 20) {
                    userName.setFocus(true);
                    result.setText("Проверка не выполена, имя пользователя не может превышать 20 символов");
                    cellTable.redraw();
                    return;
                }

                taskDTO.setCode(code.getText());
                final AsyncCallback<String> callback = new AsyncCallback<String>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        styledButtonCell.setDisabled(false);
                        cellTable.redraw();
                        Window.alert("Внутренняя ошибка. Обратитесь к разработчикам");
                    }

                    @Override
                    public void onSuccess(String testResult) {
//                        Window.alert(String.valueOf(isTestButtonsDisabled));
                        taskDTO.setResult(testResult);
                        result.setText(testResult);
                        //dataProvider.flush();
                        dataProvider.refresh();
                        isTestButtonsDisabled = false;
                        styledButtonCell.setDisabled(false);
                        cellTable.redraw();
                    }
                };
                if(taskDTO.getCode().length() > 50000) {
                    Window.alert("Исходный код задания не может быть больше 50000 символов");
                    return;
                }
                taskDTO.setText("");
                taskDTO.setResult("");
                styledButtonCell.setDisabled(true);
                cellTable.redraw();
                if(!isTestButtonsDisabled) {
                    isTestButtonsDisabled = true;
                    tasksService.postForTest(taskDTO, userName.getText(), callback);
                }
            }
        });

        cellTable.addColumn(buttonTestCol, "Проверка");

        TextColumn<TaskDTO> resultColumn = new TextColumn<TaskDTO>() {

            @Override
            public String getValue(TaskDTO taskDto) {
                int newLineMarkIdx = taskDto.getResult().indexOf('\n');
                return taskDto.getResult().substring(0, newLineMarkIdx);
            }
        };

        cellTable.addColumn(resultColumn, "Результат");
    }

    private void disableTestButtons() {

    }

    public enum TaskType {
        SVN, CLASS
    }
}

