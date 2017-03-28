package eu.quanticol.cgp.gef.editor.factory;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.swt.graphics.Color;

import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.NodePrototype;

public class CGPNodeInstanceFactory implements CreationFactory {
	
	private NodePrototype prototype;
	
	public CGPNodeInstanceFactory(NodePrototype prototype) {
		super();
		this.prototype = prototype;
	}

	@Override
	public Object getNewObject() {
		NodeInstance instance = CGPFactory.eINSTANCE.createNodeInstance();
		instance.setPrototype(this.prototype);
		return instance;
	}

	@Override
	public Object getObjectType() {
		return NodeInstance.class;
	}
	
	

}
