package ua.com.jon.cabinet.shared;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 12.10.13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class NotificationEvent extends GwtEvent<NotificationEventHandler> {

    public static Type<NotificationEventHandler> TYPE = new Type<NotificationEventHandler>();

    @Override
    public Type<NotificationEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(NotificationEventHandler handler) {
        handler.onNotificationChanged(this);
    }
}
