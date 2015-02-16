package org.terifan.ui.ribbon;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.plaf.ComponentUI;
import org.terifan.ui.ribbon.plaf.RibbonDropButtonUI;


public class RibbonDropButton extends JButton
{
	private boolean mCommandButton;


	public RibbonDropButton()
	{
		this("", null, false);
	}


	public RibbonDropButton(Icon aIcon)
	{
		this("", aIcon, false);
	}


	public RibbonDropButton(String aText, Icon aIcon)
	{
		this(aText, aIcon, false);
	}


	public RibbonDropButton(Icon aIcon, boolean aCommandButton)
	{
		this("", aIcon, aCommandButton);
	}


	public RibbonDropButton(String aText, Icon aIcon, boolean aCommandButton)
	{
		super(aText, aIcon);
		setCommandButton(aCommandButton);
		installDefaults();
	}


	public RibbonDropButton(Action aAction)
	{
		super(aAction);
		installDefaults();
	}


	public RibbonDropButton(Action aAction, boolean aCommandButton)
	{
		super(aAction);
		setCommandButton(aCommandButton);
		installDefaults();
	}


	protected void installDefaults()
	{
		setUI(new RibbonDropButtonUI());
	}


	@Override
	protected void setUI(ComponentUI newUI)
	{
		if (newUI instanceof RibbonDropButtonUI)
		{
			super.setUI(newUI);
		}
	}


	public boolean isCommandButton()
	{
		return mCommandButton;
	}


	public void setCommandButton(boolean aCommandButton)
	{
		mCommandButton = aCommandButton;
	}
}