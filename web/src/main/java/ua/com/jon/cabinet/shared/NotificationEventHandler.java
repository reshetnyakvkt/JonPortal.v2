package ua.com.jon.cabinet.shared;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 12.10.13
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public interface NotificationEventHandler extends EventHandler {

    void onNotificationChanged(NotificationEvent authenticationEvent);
}
