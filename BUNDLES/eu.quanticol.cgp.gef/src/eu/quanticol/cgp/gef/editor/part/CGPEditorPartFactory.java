package eu.quanticol.cgp.gef.editor.part;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.swt.graphics.Color;

import eu.quanticol.cgp.gef.LooksCatalog;
import eu.quanticol.cgp.model.Colour;
import eu.quanticol.cgp.model.ComponentInstance;
import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPEditorPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
	     
	    if (model instanceof SpatialModel) {
	    	part = new CGPSpatialModelEditPart();
	    } else if (model instanceof NodeInstance) {

	    	part = new CGPNodeInstanceEditPart();
	    } else if (model instanceof ComponentInstance) {

	    	part = new CGPComponentInstanceEditPart();
	    } else if(model instanceof ConnectionInstance){

	    	part = new CGPConnectionEditPart();
	    }
	    
	     
	    if (part != null) {
	      part.setModel(model);
	    }
	     
	    return part;
	}

}
