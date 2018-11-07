package ch.shaped.microprofile.demo.util;

import ch.shaped.microprofile.demo.model.Information;
import ch.shaped.microprofile.demo.greeter.Greeting;

public class InformationBuilder {

    private final Information entity;

    public InformationBuilder() {
        this(new Information());
    }

    private InformationBuilder(Information entity) {
        this.entity = entity;
    }

    public InformationBuilder hostname(String hostname) {
        entity.setHostname(hostname);
        return this;
    }

    public InformationBuilder ip(String ip) {
        entity.setIp(ip);
        return this;
    }

    public InformationBuilder osArch(String osArch) {
        entity.setOsArch(osArch);
        return this;
    }

    public InformationBuilder osVersion(String osVersion) {
        entity.setOsVersion(osVersion);
        return this;
    }


    public InformationBuilder osName(String osName) {
        entity.setOsName(osName);
        return this;
    }

    public InformationBuilder greeting(Greeting greeting) {
        entity.setGreeting(greeting);
        return this;
    }

    public Information build() {
        return entity;
    }
}
