package ch.shaped.microprofile.demo.greeter.util;

import javax.enterprise.context.Dependent;

@Dependent
public class UnreliableService {

    public void pass() {
        double pass = Math.random();
        if(pass < 0.75d) {
            throw new RuntimeException("This time we fail "+pass+" < 0.75.");
        }
    }
}
