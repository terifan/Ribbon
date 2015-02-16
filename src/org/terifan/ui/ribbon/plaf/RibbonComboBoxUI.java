package org.terifan.ui.ribbon.plaf;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.LookAndFeel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import org.terifan.ui.ribbon.RibbonUtils;


public class RibbonComboBoxUI extends BasicComboBoxUI
{
	private boolean mHoverDrop;
	private boolean mHoverEditor;
	private MouseListener mMouseListener;
	private PropertyChangeListener mPropertyChangeListener;
	private JTextField mEditorTextField;


	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);

		mMouseListener = new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent aEvent)
			{
				mHoverEditor = true;
				comboBox.repaint();
			}


			@Override
			public void mouseExited(MouseEvent aEvent)
			{
				mHoverEditor = false;
				comboBox.repaint();
			}
		};

		mPropertyChangeListener = new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent aEvent)
			{
				comboBox.repaint();
			}
		};

		c.addMouseListener(mMouseListener);
		((BasicComboPopup) popup).addPropertyChangeListener(mPropertyChangeListener);
	}


	@Override
	protected void installDefaults()
	{
		comboBox.setForeground(RibbonUtils.getColor("comboboxtextcolor"));
		comboBox.setFont(RibbonUtils.getFont("comboboxfont"));
		LookAndFeel.installProperty(comboBox, "opaque", Boolean.TRUE);
	}


	@Override
	public void uninstallUI(JComponent c)
	{
		c.removeMouseListener(mMouseListener);
		((BasicComboPopup) popup).removePropertyChangeListener(mPropertyChangeListener);
	}


	@Override
	protected ListCellRenderer createRenderer()
	{
		return new Renderer();
	}


	@Override
	protected JButton createArrowButton()
	{
		JButton button = new JButton("", RibbonUtils.loadIcon("combobox/drop_icon.png", true))
		{
			@Override
			public void paintComponent(Graphics aGraphics)
			{
				BufferedImage image;

				if (isPopupVisible(comboBox))
				{
					image = RibbonUtils.load("combobox/armed.png");
				}
				else if (mEditorTextField != null && mEditorTextField.hasFocus())
				{
					image = RibbonUtils.load("combobox/focused.png");
				}
				else if (model.isRollover())
				{
					image = RibbonUtils.load("combobox/hover_drop.png");

					if (!mHoverDrop)
					{
						mHoverDrop = true;
						comboBox.repaint();
					}
				}
				else if (mHoverEditor)
				{
					image = RibbonUtils.load("combobox/hover.png");
				}
				else
				{
					image = RibbonUtils.load("combobox/normal.png");
				}

				if (mHoverDrop && !model.isRollover())
				{
					mHoverDrop = false;
					comboBox.repaint();
				}

				aGraphics.drawImage(image, 0, 0, getWidth(), getHeight(), image.getWidth() - getWidth(), 0, image.getWidth(), image.getHeight(), null);
			}
		};
		button.setBorder(null);
		button.setName("ComboBox.arrowButton");
		button.setRolloverEnabled(true);
		return button;
	}


	@Override
	protected ComboBoxEditor createEditor()
	{
		return new BasicComboBoxEditor();
	}


	@Override
	public Dimension getPreferredSize(JComponent c)
	{
		Dimension d = super.getPreferredSize(c);
		d.height = 22;
		return d;
	}


	@Override
	public Dimension getMinimumSize(JComponent c)
	{
		Dimension d = super.getMinimumSize(c);
		d.height = 22;
		return d;
	}


	@Override
	public void setPopupVisible(JComboBox c, boolean v)
	{
		super.setPopupVisible(c, v);
		comboBox.repaint();
	}


	@Override
	public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus)
	{
		ListCellRenderer renderer = comboBox.getRenderer();
		Component c;

		if (hasFocus && !isPopupVisible(comboBox))
		{
			c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, true, false);
		}
		else
		{
			c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
		}

		if (renderer instanceof Renderer)
		{
			((Renderer) renderer).mSelected = false;
		}

		c.setForeground(RibbonUtils.getColor("comboboxtextcolor"));

		boolean shouldValidate = false;
		if (c instanceof JPanel)
		{
			shouldValidate = true;
		}
		if (c instanceof JComponent)
		{
			((JComponent) c).setOpaque(false);
		}

		BufferedImage image;

		if (isPopupVisible(comboBox))
		{
			image = RibbonUtils.load("combobox/armed.png");
		}
		else if (mEditorTextField != null && mEditorTextField.hasFocus())
		{
			image = RibbonUtils.load("combobox/focused.png");
		}
		else if (mHoverDrop)
		{
			image = RibbonUtils.load("combobox/hover_drop.png");
		}
		else if (mHoverEditor)
		{
			image = RibbonUtils.load("combobox/hover.png");
		}
		else
		{
			image = RibbonUtils.load("combobox/normal.png");
		}

		g.drawImage(image, 0, 0, 5, bounds.height, 0, 0, 5, image.getHeight(), null);
		g.drawImage(image, 5, 0, bounds.width, bounds.height, 5, 0, image.getWidth() - 14, image.getHeight(), null);

		currentValuePane.paintComponent(g, c, comboBox, bounds.x, bounds.y, bounds.width, bounds.height, shouldValidate);
	}


	private class Renderer extends JLabel implements ListCellRenderer
	{
		protected boolean mSelected;


		public Renderer()
		{
			super();
			setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		}


		@Override
		public Dimension getPreferredSize()
		{
			Dimension size;

			if ((this.getText() == null) || (this.getText().equals("")))
			{
				setText(" ");
				size = super.getPreferredSize();
				setText("");
			}
			else
			{
				size = super.getPreferredSize();
			}

			// Fixed bugs in size calculation...
			size.width += 5;

			return size;
		}


		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			mSelected = isSelected;

			setOpaque(!isSelected);
			setBackground(RibbonUtils.getColor("comboboxbackgroundcolor"));
//			setForeground(Color.BLACK);

			if (value instanceof Icon)
			{
				setIcon((Icon) value);
			}
			else
			{
				setText((value == null) ? "" : value.toString());
			}
			return this;
		}


		@Override
		protected void paintComponent(Graphics g)
		{
			if (mSelected)
			{
				BufferedImage image = RibbonUtils.load("combobox/selected_item.png", true);
				RibbonUtils.drawScaledImage(g, image, 0, 0, getWidth(), getHeight(), 5, 5);
			}

			super.paintComponent(g);
		}
	}


	class BasicComboBoxEditor implements ComboBoxEditor, FocusListener
	{
		protected JTextField comboBoxEditor;
		private Object oldValue;


		public BasicComboBoxEditor()
		{
			comboBoxEditor = new BorderlessTextField("", 9);
			comboBoxEditor.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));

			mEditorTextField = comboBoxEditor;

			comboBoxEditor.addFocusListener(this);
		}


		@Override
		public Component getEditorComponent()
		{
			return comboBoxEditor;
		}


		@Override
		public void focusGained(FocusEvent aEvent)
		{
			comboBox.repaint();
		}


		@Override
		public void focusLost(FocusEvent aEvent)
		{
			comboBox.repaint();
		}


		@Override
		public void setItem(Object anObject)
		{
			if (anObject != null)
			{
				comboBoxEditor.setText(anObject.toString());

				oldValue = anObject;
			}
			else
			{
				comboBoxEditor.setText("");
			}
		}


		@Override
		public Object getItem()
		{
			Object newValue = comboBoxEditor.getText();

			if (oldValue != null && !(oldValue instanceof String))
			{
				if (newValue.equals(oldValue.toString()))
				{
					return oldValue;
				}
				else
				{
					Class cls = oldValue.getClass();
					try
					{
						java.lang.reflect.Method method = cls.getMethod("valueOf", new Class[]
								{
									String.class
								});
						newValue = method.invoke(oldValue, new Object[]
								{
									comboBoxEditor.getText()
								});
					}
					catch (Exception ex)
					{
					}
				}
			}
			return newValue;
		}


		@Override
		public void selectAll()
		{
			comboBoxEditor.selectAll();
			comboBoxEditor.requestFocus();
		}


		@Override
		public void addActionListener(ActionListener l)
		{
			comboBoxEditor.addActionListener(l);
		}


		@Override
		public void removeActionListener(ActionListener l)
		{
			comboBoxEditor.removeActionListener(l);
		}


		class BorderlessTextField extends JTextField
		{
			public BorderlessTextField(String value, int n)
			{
				super(value, n);
				setOpaque(false);
				addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseEntered(MouseEvent aEvent)
					{
						mHoverEditor = true;
						comboBox.repaint();
					}


					@Override
					public void mouseExited(MouseEvent aEvent)
					{
						mHoverEditor = false;
						comboBox.repaint();
					}
				});
			}


			@Override
			public void setText(String s)
			{
				if (getText().equals(s))
				{
					return;
				}
				super.setText(s);
			}


			@Override
			public void setBorder(Border b)
			{
				if (!(b instanceof UIResource))
				{
					super.setBorder(b);
				}
			}


			@Override
			protected void paintComponent(Graphics g)
			{
				BufferedImage image;

				if (isPopupVisible(comboBox))
				{
					image = RibbonUtils.load("combobox/armed.png");
				}
				else if (comboBoxEditor.hasFocus())
				{
					image = RibbonUtils.load("combobox/focused.png");
				}
				else if (mHoverDrop || comboBoxEditor.hasFocus())
				{
					image = RibbonUtils.load("combobox/hover_drop.png");
				}
				else if (mHoverEditor)
				{
					image = RibbonUtils.load("combobox/hover.png");
				}
				else
				{
					image = RibbonUtils.load("combobox/normal.png");
				}

				g.drawImage(image, 0, 0, 5, getHeight(), 0, 0, 5, image.getHeight(), null);
				g.drawImage(image, 5, 0, getWidth(), getHeight(), 5, 0, image.getWidth() - 14, image.getHeight(), null);

				super.paintComponent(g);
			}
		}
	}
}
