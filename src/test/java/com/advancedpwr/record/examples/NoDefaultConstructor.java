package com.advancedpwr.record.examples;

/**
 * @author hani
 *         Date: 2/4/12
 *         Time: 10:23 PM
 */
public class NoDefaultConstructor {
  private String bar;

  public NoDefaultConstructor(String foo) {

  }

  public String getBar() {
    return bar;
  }

  public void setBar(String bar) {
    this.bar = bar;
  }
}
