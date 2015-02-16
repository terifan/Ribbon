package org.terifan.ui.ribbon;

import javax.swing.JTabbedPane;
import javax.swing.plaf.ComponentUI;
import org.terifan.ui.ribbon.plaf.RibbonTabbedPaneUI;


/**
 * A Ribbon is a toolbar that contain a top layer of tabs, middle layer of
 * boxes and bottom level of groups and button.<p/>
 *
 * <code>
 *		Ribbon ribbon = new Ribbon();<br/>
 *		RibbonTab tab = new RibbonTab();<br/>
 *		RibbonGroup group = new RibbonGroup("group title");<br/>
 *		RibbonLabel label = new RibbonLabel("label");<br/>
 *		RibbonButton button = new RibbonButton("button title");<br/>
 *		RibbonBox box = new RibbonBox();<br/>
 *		box.add(label);<br/>
 *		box.add(button);<br/>
 *		group.add(box);<br/>
 *		RibbonButtonGroup buttonGroup = new RibbonButtonGroup();<br/>
 *		buttonGroup.add(new RibbonButton("second button"));<br/>
 *		buttonGroup.add(new RibbonButton("third button"));<br/>
 *		buttonGroup.add(new RibbonButton("fourth button"));<br/>
 *		group.add(buttonGroup);<br/>
 *		tab.add(group);<br/>
 *		ribbon.add("tab title", tab);<br/>
 * </code>
 */
public class Ribbon extends JTabbedPane
{
	public Ribbon()
	{
		installDefaults();
	}


	protected void installDefaults()
	{
		setUI(new RibbonTabbedPaneUI());
	}


	@Override
	protected void setUI(ComponentUI newUI)
	{
		if (newUI instanceof RibbonTabbedPaneUI)
		{
			super.setUI(newUI);
		}
	}
}
