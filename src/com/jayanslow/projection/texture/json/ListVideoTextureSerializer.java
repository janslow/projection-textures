package com.jayanslow.projection.texture.json;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.ImageTexture;
import com.jayanslow.projection.texture.models.ListVideoTexture;
import com.jayanslow.utils.serializer.SerializerFactory;

public class ListVideoTextureSerializer extends AbstractVideoTextureSerializer<ListVideoTexture> {

	public static final String	KEY_IMAGES	= "images";

	public static boolean register(SerializerFactory f) {
		return f.addSerializer(new ListVideoTextureSerializer(f));
	}

	public static void registerNested(SerializerFactory f) {
		register(f);
	}

	public ListVideoTextureSerializer(SerializerFactory factory) {
		super(factory, ListVideoTexture.class);
	}

	@Override
	public ListVideoTexture deserialize(JSONObject o) throws JSONException {
		JSONArray array = o.getJSONArray(KEY_IMAGES);
		List<ImageTexture> images = getFactory().deserialize(ImageTexture.class, array);
		return new ListVideoTexture(images);
	}

	@Override
	protected void serializeVideoTexture(ListVideoTexture t, JSONObject o) {
		JSONArray array = getFactory().serialize(ImageTexture.class, t.getFrames());
		o.put(KEY_IMAGES, array);
	}
}
