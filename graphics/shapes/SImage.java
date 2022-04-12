package graphics.shapes;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import graphics.shapes.ui.ShapeVisitor;

public class SImage extends Shape {
	private Rectangle rect;
	private ImageIcon image;
	private String path;
	
	public SImage(String path,Point point) throws IOException {
		this.path=path;
		Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
		BufferedImage bimg = ImageIO.read(new File(path));
		int width = bimg.getWidth();
		int height = bimg.getHeight();
		this.rect = new Rectangle(point.x,point.y,bimg.getWidth(),bimg.getHeight());
	}
	
	@Override
	public Point getLoc() {
		return this.rect.getLocation();
	}

	@Override
	public void setLoc(Point p) {
		this.rect.setLocation(p);
	}

	@Override
	public void translate(int x, int y) {
		this.rect.translate(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return this.rect.getBounds();
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitImage(this);
	}
	
	public String getPath() {
		return this.path;
	}

}
