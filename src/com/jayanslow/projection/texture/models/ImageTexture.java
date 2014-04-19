package com.jayanslow.projection.texture.models;

import java.awt.image.BufferedImage;

public interface ImageTexture extends Texture {
	public BufferedImage getBufferedImage();

	public ImageTextureType getImageTextureType();
}
