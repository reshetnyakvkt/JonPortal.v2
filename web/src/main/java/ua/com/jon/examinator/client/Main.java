package ua.com.jon.examinator.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import ua.com.jon.examinator.shared.TaskDTO;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
public class Main implements EntryPoint {
//    private ExamineServiceAsync tasksService = GWT.create(ExamineService.class);
//    private Label greetingLabel = new Label("");

    @Override
    public void onModuleLoad() {
        RootPanel.get("slot1").add(new ExamineUiBinder());
//        createGetTasksCallback();
    }

/*
    private void createGetTasksCallback() {
        final AsyncCallback<ArrayList<TaskDTO>> callback = new AsyncCallback<ArrayList<TaskDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                greetingLabel.setText("ERROR!");
            }

            @Override
            public void onSuccess(ArrayList<TaskDTO> result) {
            }
        };

        tasksService.getUserTasks(callback);
    }
*/

//    public void buildTable() {
//
//
//        // Create name column.
//        TextColumn<TaskDTO> nameColumn = new TextColumn<TaskDTO>() {
//            @Override
//            public String getValue(TaskDTO contact) {
//                return contact.getName();
//            }
//        };
//
//        // Make the name column sortable.
//        nameColumn.setSortable(true);
//
//        // Create address column.
//        TextColumn<TaskDTO> addressColumn = new TextColumn<TaskDTO>() {
//            @Override
//            public String getValue(TaskDTO contact) {
//                return contact.getText();
//            }
//        };
//
//        // Add the columns.
//        cellTable.addColumn(nameColumn, "Name");
//        cellTable.addColumn(addressColumn, "Text");
//
//        ArrayList<TaskDTO> tasks = new ArrayList<TaskDTO>();
//        tasks.add(new TaskDTO("1", "do"));
//        tasks.add(new TaskDTO("2", "to do"));
//        tasks.add(new TaskDTO("3", "do not"));
//        tasks.add(new TaskDTO("4", "dodo"));
//        tasks.add(new TaskDTO("5", "dododo"));
//        cellTable.setRowData(tasks);
//        ListDataProvider<TaskDTO> dataProvider = new ListDataProvider<TaskDTO>();
//
//        // Connect the table to the data provider.
//        dataProvider.addDataDisplay(cellTable);
//        final List<TaskDTO> list = dataProvider.getList();
//        for (TaskDTO task : tasks) {
//            list.add(task);
//            Window.alert(task.toString());
//        }
//    }

}
