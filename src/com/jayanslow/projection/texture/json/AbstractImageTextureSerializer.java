package com.jayanslow.projection.texture.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.ImageTexture;
import com.jayanslow.utils.serializer.SerializerFactory;

public abstract class AbstractImageTextureSerializer<T extends ImageTexture> extends AbstractTextureSerializer<T> {

	public static final String	KEY_IMAGE_TEXTURE_TYPE	= "image_texture_type";

	public AbstractImageTextureSerializer(SerializerFactory factory, Class<T> targetClass) {
		super(factory, targetClass);
	}

	protected abstract void serializeImageTexture(T t, JSONObject o);

	@Override
	protected void serializeTexture(T t, JSONObject o) throws JSONException {
		o.put(KEY_IMAGE_TEXTURE_TYPE, t.getImageTextureType().toString().toLowerCase());

		serializeImageTexture(t, o);
	}

}
