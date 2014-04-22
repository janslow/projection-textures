package com.jayanslow.projection.texture.json;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.FileImageTexture;
import com.jayanslow.utils.serializer.SerializerFactory;

public class FileImageTextureSerializer extends AbstractTextureSerializer<FileImageTexture> {

	public static final String	KEY_FILE	= "file";

	public static boolean register(SerializerFactory f) {
		return f.addSerializer(new FileImageTextureSerializer(f));
	}

	public static void registerNested(SerializerFactory f) {
		if (register(f))
			AbsoluteFileSerializer.registerNested(f);
	}

	public FileImageTextureSerializer(SerializerFactory factory) {
		super(factory, FileImageTexture.class);
	}

	@Override
	public FileImageTexture deserialize(JSONObject o) throws JSONException {
		File file = getFactory().deserialize(File.class, o.getJSONObject(KEY_FILE));
		return new FileImageTexture(file);
	}

	@Override
	protected void serializeTexture(FileImageTexture t, JSONObject o) {
		o.put(KEY_FILE, getFactory().serialize(File.class, t.getFile()));
	}

}
