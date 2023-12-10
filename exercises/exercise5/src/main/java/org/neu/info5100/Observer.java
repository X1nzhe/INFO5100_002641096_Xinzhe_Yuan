/*
INFO5100 EXERCISE5
DATE: Dec/09/2023
STUDENT FULL NAME: Xinzhe Yuan
NUID: 002641096
 */
package org.neu.info5100;

//Observer Pattern
public abstract class Observer {
  // protected Router router;
   public abstract void update(String msg);
   public abstract String getDeviceName();
}

