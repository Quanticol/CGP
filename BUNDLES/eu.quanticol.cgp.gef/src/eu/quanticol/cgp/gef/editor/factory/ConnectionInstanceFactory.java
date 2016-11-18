package eu.quanticol.cgp.gef.editor.factory;

import org.eclipse.gef.requests.CreationFactory;

import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ConnectionInstance;

public class ConnectionInstanceFactory implements CreationFactory {

	@Override
	public Object getNewObject() {
		
		return CGPFactory.eINSTANCE.createConnectionInstance();
	}

	@Override
	public Object getObjectType() {
		
		return ConnectionInstance.class;
	}

}
