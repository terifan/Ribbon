package org.terifan.ui.ribbon.plaf;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTextFieldUI;
import org.terifan.ui.ribbon.RibbonUtils;


public class RibbonTextFieldUI extends BasicTextFieldUI
{
	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);

		c.setFont(RibbonUtils.getFont("labelfont"));
		c.setForeground(RibbonUtils.getColor("labelcolor"));
	}


	@Override
	public Dimension getPreferredSize(JComponent c)
	{
		Dimension d = super.getPreferredSize(c);
		d.height = 22;
		return d;
	}
}
