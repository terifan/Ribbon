package org.terifan.ui.ribbon.samples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.terifan.io.Streams;
import org.terifan.ui.Utilities;
import org.terifan.ui.ribbon.Ribbon;
import org.terifan.ui.ribbon.RibbonBox;
import org.terifan.ui.ribbon.RibbonButton;
import org.terifan.ui.ribbon.RibbonButtonGroup;
import org.terifan.ui.ribbon.RibbonCheckBox;
import org.terifan.ui.ribbon.RibbonComboBox;
import org.terifan.ui.ribbon.RibbonDropButton;
import org.terifan.ui.ribbon.RibbonGroup;
import org.terifan.ui.ribbon.RibbonLabel;
import org.terifan.ui.ribbon.RibbonTab;
import org.terifan.ui.ribbon.RibbonToggleButton;
import org.terifan.ui.ribbon.RibbonUtils;
import org.terifan.util.log.Log;


public class Sample
{
	private static HashMap<String,BufferedImage> mImages = new HashMap<>();
	private final static String colorScheme = "gray";


	public Sample() throws IOException
	{
		Utilities.setSystemLookAndFeel();

		RibbonBox ribbonBox;
		RibbonButtonGroup ribbonButtonGroup;
		RibbonGroup ribbonGroup;

		RibbonTab messageTab = new RibbonTab();

		ribbonGroup = new RibbonGroup("Clipboard");
		messageTab.add(ribbonGroup);

		ribbonGroup.add(new RibbonButton("Paste", getIcon("paste")));
		ribbonGroup.add(new RibbonDropButton("Paste", getIcon("paste")));
		ribbonGroup.add(new RibbonDropButton("Paste", getIcon("paste"), true));

		RibbonBox box = new RibbonBox();
		ribbonGroup.add(box);

		RibbonButtonGroup grp = new RibbonButtonGroup();
		grp.add(new RibbonDropButton(getIcon("paste16")));
		grp.add(new RibbonDropButton(getIcon("paste16")));
		grp.add(new RibbonDropButton(getIcon("paste16")));
		box.add(grp);

		grp = new RibbonButtonGroup();
		grp.add(new RibbonDropButton(getIcon("paste16")));
		box.add(grp);

		box.add(new RibbonDropButton("Paste", getIcon("paste16")));

		box = new RibbonBox();
		ribbonGroup.add(box);

		grp = new RibbonButtonGroup();
		grp.add(new RibbonDropButton(getIcon("paste16"), true));
		grp.add(new RibbonDropButton(getIcon("paste16"), true));
		grp.add(new RibbonDropButton(getIcon("paste16"), true));
		box.add(grp);

		grp = new RibbonButtonGroup();
		grp.add(new RibbonDropButton(getIcon("paste16"), true));
		box.add(grp);

		box.add(new RibbonDropButton("Paste", getIcon("paste16"), true));

		ribbonGroup.addSeparator();
		ribbonGroup.add(new RibbonButton("Format Painter", getIcon("formatpainter")));
		RibbonButton btn = new RibbonButton("Format Painter", getIcon("formatpainter"));
		btn.setEnabled(false);
		ribbonGroup.add(btn);

		//ribbonGroup.add(new JScrollPane(new JList(new String[]{"Arial", "Calibri", "Helvetica", "Times new roman", "Verdana"}), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));

		ribbonGroup = new RibbonGroup("Basic Text");
		messageTab.add(ribbonGroup);

		ribbonBox = new RibbonBox();
		ribbonGroup.add(ribbonBox);

		RibbonComboBox fonts = new RibbonComboBox(new Object[]{"Arial", "Calibri", "Helvetica", "Times new roman", "Verdana"});
		fonts.setEditable(true);

		ribbonBox.add(new RibbonLabel("Font:"));
		ribbonBox.add(fonts);
		ribbonBox.add(new RibbonLabel("Size:"));
		ribbonBox.add(new RibbonComboBox(new Object[]{8, 9, 10, 11, 12, 14, 18, 24}));

		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("fontincr")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("fontdecr")));
		ribbonBox.add(ribbonButtonGroup);

		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("linespacing")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("numbering")));
		ribbonBox.add(ribbonButtonGroup);

		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("copystyle")));
		ribbonBox.add(ribbonButtonGroup);

		ribbonBox = new RibbonBox();
		ribbonGroup.add(ribbonBox);

		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonToggleButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonToggleButton(getIcon("italic")));
		ribbonButtonGroup.add(new RibbonToggleButton(getIcon("underlined")));
		ribbonBox.add(ribbonButtonGroup);

		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("highlight")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("color")));
		ribbonBox.add(ribbonButtonGroup);

		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonToggleButton(getIcon("alignleft")));
		ribbonButtonGroup.add(new RibbonToggleButton(getIcon("aligncenter")));
		ribbonButtonGroup.add(new RibbonToggleButton(getIcon("alignright")));
		ribbonBox.add(ribbonButtonGroup);

		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("indentincr")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("indentdecr")));
		ribbonBox.add(ribbonButtonGroup);


		ribbonGroup = new RibbonGroup("a");
		messageTab.add(ribbonGroup);
		ribbonGroup.add(new RibbonButton("Bold", getIcon("bold")));
		ribbonGroup.add(new RibbonButton("Paste", getIcon("paste")));


		ribbonGroup = new RibbonGroup("b");
		messageTab.add(ribbonGroup);
		ribbonGroup.add(new RibbonButton("Bold", getIcon("bold")));
		ribbonGroup.add(new RibbonButton("Bold", getIcon("bold")));
		ribbonGroup.add(new RibbonButton("Paste", getIcon("paste")));


		ribbonGroup = new RibbonGroup("c");
		messageTab.add(ribbonGroup);
		ribbonGroup.add(new RibbonButton("Bold", getIcon("bold")));
		ribbonGroup.add(new RibbonButton("Bold", getIcon("bold")));
		ribbonGroup.add(new RibbonButton("Bold", getIcon("bold")));
		ribbonGroup.add(new RibbonButton("Paste", getIcon("paste")));


		ribbonGroup = new RibbonGroup("d");
		messageTab.add(ribbonGroup);
		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("indentincr")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("indentdecr")));
		ribbonGroup.add(ribbonButtonGroup);


		ribbonGroup = new RibbonGroup("e");
		messageTab.add(ribbonGroup);
		ribbonGroup.add(new RibbonButton("Paste", getIcon("paste")));


		ribbonGroup = new RibbonGroup("f");
		messageTab.add(ribbonGroup);
		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonGroup.add(ribbonButtonGroup);


		ribbonGroup = new RibbonGroup("g");
		messageTab.add(ribbonGroup);
		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonGroup.add(ribbonButtonGroup);


		ribbonGroup = new RibbonGroup("h");
		messageTab.add(ribbonGroup);
		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonGroup.add(ribbonButtonGroup);
		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonGroup.add(ribbonButtonGroup);


		ribbonGroup = new RibbonGroup("i");
		messageTab.add(ribbonGroup);
		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonGroup.add(ribbonButtonGroup);
		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonGroup.add(ribbonButtonGroup);
		ribbonButtonGroup = new RibbonButtonGroup();
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonButtonGroup.add(new RibbonButton(getIcon("bold")));
		ribbonGroup.add(ribbonButtonGroup);


		ribbonGroup = new RibbonGroup("j");
		ribbonGroup.add(new RibbonCheckBox("Ruler"));
		ribbonGroup.add(new RibbonCheckBox("Gridlines"));
		ribbonGroup.add(new RibbonCheckBox("Message Bar"));
		ribbonGroup.add(new RibbonCheckBox("Document Map"));
		ribbonGroup.add(new RibbonCheckBox("Thumbnails"));
		ribbonGroup.add(new RibbonLabel(""));
		messageTab.add(ribbonGroup);


		ribbonGroup = new RibbonGroup("k");
		messageTab.add(ribbonGroup);
		ribbonGroup.add(new RibbonToggleButton("Toggle1", getIcon("paste")));
		ribbonGroup.add(new RibbonToggleButton("Toggle2", getIcon("paste")));
		ribbonGroup.add(new RibbonToggleButton("Toggle3", getIcon("paste")));



		RibbonTab insertTab = new RibbonTab();

		ribbonGroup = new RibbonGroup("Clipboard");
		insertTab.add(ribbonGroup);

		ribbonGroup.add(new RibbonButton("Paste", getIcon("paste")));
		ribbonGroup.add(new RibbonDropButton("Paste", getIcon("paste")));
		ribbonGroup.add(new RibbonDropButton("Paste", getIcon("paste"), true));

		box = new RibbonBox();
		ribbonGroup.add(box);

		grp = new RibbonButtonGroup();
		grp.add(new RibbonDropButton(getIcon("paste16")));
		grp.add(new RibbonDropButton(getIcon("paste16")));
		grp.add(new RibbonDropButton(getIcon("paste16")));
		box.add(grp);

		grp = new RibbonButtonGroup();
		grp.add(new RibbonDropButton(getIcon("paste16")));
		box.add(grp);

		box.add(new RibbonDropButton("Paste", getIcon("paste16")));

		box = new RibbonBox();
		ribbonGroup.add(box);

		grp = new RibbonButtonGroup();
		grp.add(new RibbonDropButton(getIcon("paste16"), true));
		grp.add(new RibbonDropButton(getIcon("paste16"), true));
		grp.add(new RibbonDropButton(getIcon("paste16"), true));
		box.add(grp);

		grp = new RibbonButtonGroup();
		grp.add(new RibbonDropButton(getIcon("paste16"), true));
		box.add(grp);

		box.add(new RibbonDropButton("Paste", getIcon("paste16"), true));

		ribbonGroup.addSeparator();
		ribbonGroup.add(new RibbonButton("Format Painter", getIcon("formatpainter")));
		btn = new RibbonButton("Format Painter", getIcon("formatpainter"));
		btn.setEnabled(false);
		ribbonGroup.add(btn);


		Ribbon ribbon = new Ribbon();
		ribbon.add("Message", messageTab);
		ribbon.add("Insert", insertTab);
		ribbon.add("Options", new RibbonLabel("options"));
		ribbon.add("Format Text", new RibbonLabel("format text"));

		RibbonUtils.setColorScheme(colorScheme);

		JFrame frame = new JFrame();
		frame.add(ribbon, BorderLayout.NORTH);
		frame.add(mWorkarea, BorderLayout.CENTER);
		frame.add(mStatusBar, BorderLayout.SOUTH);
		frame.setSize(1600,1000);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private Icon getIcon(String aName)
	{
		return new ImageIcon(getImage(aName + ".png"));
	}


	private BufferedImage getImage(String aName)
	{
		BufferedImage image = mImages.get(aName);
		if (image == null)
		{
			try
			{
				URL url = Sample.class.getResource(aName);
				if (url == null)
				{
					throw new IOException("Image not found: " + aName);
				}
				image = ImageIO.read(url);
				mImages.put(aName, image);
			}
			catch (Exception e)
			{
				e.printStackTrace(Log.out);
			}
		}
		return image;
	}


	private JPanel mWorkarea = new JPanel()
	{
		GradientPaint mPaint0;
		GradientPaint mPaint1;
		GradientPaint mPaint2;

		@Override
		public void paintComponent(Graphics aGraphics)
		{
			BufferedImage image = getImage("arch_"+colorScheme+".png");
			Graphics2D graphics = (Graphics2D)aGraphics;

			int y0 = 0;
			int y1 = image.getHeight();
			int y2 = Math.max(y1+30, getHeight()*8/10);
			int y3 = Math.max(y2+10, getHeight());

			switch (colorScheme)
			{
				case "black":
				case "gray":
					mPaint0 = new GradientPaint(0f, y0, new Color(78,78,78), 0f, y1, new Color(70,70,70));
					mPaint1 = new GradientPaint(0f, y1, new Color(70,70,70), 0f, y2, new Color(56,56,56));
					mPaint2 = new GradientPaint(0f, y2, new Color(56,56,56), 0f, y3, new Color(0,0,0));
					break;
				case "blue":
					mPaint0 = new GradientPaint(0f, y0, new Color(163,194,234), 0f, y1, new Color(135,169,213));
					mPaint1 = new GradientPaint(0f, y1, new Color(135,169,213), 0f, y2, new Color(86,125,176));
					mPaint2 = new GradientPaint(0f, y2, new Color(86,125,176), 0f, y3, new Color(101,145,205));
					break;
				default:
					mPaint0 = new GradientPaint(0f, y0, new Color(204,207,216), 0f, y1, new Color(197,200,209));
					mPaint1 = new GradientPaint(0f, y1, new Color(197,200,209), 0f, y2, new Color(187,190,198));
					mPaint2 = new GradientPaint(0f, y2, new Color(187,190,198), 0f, y3, new Color(155,159,166));
					break;
			}

			graphics.setPaint(mPaint0);
			graphics.fillRect(0, y0, getWidth(), y1-y0);
			graphics.setPaint(mPaint1);
			graphics.fillRect(0, y1, getWidth(), y2-y1);
			graphics.setPaint(mPaint2);
			graphics.fillRect(0, y2, getWidth(), y3-y2);
			graphics.drawImage(image, 0, 0, null);
		}
	};

	private JPanel mStatusBar = new JPanel()
	{
		@Override
		public void paintComponent(Graphics g)
		{
			BufferedImage image = getImage("statusbar_"+colorScheme+".png");
			for (int x = 0; x < getWidth(); x+=image.getWidth())
			{
				g.drawImage(image, x, 0, null);
			}
		}
		@Override
		public Dimension getPreferredSize()
		{
			BufferedImage image = getImage("statusbar_"+colorScheme+".png");
			Dimension d = super.getPreferredSize();
			d.height = image.getHeight();
			return d;
		}
	};


	public static void main(String ... args)
	{
		try
		{
			new Sample();
		}
		catch (Exception e)
		{
			e.printStackTrace(Log.out);
		}
	}
}