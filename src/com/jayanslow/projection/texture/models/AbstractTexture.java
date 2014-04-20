package com.jayanslow.projection.texture.models;


public abstract class AbstractTexture implements Texture {
	private final boolean	isImageTexture;

	public AbstractTexture(boolean isImageTexture) {
		super();

		this.isImageTexture = isImageTexture;
	}

	@Override
	public boolean isImageTexture() {
		return isImageTexture;
	}

}
