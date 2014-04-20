package com.jayanslow.projection.texture.models;

public interface Texture {

	public ImageTexture getImageTexture(int frame);

	public int getNumberOfFrames();

	boolean isImageTexture();
}
