package ua.com.jon.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ua.com.jon.auth.domain.AssemblaSpace;
import ua.com.jon.auth.domain.AssemblaSpaces;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.auth.domain.AssemblaUsers;
import ua.com.jon.auth.exceptions.RestException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/25/13
 */
@Component
public class RestClient {
    private static final Logger log = Logger.getLogger(RestClient.class);

    @Value("${assembla.apiKey}")
    private String apiKey;

    @Value("${assembla.apiKeySecret}")
    private String apiKeySecret;

    @Value("${assembla.clientUri}")
    private String restUri;

    @Value("${assembla.spaceUri}")
    private String spaceUri;

    @Value("${assembla.usersSpaceUri}")
    private String usersSpaceUri;

    @Value("${assembla.spacesUri}")
    private String spacesUri;

    @Autowired
    private RestTemplate restTemplate;

    public AssemblaUser getUser(String login) throws RestException {
        log.debug("Get user by login: " + login);
        if (devauth()) {
            return new AssemblaUser(login);
        }

        AssemblaUser assemblaUser = doGet(AssemblaUser.class, apiKey, apiKeySecret, restUri, login);
        log.debug("Got user: " + assemblaUser);

        return assemblaUser;
    }

    public AssemblaUser getUserBySpaceAndUName(String spaceName, String userName) throws RestException {

        if (devauth()) {
            return new AssemblaUser(userName);
        }
        log.debug("Get user by space and name: " + spaceName + ", " + userName);
        AssemblaUsers users = getUsersBySpace(spaceName);
        AssemblaUser user = null;
        log.debug("Got users: " + users);
        for (AssemblaUser usr : users.getUsers()) {
            if(usr.getLogin().equals(userName)) {
                user = usr;
            }
        }

        return user;
    }

    private AssemblaUsers getUsersBySpace(String spaceName) throws RestException {
        return doGet(AssemblaUsers.class, apiKey, apiKeySecret, usersSpaceUri, spaceName);
    }

    public List<AssemblaUser> getUserListBySpace(String spaceName) throws RestException {
        AssemblaUsers users = getUsersBySpace(spaceName);
        return users.getUsers();

    }

    public AssemblaSpaces getSpaces() throws RestException {
        log.debug("Get all assembla spaces ");
        AssemblaSpaces users = doGet(AssemblaSpaces.class, apiKey, apiKeySecret, spacesUri);
        return users;

    }

    private boolean devauth() {
        log.warn("Dev athentiction enabled");
        if("key".equals(apiKey)) {
            return true;
        }
        return false;
    }

    public AssemblaSpace getSpace(String name) throws RestException {
        log.debug("Get space by spaceName: " + name);
        AssemblaSpace assemblaSpace = doGet(AssemblaSpace.class, apiKey, apiKeySecret, spaceUri, name);
        log.debug("Got space: " + assemblaSpace);
        return assemblaSpace;
    }

    @SuppressWarnings("unchecked")
    public <T> T doGet(Class<T> responseType, String apiKey, String apiKeySecret,
                       String restUri, Object... uriVariables) throws RestException {
        HttpHeaders requestHeaders = createHttpHeader(apiKey, apiKeySecret);
        HttpEntity<T> response;
        try {
            HttpEntity<T> requestEntity = new HttpEntity(requestHeaders);
            response = restTemplate.exchange(restUri,
                    HttpMethod.GET,
                    requestEntity,
                    responseType,
                    uriVariables);
        } catch (ResourceAccessException e) {
            throw e;
        } catch (Exception e) {
            throw new RestException("An error when call to {"+ restUri +"}, " + Arrays.toString(uriVariables), e);
        }

        return response.getBody();
    }

    private HttpHeaders createHttpHeader(String apiKey, String apiKeySecret) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("X-Api-Key", apiKey);
        requestHeaders.set("X-Api-Secret", apiKeySecret);

        return requestHeaders;
    }
}
