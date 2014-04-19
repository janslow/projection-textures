package com.jayanslow.projection.texture.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.FileImageTexture;
import com.jayanslow.projection.texture.models.ImageTexture;
import com.jayanslow.projection.texture.models.ImageTextureType;
import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class ImageTextureSerializer extends AbstractSerializer<ImageTexture> {
	private static boolean register(SerializerFactory f) {
		return f.addSerializer(new ImageTextureSerializer(f));
	}

	public static void registerNested(SerializerFactory f) {
		if (register(f))
			FileImageTextureSerializer.registerNested(f);
	}

	public ImageTextureSerializer(SerializerFactory factory) {
		super(factory, ImageTexture.class);
	}

	@Override
	public ImageTexture deserialize(JSONObject o) throws JSONException {
		ImageTextureType type = ImageTextureType.valueOf(o.getString(
				AbstractImageTextureSerializer.KEY_IMAGE_TEXTURE_TYPE).toUpperCase());
		switch (type) {
		case FILE:
			return getFactory().deserialize(FileImageTexture.class, o);
		default:
			throw new RuntimeException("Unhandled ImageTextureType in ImageTextureSerializer.deserialize");
		}
	}

	@Override
	public void serialize(ImageTexture t, JSONObject o) throws JSONException {
		switch (t.getImageTextureType()) {
		case FILE:
			getFactory().serialize(FileImageTexture.class, (FileImageTexture) t);
			break;
		default:
			throw new RuntimeException("Unhandled ImageTextureType in ImageTextureSerializer.serialize");
		}
	}

}
