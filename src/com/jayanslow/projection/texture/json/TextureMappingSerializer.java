package com.jayanslow.projection.texture.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.Texture;
import com.jayanslow.projection.texture.models.TextureMapping;
import com.jayanslow.projection.world.controllers.WorldController;
import com.jayanslow.projection.world.models.Face;
import com.jayanslow.projection.world.models.Screen;
import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class TextureMappingSerializer extends AbstractSerializer<TextureMapping> {

	public static final String	KEY_TEXTURE		= "texture";
	public static final String	KEY_FACE_ID		= "face";
	public static final String	KEY_SCREEN_ID	= "screen";

	public static boolean register(SerializerFactory f, WorldController world) {
		return f.addSerializer(new TextureMappingSerializer(f, world));
	}

	public static void registerNested(SerializerFactory f, WorldController world) {
		if (register(f, world))
			TextureSerializer.registerNested(f);

	}

	private final WorldController	world;

	public TextureMappingSerializer(SerializerFactory factory, WorldController world) {
		super(factory, TextureMapping.class);

		this.world = world;
	}

	@Override
	public TextureMapping deserialize(JSONObject o) throws JSONException {
		Screen screen = world.getScreen(o.getInt(KEY_SCREEN_ID));
		Face face = screen.getFace(o.getInt(KEY_FACE_ID));

		Texture texture = getFactory().deserialize(Texture.class, o.getJSONObject(KEY_TEXTURE));

		return new TextureMapping(face, texture);
	}

	@Override
	public void serialize(TextureMapping t, JSONObject o) throws JSONException {
		o.put(KEY_SCREEN_ID, t.getScreenId());
		o.put(KEY_FACE_ID, t.getFaceId());
		o.put(KEY_TEXTURE, getFactory().serialize(Texture.class, t.getTexture()));
	}
}
