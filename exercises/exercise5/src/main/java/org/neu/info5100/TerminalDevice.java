/*
INFO5100 EXERCISE5
DATE: Dec/09/2023
STUDENT FULL NAME: Xinzhe Yuan
NUID: 002641096
 */

package org.neu.info5100;

//Observer Pattern
public class TerminalDevice extends Observer {
    private final String deviceName;

    public TerminalDevice(String deviceName) {
        this.deviceName = deviceName;
    }
    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public void update(String msg) {
        System.out.println(deviceName + " received message from the Router : " + msg + " .");
    }
}
