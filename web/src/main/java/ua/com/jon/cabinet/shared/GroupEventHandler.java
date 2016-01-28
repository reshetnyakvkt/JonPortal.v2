package ua.com.jon.cabinet.shared;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created by sergey on 12.07.14.
 */
public interface GroupEventHandler extends EventHandler {

    void onGroupChanged(GroupEvent groupEvent);
}
