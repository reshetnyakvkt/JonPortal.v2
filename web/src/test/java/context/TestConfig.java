package context;

import com.jon.tron.domain.*;
import com.jon.tron.service.vc.git.GitblitClient;
import com.jon.tron.service.vc.git.GitblitClientImpl;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/17/13
 */
@Configuration
//@ComponentScan({"com.jon.tron.service", "com.jon.tron.dao"})
@PropertySource("classpath:tron.properties")
@EnableScheduling
public class TestConfig {
    @Bean
    public static GitblitClient gitblitClient() {
        GitblitClient client = new GitblitClientImpl();
        return client;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("tron.properties"));
        return propertySourcesPlaceholderConfigurer;
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<HttpMessageConverter<?>>();
        httpMessageConverters.add(messageConverter());
        template.setMessageConverters(httpMessageConverters);
        return template;
    }

    @Bean
    public XStreamMarshaller marshaller() {
        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setAutodetectAnnotations(true);
        marshaller.setAnnotatedClasses(new Class[]{
                GitUsers.class,
                JonTask.class,
                Task.class,
                User.class,
                TaskTemplate.class,
                Group.class,
                Tasks.class
        });
        return marshaller;
    }

    @Bean
    public HttpMessageConverter messageConverter() {
        MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));
        converter.setMarshaller(marshaller());
        converter.setUnmarshaller(marshaller());
        return converter;
    }

    @Test
    public void mockTestForMaven() {

    }
/*
    <bean id="restTemplate" class="org.springframework.web.client.RestTemplate">
        <property name="messageConverters">
            <bean id="messageConverter" class="org.springframework.http.converter.xml.MarshallingHttpMessageConverter">
                <property name="marshaller" ref="xstreamMarshaller" />
                <property name="unmarshaller" ref="xstreamMarshaller" />
            </bean>
    <   /property>
    </bean>


    <bean id="xstreamMarshaller" class="org.springframework.oxm.xstream.XStreamMarshaller">
        <property name="autodetectAnnotations" value="true"/>
        <property name="annotatedClasses">
            <array>
                <value>ua.com.jon.auth.domain.AssemblaUsers</value>
                <value>ua.com.jon.auth.domain.AssemblaUser</value>
                <value>ua.com.jon.auth.domain.AssemblaSpace</value>
                <value>ua.com.jon.auth.domain.AssemblaSpaces</value>
            </array>
        </property>
    </bean>
*/
}
