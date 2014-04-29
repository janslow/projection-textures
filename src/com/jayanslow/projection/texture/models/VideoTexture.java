package com.jayanslow.projection.texture.models;

import java.util.ListIterator;

public interface VideoTexture extends Texture, Iterable<ImageTexture> {

	public ListIterator<ImageTexture> listIterator();

	public ListIterator<ImageTexture> listIterator(int position);
}
