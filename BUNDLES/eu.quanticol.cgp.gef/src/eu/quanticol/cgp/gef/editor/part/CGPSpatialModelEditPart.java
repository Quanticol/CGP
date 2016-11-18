/**
 * 
 */
package eu.quanticol.cgp.gef.editor.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;

import eu.quanticol.cgp.gef.editor.policy.CGPLocatedElementXYLayoutPolicy;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.SpatialModel;

/**
 * @author loreti
 *
 */
public class CGPSpatialModelEditPart extends AbstractGraphicalEditPart {
	
	private SpatialModelAdapter adapter;

	public CGPSpatialModelEditPart() {
		super();
		this.adapter = new SpatialModelAdapter();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
	    FreeformLayer layer = new FreeformLayer();
	    layer.setLayoutManager(new FreeformLayout());
	    layer.setBorder(new LineBorder(1));
	    return layer;
	  }
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new CGPLocatedElementXYLayoutPolicy());
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy());
	}

	@Override public void activate() {
	    if(!isActive()) {
	      ((SpatialModel) getModel()).eAdapters().add(adapter);
	    }
	    super.activate();
	  }	
	
	@Override public void deactivate() {
	    if(isActive()) {
	      ((SpatialModel)getModel()).eAdapters().remove(adapter);
	    }
	    super.deactivate();
	  }	
	
	@Override
	protected List<LocatedElement> getModelChildren() {
	    List<LocatedElement> retVal = new ArrayList<LocatedElement>();
	    SpatialModel sm = (SpatialModel) getModel();
	    retVal.addAll(sm.getLocatedElements());
	    return retVal;
	}
	
	@Override public Object getAdapter(Class key) {
		 if (key == SnapToHelper.class) {
		        List<SnapToHelper> helpers = new ArrayList<SnapToHelper>();
		        if (Boolean.TRUE.equals(getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED))) {
		            helpers.add(new SnapToGeometry(this));
		        }
		        if (Boolean.TRUE.equals(getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED))) {
		            helpers.add(new SnapToGrid(this));
		        }
		        if(helpers.size()==0) {
		            return null;
		        } else {
		            return new CompoundSnapToHelper(helpers.toArray(new SnapToHelper[0]));
		        }
		    }
		    return super.getAdapter(key);
	}
	
	public class SpatialModelAdapter implements Adapter {
		 
	    @Override public void notifyChanged(Notification notification) {
	      refreshChildren();
	    }
	 
	    @Override public Notifier getTarget() {
	      return (SpatialModel) getModel();
	    }
	 
	    @Override public void setTarget(Notifier newTarget) {
	      // Do nothing.
	    }
	 
	    @Override public boolean isAdapterForType(Object type) {
	      return type.equals(SpatialModel.class);
	    }
	  }
	
	
}
