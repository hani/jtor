package com.advancedpwr.record.examples;

class PrivatePerson extends Person
{
	public Being createPerson()
	{
		return new PrivatePerson();
	}
}
