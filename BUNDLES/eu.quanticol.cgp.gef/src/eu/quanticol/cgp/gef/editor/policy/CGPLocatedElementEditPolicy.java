package eu.quanticol.cgp.gef.editor.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import eu.quanticol.cgp.gef.editor.command.CGPLocatedElementDeleteCommand;
import eu.quanticol.cgp.model.LocatedElement;

public class CGPLocatedElementEditPolicy extends ComponentEditPolicy {
	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		CGPLocatedElementDeleteCommand deleteCommand = new CGPLocatedElementDeleteCommand();
		deleteCommand.setLocatedElement((LocatedElement)getHost().getModel());
		return deleteCommand;
	}
	
}
