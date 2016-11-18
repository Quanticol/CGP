package eu.quanticol.cgp.gef.editor.policy;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import eu.quanticol.cgp.gef.editor.command.CGPLocatedElementChangeLocationCommand;
import eu.quanticol.cgp.gef.editor.command.CGPLocatedElementCreateCommand;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPLocatedElementXYLayoutPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		Command toReturn = null;
		if (request.getNewObject() instanceof LocatedElement) {
			CGPLocatedElementCreateCommand command = new CGPLocatedElementCreateCommand();
			command.setLocatedElement( (LocatedElement) request.getNewObject() );
			command.setLocation(request.getLocation());
			command.setParent( (SpatialModel) (getHost().getModel()) );
			toReturn = command;
		}
		return toReturn;
	}
	
	
	  @Override 
	  protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		  CGPLocatedElementChangeLocationCommand command = new CGPLocatedElementChangeLocationCommand();
		    command.setModel((LocatedElement) child.getModel());
		    command.setNewLocation((Rectangle) constraint);
		    return command;
		  }

}
