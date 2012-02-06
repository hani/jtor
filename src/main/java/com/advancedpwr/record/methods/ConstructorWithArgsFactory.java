package com.advancedpwr.record.methods;

import com.advancedpwr.record.AccessPath;

/**
 * @author hani
 *         Date: 2/6/12
 *         Time: 8:37 AM
 */
public class ConstructorWithArgsFactory implements MethodWriterFactory
{
	public boolean accept( Class inClass )
	{
    try {
      inClass.getConstructor();
      return false;
    } catch(NoSuchMethodException e) {
      return true;
    }
  }

	public BuildMethodWriter createMethodBuilder( AccessPath inPath )
	{
		return new ConstructorWithArgsBuilder();
	}
}
