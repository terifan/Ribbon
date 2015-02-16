package org.terifan.ui.ribbon;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;


public class RibbonGroup extends JPanel
{
	private final static FontRenderContext FRC = new FontRenderContext(null, true, false);
	private ArrayList<Rectangle> mComponentRects;
	private Dimension mDimension;
	private String mName;
	private boolean mHover;


	public RibbonGroup(String aName)
	{
		mName = aName;
		mComponentRects = new ArrayList<>();

		installDefaults();
	}


	protected void installDefaults()
	{
		setOpaque(false);
		setFont(RibbonUtils.getFont("groupfont"));
		setBorder(null);
		setLayout(new GroupLayout());
	}


	protected void setHover(boolean aHover)
	{
		mHover = aHover;
		repaint();
	}


	public RibbonSeparator addSeparator()
	{
		RibbonSeparator separator = new RibbonSeparator();
		add(separator);
		return separator;
	}


	@Override
	protected void paintComponent(Graphics aGraphics)
	{
		super.paintComponent(aGraphics);

		Graphics2D graphics = (Graphics2D)aGraphics;
		graphics.setFont(getFont());

		int labelWidth = (int) getFont().getStringBounds(mName, FRC).getWidth();

		BufferedImage background;

		if (mHover)
		{
			background = RibbonUtils.load("group/hover.png", true);
		}
		else
		{
			background = RibbonUtils.load("group/normal.png", true);
		}

		int w = getWidth();
		int h = getHeight();

		RibbonUtils.drawScaledImage(graphics, background, 0, 0, w, h, 5, 5);

		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		graphics.setColor(RibbonUtils.getColor("grouptextcolor"));
		graphics.drawString(mName, (w - labelWidth) / 2, h - 5);
	}


	private class GroupLayout implements LayoutManager
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
				layoutComponents(target);
				return mDimension;
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
				layoutComponents(target);

				int numComponents = target.getComponentCount();

				for (int i = 0; i < numComponents; i++)
				{
					Component component = target.getComponent(i);

					if (component.isVisible())
					{
						component.setBounds(mComponentRects.get(i));
					}
				}
			}
		}


		private void layoutComponents(Container target)
		{
			int w = 0;

			ArrayList<Rectangle> newComponentRects = new ArrayList<>();

			int numComponents = target.getComponentCount();

			for (int i = 0, j, x = 4; i < numComponents;)
			{
				if (i + 0 == numComponents - 1 || isLarge(target.getComponent(i + 0)) || isLarge(target.getComponent(i + 1)))
				{
					j = i + 0;
				}
				else if (i + 1 == numComponents - 1 || isLarge(target.getComponent(i + 2)))
				{
					j = i + 1;
				}
				else if (i + 2 == numComponents - 1 || isLarge(target.getComponent(i + 3)))
				{
					j = i + 2;
				}
				else
				{
					j = i + 2;
				}

				int y = 2;
				int n = j - i + 1;
				int step = 0;

				if (n == 3)
				{
					step = 22;
				}
				else if (n == 2)
				{
					double yy = 22 / 3.0;
					y += (int) Math.round(yy);
					step = 22 + (int) Math.round(yy + yy - Math.round(yy));
				}
				else if (!isLarge(target.getComponent(i)))
				{
					y += 22;
				}

				int columnWidth = 0;

				for (; i <= j; i++)
				{
					Component component = target.getComponent(i);
					Dimension d = component.getPreferredSize();

					if (isLarge(component))
					{
						d.height = 22 * 3;
					}
					else
					{
						d.height = 22;
					}

					newComponentRects.add(new Rectangle(x, y, d.width, d.height));
					columnWidth = Math.max(d.width, columnWidth);
					y += step;
				}

				x += columnWidth + 1;
				w += columnWidth;
			}

			w += 9;

			mComponentRects = newComponentRects;

			int lw = (int) getFont().getStringBounds(mName, FRC).getWidth() + 10;

			mDimension = new Dimension(Math.max(w, lw), 86);
		}


		private boolean isLarge(Component aComponent)
		{
			return aComponent instanceof RibbonSeparator || aComponent.getPreferredSize().height >= 32;
		}
	}
}
