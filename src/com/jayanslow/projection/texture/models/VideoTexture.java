package com.jayanslow.projection.texture.models;

import java.util.ListIterator;

public interface VideoTexture extends Texture, Iterable<ImageTexture> {

	public int getNumberOfFrames();

	public VideoTextureType getVideoTextureType();

	public ListIterator<ImageTexture> listIterator();

	public void setIsLoopable(boolean loop);
}
