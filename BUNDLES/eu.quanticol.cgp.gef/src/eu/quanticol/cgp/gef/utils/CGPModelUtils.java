/**
 * 
 */
package eu.quanticol.cgp.gef.utils;

import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.SpatialModel;

/**
 * @author loreti
 *
 */
public enum CGPModelUtils {

	INSTANCE;
	
	private CGPFactory factory = CGPFactory.eINSTANCE;
	
	public SpatialModel createModel() {
		SpatialModel spatialModel = factory.createSpatialModel();
		ComponentPrototype componentPrototype = factory.createComponentPrototype();
		componentPrototype.setName("Default");
		componentPrototype.setDescription("Default component prototype.");
		componentPrototype.setModel( spatialModel );
		spatialModel.getComponentPrototypes().add(componentPrototype);
		NodePrototype nodePrototype = factory.createNodePrototype();
		nodePrototype.setDescription("Un named node.");
		nodePrototype.setModel(spatialModel);
		spatialModel.getNodePrototypes().add(nodePrototype);
		ConnectionPrototype connectionPrototype = factory.createConnectionPrototype();
		connectionPrototype.setName("Default");
		connectionPrototype.setModel(spatialModel);
		spatialModel.getConnectionPrototypes().add(connectionPrototype);
		NodeInstance n1 = createNodeInstance(10, 10, nodePrototype, spatialModel);
		NodeInstance n2 = createNodeInstance(200, 200, nodePrototype, spatialModel);
		ConnectionInstance ci = factory.createConnectionInstance();
		ci.setPrototype(connectionPrototype);
		ci.setFrom(n1);
		ci.setTo(n2);
		ci.setModel(spatialModel);
		return spatialModel;
	}
	
	private NodeInstance createNodeInstance( int x , int y , NodePrototype nodePrototype , SpatialModel spatialModel ) {
		NodeInstance n = factory.createNodeInstance();
		n.setX(x);
		n.setY(y);
		n.setPrototype(nodePrototype);
		n.setModel(spatialModel);
		return n;
	}

}
