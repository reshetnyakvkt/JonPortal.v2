package ua.com.jon.cabinet.client.components;

import com.github.gwtbootstrap.client.ui.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.dom.client.ClickEvent;
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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.client.TasksServiceAsync;
import ua.com.jon.cabinet.shared.GroupDTO;
import ua.com.jon.cabinet.shared.GroupEvent;
import ua.com.jon.cabinet.shared.GroupEventHandler;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 19.04.14
 */
public class GroupInfoTabPanel extends Composite {
    @UiField
    Button refreshGroupsBtn = new Button();

    @UiField
    ProgressBar sprintsProgress;

    @UiField
    Strong groupRate;

    final SingleSelectionModel<List<String>> selectionModel = new SingleSelectionModel<List<String>>();

    @UiField(provided = true)
    CellTable<List<String>> studentsGrid = new CellTable<List<String>>(25, GWT.<CellTable.SelectableResources>create(CellTable.SelectableResources.class));

    /*@UiField
    DataGrid<List<String>> studentsGrid = new DataGrid<List<String>>(20, GWT.<DataGrid.SelectableResources>create(DataGrid.SelectableResources.class));
    */

    @UiField(provided=true)
    ValueListBox<GroupDTO> groupsListBox = new ValueListBox<GroupDTO>(new AbstractRenderer<GroupDTO>() {
        @Override
        public String render(GroupDTO sprintDTO) {
            if(sprintDTO == null) {
                return "";
            } else {
                return sprintDTO.getName();
            }
        }
    });

    private ListDataProvider<List<String>> dataProvider = new ListDataProvider<List<String>>();

    private TasksServiceAsync taskService = GWT.create(TasksService.class);

    private UserTasksTabPanel userPanel;

    private final int weeksCount = 14;

    public GroupInfoTabPanel(final UiBinder<Widget, GroupInfoTabPanel> binder, UserTasksTabPanel userPanel) {
        this.userPanel = userPanel;
        initWidget(binder.createAndBindUi(this));
        studentsGrid.setEmptyTableWidget(new Label("Please add data."));

        try {
            loadGroups();
            loadGroupInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RootPanel.CABINET_EVENT_BUS.addHandler(GroupEvent.TYPE, new GroupEventHandler()     {
            @Override
            public void onGroupChanged(GroupEvent groupEvent) {
                List<List<String>> sprintNames = null;
                try {
                    loadGroupInfo();
                } catch (Exception e) {
                    Window.alert(e.getMessage() + e.getCause().getMessage());
                    e.printStackTrace();
                }
                //buildTable(sprintNames);
                //addSprintsToTable(sprintNames);
            }
        });
    }

    private void loadGroups() {
        final AsyncCallback<List<GroupDTO>> groupCallback = new AsyncCallback<List<GroupDTO>>() {
            private static final int RATE_INDEX = 1;
            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                Window.alert("Error callback loadGroups()");
            }

            @Override
            public void onSuccess(List<GroupDTO> groups) {
                groupsListBox.setValue(null);
                groupsListBox.setAcceptableValues(groups);

            }
        };

        taskService.getUserGroups(groupCallback);
    }

    public void buildTable(List<List<String>> sprints) {
        final int userNameIdx = 0;
        final int globalRateIdx = 1;
        studentsGrid.setEmptyTableWidget(new Label("Please add data."));

        studentsGrid.addColumn(new TextColumn<List<String>>() {
            @Override
            public String getValue(List<String> userList) {
                return String.valueOf(userList.get(userNameIdx));
            }
        }, "Студент");


        studentsGrid.addColumn(new TextColumn<List<String>>() {
            @Override
            public String getValue(List<String> userList) {
                return String.valueOf(userList.get(globalRateIdx));
            }
        }, "Общий рейтинг");

        //int i = 0;

        for (int i=2; i < weeksCount; i++) {
            //final String session = sprints.get(i).get(888);
            final int sprintIdx = i;
            studentsGrid.addColumn(new TextColumn<List<String>>() {
                //private int columnIdx = sprintIdx;
                @Override
                public String getValue(List<String> userList) {
                    //Window.alert(userList.toString());
                    if (userList.size() <= sprintIdx) {
                        return "0";
                    }
                    return userList.get(sprintIdx);
                }
            }, String.valueOf(sprintIdx - 1));
        }

        dataProvider.addDataDisplay(studentsGrid);
    }

    private void clearTable(CellTable<List<String>> table) {
        table.setRowCount(0);
        Window.alert("columns: " + table.getColumnCount());
        NodeList<Element> colGroups = table.getElement().getElementsByTagName("colgroup");

        for (int i = 0; i < colGroups.getLength(); i++) {
            table.removeColumn(0);
        }
        for (int i = 0; i < colGroups.getLength(); i++) {
            Element colGroupEle = colGroups.getItem(i);
            NodeList<Element> colList = colGroupEle.getElementsByTagName("col");

            for (int j = colList.getLength()-1; j >= table.getColumnCount(); j--) {
                colGroupEle.removeChild(colList.getItem(j));
            }
        }
        Window.alert("columns: " + table.getColumnCount());
        dataProvider.flush();
        dataProvider.refresh();
        studentsGrid.redraw();
    }

