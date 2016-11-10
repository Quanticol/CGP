package eu.quanticol.cgp.gef.editor;

import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;

import eu.quanticol.cgp.gef.editor.factory.CGPComponentInstanceFactory;
import eu.quanticol.cgp.gef.editor.factory.CGPNodeInstanceFactory;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.NodePrototype;

public class CGPGraphicalEditorPalette extends PaletteRoot {
	
	PaletteGroup toolGroup;
	PaletteGroup nodeGroup;
	PaletteGroup componentGroup;
	 
	  public CGPGraphicalEditorPalette( ) {
	    addGroup();
	    addSelectionTool();
	  }
	 
	  private void addSelectionTool() {
	    SelectionToolEntry entry = new SelectionToolEntry();
	    toolGroup.add(entry);
	    setDefaultEntry(entry);
	  }
	 
	  private void addGroup() {
		toolGroup = new PaletteGroup("Tools");
		nodeGroup = new PaletteGroup("Nodes");
		componentGroup = new PaletteGroup("Components");
	    add(toolGroup);
	    add(nodeGroup);
	    add(componentGroup);
	  }
	 
	  public void addNodeTool( NodePrototype prototype ) {
	    CreationToolEntry entry = new CreationToolEntry(prototype.getName(), prototype.getDescription(), new CGPNodeInstanceFactory(prototype), null, null);
	    nodeGroup.add(entry);
	  }
	 
	  public void addComponentTool( ComponentPrototype prototype ) {
	    CreationToolEntry entry = new CreationToolEntry(prototype.getName(), prototype.getDescription(), new CGPComponentInstanceFactory(prototype), null, null);
	    componentGroup.add(entry);
	  }

}
