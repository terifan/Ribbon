package org.terifan.ui.ribbon;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;


public class RibbonTab extends Container
{
	public RibbonTab()
	{
		installDefaults();
	}


	protected void installDefaults()
	{
		setLayout(new FlowLayout());
	}


	private class FlowLayout implements LayoutManager
	{
		@Override
		public void addLayoutComponent(String name, Component comp)
		{
		}


		@Override
		public void removeLayoutComponent(Component comp)
		{
		}


		@Override
		public Dimension preferredLayoutSize(Container target)
		{
			synchronized (target.getTreeLock())
			{
				Dimension dimension = new Dimension(0, 92);

				int numComponents = target.getComponentCount();

				for (int i = 0; i < numComponents; i++)
				{
					Component component = target.getComponent(i);

					if (component.isVisible())
					{
						dimension.width += component.getPreferredSize().width;
						dimension.width += 2;
					}
				}

				return dimension;
			}
		}


		@Override
		public Dimension minimumLayoutSize(Container target)
		{
			return preferredLayoutSize(target);
		}


		@Override
		public void layoutContainer(Container target)
		{
			synchronized (target.getTreeLock())
			{
				int numComponents = target.getComponentCount();

				for (int i = 0, x = 0; i < numComponents; i++)
				{
					Component component = target.getComponent(i);

					if (component.isVisible())
					{
						Dimension d = component.getPreferredSize();
						component.setLocation(x, 0);
						component.setSize(d);

						x += d.width + 2;
					}
				}
			}
		}
	}
}
