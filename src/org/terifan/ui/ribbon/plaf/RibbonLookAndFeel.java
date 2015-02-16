package org.terifan.ui.ribbon.plaf;

import javax.swing.plaf.basic.BasicLookAndFeel;


public class RibbonLookAndFeel extends BasicLookAndFeel
{
	@Override
	public String getName() 
	{
		return "RibbonUI";
	}

	@Override
	public String getDescription() 
	{
		return "RibbonUI";
	}

	@Override
	public String getID() 
	{
		return "RibbonUI";
	}

	@Override
	public boolean isNativeLookAndFeel() 
	{
		return false;
	}

	@Override
	public boolean isSupportedLookAndFeel() 
	{
		return true;
	}

//	@Override
//	protected void initClassDefaults(UIDefaults table)
//	{
//		super.initClassDefaults(table);
//
//		Object [] uiDefaults =
//		{
//			"ButtonUI", "org.terifan.ui.ribbon.plaf.RibbonButtonUI"
//		};
//
//		table.putDefaults(uiDefaults);
//	}
}