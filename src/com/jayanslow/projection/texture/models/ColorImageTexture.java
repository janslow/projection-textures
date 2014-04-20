package com.jayanslow.projection.texture.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.vecmath.Vector2f;

public class ColorImageTexture extends BufferedImageTexture {

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

	private final Vector2f	dimensions;
	private final Color		color;

	public ColorImageTexture(Color color) {
		this(color, new Vector2f(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	public ColorImageTexture(Color color, Vector2f dimensions) {
		super(createBufferedImage(color, dimensions));
		this.color = color;
		this.dimensions = new Vector2f(dimensions);
	}

	public Color getColor() {
		return color;
	}

	public Vector2f getDimensions() {
		return new Vector2f(dimensions);
	}

	@Override
	public ImageTextureType getImageTextureType() {
		return ImageTextureType.COLOR;
	}

}
