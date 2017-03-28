package eu.quanticol.cgp.gef.editor.factory;

import org.eclipse.gef.requests.CreationFactory;

import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.ConnectionPrototype;

public class ConnectionInstanceFactory implements CreationFactory {

	private ConnectionPrototype prototype;
	
	
	public  ConnectionInstanceFactory(ConnectionPrototype prototype) {
this.prototype=prototype;
}
	
	@Override
	public Object getNewObject() {
		ConnectionInstance ci = CGPFactory.eINSTANCE.createConnectionInstance();
		ci.setPrototype(prototype);
		return ci;
	}

	@Override
	public Object getObjectType() {
		
		return ConnectionInstance.class;
	}

}
