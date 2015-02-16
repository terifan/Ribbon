package org.terifan.ui.ribbon;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JToggleButton.ToggleButtonModel;


public class RibbonToggleButton extends RibbonButton
{
	public RibbonToggleButton()
	{
		super();
        setModel(new ToggleButtonModel());
	}


	public RibbonToggleButton(String aText, Icon aIcon)
	{
		super(aText, aIcon);
        setModel(new ToggleButtonModel());
	}


	public RibbonToggleButton(String aText)
	{
		super(aText);
        setModel(new ToggleButtonModel());
	}


	public RibbonToggleButton(Icon aIcon)
	{
		super(aIcon);
        setModel(new ToggleButtonModel());
	}


	public RibbonToggleButton(Action aAction)
	{
		super(aAction);
        setModel(new ToggleButtonModel());
	}
}
