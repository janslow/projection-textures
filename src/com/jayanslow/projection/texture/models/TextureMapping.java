package com.jayanslow.projection.texture.models;

import com.jayanslow.projection.world.models.Face;

public class TextureMapping {
	private final Texture	texture;
	private final Face		face;

	public TextureMapping(Face face, Texture texture) {
		this.face = face;
		this.texture = texture;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextureMapping other = (TextureMapping) obj;
		if (face == null) {
			if (other.face != null)
				return false;
		} else if (!face.equals(other.face))
			return false;
		if (texture == null) {
			if (other.texture != null)
				return false;
		} else if (!texture.equals(other.texture))
			return false;
		return true;
	}

	public Face getFace() {
		return face;
	}

	public int getFaceId() {
		return face.getFaceId();
	}

	public int getScreenId() {
		return face.getScreen().getId();
	}

	public Texture getTexture() {
		return texture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((face == null) ? 0 : face.hashCode());
		result = prime * result + ((texture == null) ? 0 : texture.hashCode());
		return result;
	}
}
