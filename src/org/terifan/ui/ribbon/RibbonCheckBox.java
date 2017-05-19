package org.terifan.ui.ribbon;

import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.plaf.ComponentUI;
import org.terifan.ui.ribbon.plaf.RibbonCheckBoxUI;


public class RibbonCheckBox extends JCheckBox
{
	public RibbonCheckBox()
	{
		super();
		installDefaults();
	}


	public RibbonCheckBox(Action a)
	{
		super(a);
		installDefaults();
	}


	public RibbonCheckBox(Action a, boolean aSelected)
	{
		super(a);
		installDefaults();
		setSelected(aSelected);
	}


	public RibbonCheckBox(String text)
	{
		super(text);
		installDefaults();
	}


	public RibbonCheckBox(String text, boolean selected)
	{
		super(text, selected);
		installDefaults();
	}


	protected void installDefaults()
	{
		setUI(new RibbonCheckBoxUI());
	}


	@Override
	protected void setUI(ComponentUI newUI)
	{
		if (newUI instanceof RibbonCheckBoxUI)
		{
			super.setUI(newUI);
		}
	}
}
