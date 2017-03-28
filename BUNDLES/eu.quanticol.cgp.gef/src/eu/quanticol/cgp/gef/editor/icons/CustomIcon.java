package eu.quanticol.cgp.gef.editor.icons;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public interface CustomIcon {

	Image getImage16(RGB colour, Display display);
	Image getImage24(RGB colour, Display display);

}
