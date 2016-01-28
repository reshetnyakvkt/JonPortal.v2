package com.jon.tron.service.vc.git;

import com.jon.tron.domain.GitUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 25.01.16
 */
@Component
public class GitblitClientImpl implements GitblitClient {
    @Override
    public ResponseEntity<GitUser> setRepoPermissionForUser(String name, String pass, String userName) {
        return null;
    }

    @Override
    public ResponseEntity<GitUser> createUser(String name, String pass) {
        return null;
    }

    @Override
    public Map<String, Map<String, String>> getRepos() {
        return null;
    }

    @Override
    public Long getLastRevision(String repoName) {
        return null;
    }

    @Override
    public void getUsers() {

    }
}
