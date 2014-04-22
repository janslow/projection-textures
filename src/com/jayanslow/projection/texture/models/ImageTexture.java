package com.jayanslow.projection.texture.models;

import java.awt.image.BufferedImage;

import javax.vecmath.Vector2f;

public interface ImageTexture extends Texture {
	public BufferedImage getBufferedImage();

	Vector2f getDimensions();

	boolean isLoaded();

	void load();
}
