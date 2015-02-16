package org.terifan.ui.ribbon.plaf;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicSeparatorUI;
import org.terifan.ui.ribbon.RibbonUtils;


public class RibbonSeparatorUI extends BasicSeparatorUI
{
	private int mPadBefore;
	private int mPadAfter;
	private boolean mDrawBevel;


	public RibbonSeparatorUI(int aPadBefore, int aPadAfter, boolean aDrawBevel)
	{
		mPadBefore = aPadBefore;
		mPadAfter = aPadAfter;
		mDrawBevel = aDrawBevel;
	}


	@Override
    public void paint(Graphics g, JComponent c)
    {
		if (mDrawBevel)
		{
			Dimension d = c.getSize();
			BufferedImage image = RibbonUtils.load("separator/normal.png");
			g.drawImage(image, (d.width-image.getWidth())/2, (d.height-image.getHeight())/2, null);
		}
    }


	@Override
	public Dimension getPreferredSize(JComponent c)
	{
		return new Dimension(mPadBefore+2+mPadAfter,66);
	}
}
