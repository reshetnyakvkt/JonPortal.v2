//package ua.com.jon.utils;
//
//import org.apache.log4j.Logger;
//import org.kohsuke.github.GHRepository;
//import org.kohsuke.github.GHUser;
//import org.kohsuke.github.GitHub;
//import ua.com.jon.admin.shared.SpaceDTO;
//import ua.com.jon.admin.shared.UserDTO;
//import ua.com.jon.auth.domain.GitHubRepo;
//import ua.com.jon.auth.domain.GitHubUser;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created with IntelliJ IDEA.
// * User: sergey
// * Date: 26.04.14
// */
//public class GitHubClient {
//    private static final Logger log = Logger.getLogger(GitHubClient.class);
//    public static ArrayList<SpaceDTO> getRepositoriesAndCollaborators() throws IOException {
//        GitHub github = GitHub.connectUsingPassword("sergey_zagalskiy@mail.ru", "darcyk123");
//        Map<String, GHRepository> reposMap = github.getMyself().getAllRepositories();
//        System.out.println(reposMap);
//        SpaceDTO repo;
//        ArrayList<UserDTO> gitHubUsers;
//        ArrayList<SpaceDTO> gitHubRepos = new ArrayList<SpaceDTO>();
//        //GHRepository repo = github.getMyself().getRepository("JonPortal");
//        for (GHRepository repository : reposMap.values()) {
//            gitHubUsers = new ArrayList<UserDTO>();
//            for (GHUser collaborator : repository.getCollaborators()) {
//                gitHubUsers.add(new UserDTO(null, collaborator.getLogin()));
//            }
//            repo = new SpaceDTO(null, repository.getName(), gitHubUsers, repository.getUrl());
//            gitHubRepos.add(repo);
//        }
//        return gitHubRepos;
//    }
//
//    public static void main (String [] args) throws IOException {
//        getRepositoriesAndCollaborators();
//    }
//}
