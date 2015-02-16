package org.terifan.ui.ribbon;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;


public interface Overlay
{
	public void setComponent(JComponent aComponent);

	public void paint(Graphics aGraphics);

	public void mouseMoved(MouseEvent aEvent);

	public void mouseDragged(MouseEvent aEvent);

	public void mousePressed(MouseEvent aEvent);

	public void mouseReleased(MouseEvent aEvent);

	public void mouseEntered(MouseEvent aEvent);

	public void mouseExited(MouseEvent aEvent);
}
