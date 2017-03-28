package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class AddNewNodePrototypeSelectionListener implements SelectionListener {

	SpatialModelPrototypesView view;
	
	public AddNewNodePrototypeSelectionListener(SpatialModelPrototypesView spatialModelPrototypesView) {
		super();
		this.view = spatialModelPrototypesView;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		view.createNodePrototype();
		view.refreshAddNodeButton();
		view.refreshMovementAllowanceTab();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
