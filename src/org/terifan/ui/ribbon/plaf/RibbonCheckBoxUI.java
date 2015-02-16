package org.terifan.ui.ribbon.plaf;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicButtonUI;
import org.terifan.ui.ribbon.RibbonUtils;


public class RibbonCheckBoxUI extends BasicButtonUI
{
	private final static FontRenderContext FRC = new FontRenderContext(null,true,false);


	@Override
	protected void installDefaults(AbstractButton b)
	{
		LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);
		b.setFont(RibbonUtils.getFont("buttonfont"));
		b.setRolloverEnabled(true);
	}


	@Override
	public void paint(Graphics aGraphics, JComponent aComponent)
	{
		AbstractButton button = (AbstractButton)aComponent;
		Graphics2D graphics = (Graphics2D)aGraphics;

		int width = button.getWidth();
		int height = button.getHeight();

		AffineTransform transform = graphics.getTransform();

		if (button.getBorder() != null)
		{
			Insets insets = button.getBorder().getBorderInsets(aComponent);
			width -= insets.left + insets.right;
			height -= insets.top + insets.bottom;
			graphics.translate(insets.left, insets.top);
		}

		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

		paintCheckboxButton(aComponent, graphics, width, height);

		graphics.setTransform(transform);
	}


	protected void paintCheckboxButton(JComponent aComponent, Graphics2D graphics, int width, int height)
	{
		AbstractButton button = (AbstractButton)aComponent;
		ButtonModel model = button.getModel();
		String text = button.getText();

		BufferedImage image;

		if (model.isArmed())
		{
			image = RibbonUtils.load("checkbox/armed.png");
		}
		else if (model.isRollover() && model.isSelected())
		{
			image = RibbonUtils.load("checkbox/selected_hover.png");
		}
		else if (model.isPressed() || model.isSelected())
		{
			image = RibbonUtils.load("checkbox/selected.png");
		}
		else if (model.isRollover())
		{
			image = RibbonUtils.load("checkbox/hover.png");
		}
		else if (!model.isEnabled())
		{
			image = RibbonUtils.load("checkbox/disabled.png");
		}
		else
		{
			image = RibbonUtils.load("checkbox/normal.png");
		}

		graphics.drawImage(image, 5, 5, null);

		if (text.length() > 0)
		{
			graphics.setColor(RibbonUtils.getColor("buttontextcolor"));
			graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
			graphics.drawString(text, 3+16+3, height/2+5);
		}
	}


	@Override
	public Dimension getPreferredSize(JComponent aComponent)
	{
		AbstractButton button = (AbstractButton)aComponent;
		String text = button.getText();

		int labelWidth = (int)aComponent.getFont().getStringBounds(text, FRC).getWidth();

		return new Dimension(3+16+3+labelWidth+5, 22);
	}
}