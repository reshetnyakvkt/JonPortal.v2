//package ua.com.jon.utils;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.jon.tron.domain.GitUser;
//import com.jon.tron.domain.GitUsers;
//import com.jon.tron.domain.Tasks;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.log4j.Logger;
//import org.apache.log4j.helpers.ISO8601DateFormat;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.stereotype.Service;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.TimeZone;
//
///**
// * Created with IntelliJ IDEA.
// * User: al1
// * Date: 05.08.14
// */
//@Service
//public class GitblitClient {
//    private static final Logger log = Logger.getLogger(GitblitClient.class);
//
////    @Autowired
//    private RestTemplate restTemplate;
//
//    @Value("${gitblit.admin.name}")
//    private String adminName;
//
//    @Value("${gitblit.admin.pass}")
//    private String adminPass;
//
//    @Value("${gitblit.address}")
//    private String address;
//
//    public GitblitClient() {
//        restTemplate = new RestTemplate();
//        restTemplate.setMessageConverters(getMessageConverters());
//    }
//
//    public ResponseEntity<GitUser> createUser(String name, String pass) {
//        String url = "http://"+address+"/gitblit/rpc?req=CREATE_USER&name=" + name;
////        RestTemplate restTemplate = new RestTemplate();
////        restTemplate.setMessageConverters(getMessageConverters());
//        GitUser user = new GitUser(name, pass);
//
//        HttpEntity requestEntity = new HttpEntity<GitUser>(user, createHeaders(adminName, adminPass));
////        ResponseEntity<GitUser> response =
////        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(createHeaders("admin", "admin")), GitUser.class, new GitUser("a", "a"));
//        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, GitUser.class);
////        System.out.println(response);
//    }
//
//
//    public void getUsers() {
//
//        Class responseClazz = GitUsers.class;
//        String url = "http://localhost:8083/gitblit/rpc?req=LIST_USERS";
////        Object resp = restTemplate.getForObject(url, responseClazz);
////        Object resp = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(createHeaders("admin", "admin")), GitUsers.class, "1");
////        System.out.println(resp);
//
////        RestTemplate restTemplate = new RestTemplate();
////        restTemplate.setMessageConverters(getMessageConverters());
//
////        HttpHeaders headers = new HttpHeaders();
////        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////        HttpEntity<String> entity = new HttpEntity<String>(headers);
////        Object resp = restTemplate.getForObject(url, responseClazz);
////        System.out.println(resp);
//        ResponseEntity<List> response =
//                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(createHeaders("admin", "admin")), List.class, "1");
//        List<GitUser> resource = response.getBody();
//        log.info(resource);
//    }
//
//    private List<HttpMessageConverter<?>> getMessageConverters() {
//        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
//        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
////        objectMapper.registerModule(new JodaModule());
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
//        objectMapper.setDateFormat(new ISO8601DateFormat());
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        jsonMessageConverter.setObjectMapper(objectMapper);
//        converters.add(jsonMessageConverter);
//        return converters;
//    }
//
//    HttpHeaders createHeaders(final String username, final String password ){
//        return new HttpHeaders(){
//            {
//                String auth = username + ":" + password;
//                byte[] encodedAuth = Base64.encodeBase64(
//                        auth.getBytes(Charset.forName("US-ASCII")));
//                String authHeader = "Basic " + new String( encodedAuth );
//                set( "Authorization", authHeader );
//                setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//            }
//        };
//    }
//}
