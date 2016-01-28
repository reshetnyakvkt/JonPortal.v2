package ua.com.jon.common.controller;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import ua.com.jon.common.service.FtpService;
import ua.com.jon.utils.NetUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/2/13
 */
@Controller
public class DownloadController {
    private static Logger log = Logger.getLogger(DownloadController.class.getName());

    @Value("${files.folder}")
    private String filesBaseUrl;

    private Map<String, String> files;

    private FtpService ftpService = new FtpService();

    public DownloadController() {
        files = new HashMap<String, String>();
        files.put("jdk-7u25-windows-i586", "/install/jdk-7u25-windows-i586.exe");
        files.put("jdk-7u25-windows-x64", "/install/jdk-7u25-windows-x64.exe");
        files.put("ideaIU-12_1_4", "/install/ideaIU-12.1.4.exe");
//        files.put("ojdbc14", "/libs/Oracle JDBC Driver/ojdbc14.jar");
        files.put("OracleXE", "/install/OracleXE.exe");
        files.put("hibernate_reference", "/docs/hibernate_reference.pdf");
        files.put("hibernate_reference4", "/docs/hibernate_reference-4.3.4.pdf");
        files.put("spring-framework-reference", "/docs/spring-framework-reference.pdf");
        files.put("apache-tomcat-7_0_41", "/install/apache-tomcat-7.0.41.zip");
        files.put("apache-maven-2_2_1-bin", "/install/apache-maven-2.2.1-bin.zip");
        files.put("eclipse-java-kepler-R-win32", "/install/eclipse-java-kepler-R-win32.zip");
        files.put("eclipse-java-kepler-R-win32-x86_64", "/install/eclipse-java-kepler-R-win32-x86_64.zip");
        files.put("netbeans-7_3_1-javase-windows", "/install/netbeans-7.3.1-javase-windows.exe");
        files.put("ideaIU-12_0_4", "/install/ideaIU-12.0.4.exe");
    }

    @RequestMapping("/download.html")
    public String mainPage(ModelMap modelMap) {
        log.info("download page");
        modelMap.put("item", "item5");
        String ip = NetUtil.distinguishLocationAndGetIP();
        modelMap.put("ftpAddress", "ftp://" + ip);

        Map<String, String> urls;
        try {
            urls = ftpService.getContent(ip);
        } catch (IOException e) {
            log.error(e);
            urls = new HashMap<>();
        }
        List<Map.Entry<String, String>> entries = new ArrayList<>(urls.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        modelMap.addAttribute("urls", entries);
        return "download";
    }

/*
    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getStreamFromPath(@PathVariable("file_name") String fileName) {
        log.info("File download request: " + fileName);
        return new FileSystemResource(getFileFor(fileName));
    }java.io.FileNotFoundException: File found but not exists, contact to admin: /home/al1/work/It-centre_old/share/install/jdk-7u25-windows-i586.exe
*/

    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("file_name") String fileName, HttpServletResponse response) {
        try {
            File file;
            InputStream is;
            String preparedFileName = "";
            try {
                String pathName = getPathName(fileName);
                preparedFileName = prepareFileName(pathName);
                try {
                    is = getStreamFromClassPath(pathName);
                } catch (FileNotFoundException e) {
                    log.error(e);
                    is = getStreamFromPath(pathName);
                }
            } catch (FileNotFoundException e) {
                log.error(e);
                response.getWriter().print(e.getMessage());
                return;
            }

            // copy it to response's OutputStream
            response.setHeader("Content-Disposition", "attachment; filename=" + preparedFileName);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            log.info("Error writing file to output stream. Filename was '" + fileName + "'");
            throw new RuntimeException("IOError writing file to output stream");
        }

    }

    private String prepareFileName(String pathName) {
        return pathName.substring(pathName.lastIndexOf('/') + 1);
    }

    private String getPathName(String fileName) throws FileNotFoundException {
        String pathname = files.get(fileName);
        if (pathname == null) {
            throw new FileNotFoundException("File not found " + fileName);
        }
        return pathname;
    }

    private InputStream getStreamFromPath(String pathname) throws FileNotFoundException {
        File file = new File(filesBaseUrl + pathname);
        if (!file.exists()) {
            throw new FileNotFoundException("File found but not exists, contact to admin: " + filesBaseUrl + pathname);
        }
        return new FileInputStream(file);
    }

    private InputStream getStreamFromClassPath(String pathname) throws FileNotFoundException {
        InputStream is = getClass().getResourceAsStream(pathname);
        if (is == null) {
            throw new FileNotFoundException("File found in classpath but not exists, contact to admin: " + pathname);
        }
        return is;
    }
}
