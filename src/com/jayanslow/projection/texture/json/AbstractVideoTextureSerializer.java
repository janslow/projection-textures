package com.jayanslow.projection.texture.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.VideoTexture;
import com.jayanslow.utils.serializer.SerializerFactory;

public abstract class AbstractVideoTextureSerializer<T extends VideoTexture> extends AbstractTextureSerializer<T> {

	public static final String	KEY_VIDEO_TEXTURE_TYPE	= "video_texture_type";

	public AbstractVideoTextureSerializer(SerializerFactory factory, Class<T> targetClass) {
		super(factory, targetClass);
	}

	protected abstract void serializeVideoTexture(T t, JSONObject o);

	@Override
	protected void serializeTexture(T t, JSONObject o) throws JSONException {
		o.put(KEY_VIDEO_TEXTURE_TYPE, t.getVideoTextureType().toString().toLowerCase());

		serializeVideoTexture(t, o);
	}
}
