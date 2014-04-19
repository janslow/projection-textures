package com.jayanslow.projection.texture.models;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.List;

public class DirectoryVideoTexture extends ListVideoTexture {
	private static List<ImageTexture> getFiles(File dir) throws NullPointerException, IllegalArgumentException {
		if (dir == null)
			throw new NullPointerException();
		if (!dir.isDirectory())
			throw new IllegalArgumentException();

		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {

				return name.toLowerCase().matches(".*\\.(jpg|jpeg|png|gif)");
			}
		});
		List<ImageTexture> textures = new LinkedList<>();
		for (File f : files)
			textures.add(new FileImageTexture(f));

		return textures;
	}

	private final File	directory;

	public DirectoryVideoTexture(File directory) throws NullPointerException, IllegalArgumentException {
		this(directory, getFiles(directory));
	}

	public DirectoryVideoTexture(File directory, List<ImageTexture> images) {
		super(images);
		this.directory = directory;
	}

	public DirectoryVideoTexture(String path) throws NullPointerException, IllegalArgumentException {
		this(new File(path));
	}

	public File getDirectory() {
		return directory;
	}

	@Override
	public VideoTextureType getVideoTextureType() {
		return VideoTextureType.DIRECTORY;
	}
}
