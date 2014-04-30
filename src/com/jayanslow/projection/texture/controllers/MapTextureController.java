package com.jayanslow.projection.texture.controllers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jayanslow.projection.texture.listeners.TextureListener;
import com.jayanslow.projection.texture.models.ImageTexture;
import com.jayanslow.projection.texture.models.Texture;
import com.jayanslow.projection.texture.models.TextureMapping;
import com.jayanslow.projection.world.models.Face;

public class MapTextureController implements TextureController {
	private int							currentFrame;
	private final Map<Face, Texture>	map;

	private final List<TextureListener>	listeners			= new LinkedList<>();
	private final TextureListener		internalListener	= new TextureListener() {
																@Override
																public void textureChange(Texture texture) {
																	fireTextureChange(texture);
																}

																@Override
																public void textureFrameChange(int current, int old) {}
															};

	public MapTextureController(Map<Face, Texture> map) {
		this(map, 0);
	}

	public MapTextureController(Map<Face, Texture> map, Collection<TextureMapping> mappings) {
		this(map, 0);
		for (TextureMapping mapping : mappings)
			map.put(mapping.getFace(), mapping.getTexture());
	}

	public MapTextureController(Map<Face, Texture> map, int currentFrame) {
		this.currentFrame = currentFrame;
		this.map = map;

		for (Texture t : map.values())
			t.addTextureListener(internalListener);
	}

	@Override
	public void addTextureListener(TextureListener l) throws NullPointerException {
		if (l == null)
			throw new NullPointerException();
		listeners.add(l);
	}

	protected void fireFrameChange(int current, int old) {
		for (TextureListener l : listeners)
			l.textureFrameChange(current, old);
	}

	protected void fireTextureChange(Texture texture) {
		for (TextureListener l : listeners)
			l.textureChange(texture);
	}

	@Override
	public int getCurrentFrame() {
		return currentFrame;
	}

	@Override
	public ImageTexture getCurrentImageTexture(Face face) {
		Texture texture = getTexture(face);
		if (texture == null)
			return null;
		return texture.getImageTexture(currentFrame);
	}

	@Override
	public int getMaximumFrame() {
		int max = 0;
		for (Texture t : map.values()) {
			int temp = t.getNumberOfFrames();
			if (temp > max)
				max = temp;

		}
		return max;
	}

	@Override
	public Texture getTexture(Face face) {
		return map.get(face);
	}

	@Override
	public Collection<TextureMapping> getTextureMappings() {
		List<TextureMapping> mappings = new LinkedList<>();
		for (Entry<Face, Texture> entry : map.entrySet())
			mappings.add(new TextureMapping(entry.getKey(), entry.getValue()));
		return mappings;
	}

	@Override
	public void putTexture(Face face, Texture texture) throws NullPointerException {
		if (face == null || texture == null)
			throw new NullPointerException();
		map.put(face, texture);
		texture.addTextureListener(internalListener);
		fireTextureChange(texture);
	}

	@Override
	public Texture remove(Face face) throws NullPointerException {
		if (face == null)
			throw new NullPointerException();

		Texture t = map.remove(face);
		if (t != null) {
			t.removeTextureListener(internalListener);
			fireTextureChange(null);
		}
		return t;
	}

	@Override
	public void removeTextureListener(TextureListener l) {
		listeners.remove(l);
	}

	@Override
	public void setCurrentFrame(int currentFrame) throws IllegalArgumentException {
		if (currentFrame < 0)
			throw new IllegalArgumentException("currentFrame must be non-negative");

		int old = this.currentFrame;
		this.currentFrame = currentFrame;

		fireFrameChange(currentFrame, old);
	}
}
