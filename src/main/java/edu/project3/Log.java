package edu.project3;

import java.net.InetAddress;
import java.net.URI;
import java.time.LocalDateTime;

public record Log(InetAddress remoteAddress, String remoteUser, LocalDateTime timeLocal, String request,
                  int status, int bodyBytesSent, URI httpReferer, String httpUserAgent) {
}
