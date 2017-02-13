package org.terifan.ui.ribbon.plaf;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.TabbedPaneUI;
import org.terifan.ui.ribbon.Overlay;
import org.terifan.ui.ribbon.RibbonUtils;


public class RibbonTabbedPaneUI extends TabbedPaneUI
{
	private final static FontRenderContext FRC = new FontRenderContext(null, true, false);
	private int mHoverIndex = -1;
	protected JTabbedPane mTabbedPane;
	private Overlay mOverlay;


	@Override
	public void installUI(JComponent aComponent)
	{
		mTabbedPane = (JTabbedPane) aComponent;

		final JTabbedPane tabbedPane = (JTabbedPane) aComponent;

		aComponent.setFont(RibbonUtils.getFont("tabfont"));
		aComponent.setLayout(new TabbedPaneLayout((JTabbedPane) aComponent));
		aComponent.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent aEvent)
			{
				int index = tabForCoordinate(tabbedPane, aEvent.getX(), aEvent.getY());
				if (index != -1)
				{
					tabbedPane.setSelectedIndex(index);
					tabbedPane.revalidate();
				}
				if (mOverlay != null)
				{
					mOverlay.mousePressed(aEvent);
				}
			}


			@Override
			public void mouseReleased(MouseEvent aEvent)
			{
				tabbedPane.repaint();

				int index = tabForCoordinate(tabbedPane, aEvent.getX(), aEvent.getY());
				if (mHoverIndex != index)
				{
					mHoverIndex = index;
					tabbedPane.repaint();
				}
				if (mOverlay != null)
				{
					mOverlay.mouseReleased(aEvent);
				}
			}


			@Override
			public void mouseExited(MouseEvent aEvent)
			{
				mHoverIndex = -1;
				tabbedPane.repaint();
				if (mOverlay != null)
				{
					mOverlay.mouseExited(aEvent);
				}
			}


			@Override
			public void mouseEntered(MouseEvent aEvent)
			{
				if (mOverlay != null)
				{
					mOverlay.mouseEntered(aEvent);
				}
			}
		});
		aComponent.addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent aEvent)
			{
				int index = tabForCoordinate(tabbedPane, aEvent.getX(), aEvent.getY());
				if (mHoverIndex != index)
				{
					mHoverIndex = index;
					tabbedPane.repaint();
				}
				if (mOverlay != null)
				{
					mOverlay.mouseMoved(aEvent);
				}
			}


			@Override
			public void mouseDragged(MouseEvent aEvent)
			{
				int index = tabForCoordinate(tabbedPane, aEvent.getX(), aEvent.getY());
				if (mHoverIndex != index)
				{
					mHoverIndex = index;
				}
				tabbedPane.repaint();
				if (mOverlay != null)
				{
					mOverlay.mouseDragged(aEvent);
				}
			}
		});
	}


	@Override
	public void paint(Graphics aGraphics, JComponent aComponent)
	{
		Graphics2D graphics = (Graphics2D) aGraphics;
		JTabbedPane tabbedPane = (JTabbedPane) aComponent;

		RibbonUtils.drawScaledImage(graphics, RibbonUtils.load("tabbedpane/background.png"), 0, 0, mTabbedPane.getWidth(), mTabbedPane.getHeight(), 5, 5);

		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

		for (int i = 0, x = 37; i < tabbedPane.getTabCount(); i++)
		{
			String title = tabbedPane.getTitleAt(i);

			int titleWidth = (int) graphics.getFont().getStringBounds(title, FRC).getWidth();

			int tabWidth = titleWidth + 28;

			BufferedImage tabImage = null;

			graphics.setColor(RibbonUtils.getColor("tabtextcolor"));

			if (i == tabbedPane.getSelectedIndex() && (i == mHoverIndex))// || mMouseDragged))
			{
				tabImage = RibbonUtils.load("tabbedpane/hover_selected.png");
				graphics.setColor(RibbonUtils.getColor("tabselectedtextcolor"));
			}
			else if (i == mHoverIndex)
			{
				tabImage = RibbonUtils.load("tabbedpane/hover.png");
			}
			else if (i == tabbedPane.getSelectedIndex())
			{
				tabImage = RibbonUtils.load("tabbedpane/normal.png");
				graphics.setColor(RibbonUtils.getColor("tabselectedtextcolor"));
			}

			if (tabImage != null)
			{
				RibbonUtils.drawScaledImage(graphics, tabImage, x, 0, tabWidth, tabImage.getHeight(), 5, 5);
			}

			graphics.drawString(title, x + (tabWidth - titleWidth) / 2, 15);

			x += tabWidth + 2;
		}

		if (mOverlay != null)
		{
			mOverlay.paint(aGraphics);
		}
	}


	@Override
	public Dimension getPreferredSize(JComponent aComponent)
	{
		Component c = ((JTabbedPane)aComponent).getSelectedComponent();

		Dimension d = new Dimension(20, 23 + 94);

		if (c != null)
		{
			d.width = c.getPreferredSize().width;
		}

		return d;
	}


	@Override
	public int tabForCoordinate(JTabbedPane tabbedPane, int aPointX, int aPointY)
	{
		if (aPointY < 24)
		{
			for (int i = 0, x = 37; i < tabbedPane.getTabCount(); i++)
			{
				String title = tabbedPane.getTitleAt(i);
				int titleWidth = (int) tabbedPane.getFont().getStringBounds(title, FRC).getWidth();
				int tabWidth = titleWidth + 28;

				if (aPointX >= x && aPointX < x + tabWidth)
				{
					return i;
				}

				x += tabWidth + 2;
			}
		}

		return -1;
	}


	@Override
	public Rectangle getTabBounds(JTabbedPane tabbedPane, int index)
	{
		for (int i = 0, x = 37; i < tabbedPane.getTabCount(); i++)
		{
			String title = tabbedPane.getTitleAt(i);
			int titleWidth = (int) tabbedPane.getFont().getStringBounds(title, FRC).getWidth();
			int tabWidth = titleWidth + 28;

			if (i == index)
			{
				return new Rectangle(x, 0, tabWidth, 23);
			}

			x += tabWidth + 2;
		}

		return null;
	}


	@Override
	public int getTabRunCount(JTabbedPane pane)
	{
		return 0;
	}


	public void setOverlay(Overlay aOverlay)
	{
		mOverlay = aOverlay;
		if (mOverlay != null)
		{
			mOverlay.setComponent(mTabbedPane);
		}
	}


	private class TabbedPaneLayout implements LayoutManager
	{
		JTabbedPane mTabbedPane;


		public TabbedPaneLayout(JTabbedPane aTabbedPane)
		{
			mTabbedPane = aTabbedPane;
		}


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
				return target.getPreferredSize();
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
				Dimension d = target.getPreferredSize();

				if (mTabbedPane.getSelectedComponent() != null)
				{
					mTabbedPane.getSelectedComponent().setBounds(5, 25, d.width, 86);
				}
			}
		}
	}
}