    private void loadGroupInfo() throws Exception {

        final AsyncCallback<List<List<String>>> groupCallback = new AsyncCallback<List<List<String>>>() {
            private static final int RATE_INDEX = 1;
            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(List<List<String>> groupInfo) {
                sprintsProgress.setVisible(false);

                int grpRate = 0;
                for (List<String> user : groupInfo) {
                    grpRate += Integer.parseInt(user.get(RATE_INDEX));
                }
                grpRate /= groupInfo.size();
                groupRate.setText(String.valueOf(grpRate));
                Collections.sort(groupInfo, new Comparator<List<String>>() {

                    @Override
                    public int compare(List<String> o1, List<String> o2) {
                        int firstRate = Integer.parseInt(o1.get(RATE_INDEX));
                        int secondRate = Integer.parseInt(o2.get(RATE_INDEX));
                        return firstRate - secondRate;
                    }
                });
//                Window.alert(groups.toString());
                addSprintsToTable(groupInfo);
                buildTable(groupInfo);
            }
        };

        taskService.getGroupInfo(userPanel.getSelectedGroup().getId(), groupCallback);
//        return null;
    }

    private void loadGroupAndUsers() throws Exception {
        Long groupId = userPanel.getSelectedGroup().getId();
        fillTable(groupId);
/*
        final AsyncCallback<ArrayList<UserDTO>> groupCallback = new AsyncCallback<ArrayList<UserDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                Window.alert("Error callback loadGroupAndUsers");
            }

            @Override
            public void onSuccess(ArrayList<UserDTO> usersDTOs) {

                sprintsProgress.setVisible(false);
                for (UserDTO userDTO : usersDTOs) {
                    Window.alert(userDTO.toString());
                }
                List<List<String>> sprintNames = new ArrayList<List<String>>();
                // TODO fill
                if(usersDTOs != null && usersDTOs.size() > 0) {
                    Set<String> sprints = usersDTOs.get(0).getMarks().keySet();

                    for (String sprintName : sprints) {
                        sprintNames.add(Arrays.asList(sprintName));
                    }
                    addSprintsToTable(sprintNames);
                    //buildTable(sprints);
                }
            }
        };
*/
/*        if(userPanel.getSelectedTaskTemplateId() != null) {
            taskService.getTasksByUserGroup(userPanel.getSelectedTaskTemplateId(), userPanel.getSelectedGroup().getId(),
                    userPanel.getSelectedSprint().getId(), groupCallback);
        }*/
/*
        HashMap<String, Integer> marks = new HashMap<String, Integer>();
        marks.put("1", 50);
        marks.put("2", 75);
        marks.put("3", 100);
        HashMap<String, Boolean> presents = new HashMap<String, Boolean>();
        presents.put("1", true);
        presents.put("2", false);
        presents.put("3", true);
        UserDTO user1 = new UserDTO("user1", marks, presents);
        ArrayList<UserDTO> usersDTOs = new ArrayList<UserDTO>();
        usersDTOs.add(user1);
        groupCallback.onSuccess(usersDTOs);
*/
    }

    private void fillTable(Long groupId) throws Exception {
        final AsyncCallback<List<List<String>>> groupCallback = new AsyncCallback<List<List<String>>>() {
            private static final int RATE_INDEX = 1;
            @Override
            public void onFailure(Throwable caught) {
                sprintsProgress.setVisible(false);
                Window.alert("Error callback groupsListBox");
            }

            @Override
            public void onSuccess(List<List<String>> groupInfo) {
//                Window.alert(groups.toString());
                sprintsProgress.setVisible(false);
                int grpRate = 0;
                for (List<String> user : groupInfo) {
                    grpRate += Integer.parseInt(user.get(RATE_INDEX));
                }
                grpRate /= groupInfo.size();
                groupRate.setText(String.valueOf(grpRate));
                Collections.sort(groupInfo, new Comparator<List<String>>() {

                    @Override
                    public int compare(List<String> o1, List<String> o2) {
                        int firstRate = Integer.parseInt(o1.get(RATE_INDEX));
                        int secondRate = Integer.parseInt(o2.get(RATE_INDEX));
                        return firstRate - secondRate;
                    }
                });
                addSprintsToTable(groupInfo);
//                buildTable(groups);
            }
        };


        taskService.getGroupInfo(groupId, groupCallback);
    }

    private void addSprintsToTable(List<List<String>> tasks) {
/*        int sprintCount = 0;
        for (List<String> task : tasks) {
            if (task.size() > sprintCount) {
                sprintCount = task.size();
            }
        }
        for (List<String> task : tasks) {
            while (task.size() < sprintCount) {
                task.add(1, "0");
            }
        }*/
        dataProvider.setList(tasks);
        //studentsGrid.setSelectionModel(selectionModel);
        //dataProvider.addDataDisplay(studentsGrid);
/*
        TaskDTO last = null;
        for (TaskDTO task : tasks) {
            list.add(task);
            last = task;
        }
*/
//        if(isSelectLast && last != null) {
//            selectionModel.setSelected(last, true);
//        }
    }

    @UiHandler("groupsListBox")
    public void selectGroup(ValueChangeEvent<GroupDTO> group) {
        try {
            fillTable(group.getValue().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @UiHandler("refreshGroupsBtn")
    public void refreshSprintsHandler(ClickEvent e) {
        try {
            loadGroupAndUsers();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
/*
        try {
            loadGroupInfo();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
*/
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
