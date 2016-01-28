package ua.com.jon.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 2/26/13
 */
public class NetUtil {
    private static Logger log = Logger.getLogger(NetUtil.class.getName());

    public static final String LOCALHOST = "localhost";
    public static final String LOCAL_ADDR_PATTERN = "192.168.0";
    public static final String JON_INET_ADDR = "jon.com.ua";
    public static String getIpAsString() {
        try {
            Socket s = new Socket("yahoo.com", 80);
            return s.getLocalAddress().getHostAddress();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return LOCALHOST;
        }
    }

    public static String distinguishLocationAndGetIP() {
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            log.error("Somehow we have a socket error...");
        }

        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                InetAddress addr = address.nextElement();
                if (!addr.isLoopbackAddress() && !(addr.getHostAddress().contains(":"))) {
                    // TODO hack, to be refactor
                    if(addr.getHostAddress().contains(LOCAL_ADDR_PATTERN)) {
                        return JON_INET_ADDR;
                    } else {
                        return addr.getHostAddress();
                    }
                }
            }
        }
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

    public static void main(String[] args) {
        System.out.println(distinguishLocationAndGetIP());
    }
}
