package com.jayanslow.projection.texture.models;

public interface Texture {
	public ImageTexture getImageTexture(int frame);

	boolean isImageTexture();

	public boolean isLoopable();
}
