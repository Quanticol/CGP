package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class MakeInitSelectionListener implements SelectionListener {

	SpatialModelPrototypesView view;
	
	public MakeInitSelectionListener(SpatialModelPrototypesView spatialModelPrototypesView) {
		this.view = spatialModelPrototypesView;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		view.makeSelectedStateInit();
		view.refreshMovementAllowanceTab();

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
