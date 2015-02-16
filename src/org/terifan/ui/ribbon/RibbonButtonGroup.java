package org.terifan.ui.ribbon;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class RibbonButtonGroup extends JPanel
{
	public RibbonButtonGroup()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(0,1,0,1));
	}


	@Override
	public Component add(Component aComponent)
	{
		super.add(aComponent);

		return aComponent;
	}


	@Override
	protected void paintComponent(Graphics aGraphics)
	{
		super.paintComponent(aGraphics);

		BufferedImage image = RibbonUtils.load("button_group/borders.png", true);
		aGraphics.drawImage(image, 0, 0, 1, getHeight(), 0, 0, 1, image.getHeight(), null);
		aGraphics.drawImage(image, getWidth()-1, 0, getWidth(), getHeight(), image.getWidth()-1, 0, image.getWidth(), image.getHeight(), null);
	}
}