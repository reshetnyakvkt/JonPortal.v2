package ua.com.jon.auth.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/1/13
 */
@XStreamAlias("spaces") //org.springframework.oxm.UnmarshallingFailureException: XStream unmarshalling exception; nested exception is com.thoughtworks.xstream.mapper.CannotResolveClassException: spaces : spaces
public class AssemblaSpaces {
    @XStreamImplicit
    List<AssemblaSpace> spaces = new ArrayList<AssemblaSpace>();

    public List<AssemblaSpace> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<AssemblaSpace> spaces) {
        this.spaces = spaces;
    }

    @Override
    public String toString() {
        return "AssemblaSpaces{" +
                "spaces=" + spaces +
                '}';
    }
}
