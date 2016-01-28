package ua.com.jon.common.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.*;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 13.04.15
 */
public class FtpService {
    FTPClient client = new FTPClient();
    public static final String FTP_PREFIX = "ftp://";

    public static void main(String[] args) throws IOException {
        FtpService ftpService = new FtpService();
        System.out.println(ftpService.getContent("jon.com.ua"));
    }

    public Map<String, String> getContent(String jonUrl) throws IOException {
        Map<String, String> urls = new HashMap<>();

        client.connect("jon.com.ua");
        client.login("ANONYMOUS", "");

        FTPFile[] ftpFiles = client.listFiles();
        for (FTPFile ftpFile : ftpFiles) {
            // Check if FTPFile is a regular file
            if (ftpFile.getType() == FTPFile.FILE_TYPE) {
                String url = FTP_PREFIX + jonUrl + "/" + ftpFile.getName();
                urls.put(ftpFile.getName()  + " - " + FileUtils.byteCountToDisplaySize(ftpFile.getSize()), url);
            }
        }
        client.logout();
        client.disconnect();
        return urls;
    }
}
