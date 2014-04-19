package com.jayanslow.projection.texture.models;

import java.util.ListIterator;

public interface VideoTexture extends Texture, Iterable<ImageTexture> {

	public ImageTexture get(int frame);

	public int getCurrentFrame();

	public int getNumberOfFrames();

	public VideoTextureType getVideoTextureType();

	public ListIterator<ImageTexture> listIterator();

	public void setCurrentFrame(int frame);

	public void setIsLoopable(boolean loop);
}
