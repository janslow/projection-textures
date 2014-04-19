package com.jayanslow.projection.texture.models;

public abstract class AbstractImageTexture implements ImageTexture {

	public AbstractImageTexture() {
		super();
	}

	@Override
	public ImageTexture getImageTexture(int frame) {
		return this;
	}

	@Override
	public boolean isImageTexture() {
		return true;
	}

	@Override
	public boolean isLoopable() {
		return true;
	}
}
