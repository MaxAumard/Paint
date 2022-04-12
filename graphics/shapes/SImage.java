package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import graphics.shapes.ui.ShapeVisitor;
import graphics.shapes.ui.ShapesView;

public class SImage extends Shape {
	public Rectangle rect;
	public ImageIcon image;
	private String path;
	public ShapesView sview;
	public BufferedImage bimg;
	
	public SImage(String path,Point point, ShapesView sview) throws IOException {

		this.path=path;
		this.bimg = ImageIO.read(new File(path));
		this.image = new ImageIcon(bimg);
		this.rect = new Rectangle(point.x,point.y,bimg.getWidth(),bimg.getHeight());
		this.sview = sview;

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
