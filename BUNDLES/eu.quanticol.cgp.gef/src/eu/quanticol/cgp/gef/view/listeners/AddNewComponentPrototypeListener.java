package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;

import eu.quanticol.cgp.gef.LooksCatalog;
import eu.quanticol.cgp.gef.SpatialModelPrototypesView;
import eu.quanticol.cgp.gef.editor.CGPGraphicalEditor;
import eu.quanticol.cgp.gef.editor.command.CGPComponentPrototypeCreateCommand;
import eu.quanticol.cgp.model.CGPFactory;

public class AddNewComponentPrototypeListener implements SelectionListener {

	SpatialModelPrototypesView view;

	public AddNewComponentPrototypeListener(SpatialModelPrototypesView view) {
		super();
		this.view = view;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		view.createComponentPrototype();
		view.refreshAddComponentButton();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
