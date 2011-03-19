package com.advancedpwr.record.mock.generated;

import com.advancedpwr.record.examples.Person;
import com.advancedpwr.record.mock.MockFactory;

public class MockPersonFactory extends MockFactory
{

	protected Person person;

	public Person buildPerson()
	{
		if ( person != null ) 
		{
			return person;
		}
		person = createStrictMock( Person.class );
		expect( person.getName() ).andReturn( "Joe" );
		replay( person );
		return person;
	}
	
SimpleClassDescriptor descriptor = new SimpleClassDescriptor();
descriptor.setClassName( "MockBeingFactory" );
descriptor.setPackageName( "com.advancedpwr.record.mock.generated" );
recorder.setDescriptor( descriptor );
recorder.addPreferredInterface( Being.class );

Person person = new Person();
person.setName(  "Joe" );

Being instrumentedPerson = recorder.record( person );

instrumentedPerson.getName();

recorder.endRecording();
}