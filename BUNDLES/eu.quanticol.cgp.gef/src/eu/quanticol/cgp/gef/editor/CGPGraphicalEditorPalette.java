package eu.quanticol.cgp.gef.editor;

import java.util.List;

import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.swt.widgets.Control;

import eu.quanticol.cgp.gef.editor.factory.CGPComponentInstanceFactory;
import eu.quanticol.cgp.gef.editor.factory.CGPNodeInstanceFactory;
import eu.quanticol.cgp.gef.editor.factory.ConnectionInstanceFactory;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.NodePrototype;

public class CGPGraphicalEditorPalette extends PaletteRoot {

	private final String toolsGroupName = "Tools";
	private final String nodesGroupName = "Nodes";
	private final String componentsGroupName = "Compponents";
	private final String connectionsGroupName = "Connections";

	PaletteGroup toolGroup;
	PaletteGroup connectionGroup;
	PaletteGroup nodeGroup;
	PaletteGroup componentGroup;

	public CGPGraphicalEditorPalette() {
		addGroup();
	}

	private void addSelectionTool() {
		SelectionToolEntry entry = new SelectionToolEntry();
		toolGroup.add(entry);
		setDefaultEntry(entry);
	}

	private void addGroup() {
		toolGroup = new PaletteGroup(toolsGroupName);
		nodeGroup = new PaletteGroup(nodesGroupName);
		componentGroup = new PaletteGroup(componentsGroupName);
		connectionGroup = new PaletteGroup(connectionsGroupName);
		add(toolGroup);
		add(nodeGroup);
		add(componentGroup);
		add(connectionGroup);
		addSelectionTool();
	}

	public void addNodeTool(NodePrototype prototype) {
		String name = prototype.getName();
		if (prototype.getName() == null || prototype.getName().equals("")) {
			name = "Unnamed (" + prototype.hashCode() + ")";
		}

		CreationToolEntry entry = new CreationToolEntry(name, prototype.getDescription(),
				new CGPNodeInstanceFactory(prototype), null, null);
		nodeGroup.add(entry);
	}

	public void addComponentTool(ComponentPrototype prototype) {
		String name = prototype.getName();
		if (prototype.getName() == null || prototype.getName().equals("")) {
			name = "Unnamed (" + prototype.hashCode() + ")";
		}
		CreationToolEntry entry = new CreationToolEntry(name, prototype.getDescription(),
				new CGPComponentInstanceFactory(prototype), null, null);
		componentGroup.add(entry);
	}

	public void addConnectionTool(ConnectionPrototype connectionPrototype) {
		String name = connectionPrototype.getName();
		if (connectionPrototype.getName() == null || connectionPrototype.getName().equals("")) {
			name = "Unnamed (" + connectionPrototype.hashCode() + ")";
		}
		ConnectionCreationToolEntry connectionCreationToolEntry = new ConnectionCreationToolEntry(name,
				connectionPrototype.getDescription(), new ConnectionInstanceFactory(), null, null);
		connectionGroup.add(connectionCreationToolEntry);
	}

	public void clear() {
		List children = this.getChildren();
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) instanceof PaletteGroup) {
				PaletteGroup entry = (PaletteGroup) children.get(i);
				if (entry.getLabel().equals(componentsGroupName) || entry.getLabel().equals(nodesGroupName)
						|| entry.getLabel().equals(connectionsGroupName)) {
					int size = entry.getChildren().size();
					for (int j = 0; j < size; j++) {
						entry.getChildren().remove(0);
					}
				}
			}
		}

	}

}
