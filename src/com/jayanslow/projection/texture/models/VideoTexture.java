package com.jayanslow.projection.texture.models;

import java.util.ListIterator;

public interface VideoTexture extends Texture, Iterable<ImageTexture> {

	public VideoTextureType getVideoTextureType();

	public ListIterator<ImageTexture> listIterator();
}
