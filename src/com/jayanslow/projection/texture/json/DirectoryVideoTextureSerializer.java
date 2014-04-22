package com.jayanslow.projection.texture.json;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.DirectoryVideoTexture;
import com.jayanslow.utils.serializer.SerializerFactory;

public class DirectoryVideoTextureSerializer extends AbstractTextureSerializer<DirectoryVideoTexture> {

	public static final String	KEY_DIRECTORY	= "directory";

	public static boolean register(SerializerFactory f) {
		return f.addSerializer(new DirectoryVideoTextureSerializer(f));
	}

	public static void registerNested(SerializerFactory f) {
		if (register(f))
			AbsoluteFileSerializer.registerNested(f);
	}

	public DirectoryVideoTextureSerializer(SerializerFactory factory) {
		super(factory, DirectoryVideoTexture.class);
	}

	@Override
	public DirectoryVideoTexture deserialize(JSONObject o) throws JSONException {
		File file = getFactory().deserialize(File.class, o.getJSONObject(KEY_DIRECTORY));
		return new DirectoryVideoTexture(file);
	}

	@Override
	protected void serializeTexture(DirectoryVideoTexture t, JSONObject o) {
		o.put(KEY_DIRECTORY, getFactory().serialize(File.class, t.getDirectory()));
	}

}
