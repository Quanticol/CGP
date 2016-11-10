/**
 * 
 */
package eu.quanticol.cgp.gef.editor;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import org.eclipse.ui.IFileEditorInput;

import eu.quanticol.cgp.gef.editor.command.CGPComponentPrototypeCreateCommand;
import eu.quanticol.cgp.gef.editor.part.CGPEditorPartFactory;
import eu.quanticol.cgp.gef.utils.CGPModelUtils;
import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.CGPPackage;
import eu.quanticol.cgp.model.ComponentPrototype;
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
	
	public CGPGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
		
	}
	   
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		this.loadInput(input);
		for (NodePrototype np: cgp.getNodePrototypes()) {
			this.palette.addNodeTool(np);
		}
		for (ComponentPrototype cp: cgp.getComponentPrototypes()) {
			this.palette.addComponentTool(cp);
		}
	}
	
	@Override protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(cgp);
	} 

	@Override protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setEditPartFactory(new CGPEditorPartFactory());
	} 
	
	@Override
	protected PaletteRoot getPaletteRoot() {
		if (palette == null) {
			this.palette = new CGPGraphicalEditorPalette();
		}
		return palette;
	}

	@Override public void doSave(IProgressMonitor monitor) {
	    if(cgpResource == null) {
	      return;
	    }
	 
	    try {
	    	cgpResource.save(null);
	    } catch(IOException e) {
	      // TODO do something smarter.
	      e.printStackTrace();
	      cgpResource = null;
	    }
	  }
	 
	  private void loadInput(IEditorInput input) {
			CGPPackage.eINSTANCE.eClass();
			ResourceSet resourceSet = new ResourceSetImpl();
			if(input instanceof IFileEditorInput){
			    IFileEditorInput fileInput = (IFileEditorInput) input;
			    IFile file = fileInput.getFile();
			    cgpResource = resourceSet.createResource(URI.createURI(file.getLocationURI().toString()));
			    try {
			      cgpResource.load(null);
			      cgp = (SpatialModel) cgpResource.getContents().get(0);
			    } catch(IOException e) {
			      // TODO do something smarter.
			      e.printStackTrace();
			      cgpResource = null;
			    }
			  }
	  }

	public SpatialModel getModel() {
		return cgp;
	}
	
	public void createComponentPrototype( String name , String description ) {
		CommandStack cs = getCommandStack();
		if (cs != null) {
			CGPComponentPrototypeCreateCommand command = new CGPComponentPrototypeCreateCommand();
			command.setComponentPrototype( CGPFactory.eINSTANCE.createComponentPrototype() );
			command.setDatya(name, description);
			command.setParent(cgp);
			cs.execute(command);
		}
	}
}
