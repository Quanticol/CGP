package eu.quanticol.cgp.gef.editor.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import eu.quanticol.cgp.gef.editor.command.CGPConnectionDeleteCommand;
import eu.quanticol.cgp.model.ConnectionInstance;

public class CGPConnectionInstanceEditPolicy extends ConnectionEditPolicy {

	@Override
	protected CGPConnectionDeleteCommand getDeleteCommand(GroupRequest arg0) {
		CGPConnectionDeleteCommand command = new CGPConnectionDeleteCommand();
		command.setConnectionInstance((ConnectionInstance) getHost().getModel());
		return command;
	}

}
