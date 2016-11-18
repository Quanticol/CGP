package eu.quanticol.cgp.gef.editor.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
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

import eu.quanticol.cgp.gef.Activator;
import eu.quanticol.cgp.gef.editor.figure.CGPFigure;
import eu.quanticol.cgp.gef.editor.policy.CGPLocatedElementEditPolicy;
import eu.quanticol.cgp.model.LocatedElement;

public abstract class CGPLocatedElementEditPart extends AbstractGraphicalEditPart {
	
	
	private LocatedElementAdapter adapter;

	public CGPLocatedElementEditPart() {
		super();
		adapter = new LocatedElementAdapter();
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
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new CGPLocatedElementEditPolicy());
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy());
	}
	
	@Override protected void refreshVisuals() {
		CGPFigure figure = (CGPFigure) getFigure();
	    LocatedElement model = (LocatedElement) getModel();
	    CGPSpatialModelEditPart parent = (CGPSpatialModelEditPart) getParent();

	    figure.setLabel(model.getName());
	    
	    Rectangle layout = new Rectangle(model.getX(), model.getY(), Activator.FIGURE_SIZE, Activator.FIGURE_SIZE);
//	    Rectangle layout = new Rectangle(model.getX()-Activator.HALF_FIGURE_SIZE, model.getY()-Activator.HALF_FIGURE_SIZE, Activator.FIGURE_SIZE, Activator.FIGURE_SIZE);
	    parent.setLayoutConstraint(this, figure, layout);
	
	
	}
	
	@Override public void activate() {
	    if(!isActive()) {
	      ((LocatedElement)getModel()).eAdapters().add(adapter);
	    }
	    super.activate();
	  }
	 
	  @Override public void deactivate() {
	    if(isActive()) {
	      ((LocatedElement)getModel()).eAdapters().remove(adapter);
	    }
	 
	    super.deactivate();
	  } 
	
	public class LocatedElementAdapter implements Adapter {
		 
	    // Adapter interface
	    @Override public void notifyChanged(Notification notification) {
	      refreshVisuals();
	      refreshSourceConnections();
	      refreshTargetConnections();
	    }
	 
	    @Override public Notifier getTarget() {
	      return (LocatedElement)getModel();
	    }
	 
	    @Override public void setTarget(Notifier newTarget) {
	      // Do nothing.
	    }
	 
	    @Override public boolean isAdapterForType(Object type) {
	      return type.equals(LocatedElement.class);
	    }
	  }
}
