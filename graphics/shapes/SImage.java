package graphics.shapes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import graphics.shapes.attributes.ColorAttributes;
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
		setBImage();
		this.image = new ImageIcon(bimg);
		this.rect = new Rectangle(point.x,point.y,bimg.getWidth(),bimg.getHeight());
		this.sview = sview;

	}
	boolean isImage(String image_path) {
		Image image = new ImageIcon(image_path).getImage();
		return image.getWidth(null) == -1;
	}

	void setBImage() throws IOException {
		String extension = "";
		int index = path.lastIndexOf('.');
		if (index > 0) {
			extension = path.substring(index + 1);
		}
		if (new File(path).exists() &&
				(extension.toLowerCase(Locale.ROOT).equals("png") || extension.toLowerCase(Locale.ROOT).equals("jpg") || extension.toLowerCase(Locale.ROOT).equals("jpeg") || extension.toLowerCase(Locale.ROOT).equals("gif"))) {
				this.bimg = ImageIO.read(new File(path));
		}
		else if (isImage(path)){
			URL imageURL = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
			connection.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0");
			this.bimg = ImageIO.read(connection.getInputStream());
		}
		else{
			this.bimg = ImageIO.read(new File("icon/NoImageAvailable.png"));
		}
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
	@Override
	public void add(Shape newS) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getValues() {
		String sClass = this.getClass().toString().replace("class graphics.shapes.","");
		return sClass + ";" + String.valueOf(this.getLoc().x) + ";" + String.valueOf(this.getLoc().y) + ";" + this.path;

	}

}
