package ua.com.jon.admin.client.components.list;


import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/13/13
 */
public class SelectionEvent extends Event<SelectionHandler> {
    public static final Type<SelectionHandler> TYPE = new Type<SelectionHandler>();

    @Override
    public Type<SelectionHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SelectionHandler handler) {
        Window.alert("dispatch");
        handler.onSelect(this);
    }

    public static HandlerRegistration register(EventBus eventBus, SelectionHandler handler) {
        return eventBus.addHandler(TYPE, handler);
    }
}
