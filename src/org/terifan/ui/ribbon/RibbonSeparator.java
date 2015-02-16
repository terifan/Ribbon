package org.terifan.ui.ribbon;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.plaf.ComponentUI;
import org.terifan.ui.ribbon.plaf.RibbonSeparatorUI;


public class RibbonSeparator extends JSeparator
{
	public RibbonSeparator()
	{
		super(SwingConstants.VERTICAL);
		installDefaults(1,1,true);
	}


	public RibbonSeparator(int aPadBefore, int aPadAfter, boolean aDrawBevel)
	{
		super(SwingConstants.VERTICAL);
		installDefaults(aPadBefore, aPadAfter, aDrawBevel);
	}


	protected void installDefaults(int aPadBefore, int aPadAfter, boolean aDrawBevel)
	{
		setUI(new RibbonSeparatorUI(aPadBefore, aPadAfter, aDrawBevel));
	}


	@Override
	protected void setUI(ComponentUI newUI)
	{
		if (newUI instanceof RibbonSeparatorUI)
		{
			super.setUI(newUI);
		}
	}
}
