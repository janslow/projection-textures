package com.jayanslow.projection.texture.models;

import java.util.List;

import com.jayanslow.projection.texture.listeners.TextureListener;

public class ListVideoTexture extends AbstractVideoTexture {

	private final TextureListener	internalListener	= new TextureListener() {

															@Override
															public void textureChange(Texture texture) {
																fireTextureChange();
															}

															@Override
															public void textureFrameChange(int current, int old) {}

														};

	public ListVideoTexture(List<ImageTexture> images) {
		super(TextureType.LIST, images);
		for (ImageTexture t : images)
			t.addTextureListener(internalListener);
	}

	public void add(ImageTexture t, int index) {
		images.add(index, t);
		t.addTextureListener(internalListener);
		fireTextureChange();
	}

	@Override
	public List<ImageTexture> getFrames() {
		return images;
	}

	public void remove(int index) {
		Texture t = images.remove(index);
		if (t != null)
			t.removeTextureListener(internalListener);
		fireTextureChange();
	}

	public void setFrames(List<ImageTexture> frames) throws NullPointerException {
		if (frames == null)
			throw new NullPointerException();

		images = frames;

		fireTextureChange();
	}
}
