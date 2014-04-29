package com.jayanslow.projection.texture.models;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import com.jayanslow.utils.ExtensionFileFilter;

public class DirectoryVideoTexture extends AbstractVideoTexture {
	private static List<ImageTexture> getFiles(File dir) throws NullPointerException, IllegalArgumentException {
		if (dir == null)
			throw new NullPointerException();
		if (!dir.isDirectory())
			throw new IllegalArgumentException();

		File[] files = dir.listFiles((FileFilter) ExtensionFileFilter.createImageFileFilter());
		List<ImageTexture> textures = new ArrayList<>(files.length);
		for (File f : files)
			textures.add(new FileImageTexture(f));

		return textures;
	}

	private File	directory;

	public DirectoryVideoTexture(File directory) throws NullPointerException, IllegalArgumentException {
		this(directory, getFiles(directory));
	}

	public DirectoryVideoTexture(File directory, List<ImageTexture> images) {
		super(TextureType.DIRECTORY, images);
		this.directory = directory;
	}

	public DirectoryVideoTexture(String path) throws NullPointerException, IllegalArgumentException {
		this(new File(path));
	}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		images = getFiles(directory);
		this.directory = directory;

		fireTextureChange();
	}
}
