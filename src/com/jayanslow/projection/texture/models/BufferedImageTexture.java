package com.jayanslow.projection.texture.models;

import java.awt.image.BufferedImage;

public class BufferedImageTexture extends AbstractImageTexture {

	private final BufferedImage	image;

	public BufferedImageTexture(BufferedImage image) {
		super();
		this.image = image;
	}

	@Override
	public BufferedImage getBufferedImage() {
		return image;
	}

	@Override
	public ImageTextureType getImageTextureType() {
		return ImageTextureType.BUFFERED;
	}

}
