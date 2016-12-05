/**
 * 
 */
package eu.quanticol.cgp.gef.editor;

import java.io.IOException;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener2;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;
import eu.quanticol.cgp.gef.editor.command.CGPComponentPrototypeCreateCommand;
import eu.quanticol.cgp.gef.editor.command.CGPComponentPrototypeDeleteCommand;
import eu.quanticol.cgp.gef.editor.command.CGPConnectionPrototypeCreateCommand;
import eu.quanticol.cgp.gef.editor.command.CGPConnectionPrototypeDeleteCommand;
import eu.quanticol.cgp.gef.editor.command.CGPNodePrototypeCreateCommand;
import eu.quanticol.cgp.gef.editor.command.CGPNodePrototypeDeleteCommand;
import eu.quanticol.cgp.gef.editor.part.CGPEditorPartFactory;
import eu.quanticol.cgp.gef.utils.CGPModelUtils;
import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.CGPPackage;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.SpatialModel;

/**
 * @author loreti
 *
 */
public class CGPGraphicalEditor extends GraphicalEditorWithFlyoutPalette {

	private Resource cgpResource;
	private SpatialModel cgp;
	private CGPGraphicalEditorPalette palette;
	private SpatialModelPrototypesView prototypesView;

	public CGPGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		this.loadInput(input);
		fillPalette();
	}

	private void fillPalette() {
		for (NodePrototype np : cgp.getNodePrototypes()) {
			this.palette.addNodeTool(np);
		}
		for (ComponentPrototype cp : cgp.getComponentPrototypes()) {
			this.palette.addComponentTool(cp);
		}
		for (ConnectionPrototype connPrototype : cgp.getConnectionPrototypes()) {
			this.palette.addConnectionTool(connPrototype);
		}
	}

	private void refreshPalette() {
		if (this.palette == null) {
			return;
		}
		clearPalette();
		fillPalette();
	}

	private void refreshPrototypesView() {
		if (this.prototypesView == null) {
			return;
		}
		prototypesView.refresh();

	}

	private void clearPalette() {
		palette.clear();
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(cgp);
		cgp.eAdapters().add(new Adapter() {

			@Override
			public void setTarget(Notifier newTarget) {
				// TODO Auto-generated method stub

			}

			@Override
			public void notifyChanged(Notification notification) {
				Object o = notification.getFeature();
				System.out.println("CLASS: " + o.getClass() + " INST: " + o.toString());
				refreshPalette();
				refreshPrototypesView();
			}

			@Override
			public boolean isAdapterForType(Object type) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Notifier getTarget() {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setEditPartFactory(new CGPEditorPartFactory());
		getActionRegistry().registerAction(new ToggleGridAction(getGraphicalViewer()));
		getActionRegistry().registerAction(new ToggleSnapToGeometryAction(getGraphicalViewer()));

	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		if (palette == null) {
			this.palette = new CGPGraphicalEditorPalette();
		}
		return palette;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (cgpResource == null) {
			return;
		}

		try {
			cgpResource.save(null);
			getCommandStack().markSaveLocation();
		} catch (IOException e) {
			// TODO do something smarter.
			e.printStackTrace();
			cgpResource = null;
		}
	}

	private void loadInput(IEditorInput input) {
		CGPPackage.eINSTANCE.eClass();
		ResourceSet resourceSet = new ResourceSetImpl();
		if (input instanceof IFileEditorInput) {
			IFileEditorInput fileInput = (IFileEditorInput) input;
			IFile file = fileInput.getFile();
			cgpResource = resourceSet.createResource(URI.createURI(file.getLocationURI().toString()));
			try {
				cgpResource.load(null);
				cgp = (SpatialModel) cgpResource.getContents().get(0);
			} catch (IOException e) {
				// TODO do something smarter.
				e.printStackTrace();
				cgpResource = null;
			}
		}
	}

	@Override
	public void commandStackChanged(EventObject event) {
		firePropertyChange(PROP_DIRTY);
		super.commandStackChanged(event);
	}

	public SpatialModel getModel() {
		return cgp;
	}

	public void createComponentPrototype(Command command) {
		executeCommand(command);
	}

	public void setView(SpatialModelPrototypesView prototypesView) {
		this.prototypesView = prototypesView;

	}

	private void executeCommand(Command c){
		CommandStack cs = getCommandStack();
		if (cs != null) {
			cs.execute(c);
		}
	}
	
	public void deleteComponentPrototype(CGPComponentPrototypeDeleteCommand command) {
		executeCommand(command);
	}

	public void deleteNodePrototype(CGPNodePrototypeDeleteCommand command) {
		executeCommand(command);
	}

	public void createNodePrototype(CGPNodePrototypeCreateCommand command) {
		executeCommand(command);
		
	}

	public void createConnectionPrototype(CGPConnectionPrototypeCreateCommand command) {
		executeCommand(command);
		
	}

	public void deleteConnectionPrototype(CGPConnectionPrototypeDeleteCommand command) {
		executeCommand(command);
		
	}
}
