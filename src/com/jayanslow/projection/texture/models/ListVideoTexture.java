package com.jayanslow.projection.texture.models;

import java.util.List;

public class ListVideoTexture extends AbstractVideoTexture {

	public ListVideoTexture(List<ImageTexture> images) {
		super(TextureType.LIST, images);
	}

	@Override
	public List<ImageTexture> getFrames() {
		return images;
	}

	public void markDirty() {
		fireTextureChange();
	}

	public void setFrames(List<ImageTexture> frames) throws NullPointerException {
		if (frames == null)
			throw new NullPointerException();

		images = frames;

		fireTextureChange();
	}
}
