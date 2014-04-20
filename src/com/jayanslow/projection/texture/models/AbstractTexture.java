package com.jayanslow.projection.texture.models;

import java.util.LinkedList;
import java.util.List;

import com.jayanslow.projection.texture.listeners.TextureListener;

public abstract class AbstractTexture implements Texture {

	private final List<TextureListener>	listeners	= new LinkedList<>();
	private final boolean				isImageTexture;

	public AbstractTexture(boolean isImageTexture) {
		super();

		this.isImageTexture = isImageTexture;
	}

	@Override
	public void addTextureListener(TextureListener l) throws NullPointerException {
		if (l == null)
			throw new NullPointerException();
		listeners.add(l);
	}

	protected void fireTextureChange() {
		for (TextureListener l : listeners)
			l.textureChange(this);
	}

	@Override
	public boolean isImageTexture() {
		return isImageTexture;
	}

	@Override
	public void removeTextureListener(TextureListener l) {
		listeners.remove(l);
	}

}
