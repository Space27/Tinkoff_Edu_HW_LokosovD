package edu.hw6;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public final class Task6 {

    private Task6() {
    }

    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:MultipleStringLiterals"})
    private final static Map<Integer, String> POPULAR_PORTS = new HashMap<>() {{
        put(80, "HyperText Transfer Protocol");
        put(21, "File Transfer Protocol");
        put(25, "Simple Mail Transfer Protocol");
        put(22, "Secure Shell");
        put(443, "HyperText Transfer Protocol Secure");
        put(53, "Domain Name System");
        put(3306, "MySQL");
        put(5432, "PostgreSQL");
        put(3389, "Remote Desktop Protocol");
        put(27017, "MongoDB");
        put(1521, "Oracle");
        put(49152, "Windows RPC");
        put(5353, "Multicast Domain Name System");
        put(5672, "Advanced Message Queuing Protocol");
        put(5355, "Link-Local Multicast Name Resolution");
        put(23, "Telnet");
        put(110, "POP3");
        put(8080, "HTTP Proxy");
        put(2049, "NFS");
        put(843, "Adobe Flash");
        put(17500, "Dropbox");
        put(445, "Microsoft-DS Active Directory");
    }};

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static String scanPortsAndGetThem() throws IOException {
        final int maxPort = 49151;
        StringBuilder result = new StringBuilder("Протокол  Порт   Сервис\n");

        for (int i = 0; i <= maxPort; ++i) {
            try (ServerSocket tcp = new ServerSocket(i)) {
                if (!tcp.isClosed() && tcp.getLocalPort() > 0) {
                    result.append(String.format("%-10s", "TCP")).append(String.format("%-7d", i));
                    if (POPULAR_PORTS.containsKey(i)) {
                        result.append(POPULAR_PORTS.get(i));
                    }
                    result.append('\n');
                }
            } catch (BindException ignored) {
            }
            try (DatagramSocket udp = new DatagramSocket(i)) {
                if (!udp.isClosed() && udp.getLocalPort() > 0) {
                    result.append(String.format("%-10s", "UDP")).append(String.format("%-7d", i));
                    if (POPULAR_PORTS.containsKey(i)) {
                        result.append(POPULAR_PORTS.get(i));
                    }
                    result.append('\n');
                }
            } catch (BindException ignored) {
            }
        }

        return result.toString();
    }
}
