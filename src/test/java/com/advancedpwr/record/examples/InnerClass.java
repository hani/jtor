package com.advancedpwr.record.examples;

/**
 * Date: 21/02/12
 * Time: 10:35
 *
 * @author Hani Suleiman
 */
public class InnerClass {
  private Holder holder;

  public Holder getHolder() {
    return holder;
  }

  public void setHolder(Holder holder) {
    this.holder = holder;
  }

  public static class Holder {
    private String foo;

    public String getFoo() {
      return foo;
    }

    public void setFoo(String foo) {
      this.foo = foo;
    }
  }
}
