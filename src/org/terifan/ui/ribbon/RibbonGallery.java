package org.terifan.ui.ribbon;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;


public class RibbonGallery extends JPanel
{
	private final static FontRenderContext FRC = new FontRenderContext(null, true, false);
	private String mName;
	private ArrayList<Rectangle> mComponentRects;
	private Dimension mDimension;
	private boolean mHover;
	private int mColumnCount;
	private Insets mPadding;
	private int mButtonWidth;
	private int mLabelHeight;


	public RibbonGallery(String aName)
	{
		mName = aName;
		mColumnCount = 4;
		mPadding = new Insets(5, 5, 1, 5);
		mButtonWidth = 15;
		mLabelHeight = 17;

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


	@Override
	protected void paintChildren(Graphics aGraphics)
	{
		Shape old = aGraphics.getClip();

		aGraphics.clipRect(mPadding.left, mPadding.top, getWidth() - mPadding.left - mPadding.right - 2 - mButtonWidth, getHeight() - mPadding.top - mPadding.bottom - mLabelHeight + 1);

		super.paintChildren(aGraphics);

		aGraphics.setClip(old);
	}


	@Override
	protected void paintComponent(Graphics aGraphics)
	{
		super.paintComponent(aGraphics);

		Graphics2D graphics = (Graphics2D)aGraphics;
		graphics.setFont(getFont());

		int labelWidth = (int)getFont().getStringBounds(mName, FRC).getWidth();

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

		background = RibbonUtils.load("gallery/background.png", false);

		int areaHeight = h - mPadding.top - mPadding.bottom - mLabelHeight;
		int areaWidth = w - mPadding.left - mPadding.right - mButtonWidth - 1;

		RibbonUtils.drawScaledImage(graphics, background, mPadding.left, mPadding.top, areaWidth, areaHeight, 0, 0);

		graphics.setColor(RibbonUtils.getColor("gallerybordercolor"));
		graphics.drawRect(mPadding.left, mPadding.top, areaWidth, areaHeight - 1);

		int bh1 = (areaHeight) / 3;
		int bh2 = areaHeight - 2 * bh1;
		RibbonUtils.drawScaledImage(graphics, RibbonUtils.load("gallery/arrow_up_disabled.png", false), w - mPadding.right - mButtonWidth - 1, mPadding.top, mButtonWidth, bh1, 0, 0);
		RibbonUtils.drawScaledImage(graphics, RibbonUtils.load("gallery/arrow_down_disabled.png", false), w - mPadding.right - mButtonWidth - 1, mPadding.top + bh1, mButtonWidth, bh1, 0, 0);
		RibbonUtils.drawScaledImage(graphics, RibbonUtils.load("gallery/arrow_drop_disabled.png", false), w - mPadding.right - mButtonWidth - 1, mPadding.top + 2 * bh1, mButtonWidth, bh2, 0, 0);

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
			ArrayList<Rectangle> newComponentRects = new ArrayList<>();

			int numComponents = target.getComponentCount();

			int columnWidth = 0;

			for (int i = 0; i < numComponents; i++)
			{
				Component component = target.getComponent(i);
				columnWidth = Math.max(columnWidth, component.getPreferredSize().width);
			}

			for (int i = 0, y = mPadding.top; i < numComponents;)
			{
				int rowHeight = 21;
//				for (int j = 0; i + j < numComponents && j < mColumnCount; j++)
//				{
//					Component component = target.getComponent(i + j);
//					Dimension d = component.getPreferredSize();
//					rowHeight = Math.max(d.height, rowHeight);
//				}

				for (int x = mPadding.left, j = 0; i < numComponents && j < mColumnCount; i++, j++)
				{
					newComponentRects.add(new Rectangle(x, y, columnWidth, rowHeight));

					x += columnWidth + 1;
				}

				y += rowHeight;
			}

			mComponentRects = newComponentRects;

			int lw = (int)getFont().getStringBounds(mName, FRC).getWidth() + 10;

			mDimension = new Dimension(Math.max(mColumnCount * columnWidth + mPadding.left + mPadding.right + mButtonWidth + 1 + 4, lw), 86);
		}
	}
}
