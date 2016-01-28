package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.SpaceDTO;
import ua.com.jon.admin.shared.UserDTO;
import ua.com.jon.auth.domain.AssemblaSpace;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.auth.domain.GitHubRepo;
import ua.com.jon.auth.util.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/2/13
 */
public class SpaceDtoMapper {
    public static SpaceDTO spaceToDto(Long id, AssemblaSpace space, boolean isActive, String repo) {
        return new SpaceDTO(
                id,
                space.getName(),
                new ArrayList<UserDTO>(),
                repo
        );
    }

    public static SpaceDTO spaceToDto(Long id, AssemblaSpace space, List<AssemblaUser> users, String repo) {
        return new SpaceDTO(
                id,
                space.getName(),
                UserMapper.assemblaToDtos(users),
                repo
        );
    }

    public static ArrayList<SpaceDTO> spacesToDtos(List<AssemblaSpace> assemblaSpaces, boolean isActive, String repo) {
        ArrayList<SpaceDTO> spaces = new ArrayList<SpaceDTO>(assemblaSpaces.size());
        for (AssemblaSpace assemblaSpace : assemblaSpaces) {
            spaces.add(spaceToDto(null, assemblaSpace, isActive, repo));
        }
        return spaces;
    }

/*    public static ArrayList<SpaceDTO> reposToDtos(Set<GitHubRepo> repositoriesAndCollaborators) {
        ArrayList<SpaceDTO> spaces = new ArrayList<SpaceDTO>(repositoriesAndCollaborators.size());
        for (GitHubRepo repo : repositoriesAndCollaborators) {
            spaces.add(repoToSpaceDto(null, repo));
        }
        return null;
    }

    private static SpaceDTO repoToSpaceDto(Long id, GitHubRepo repo) {
        return new SpaceDTO(
                id,
                repo.getName(),
                UserMapper.assemblaToDtos(users),
                repo
        );
    }*/
}
