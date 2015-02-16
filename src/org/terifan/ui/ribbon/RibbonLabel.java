package org.terifan.ui.ribbon;

import javax.swing.JLabel;
import javax.swing.plaf.ComponentUI;
import org.terifan.ui.ribbon.plaf.RibbonLabelUI;


public class RibbonLabel extends JLabel
{
	public RibbonLabel()
	{
		super();
		installDefaults();
	}


	public RibbonLabel(String text)
	{
		super(text);
		installDefaults();
	}


	protected void installDefaults()
	{
		setUI(new RibbonLabelUI());
	}


	@Override
	protected void setUI(ComponentUI newUI)
	{
		if (newUI instanceof RibbonLabelUI)
		{
			super.setUI(newUI);
		}
	}
}
