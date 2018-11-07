package ch.shaped.microprofile.demo.model;


import ch.shaped.microprofile.demo.greeter.Greeting;

public class Information {
    private String hostname;
    private String ip;
    private String osArch;
    private String osVersion;
    private String osName;
    private Greeting greeting;

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public Greeting getGreeting() {
        return greeting;
    }

    public void setGreeting(Greeting greeting) {
        this.greeting = greeting;
    }
}
