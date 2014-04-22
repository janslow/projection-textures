package com.jayanslow.projection.texture.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.BufferedImageTexture;
import com.jayanslow.projection.texture.models.ColorImageTexture;
import com.jayanslow.projection.texture.models.DirectoryVideoTexture;
import com.jayanslow.projection.texture.models.FileImageTexture;
import com.jayanslow.projection.texture.models.ListVideoTexture;
import com.jayanslow.projection.texture.models.PreviewTexture;
import com.jayanslow.projection.texture.models.Texture;
import com.jayanslow.projection.texture.models.TextureType;
import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class TextureSerializer extends AbstractSerializer<Texture> {

	public static boolean register(SerializerFactory f) {
		return f.addSerializer(new TextureSerializer(f));
	}

	public static void registerNested(SerializerFactory f) {
		if (register(f)) {
			DirectoryVideoTextureSerializer.registerNested(f);
			ListVideoTextureSerializer.registerNested(f);
			FileImageTextureSerializer.registerNested(f);
		}
	}

	public TextureSerializer(SerializerFactory factory) {
		super(factory, Texture.class);
	}

	@Override
	public Texture deserialize(JSONObject o) throws JSONException {
		TextureType type = TextureType.valueOf(o.getString(AbstractTextureSerializer.KEY_TEXTURE_TYPE).toUpperCase());
		return getFactory().deserialize(type.getTextureClass(), o);

	}

	@Override
	public void serialize(Texture t, JSONObject o) throws JSONException {
		switch (t.getTextureType()) {
		case BUFFERED:
			getFactory().serialize(BufferedImageTexture.class, (BufferedImageTexture) t, o);
			break;
		case COLOR:
			getFactory().serialize(ColorImageTexture.class, (ColorImageTexture) t, o);
			break;
		case DIRECTORY:
			getFactory().serialize(DirectoryVideoTexture.class, (DirectoryVideoTexture) t, o);
			break;
		case FILE:
			getFactory().serialize(FileImageTexture.class, (FileImageTexture) t, o);
			break;
		case LIST:
			getFactory().serialize(ListVideoTexture.class, (ListVideoTexture) t, o);
			break;
		case PREVIEW:
			getFactory().serialize(PreviewTexture.class, (PreviewTexture) t, o);
			break;
		default:
			throw new RuntimeException("Unknown TextureType in TextureSerializer");

		}
	}

}
