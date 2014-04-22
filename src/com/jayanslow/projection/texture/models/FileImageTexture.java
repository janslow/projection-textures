package com.jayanslow.projection.texture.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileImageTexture extends AbstractImageTexture {

	private final File	file;

	public FileImageTexture(File file) throws NullPointerException, IllegalArgumentException {
		super(null);
		if (file == null)
			throw new NullPointerException();
		if (!file.isFile())
			throw new IllegalArgumentException();
		this.file = file;
	}

	public FileImageTexture(String path) {
		this(path == null ? null : new File(path));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileImageTexture other = (FileImageTexture) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		return true;
	}

	public File getFile() {
		return file;
	}

	@Override
	public TextureType getTextureType() {
		return TextureType.FILE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		return result;
	}

	@Override
	protected BufferedImage loadImage() {
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void refresh() {
		markDirty();
	}

}
