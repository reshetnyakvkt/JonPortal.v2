package ua.com.jon.cabinet.shared;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by sergey on 12.07.14.
 */
public class GroupEvent extends GwtEvent<GroupEventHandler>  {

    public static Type<GroupEventHandler> TYPE = new Type<GroupEventHandler>();

    private Long groupId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }


    @Override
    public Type<GroupEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(GroupEventHandler handler) {
        handler.onGroupChanged(this);
    }
}
