package org.terifan.ui.ribbon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import org.terifan.util.log.Log;


public class RibbonUtils
{
	private final static HashMap<String, BufferedImage> mCache = new HashMap<>();
	private final static HashMap<String, Icon> mCacheIcon = new HashMap<>();
	private final static HashMap<String, HashMap<String, Color>> mColorSchemes = new HashMap<>();
	private final static Font FONT = new Font("segoe ui", Font.PLAIN, 11);
	private static HashMap<String, Color> mColors = new HashMap<>();
	private static String mColorScheme;


	static
	{
		HashMap<String, Color> colorScheme = new HashMap<>();
		colorScheme.put("buttontextcolor", new Color(21, 66, 139));
		colorScheme.put("buttonarmedtextcolor", new Color(21, 66, 139));
		colorScheme.put("buttonhovertextcolor", new Color(21, 66, 139));
		colorScheme.put("buttondisabledtextcolor", new Color(140, 140, 140));
		colorScheme.put("comboboxbackgroundcolor", Color.WHITE);
		colorScheme.put("comboboxtextcolor", Color.BLACK);
		colorScheme.put("groupbuttontextcolor", new Color(21, 66, 139));
		colorScheme.put("grouptextcolor", new Color(62, 106, 170));
		colorScheme.put("labelcolor", new Color(21, 66, 139));
		colorScheme.put("tabselectedtextcolor", new Color(21, 66, 139));
		colorScheme.put("tabtextcolor", new Color(21, 66, 139));
		mColorSchemes.put("blue", colorScheme);

		colorScheme = new HashMap<>();
		colorScheme.put("buttontextcolor", new Color(200, 200, 200));
		colorScheme.put("buttonhovertextcolor", new Color(200, 200, 200));
		colorScheme.put("buttonarmedtextcolor", new Color(200, 200, 200));
		colorScheme.put("buttondisabledtextcolor", new Color(140, 140, 140));
		colorScheme.put("comboboxbackgroundcolor", Color.WHITE);
		colorScheme.put("comboboxtextcolor", Color.BLACK);
		colorScheme.put("groupbuttontextcolor", new Color(200, 200, 200));
		colorScheme.put("grouptextcolor", Color.WHITE);
		colorScheme.put("labelcolor", new Color(200, 200, 200));
		colorScheme.put("tabselectedtextcolor", new Color(220, 220, 220));
		colorScheme.put("tabtextcolor", new Color(200, 200, 200));
		mColorSchemes.put("black", colorScheme);

		colorScheme = new HashMap<>();
		colorScheme.put("buttontextcolor", new Color(200, 200, 200));
		colorScheme.put("buttonhovertextcolor", new Color(64, 64, 64));
		colorScheme.put("buttonarmedtextcolor", new Color(0, 0, 0));
		colorScheme.put("buttonselectedtextcolor", new Color(0, 0, 0));
		colorScheme.put("buttondisabledtextcolor", new Color(140, 140, 140));
		colorScheme.put("comboboxbackgroundcolor", Color.WHITE);
		colorScheme.put("comboboxtextcolor", Color.BLACK);
		colorScheme.put("groupbuttontextcolor", new Color(200, 200, 200));
		colorScheme.put("grouptextcolor", Color.WHITE);
		colorScheme.put("labelcolor", new Color(200, 200, 200));
		colorScheme.put("tabselectedtextcolor", new Color(220, 220, 220));
		colorScheme.put("tabtextcolor", new Color(200, 200, 200));
		mColorSchemes.put("gray", colorScheme);

		colorScheme = new HashMap<>();
		colorScheme.put("buttontextcolor", new Color(76, 83, 92));
		colorScheme.put("buttonarmedtextcolor", new Color(76, 83, 92));
		colorScheme.put("buttonhovertextcolor", new Color(76, 83, 92));
		colorScheme.put("buttondisabledtextcolor", new Color(140, 140, 140));
		colorScheme.put("comboboxbackgroundcolor", Color.WHITE);
		colorScheme.put("comboboxtextcolor", Color.BLACK);
		colorScheme.put("groupbuttontextcolor", new Color(70, 70, 70));
		colorScheme.put("grouptextcolor", new Color(83, 84, 89));
		colorScheme.put("labelcolor", new Color(70, 70, 70));
		colorScheme.put("tabselectedtextcolor", new Color(76, 83, 92));
		colorScheme.put("tabtextcolor", new Color(76, 83, 92));
		mColorSchemes.put("silver", colorScheme);

		setColorScheme("blue");
	}


	private RibbonUtils()
	{
	}


	public static void setColorScheme(String aColorScheme)
	{
		if (!mColorSchemes.containsKey(aColorScheme))
		{
			throw new IllegalArgumentException("No color scheme exists by this name: " + aColorScheme);
		}

		mColorScheme = aColorScheme;
		mColors = mColorSchemes.get(aColorScheme);
	}


	public static Color getColor(String aKey)
	{
		return mColors.get(aKey);
	}


	public static Font getFont(String aKey)
	{
		return FONT;
	}


	public static BufferedImage load(String aPath)
	{
		return load(aPath, false);
	}


	public static BufferedImage load(String aPath, boolean aAlpha)
	{
		BufferedImage image = mCache.get(aPath);

		if (image == null)
		{
			try
			{
				URL url = Ribbon.class.getResource("resources/" + aPath.replace(".png","") + "__" + mColorScheme + ".png");

				if (url == null)
				{
					url = Ribbon.class.getResource("resources/" + aPath.replace(".png","") + "__default.png");
				}

				if (url == null)
				{
					throw new IllegalArgumentException("Resource not found: color: " + mColorScheme+", path: " + aPath);
				}

				image = ImageIO.read(url);
			}
			catch (IOException e)
			{
				throw new IllegalStateException(e);
			}

			mCache.put(aPath, image);
		}

		return image;
	}


	public static Icon loadIcon(String aPath)
	{
		return loadIcon(aPath, false);
	}


	public static Icon loadIcon(final String aPath, final boolean aAlpha)
	{
		Icon icon = mCacheIcon.get(aPath);

		if (icon != null)
		{
			return icon;
		}

		icon = new Icon()
		{
			BufferedImage image = load(aPath, aAlpha);


			@Override
			public void paintIcon(Component c, Graphics g, int x, int y)
			{
				g.drawImage(image, x, y, null);
			}


			@Override
			public int getIconWidth()
			{
				return image.getWidth();
			}


			@Override
			public int getIconHeight()
			{
				return image.getHeight();
			}
		};

		mCacheIcon.put(aPath, icon);

		return icon;
	}


	public static void drawScaledImage(Graphics graphics, BufferedImage image, int x, int y, int w, int h, int left, int right)
	{
		int tw = image.getWidth();
		int th = image.getHeight();

		graphics.drawImage(image, x, y, x + left, y + h, 0, 0, left, th, null);
		graphics.drawImage(image, x + left, y, x + w - right, y + h, left, 0, tw - right, th, null);
		graphics.drawImage(image, x + w - right, y, x + w, y + h, tw - right, 0, tw, th, null);
	}
}
