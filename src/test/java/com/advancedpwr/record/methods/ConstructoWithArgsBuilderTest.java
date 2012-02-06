package com.advancedpwr.record.methods;

import com.advancedpwr.record.BeanRecorderTest;
import com.advancedpwr.record.examples.NoDefaultConstructor;

/**
 * @author hani
 *         Date: 2/6/12
 *         Time: 9:02 AM
 */
public class ConstructoWithArgsBuilderTest extends BeanRecorderTest
{
	protected void setUp()
	{
		setWriteFiles();
		super.setUp();
	}

	public void testNoDefaultConstructor()
	{
    NoDefaultConstructor obj = new NoDefaultConstructor("foo");
    obj.setBar("bar");
		recorder.record( obj );
		assertResult("package com.advancedpwr.record.examples.generated;\n" +
      "\n" +
      "import com.advancedpwr.record.examples.NoDefaultConstructor;\n" +
      "\n" +
      "public class NoDefaultConstructorFactory\n" +
      "{\n" +
      "\n" +
      "\tprotected NoDefaultConstructor nodefaultconstructor;\n" +
      "\n" +
      "\tpublic NoDefaultConstructor buildNoDefaultConstructor()\n" +
      "\t{\n" +
      "\t\treturn null;\n" +
      "\t}\n" +
      "\n" +
      "}");

	}
}
