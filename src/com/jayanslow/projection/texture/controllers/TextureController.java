package com.jayanslow.projection.texture.controllers;

import java.util.Collection;

import com.jayanslow.projection.texture.models.ImageTexture;
import com.jayanslow.projection.texture.models.Texture;
import com.jayanslow.projection.texture.models.TextureMapping;
import com.jayanslow.projection.world.models.Face;

public interface TextureController {

	/**
	 * Gets the current frame number to be displayed
	 * 
	 * @return Current frame number
	 */
	public int getCurrentFrame();

	/**
	 * Gets the current image texture for the specified face at the current time
	 * 
	 * @param face
	 *            Face to lookup
	 * @return Current texture for face, or null if the specified face doesn't exist
	 */
	public ImageTexture getCurrentImageTexture(Face face);

	/**
	 * Gets the maximum frame number that can be displayed.
	 * 
	 * @return
	 */
	public int getMaximumFrame();

	/**
	 * Gets the texture for the specified face
	 * 
	 * @param face
	 *            Face to lookup
	 * @return Texture for face, or null if the specified face doesn't exist
	 */
	public Texture getTexture(Face face);

	/**
	 * Gets a collection of mappings between faces and textures
	 * 
	 * @return
	 */
	public Collection<TextureMapping> getTextureMappings();

	/**
	 * Maps the specified face to the specified texture
	 * 
	 * @param face
	 *            Face
	 * @param texture
	 *            Texture
	 */
	public void putTexture(Face face, Texture texture);

	/**
	 * Removes the mapping from the specifed face to it's texture (if it exists)
	 * 
	 * @param face
	 *            Face to remove
	 */
	public void remove(Face face);

	/**
	 * Sets the current frame number. The frame will be looped if time > getMaximumFrame()
	 * 
	 * @param time
	 *            Non-negative frame number
	 * @throws IllegalArgumentException
	 *             Thrown if time is negative
	 */
	public void setCurrentFrame(int frame) throws IllegalArgumentException;
}
