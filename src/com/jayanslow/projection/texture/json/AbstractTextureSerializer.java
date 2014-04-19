package com.jayanslow.projection.texture.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.Texture;
import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public abstract class AbstractTextureSerializer<T extends Texture> extends AbstractSerializer<T> {
	public static String	KEY_IS_IMAGE	= "is_image";

	public AbstractTextureSerializer(SerializerFactory factory, Class<T> targetClass) {
		super(factory, targetClass);
	}

	@Override
	public void serialize(T t, JSONObject o) throws JSONException {
		o.put(KEY_IS_IMAGE, t.isImageTexture());
		serializeTexture(t, o);
	}

	protected abstract void serializeTexture(T t, JSONObject o) throws JSONException;
}
