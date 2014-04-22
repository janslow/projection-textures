package com.jayanslow.projection.texture.models;

import java.awt.image.BufferedImage;

import javax.vecmath.Vector2f;

public abstract class AbstractImageTexture extends AbstractTexture implements ImageTexture {

	private BufferedImage	image;
	private boolean			isLoaded;

	public AbstractImageTexture(TextureType type) {
		this(type, false, null);
	}

	private AbstractImageTexture(TextureType type, boolean isLoaded, BufferedImage image) {
		super(type);
		this.isLoaded = isLoaded;
		this.image = image;
	}

	public AbstractImageTexture(TextureType type, BufferedImage image) {
		this(type, true, image);
	}

	@Override
	public BufferedImage getBufferedImage() {
		if (!isLoaded())
			load();
		return image;
	}

	@Override
	public Vector2f getDimensions() {
		if (isLoaded()) {
			BufferedImage image = getBufferedImage();
			return new Vector2f(image.getWidth(), image.getHeight());
		} else
			return new Vector2f();
	}

	@Override
	public ImageTexture getImageTexture(int frame) {
		return this;
	}

	@Override
	public int getNumberOfFrames() {
		return 1;
	}

	@Override
	public boolean isLoaded() {
		return isLoaded;
	}

	@Override
	public void load() {
		image = loadImage();
		isLoaded = true;
	}

	protected abstract BufferedImage loadImage();

	protected void markDirty() {
		isLoaded = false;
		fireTextureChange();
	}

}
