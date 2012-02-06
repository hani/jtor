package com.advancedpwr.record.methods;

/**
 * @author hani
 *         Date: 2/6/12
 *         Time: 9:42 AM
 */
public class ConstructorWithArgsBuilder extends BuildMethodWriter {

  public void buildMethod()
 	{
 //		System.out.println( "writing " + returnType() + SPACE + resultBuilder() + " : " + toString() );
 		newLine();
 		writeField();
 		writeMethodSignature();
 		openBrace();
 		writeLine( "return null");
 		closeBrace();
 		// This instance is built, so we can cache it
 		getFactory().storeBuilder( this );
 		writeBuilderMethods();
 	}
}
