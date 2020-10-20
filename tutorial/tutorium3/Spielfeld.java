package tutorium3;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.RenderingHints;

public class Spielfeld extends MiniJava {
	private static class Field extends JPanel {
		private static Field Leiter(int feldnummer, int spielstein) {
			return new Field(feldnummer, spielstein) {
				public void paint(Graphics g) {
					super.paint(g);
					g.setColor(Color.GREEN);

					g.drawLine((int) (getWidth() * 0.75), 3, (int) (getWidth() * 0.75), (int) (getHeight() * 0.78));
					g.drawLine((int) (getWidth() * 0.5), 3, (int) (getWidth() * 0.5), (int) (getHeight() * 0.78));
					for (int i = 1; i < 5; i++) {
						int hoehe = i * getHeight() / 6;
						g.drawLine((int) (getWidth() * 0.5), hoehe, (int) (getWidth() * 0.75), hoehe);
					}

					this.paintSpielstein(g);
				}
			};
		}

		private static Field Schlange(int feldnummer, int spielstein) {
			return new Field(feldnummer, spielstein) {
				public void paint(Graphics g) {
					super.paint(g);
					g.setColor(Color.RED);

					g.fillRoundRect((int) (getWidth() * 0.6), (int) (getHeight() * 0.1), 7, (int) (getHeight() * 0.6),
							13, 13);

					g.setColor(Color.YELLOW);
					g.fillRoundRect((int) (getWidth() * 0.6) + 2, (int) (getHeight() * 0.1 - 5), 2,
							(int) (getHeight() * 0.1), 13, 13);
					g.fillOval((int) (getWidth() * 0.6) + 1, (int) (getHeight() * 0.1 - 11) + 5, 4, 4);

					g.fillOval((int) (getWidth() * 0.6) - 2, (int) (getHeight() * 0.1) + 5, 5, 5);
					g.fillOval((int) (getWidth() * 0.6) + 5, (int) (getHeight() * 0.1) + 5, 5, 5);

					g.setColor(Color.BLACK);
					g.fillOval((int) (getWidth() * 0.6) - 2, (int) (getHeight() * 0.1) + 5, 2, 2);
					g.fillOval((int) (getWidth() * 0.6) + 5, (int) (getHeight() * 0.1) + 5, 2, 2);

					this.paintSpielstein(g);
				}
			};
		}

		int feldnummer, spielstein;
		Point p;

		public Field(int feldnummer, int spielstein) {
			this.feldnummer = feldnummer;
			this.spielstein = spielstein;
			p = getLocation();
		}

		public void paint(Graphics g) {
			super.paint(g);
			// Hintergrund
			g.setColor(Color.BLACK);
			g.fillRect(p.getLocation().x, p.getLocation().y, getWidth() * 2, getHeight());
			// Feld
			Paint pa = ((Graphics2D) g).getPaint();
			g.setColor(Color.WHITE);
			GradientPaint gradient = new GradientPaint(0, 0, Color.WHITE, getWidth(), 0, Color.DARK_GRAY);
			((Graphics2D) g).setPaint(gradient);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g.fillRect(p.getLocation().x, p.getLocation().y, (int) (getWidth() * .8), (int) (getHeight() * .8));
			g.setColor(Color.BLACK);
			g.drawString("" + feldnummer, (int) (getWidth() * .1), (int) (getHeight() * .15));

			// Spielstein

			paintSpielstein(g);
			((Graphics2D) g).setPaint(pa);
		}

		protected void paintSpielstein(Graphics g) {
			int offset = 0;
			Color c = Color.ORANGE;
			if (spielstein == 1)
				c = Color.BLUE;

			GradientPaint gradient = new GradientPaint(0, 0, c, getWidth(), 0, Color.WHITE);
			((Graphics2D) g).setPaint(gradient);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (spielstein != 0) {
				g.fillOval((int) (getWidth() * .05) + offset, (int) (getHeight() * .05),
						(int) (getWidth() * .65) + offset, (int) (getHeight() * .7));
				if (spielstein < 0) {
					offset = 5;
					gradient = new GradientPaint(0, 0, Color.BLUE, getWidth(), 0, Color.WHITE);
					((Graphics2D) g).setPaint(gradient);
					g.fillOval((int) (getWidth() * .05) + offset, (int) (getHeight() * .05),
							(int) (getWidth() * .65) + offset, (int) (getHeight() * .7));
				}
				g.setColor(Color.WHITE);
				g.setFont(g.getFont().deriveFont((float) 32));
				g.drawString("" + Math.abs(spielstein), (int) (getWidth() * .3) + offset, (int) (getHeight() * .5));
			}
		}

	}

	static JFrame myFrame;
	static JPanel pan;

	public static void paintField(int playerone, int playertwo) {
		if (myFrame != null) {
			pan.removeAll();
		} else {
			myFrame = new JFrame("Spielfeld");
			pan = new JPanel();
		}
		java.util.Set<Integer> s1 = new java.util.HashSet<Integer>(), s2 = new java.util.HashSet<Integer>(),
				s3 = new java.util.HashSet<Integer>(), s4 = new java.util.HashSet<Integer>();
		pan.setLayout(new GridLayout(4, 9));
		for (int x = 0; x < 35; x++) {
			int figur = 0;
			if (x == playerone && x == playertwo)
				figur = -1;
			else if (x == playerone)
				figur = 1;
			else if (x == playertwo)
				figur = 2;

			if (x % 5 == 0 && x != 0)
				pan.add(Field.Leiter(x, figur));
			else if (x % 7 == 0 && x != 0)
				pan.add(Field.Schlange(x, figur));
			else
				pan.add(new Field(x, figur));
		}
		pan.add(new Field(35, 0));
		myFrame.add(pan);
		myFrame.setSize(900, 400);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setVisible(true);
	}

	public static void main(String[] args) {
		paintField(0, 0);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ie) {
		}
		;
		paintField(0, 10);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ie) {
		}
		;
		paintField(5, 10);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ie) {
		}
		;
		paintField(5, 7);
	}
}
