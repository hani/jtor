***  Java Test Object Recorder ***

The Java Test Object Recorder (JTOR) project provides three "object recorders" 
to record live object trees or behavior for playback during unit testing.  

Object recorders enable the recording of state or behavior so that data 
retrieved from a remote system, or possibly a complex object graph, can be 
easily reconstructed or mocked to facilitate unit testing.

There are two types of object recorders, state and behavior recorders.

State recorders record the entire object graph in either a Java factory class 
or XML file.

Behavior recorders record only the methods that are called on the live object 
tree in a factory class that will construct a tree of mock objects that will 
replicate the recorded behavior.

State Recorders:

com.advancedpwr.record.BeanRecorder – The first implementation, this recorder 
is only marginally useful as it records an object tree that follows the Java 
Bean conventions.  This method of recording does have the advantage that the 
resulting factory class is entirely Java and will “follow” refactorings 
such as method, class or package renaming.  No additional jar files are 
necessary to use this recorder.

com.advancedpwr.record.xtream.XstreamRecorder – This implementation uses 
Xstream to create a factory that stores the object tree in an XML file.  
Xstream is impressive in its capabilities to serialize and deserialize objects. 
 Only the xstream-1.3.1.jar and objenesis-1.2.jar are required to use this 
recorder.


Behavior Recorder:

com.advancedpwr.record.mock.MockBehaviorRecorder – Records all method calls 
made on an object tree in a factory that constructs mock objects to replicate 
and verify the expected behavior.  This recorder requires EasyMock-3.0.jar, 
cglib-nodep-2.2.jar and objenesis-1.2.jar.


User and API documentation can be found at http://jtor.sourceforge.net.
