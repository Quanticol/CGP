package eu.quanticol.cgp.gef.editor.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import eu.quanticol.cgp.gef.editor.command.CGPLocatedElementCreateCommand;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPLocateElementXYLayoutPolicy extends XYLayoutEditPolicy {

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

}
