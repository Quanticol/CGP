package eu.quanticol.cgp.gef;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class StatesListForAllowanceTableSelectionChangedLIstenter implements SelectionListener {

	SpatialModelPrototypesView view;
	
	public StatesListForAllowanceTableSelectionChangedLIstenter(SpatialModelPrototypesView spatialModelPrototypesView) {
		this.view = spatialModelPrototypesView;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		view.populateCombosAllowance();

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
