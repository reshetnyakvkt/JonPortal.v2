package ua.com.jon.admin.client.components.list;

import com.github.gwtbootstrap.client.ui.NavHeader;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.WellNavList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import ua.com.jon.admin.client.components.list.SelectionHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import ua.com.jon.admin.shared.SprintDTO;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/12/13
 */
public class SelectableNavList<T extends Nameble> extends Composite {
    @UiField
    WellNavList navList = new WellNavList();

    private ArrayList<T> modelList;
    private NavHeader header;

    public SelectableNavList() {
        this.modelList = new ArrayList<T>();
        VerticalPanel panel = new VerticalPanel();
        panel.add(navList);

        initWidget(panel);
    }

    public void addSprintsToSprintNavList(ArrayList<T> sprints, String sprintName) {
        if (sprints != null) {
            this.modelList = sprints;
            for (T sprintDTO : modelList) {
                String name = sprintDTO.getName();
                NavLink sprintNL = new NavLink(name);
                sprintNL.addClickHandler(onlyOneHandler);
                if(name.equals(sprintName)) {
                    sprintNL.setActive(true);
                }
                navList.add(sprintNL);
            }
        }
    }

    private ClickHandler onlyOneHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent clickEvent) {

            Widget icon = (Widget) clickEvent.getSource();
            // TODO check unique selection
            NavLink navLink = (NavLink) icon.getParent();
            if (navLink.isActive()) {
                navLink.setActive(false);
            } else {
                Window.alert("Fire Selection event");
//                navList.fireEvent(new SelectionEvent());
                navLink.setActive(true);
            }
        }
    };

    public ArrayList<T> getModel() {
        return modelList;
    }

    public void setHeader(String headerName) {
        this.header = new NavHeader(headerName);
        navList.add(header);
    }

    public boolean add(T item) {
        boolean addResult = modelList.add(item);
        if(addResult) {
            navList.add(new NavLink(item.getName()));
        }
        return addResult;
    }

    public void add(Widget child) {
        Window.alert(child.toString());
        navList.add(child);
    }

/*    public HandlerRegistration addSelectionHandler(SelectionHandler handler) {

        return navList.addHandler(handler, SelectionEvent.TYPE);
    }*/
}
