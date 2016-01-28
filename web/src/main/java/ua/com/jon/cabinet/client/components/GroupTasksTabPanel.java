package ua.com.jon.cabinet.client.components;

import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.client.TasksServiceAsync;
import ua.com.jon.cabinet.shared.NotificationEvent;
import ua.com.jon.cabinet.shared.NotificationEventHandler;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 8/12/13
 */
public class GroupTasksTabPanel extends Composite {
    @UiField
    Button refreshTasksBtn = new Button();

    @UiField
    ProgressBar sprintsProgress;

    @UiField(provided = true)
    CellTable<TaskDTO> cellTable = new CellTable<TaskDTO>(5, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));

    //@UiField
    // TODO: remove it
    TextArea textArea = new TextArea();

    @UiField
    CodeBlock codeBlock = new CodeBlock();

    private SprintDTO currentSprint;
    private List<SprintDTO> loadedSprints = new ArrayList<SprintDTO>();

    private ListDataProvider<TaskDTO> dataProvider = new ListDataProvider<TaskDTO>();

    private TasksServiceAsync adminService = GWT.create(TasksService.class);

    private UserTasksTabPanel userPanel;

    public GroupTasksTabPanel(final UiBinder<Widget, GroupTasksTabPanel> binder, UserTasksTabPanel userPanel) {
        this.userPanel = userPanel;
        initWidget(binder.createAndBindUi(this));
        buildTable();
        loadTasks();

        RootPanel.CABINET_EVENT_BUS.addHandler(NotificationEvent.TYPE, new NotificationEventHandler()     {
            @Override
            public void onNotificationChanged(NotificationEvent authenticationEvent) {
                loadTasks();
            }
        });
    }

    public void buildTable() {
        cellTable.setEmptyTableWidget(new Label("Please add data."));
        dataProvider.addDataDisplay(cellTable);


        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                return String.valueOf(taskDTO.getName());
            }
        }, "Название");

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                return String.valueOf(taskDTO.getUserName());
            }
        }, "Студент");

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                String result = taskDTO.getResult();
                int newLinePos = result.indexOf('\n');
                return String.valueOf(taskDTO.getResult().substring(0, newLinePos));
            }
        }, "Оценка");

        cellTable.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public String getValue(TaskDTO taskDTO) {
                return String.valueOf(taskDTO.getRate());
            }
        }, "Рейтинг");

        final SingleSelectionModel<TaskDTO> selectionModel = new SingleSelectionModel<TaskDTO>();
        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        TaskDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                            String code = selected.getCode();
                            textArea.setText(code);
                            codeBlock.setLinenums(true);
                            codeBlock.setText(code);
                        }
                    }
                });
    }

    private void loadTasks() {
        final AsyncCallback<ArrayList<TaskDTO>> groupCallback = new AsyncCallback<ArrayList<TaskDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                Window.alert("Error callback taskByUserGroup");
            }

            @Override
            public void onSuccess(ArrayList<TaskDTO> taskDTOs) {
                sprintsProgress.setVisible(false);
                //Window.alert(taskDTOs.toString());
                addSprintsToTable(taskDTOs);
            }
        };
        if(userPanel.getSelectedTaskTemplateId() != null) {
            adminService.getTasksByUserGroup(userPanel.getSelectedTaskTemplateId(), userPanel.getSelectedGroup().getId(),
                    userPanel.getSelectedSprint().getId(), groupCallback);
        }
    }

    private void addSprintsToTable(List<TaskDTO> tasks) {
//        dataProvider.addDataDisplay(cellTable);
        dataProvider.setList(tasks);
    }

    @UiHandler("refreshTasksBtn")
    public void refreshSprintsHandler(ClickEvent e) {
        loadTasks();
    }

    private void relocateTasks(List<SprintDTO> loadedSprints) {
        for (SprintDTO loadedSprint : loadedSprints) {
            List<TaskDTO> newTasks = new ArrayList<TaskDTO>(loadedSprint.getTasks().size());
            for (TaskDTO taskTemplateDTO : loadedSprint.getTasks()) {
                newTasks.add(taskTemplateDTO);
            }
            loadedSprint.setTasks(newTasks);
        }
    }
}
