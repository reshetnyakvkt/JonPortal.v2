package ua.com.jon.examinator.client;

import com.google.gwt.cell.client.AbstractSafeHtmlCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 07.06.14
 */
public class StyledButtonCell extends AbstractSafeHtmlCell<String> {
    private String disabledString = "";

    private boolean disabled = false;

    /**
     * Construct a new ButtonCell that will use a {@link SimpleSafeHtmlRenderer}.
     */
    public StyledButtonCell() {
        this(SimpleSafeHtmlRenderer.getInstance());
    }


    /**
     * Construct a new ButtonCell that will use a given {@link SafeHtmlRenderer}.
     *
     * @param renderer a {@link SafeHtmlRenderer SafeHtmlRenderer<String>} instance
     */
    public StyledButtonCell(SafeHtmlRenderer<String> renderer) {
        super(renderer, "click", "keydown");
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, String value,
                               NativeEvent event, ValueUpdater<String> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        if ("click".equals(event.getType())) {
            EventTarget eventTarget = event.getEventTarget();
            if (!Element.is(eventTarget)) {
                return;
            }
            if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {
                // Ignore clicks that occur outside of the main element.
                onEnterKeyDown(context, parent, value, event, valueUpdater);
            }
        }
    }

    @Override
    public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
        sb.appendHtmlConstant("<button type=\"button\" tabindex=\"-1\"" + disabledString + ">");
        if (data != null) {
            sb.append(data);
        }
        sb.appendHtmlConstant("</button>");
    }

    @Override
    protected void onEnterKeyDown(Context context, Element parent, String value,
                                  NativeEvent event, ValueUpdater<String> valueUpdater) {
        if (valueUpdater != null) {
            valueUpdater.update(value);
        }
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
        if (disabled) {

            disabledString = "disabled=\"disabled\"";
        } else {
            disabledString = "";
        }
    }
}