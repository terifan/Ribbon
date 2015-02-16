package org.terifan.ui.ribbon;

import javax.swing.JTextField;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.Document;
import org.terifan.ui.ribbon.plaf.RibbonTextFieldUI;


public class RibbonTextField extends JTextField
{
	public RibbonTextField(Document doc, String text, int columns)
	{
		super(doc, text, columns);
		installDefaults();
	}


	public RibbonTextField(String text, int columns)
	{
		super(text, columns);
		installDefaults();
	}


	public RibbonTextField(int columns)
	{
		super(columns);
		installDefaults();
	}


	public RibbonTextField(String text)
	{
		super(text);
		installDefaults();
	}


	public RibbonTextField()
	{
		super();
		installDefaults();
	}


	protected void installDefaults()
	{
		setUI(new RibbonTextFieldUI());
	}


	@Override
	protected void setUI(ComponentUI newUI)
	{
		if (newUI instanceof RibbonTextFieldUI)
		{
			super.setUI(newUI);
		}
	}
}
