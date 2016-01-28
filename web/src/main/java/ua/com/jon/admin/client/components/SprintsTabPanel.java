package ua.com.jon.admin.client.components;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonCell;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.admin.client.AdminService;
import ua.com.jon.admin.client.AdminServiceAsync;
import ua.com.jon.admin.shared.SprintDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/4/13
 */
public class SprintsTabPanel extends Composite {
    public static final String INITIAL_SPRINT_NAME = "name";

    @UiField
    Button refreshSprintsBtn;

    @UiField
    Button createSprintBtn;

    @UiField
    Button saveSprintBtn;

    final SingleSelectionModel<SprintDTO> selectionModel = new SingleSelectionModel<SprintDTO>();

    @UiField(provided = true)
    CellTable<SprintDTO> cellTable = new CellTable<SprintDTO>(25, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));

    private ListDataProvider<SprintDTO> dataProvider = new ListDataProvider<SprintDTO>();

    private AdminServiceAsync adminService = GWT.create(AdminService.class);

    public SprintsTabPanel(final UiBinder<Widget, SprintsTabPanel> binder) {
        initWidget(binder.createAndBindUi(this));
        buildTable();
        loadSprints();
    }

    private void loadSprints() {
        final AsyncCallback<ArrayList<SprintDTO>> groupCallback = new AsyncCallback<ArrayList<SprintDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(ArrayList<SprintDTO> sprintDTOs) {
                addSprintsToTable(sprintDTOs);
            }
        };

        adminService.getSprintsAndTasks(groupCallback);

    }

    public void buildTable() {
        cellTable.setEmptyTableWidget(new Label("Please add data."));
        dataProvider.addDataDisplay(cellTable);

        Column<SprintDTO, String> nameColumn = new Column<SprintDTO, String>(new TextInputCell()) {
            @Override
            public String getValue(SprintDTO object) {
                return object.getName() == null ? "" : object.getName();
            }
        };
        nameColumn.setFieldUpdater(new FieldUpdater<SprintDTO, String>() {
            @Override
            public void update(int index, SprintDTO object, String value) {
        //        Window.alert(value);
                dataProvider.getList().get(index).setName(value);
                dataProvider.flush();
                dataProvider.refresh();
            }
        });
        cellTable.addColumn(nameColumn, "Название");

        SelectionCell cell = new SelectionCell(getAcceptableValues()) {

            @Override
            public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {

                super.onBrowserEvent(context, parent, value, event, valueUpdater);

                if (BrowserEvents.CHANGE.equals(event.getType())) {

                    SelectElement select = parent.getFirstChild().cast();
                    String newValue = select.getValue();

                    SprintDTO dto = selectionModel.getSelectedObject();

                    if(dto == null){
                        Window.alert("Не выбрано ни одного задания!");
                        return;
                    }
                    dto.setType(newValue);


                    adminService.sprintTypeChanged(dto, new AsyncCallback<Void>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            Window.alert("Status changed with error");
                        }

                        @Override
                        public void onSuccess(Void aVoid) {
//                            Window.alert("Status changed successfully");
                        }
                    });
                }
            }
        };

        cellTable.addColumn(new Column<SprintDTO, String>(cell) {

            @Override
            public String getValue(SprintDTO taskDto) {
                return taskDto.getType();
            }
        }, "Тип");

        cellTable.addColumn(new TextColumn<SprintDTO>() {
            @Override
            public String getValue(SprintDTO contact) {
                return String.valueOf(contact.isActive());
            }
        }, "Активность");

        Column<SprintDTO, String> buttonCol = new Column<SprintDTO, String>(new ButtonCell(IconType.REMOVE, ButtonType.DANGER)) {
            @Override
            public String getValue(SprintDTO object) {
                return "";
            }
        };
        buttonCol.setFieldUpdater(new FieldUpdater<SprintDTO, String>() {
            @Override
            public void update(int index, SprintDTO object, String value) {
                dataProvider.getList().remove(object);
                dataProvider.flush();
                dataProvider.refresh();
            }
        });
        cellTable.addColumn(buttonCol);

        cellTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(
                new SelectionChangeEvent.Handler() {
                    public void onSelectionChange(SelectionChangeEvent event) {
                        SprintDTO selected = selectionModel.getSelectedObject();
                        if (selected != null) {
                        }
                    }
                });

    }

    private void addSprintsToTable(List<SprintDTO> sprints) {

        dataProvider.setList(sprints);
        SprintDTO last = null;
        if (sprints.iterator().hasNext() &&
                (last = sprints.iterator().next()) != null) {
            selectionModel.setSelected(last, true);
        }
        dataProvider.flush();
        dataProvider.refresh();
    }

    private List<String> getAcceptableValues() {
        return Arrays.asList("IT_CENTRE", "ANONYMOUS");
    }

    @UiHandler("refreshSprintsBtn")
    public void handleRefreshClick(ClickEvent e) {
        loadSprints();
    }

    @UiHandler("createSprintBtn")
    void handleCreateClick(ClickEvent e) {
        String sprintName = INITIAL_SPRINT_NAME;
        SprintDTO sprint = new SprintDTO(null, sprintName, true, "IT_CENTRE", null, new Date());

        List<SprintDTO> sprintDTOs = dataProvider.getList();
        if(sprintDTOs.contains(sprint)) {
            Window.alert("Sprint with name is already exists: " + sprint.getName());
        } else {
            sprintDTOs.add(sprint);
            dataProvider.flush();
        }
    }

    @UiHandler("saveSprintBtn")
    void handleSaveClick(ClickEvent e) {
        final AsyncCallback sprintsCallback = new AsyncCallback() {

            @Override
            public void onFailure(Throwable caught) {
//                sprintsProgress.setVisible(false);
//                sprintNavList.setVisible(true);
                Window.alert("Error callback save sprints, " + caught);
            }

            @Override
            public void onSuccess(Object o) {
//                sprintsProgress.setVisible(false);
//                sprintNavList.setVisible(true);
            }
        };


        List<SprintDTO> list = dataProvider.getList();
        ArrayList<SprintDTO> listToSave = new ArrayList<SprintDTO>(list);
        adminService.saveSprints(listToSave, sprintsCallback);
    }
}
