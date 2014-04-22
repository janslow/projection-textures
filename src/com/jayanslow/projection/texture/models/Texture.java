package com.jayanslow.projection.texture.models;

import com.jayanslow.projection.texture.listeners.TextureListener;

public interface Texture {
	public void addTextureListener(TextureListener l) throws NullPointerException;

	public ImageTexture getImageTexture(int frame);

	public int getNumberOfFrames();

	public TextureType getTextureType();

	public void removeTextureListener(TextureListener l);
}
