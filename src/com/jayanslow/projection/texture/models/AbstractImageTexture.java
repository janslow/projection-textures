package com.jayanslow.projection.texture.models;

public abstract class AbstractImageTexture extends AbstractTexture implements ImageTexture {

	public AbstractImageTexture() {
		super(true);
	}

	@Override
	public ImageTexture getImageTexture(int frame) {
		return this;
	}

	@Override
	public int getNumberOfFrames() {
		return 1;
	}

}
