package org.terifan.ui.ribbon;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.ComponentUI;
import org.terifan.ui.ribbon.plaf.RibbonComboBoxUI;


public class RibbonComboBox extends JComboBox
{
	public RibbonComboBox()
	{
		super();
		installDefaults();
	}


	public RibbonComboBox(Object[] items)
	{
		super(items);
		installDefaults();
	}


	public RibbonComboBox(ComboBoxModel aModel)
	{
		super(aModel);
		installDefaults();
	}


	protected void installDefaults()
	{
		setUI(new RibbonComboBoxUI());
	}


	@Override
	protected void setUI(ComponentUI newUI)
	{
		if (newUI instanceof RibbonComboBoxUI)
		{
			super.setUI(newUI);
		}
	}
}
