package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class RemoveSelectedConnectionAllowanceListener implements SelectionListener {

	SpatialModelPrototypesView view;
	
	public RemoveSelectedConnectionAllowanceListener(SpatialModelPrototypesView spatialModelPrototypesView) {
		this.view = spatialModelPrototypesView;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		this.view.removeSelectedConnectionAllowance();

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
