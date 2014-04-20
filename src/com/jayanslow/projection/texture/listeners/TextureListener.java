package com.jayanslow.projection.texture.listeners;

import com.jayanslow.projection.texture.models.Texture;

public interface TextureListener {
	/**
	 * Called when a texture changes
	 * 
	 * @param texture
	 *            Texture which has changed
	 */
	public void textureChange(Texture texture);

	/**
	 * Called when the current frame changes
	 * 
	 * @param current
	 *            New frame number
	 * @param old
	 *            Old frame number
	 */
	public void textureFrameChange(int current, int old);
}
