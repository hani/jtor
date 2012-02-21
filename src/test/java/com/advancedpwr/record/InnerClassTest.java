package com.advancedpwr.record;

import com.advancedpwr.record.examples.InnerClass;

/**
 * Date: 21/02/12
 * Time: 10:38
 *
 * @author Hani Suleiman
 */
public class InnerClassTest extends AbstractRecorderTest {
  public void testRecordInnerClass() {
    InnerClass obj = new InnerClass();
    InnerClass.Holder holder = new InnerClass.Holder();
    holder.setFoo("foo");
    obj.setHolder(holder);
    
    recorder.record(obj);
    assertContains("import com.advancedpwr.record.examples.InnerClass.Holder;");
  }
}
