package eu.quanticol.cgp.gef.editor.factory;

import org.eclipse.gef.requests.CreationFactory;

import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ComponentInstance;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.NodeInstance;

public class CGPComponentInstanceFactory implements CreationFactory {
	
	private ComponentPrototype prototype;
	
	public CGPComponentInstanceFactory( ComponentPrototype prototype ) {
		this.prototype = prototype;
	}

	@Override
	public Object getNewObject() {
		ComponentInstance instance = CGPFactory.eINSTANCE.createComponentInstance();
		instance.setPrototype(prototype);
		return instance;
	}

	@Override
	public Object getObjectType() {
		return NodeInstance.class;
	}
	
	

}
