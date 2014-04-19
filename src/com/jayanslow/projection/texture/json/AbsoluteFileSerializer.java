package com.jayanslow.projection.texture.json;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class AbsoluteFileSerializer extends AbstractSerializer<File> {

	public static final String	KEY_ABSOLUTE_PATH	= "absolute_path";

	public static void register(SerializerFactory f) {
		f.addSerializer(new AbsoluteFileSerializer(f));
	}

	public static void registerNested(SerializerFactory f) {
		register(f);
	}

	public AbsoluteFileSerializer(SerializerFactory factory) {
		super(factory, File.class);
	}

	@Override
	public File deserialize(JSONObject o) throws JSONException {
		String s = o.getString(KEY_ABSOLUTE_PATH);
		File file = new File(s);
		if (file.exists())
			return file;
		else
			return null;
	}

	@Override
	public void serialize(File t, JSONObject o) throws JSONException {
		o.put(KEY_ABSOLUTE_PATH, t.getAbsolutePath());
	}

}
