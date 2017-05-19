package org.terifan.ui.ribbon;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.plaf.ComponentUI;
import org.terifan.ui.ribbon.plaf.RibbonButtonUI;


public class RibbonButton extends JButton
{
	public RibbonButton()
	{
		super();
		installDefaults();
	}


	public RibbonButton(String aText, Icon aIcon)
	{
		super(aText, aIcon);
		installDefaults();
	}


	public RibbonButton(String aText)
	{
		super(aText);
		installDefaults();
	}


	public RibbonButton(Icon aIcon)
	{
		super(aIcon);
		installDefaults();
	}


	public RibbonButton(Action aAction)
	{
		super(aAction);
		installDefaults();
	}


	protected void installDefaults()
	{
		setUI(new RibbonButtonUI());
	}


	@Override
	protected void setUI(ComponentUI newUI)
	{
		if (newUI instanceof RibbonButtonUI)
		{
			super.setUI(newUI);
		}
	}


	public RibbonButton setGroup(ButtonGroup aButtonGroup)
	{
		aButtonGroup.add(this);
		return this;
	}


	public RibbonButton setActionCommandEx(String aCtionCommand)
	{
		super.setActionCommand(aCtionCommand);
		return this;
	}
}