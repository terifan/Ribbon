package org.terifan.ui.ribbon.plaf;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicButtonUI;
import org.terifan.ui.ribbon.RibbonButtonGroup;
import org.terifan.ui.ribbon.RibbonDropButton;
import org.terifan.ui.ribbon.RibbonUtils;


public class RibbonDropButtonUI extends BasicButtonUI
{
	private final static FontRenderContext FRC = new FontRenderContext(null,true,false);
	private MyMouseMotionListener mMouseMotionListener;
	private boolean mHoverButton;


	@Override
	protected void installDefaults(AbstractButton b)
	{
		mMouseMotionListener = new MyMouseMotionListener();

		LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);
		b.setFont(RibbonUtils.getFont("buttonfont"));
		b.setRolloverEnabled(true);
		b.addMouseMotionListener(mMouseMotionListener);
	}


	@Override
	protected void uninstallDefaults(AbstractButton b)
	{
		super.uninstallDefaults(b);

		b.removeMouseMotionListener(mMouseMotionListener);
	}


	@Override
	public void paint(Graphics aGraphics, JComponent aComponent)
	{
		AbstractButton button = (AbstractButton)aComponent;
		Graphics2D graphics = (Graphics2D)aGraphics;

		int width = button.getWidth();
		int height = button.getHeight();

		if (aComponent.getParent() instanceof RibbonButtonGroup)
		{
			paintGroupButton(aComponent, graphics, width, height);
		}
		else
		{
			AffineTransform transform = graphics.getTransform();

			if (button.getBorder() != null)
			{
				Insets insets = button.getBorder().getBorderInsets(aComponent);
				width -= insets.left + insets.right;
				height -= insets.top + insets.bottom;
				graphics.translate(insets.left, insets.top);
			}

			graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

			if (button.getIcon() != null && button.getIcon().getIconHeight() > 16)
			{
				paintLargeButton(aComponent, graphics, width, height);
			}
			else
			{
				paintNormalButton(aComponent, graphics, width, height);
			}

			graphics.setTransform(transform);
		}
	}


	protected void paintLargeButton(JComponent aComponent, Graphics2D graphics, int width, int height)
	{
		RibbonDropButton button = (RibbonDropButton)aComponent;
		ButtonModel model = button.getModel();
		String text = button.getText();
		Icon icon = button.getIcon();

		String image = null;
		String arrow = "drop_button/IconPullDownHover.png";
		if (button.isCommandButton())
		{
			if (mHoverButton && model.isArmed())
			{
				image = "large_drop_button/button_armed.png";
			}
			else if (mHoverButton && model.isRollover())
			{
				image = "large_drop_button/button_hover.png";
			}
			else if (model.isArmed())
			{
				image = "large_drop_button/drop_armed.png";
			}
			else if (model.isRollover())
			{
				image = "large_drop_button/drop_hover.png";
			}
			else if (button.isSelected())
			{
				image = "large_drop_button/button_selected.png";
			}
			else
			{
				arrow = "drop_button/IconPullDown.png";
			}
		}
		else
		{
			if (model.isArmed())
			{
				image = "large_button/armed.png";
			}
			else if (model.isRollover())
			{
				image = "large_button/hover.png";
			}
			else
			{
				arrow = "drop_button/IconPullDown.png";
			}
		}

		if (image != null)
		{
			RibbonUtils.drawScaledImage(graphics, RibbonUtils.load(image, true), 0, 0, width, height, 5, 5);
		}

		icon.paintIcon(aComponent, graphics, (width-icon.getIconWidth())/2, 4+32-icon.getIconHeight());

		if (text.length() > 0)
		{
			Font font = aComponent.getFont();
			String [] labels = getSplitLabels(aComponent);
			int width0 = (int)font.getStringBounds(labels[0], FRC).getWidth();
			int width1 = (int)font.getStringBounds(labels[1], FRC).getWidth();

			graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
//			graphics.setColor(RibbonUtils.getColor("buttontextcolor"));
			doTextColor(model, graphics, button);
			graphics.drawString(labels[0], (width-width0)/2, 4+32+15);
			graphics.drawString(labels[1], (width-width1)/2, 4+32+15+12);
		}

		BufferedImage pulldown = RibbonUtils.load(arrow, false);
		RibbonUtils.drawScaledImage(graphics, pulldown, (width-pulldown.getWidth())/2, height-pulldown.getHeight()-5, pulldown.getWidth(), pulldown.getHeight(), 5, 5);
	}


	protected void paintNormalButton(JComponent aComponent, Graphics2D graphics, int width, int height)
	{
		RibbonDropButton button = (RibbonDropButton)aComponent;
		ButtonModel model = button.getModel();
		String text = button.getText();
		Icon icon = button.getIcon();

		String image = null;
		if (button.isCommandButton())
		{
			if (mHoverButton && model.isArmed())
			{
				image = "drop_button/button_armed.png";
			}
			else if (mHoverButton && model.isRollover())
			{
				image = "drop_button/button_hover.png";
			}
			else if (model.isArmed())
			{
				image = "drop_button/drop_armed.png";
			}
			else if (model.isRollover())
			{
				image = "drop_button/drop_hover.png";
			}
			if (image != null)
			{
				RibbonUtils.drawScaledImage(graphics, RibbonUtils.load(image, true), 0, 0, width, height, 5, 14);
			}
			else
			{
				BufferedImage pulldown = RibbonUtils.load("drop_button/IconPullDown.png", false);
				graphics.drawImage(pulldown, width-7-pulldown.getWidth()/2, (height-pulldown.getHeight())/2, null);
			}
		}
		else
		{
			if (model.isArmed())
			{
				image = "button/armed.png";
			}
			else if (model.isRollover())
			{
				image = "button/hover.png";
			}
			if (image != null)
			{
				RibbonUtils.drawScaledImage(graphics, RibbonUtils.load(image, true), 0, 0, width, height, 5, 5);
			}

			BufferedImage pulldown = RibbonUtils.load("drop_button/IconPullDown.png", false);
			graphics.drawImage(pulldown, width-7-pulldown.getWidth()/2, (height-pulldown.getHeight())/2, null);
		}

		if (icon != null)
		{
			icon.paintIcon(aComponent, graphics, 3+(16-icon.getIconWidth())/2, 3+(16-icon.getIconHeight())/2);
		}

		if (text.length() > 0)
		{
			doTextColor(model, graphics, button);
//			graphics.setColor(RibbonUtils.getColor("buttontextcolor"));
			graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
			graphics.drawString(text, 3+16+3, height/2+5);
		}
	}


	protected void paintGroupButton(JComponent aComponent, Graphics2D graphics, int width, int height)
	{
		RibbonDropButton button = (RibbonDropButton)aComponent;

		Graphics2D aGraphics = (Graphics2D)graphics;
		String text = ((JButton)aComponent).getText();
		Icon icon = ((JButton)aComponent).getIcon();
		ButtonModel model = button.getModel();

		AffineTransform transform = graphics.getTransform();

		if (aComponent.getBorder() != null)
		{
			Insets insets = aComponent.getBorder().getBorderInsets(aComponent);
			width -= insets.left + insets.right;
			height -= insets.top + insets.bottom;
			graphics.translate(insets.left, insets.top);
		}

		Container group = aComponent.getParent();
		Component [] components = group.getComponents();

		int imageIndex;
		if (components.length == 1)
		{
			imageIndex = 0;
		}
		else if (components[0] == aComponent)
		{
			imageIndex = 1;
		}
		else if (components[components.length-1] == aComponent)
		{
			imageIndex = 3;
		}
		else
		{
			imageIndex = 2;
		}

		String image;
		if (button.isCommandButton())
		{
			String part = mHoverButton ? "button" : "drop";
			if (model.isArmed())
			{
				image = "group_drop_button/cmd_" + part + "_armed_";
			}
			else if (model.isRollover())
			{
				image = "group_drop_button/cmd_" + part + "_hover_";
			}
			else
			{
				image = "group_drop_button/cmd_normal_";
			}

			RibbonUtils.drawScaledImage(graphics, RibbonUtils.load(image+imageIndex+".png", true), 0, 0, width, height, 5, 14);
		}
		else
		{
			String arrow;
			if (model.isArmed())
			{
				image = "group_drop_button/armed_";
				arrow = "drop_button/IconPullDownHover.png";
			}
			else if (model.isRollover())
			{
				image = "group_drop_button/hover_";
				arrow = "drop_button/IconPullDownHover.png";
			}
			else
			{
				image = "group_drop_button/normal_";
				arrow = "drop_button/IconPullDown.png";
			}

			RibbonUtils.drawScaledImage(graphics, RibbonUtils.load(image+imageIndex+".png", true), 0, 0, width, height, 5, 5);

			BufferedImage pulldown = RibbonUtils.load(arrow, false);
			graphics.drawImage(pulldown, width-7-pulldown.getWidth()/2, (height-pulldown.getHeight())/2, null);
		}

		if (icon != null)
		{
			icon.paintIcon(aComponent, aGraphics, 3+(16-icon.getIconWidth())/2, 3+(16-icon.getIconHeight())/2);
		}

		if (text.length() > 0)
		{
			graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
			doTextColor(model, graphics, button);
//			graphics.setColor(RibbonUtils.getColor("groupbuttontextcolor"));
			graphics.drawString(text, 3+16+3, height/2+5);
		}

		graphics.setTransform(transform);
	}


	@Override
	public Dimension getPreferredSize(JComponent aComponent)
	{
		AbstractButton button = (AbstractButton)aComponent;
		Icon icon = button.getIcon();
		String text = button.getText();
		Font font = aComponent.getFont();

		if (aComponent.getParent() instanceof RibbonButtonGroup)
		{
			int labelWidth = (int)aComponent.getFont().getStringBounds(text, FRC).getWidth();

			return new Dimension(3+16+3+labelWidth+11, 22);
		}
		else if (icon != null && icon.getIconHeight() > 16)
		{
			String [] labels = getSplitLabels(aComponent);
			int width0 = (int)font.getStringBounds(labels[0], FRC).getWidth();
			int width1 = (int)font.getStringBounds(labels[1], FRC).getWidth();
			int width = Math.max(Math.max(32, width0), width1);

			return new Dimension(3+width+3, 66);
		}
		else
		{
			int labelWidth = (int)aComponent.getFont().getStringBounds(text, FRC).getWidth();

			return new Dimension(3+16+3+labelWidth+5+9, 22);
		}
	}


	/**
	 * Splits the label into two equal length parts.
	 */
	protected String [] getSplitLabels(JComponent aComponent)
	{
		String text = ((AbstractButton)aComponent).getText();

		int offset = text.indexOf(" ");
		if (offset == -1)
		{
			return new String[]{text,""};
		}

		String text0 = text.substring(0,offset).trim();
		String text1 = text.substring(offset).trim();

		if (text1.indexOf(" ") == -1)
		{
			return new String[]{text0,text1};
		}

		String bestText0 = text0;
		String bestText1 = text1;
		Font font = aComponent.getFont();

		int width0 = (int)font.getStringBounds(text0, FRC).getWidth();
		int width1 = (int)font.getStringBounds(text1, FRC).getWidth();
		int bestDiff = Math.abs(width0-width1);

		while ((offset = text1.indexOf(" ")) != -1)
		{
			text0 += " " + text1.substring(0,offset);
			text1 = text1.substring(offset).trim();

			width0 = (int)font.getStringBounds(text0, FRC).getWidth();
			width1 = (int)font.getStringBounds(text1, FRC).getWidth();
			int diff = Math.abs(width0-width1);

			if (diff > bestDiff)
			{
				break;
			}

			bestDiff = diff;
			bestText0 = text0;
			bestText1 = text1;
		}

		return new String[]{bestText0,bestText1};
	}


	private class MyMouseMotionListener extends MouseMotionAdapter
	{
		@Override
		public void mouseMoved(MouseEvent e)
		{
			RibbonDropButton button = (RibbonDropButton)e.getComponent();
			if (button.getIcon() != null && button.getIcon().getIconHeight() > 16)
			{
				boolean b = e.getY() < e.getComponent().getHeight()-27;
				if (mHoverButton != b)
				{
					mHoverButton = b;
					e.getComponent().repaint();
				}
			}
			else
			{
				boolean b = e.getX() < e.getComponent().getWidth()-11;
				if (mHoverButton != b)
				{
					mHoverButton = b;
					e.getComponent().repaint();
				}
			}
		}
	}


	private void doTextColor(ButtonModel model, Graphics2D graphics, AbstractButton button)
	{
		if (model.isArmed())
		{
			graphics.setColor(RibbonUtils.getColor("buttonarmedtextcolor"));
		}
		else if (model.isSelected())
		{
			graphics.setColor(RibbonUtils.getColor("buttonselectedtextcolor"));
		}
		else if (model.isRollover())
		{
			graphics.setColor(RibbonUtils.getColor("buttonhovertextcolor"));
		}
		else if (!button.isEnabled())
		{
			graphics.setColor(RibbonUtils.getColor("buttondisabledtextcolor"));
		}
		else
		{
			graphics.setColor(RibbonUtils.getColor("buttontextcolor"));
		}
	}
}