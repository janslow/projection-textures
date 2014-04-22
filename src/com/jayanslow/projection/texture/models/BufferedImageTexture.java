package com.jayanslow.projection.texture.models;

import java.awt.image.BufferedImage;

public class BufferedImageTexture extends AbstractImageTexture {

	private BufferedImage	image;

	public BufferedImageTexture(BufferedImage image) {
		super(TextureType.BUFFERED);
		this.image = image;
	}

	@Override
	protected BufferedImage loadImage() {
		return image;
	}

	public void setImage(BufferedImage image) throws NullPointerException {
		if (image == null)
			throw new NullPointerException();
		this.image = image;

		markDirty();
	}

}
