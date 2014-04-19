package com.jayanslow.projection.texture.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.ImageTexture;
import com.jayanslow.projection.texture.models.Texture;
import com.jayanslow.projection.texture.models.VideoTexture;
import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class TextureSerializer extends AbstractSerializer<Texture> {

	public static boolean register(SerializerFactory f) {
		return f.addSerializer(new TextureSerializer(f));
	}

	public static void registerNested(SerializerFactory f) {
		if (register(f)) {
			ImageTextureSerializer.registerNested(f);
			VideoTextureSerializer.registerNested(f);
		}
	}

	public TextureSerializer(SerializerFactory factory) {
		super(factory, Texture.class);
	}

	@Override
	public Texture deserialize(JSONObject o) throws JSONException {
		boolean isImage = o.getBoolean(AbstractTextureSerializer.KEY_IS_IMAGE);
		if (isImage)
			return getFactory().deserialize(ImageTexture.class, o);
		else
			return getFactory().deserialize(VideoTexture.class, o);
	}

	@Override
	public void serialize(Texture t, JSONObject o) throws JSONException {
		if (t.isImageTexture())
			getFactory().serialize(ImageTexture.class, (ImageTexture) t);
		else
			getFactory().serialize(VideoTexture.class, (VideoTexture) t);
	}

}
