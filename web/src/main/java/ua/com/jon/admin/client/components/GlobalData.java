package ua.com.jon.admin.client.components;

import ua.com.jon.admin.shared.GroupAndUsersDTO;
import ua.com.jon.admin.shared.SpaceDTO;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 23.11.13
 */
public class GlobalData {

    private ArrayList<SpaceDTO> spacesDtos = new ArrayList<SpaceDTO>();

    private ArrayList<GroupAndUsersDTO> groupAndUsersDTOs = new ArrayList<GroupAndUsersDTO>();

    public ArrayList<SpaceDTO> getSpacesDtos() {
        return spacesDtos;
    }

    public void setSpacesDtos(ArrayList<SpaceDTO> spacesDtos) {
        this.spacesDtos = spacesDtos;
    }

    public ArrayList<GroupAndUsersDTO> getGroupAndUsersDTOs() {
        return groupAndUsersDTOs;
    }

    public void setGroupAndUsersDTOs(ArrayList<GroupAndUsersDTO> groupAndUsersDTOs) {
        this.groupAndUsersDTOs = groupAndUsersDTOs;
    }
}
