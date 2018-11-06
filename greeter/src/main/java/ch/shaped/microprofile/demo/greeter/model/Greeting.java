package ch.shaped.microprofile.demo.greeter.model;

public class Greeting {

    private final String greeting;

    public Greeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }
}
