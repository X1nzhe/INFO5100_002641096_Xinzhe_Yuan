/*
INFO5100 EXERCISE5
DATE: Dec/09/2023
STUDENT FULL NAME: Xinzhe Yuan
NUID: 002641096
 */

package org.neu.info5100;

//State Pattern
public interface RouterState {
    void doAction(Router router);
}
class RouterOnState implements RouterState {
    @Override
    public void doAction(Router router) {
            router.setState(new RouterOffState());
            System.out.println("The router is powered off.");
    }
}

class RouterOffState implements RouterState {
    @Override
    public void doAction(Router router) {
        router.setState(new RouterOnState());
        System.out.println("The router is powered on.");
    }
}
