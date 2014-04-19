package com.jayanslow.projection.texture.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.texture.models.DirectoryVideoTexture;
import com.jayanslow.projection.texture.models.ListVideoTexture;
import com.jayanslow.projection.texture.models.VideoTexture;
import com.jayanslow.projection.texture.models.VideoTextureType;
import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class VideoTextureSerializer extends AbstractSerializer<VideoTexture> {

	public static boolean register(SerializerFactory f) {
		return f.addSerializer(new VideoTextureSerializer(f));
	}

	public static void registerNested(SerializerFactory f) {
		if (register(f))
			DirectoryVideoTextureSerializer.registerNested(f);
	}

	public VideoTextureSerializer(SerializerFactory factory) {
		super(factory, VideoTexture.class);
	}

	@Override
	public VideoTexture deserialize(JSONObject o) throws JSONException {
		VideoTextureType type = VideoTextureType.valueOf(o.getString(
				AbstractVideoTextureSerializer.KEY_VIDEO_TEXTURE_TYPE).toUpperCase());
		switch (type) {
		case DIRECTORY:
			return getFactory().deserialize(DirectoryVideoTexture.class, o);
		case LIST:
			return getFactory().deserialize(ListVideoTexture.class, o);
		default:
			throw new RuntimeException("Unhandled VideoTextureType in VideoTextureSerializer.serialize");

		}
	}

	@Override
	public void serialize(VideoTexture t, JSONObject o) throws JSONException {
		switch (t.getVideoTextureType()) {
		case DIRECTORY:
			getFactory().serialize(DirectoryVideoTexture.class, (DirectoryVideoTexture) t, o);
			break;
		case LIST:
			getFactory().serialize(ListVideoTexture.class, (ListVideoTexture) t, o);
			break;
		default:
			throw new RuntimeException("Unhandled VideoTextureType in VideoTextureSerializer.serialize");

		}
	}

}
