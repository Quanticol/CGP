package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class AddNewStateSelectionListener implements SelectionListener {

	SpatialModelPrototypesView view;
	
	public AddNewStateSelectionListener(SpatialModelPrototypesView spatialModelPrototypesView) {
		this.view = spatialModelPrototypesView;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		view.addNewStateToSelectedComponent();
		view.refreshMovementAllowanceTab();

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
