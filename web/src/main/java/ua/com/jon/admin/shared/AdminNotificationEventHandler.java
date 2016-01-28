package ua.com.jon.admin.shared;

import com.google.gwt.event.shared.EventHandler;
import ua.com.jon.cabinet.shared.NotificationEvent;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 12.10.13
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public interface AdminNotificationEventHandler extends EventHandler {

    void onNotificationChanged(AdminNotificationEvent authenticationEvent);
}
