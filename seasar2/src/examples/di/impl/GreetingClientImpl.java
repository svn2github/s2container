package examples.di.impl;

import examples.di.Greeting;

public class GreetingClientImpl {

    private Greeting greeting;

    public void setGreeting(Greeting greeting) {
        this.greeting = greeting;
    }
    
    public void execute() {
        System.out.println(greeting.greet());
    }
}