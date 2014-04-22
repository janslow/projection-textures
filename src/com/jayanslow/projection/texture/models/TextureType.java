package com.jayanslow.projection.texture.models;

public enum TextureType {
	FILE(FileImageTexture.class), BUFFERED(BufferedImageTexture.class), COLOR(ColorImageTexture.class), DIRECTORY(
			DirectoryVideoTexture.class), LIST(ListVideoTexture.class), PREVIEW(PreviewTexture.class);

	private final Class<? extends Texture>	textureClass;

	private TextureType(boolean isImage, Class<? extends Texture> textureClass) {
		this(textureClass);
	}

	private TextureType(Class<? extends Texture> textureClass) {
		this.textureClass = textureClass;
	}

	public boolean extendsImageTexture() {
		return ImageTexture.class.isAssignableFrom(getTextureClass());
	}

	public boolean extendsVideoTexture() {
		return VideoTexture.class.isAssignableFrom(getTextureClass());
	}

	public Class<? extends Texture> getTextureClass() {
		return textureClass;
	}

}
