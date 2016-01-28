package ua.com.jon.admin.shared;

import com.google.gwt.event.shared.GwtEvent;
import ua.com.jon.cabinet.shared.NotificationEventHandler;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 12.10.13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class AdminNotificationEvent extends GwtEvent<AdminNotificationEventHandler> {

    public static Type<AdminNotificationEventHandler> TYPE = new Type<AdminNotificationEventHandler>();

    @Override
    public Type<AdminNotificationEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AdminNotificationEventHandler handler) {
        handler.onNotificationChanged(this);
    }
}
