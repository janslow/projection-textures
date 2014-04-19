package com.jayanslow.projection.texture.controllers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONArray;

import com.jayanslow.projection.texture.json.TextureMappingSerializer;
import com.jayanslow.projection.texture.models.TextureMapping;
import com.jayanslow.projection.world.controllers.WorldController;
import com.jayanslow.utils.serializer.MapSerializerFactory;
import com.jayanslow.utils.serializer.Serializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class TextureJsonController {
	private static Charset			DEFAULT_CHARSET	= StandardCharsets.UTF_8;
	private final SerializerFactory	f;
	private final Charset			charset;

	public TextureJsonController(SerializerFactory f) {
		this(f, DEFAULT_CHARSET);
	}

	public TextureJsonController(SerializerFactory f, Charset charset) {
		this.charset = charset;

		this.f = f;
	}

	public TextureJsonController(WorldController world) {
		this(world, DEFAULT_CHARSET);
	}

	public TextureJsonController(WorldController world, Charset charset) {
		this(new MapSerializerFactory(new HashMap<Class<?>, Serializer<?>>()), charset);

		TextureMappingSerializer.registerNested(f, world);
	}

	public Collection<TextureMapping> deserialize(String json) {
		JSONArray o = new JSONArray(json);
		return f.deserialize(TextureMapping.class, o);
	}

	public Collection<TextureMapping> readFromFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		String json = charset.decode(ByteBuffer.wrap(encoded)).toString();
		return deserialize(json);
	}

	public String serialize(Collection<TextureMapping> mappings, boolean pretty) {
		JSONArray o = f.serialize(TextureMapping.class, mappings);
		return o.toString(pretty ? 4 : 0);
	}

	public void writeToFile(Collection<TextureMapping> mappings, String path, boolean pretty) throws IOException {
		String json = serialize(mappings, pretty);
		byte[] encoded = charset.encode(json).array();
		Files.write(Paths.get(path), encoded);
	}
}
