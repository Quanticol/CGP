package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class AddConnectionAllowanceListener  implements SelectionListener {
	SpatialModelPrototypesView view;
	public AddConnectionAllowanceListener(SpatialModelPrototypesView spatialModelPrototypesView) {
this.view = spatialModelPrototypesView;
}

	@Override
	public void widgetSelected(SelectionEvent e) {
		view.addConnectionAllowance();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
