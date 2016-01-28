package com.jon.tron.service.vc.git;

import com.jon.tron.domain.GitUser;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 25.01.16
 */
public interface GitblitClient {
    ResponseEntity<GitUser> setRepoPermissionForUser(String name, String pass, String userName);

    ResponseEntity<GitUser> createUser(String name, String pass);

    Map<String, Map<String, String>> getRepos();

    Long getLastRevision(String repoName);

    void getUsers();

}
