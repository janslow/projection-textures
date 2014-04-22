package com.jayanslow.projection.texture.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.vecmath.Vector2f;

public class ColorImageTexture extends AbstractImageTexture {

	public static final float	DEFAULT_WIDTH	= 500;
	public static final float	DEFAULT_HEIGHT	= 500;

	private static BufferedImage createBufferedImage(Color color, Vector2f dimensions) {
		int x = 1000, y = (int) (x / dimensions.x * dimensions.y);
		BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D g = image.createGraphics();
		g.setPaint(color);
		g.fillRect(0, 0, x, y);

		return image;
	}

	private Color		color;
	private Vector2f	preferredDimensions;

	public ColorImageTexture(Color color) {
		this(color, new Vector2f(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	public ColorImageTexture(Color color, Vector2f dimensions) {
		super(TextureType.COLOR);
		this.color = color;
		preferredDimensions = dimensions;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public TextureType getTextureType() {
		return TextureType.COLOR;
	}

	@Override
	protected BufferedImage loadImage() {
		return createBufferedImage(color, preferredDimensions);
	}

	public void setColor(Color color) throws NullPointerException {
		if (color == null)
			throw new NullPointerException();

		this.color = color;

		markDirty();
	}

	public void setDimensions(Vector2f preferredDimensions) throws NullPointerException {
		if (preferredDimensions == null)
			throw new NullPointerException();

		this.preferredDimensions = new Vector2f(preferredDimensions);
	}

}
