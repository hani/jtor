package com.advancedpwr.record.examples;

import java.util.List;

public class BadPerson extends Person
{
	public List getChildren()
	{
		throw new RuntimeException( "bad stuff" );
	}
}
