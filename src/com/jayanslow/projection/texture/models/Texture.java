package com.jayanslow.projection.texture.models;

public interface Texture {
	public ImageTexture getCurrentImageTexture();

	boolean isImageTexture();

	public boolean isLoopable();

	public void seek(int offset);
}
