package com.jayanslow.projection.texture.controllers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jayanslow.projection.texture.models.ImageTexture;
import com.jayanslow.projection.texture.models.Texture;
import com.jayanslow.projection.texture.models.TextureMapping;
import com.jayanslow.projection.texture.models.VideoTexture;
import com.jayanslow.projection.world.models.Face;

public class MapTextureController implements TextureController {
	private int							currentFrame;
	private final Map<Face, Texture>	map;

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
	}

	@Override
	public int getCurrentFrame() {
		return currentFrame;
	}

	@Override
	public ImageTexture getCurrentImageTexture(Face face) {
		Texture texture = getTexture(face);
		if (texture.isImageTexture())
			return (ImageTexture) texture;

		VideoTexture videoTexture = (VideoTexture) texture;
		videoTexture.setCurrentFrame(currentFrame);
		return videoTexture.getCurrentImageTexture();
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
	public void putTexture(Face face, Texture texture) {
		map.put(face, texture);
	}

	@Override
	public void remove(Face face) {
		map.remove(face);
	}

	@Override
	public void setCurrentFrame(int currentFrame) throws IllegalArgumentException {
		if (currentFrame < 0)
			throw new IllegalArgumentException("currentFrame must be non-negative");
		this.currentFrame = currentFrame;
	}
}
