package com.jayanslow.projection.texture.models;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListVideoTexture implements VideoTexture {

	private class Itr implements Iterator<ImageTexture> {

		protected int	i;

		public Itr(int i) {
			this.i = i;
		}

		@Override
		public boolean hasNext() {
			return i < images.size();
		}

		@Override
		public ImageTexture next() {
			return images.get(i++);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	private class ListItr extends Itr implements ListIterator<ImageTexture> {

		public ListItr(int i) {
			super(i);
		}

		@Override
		public void add(ImageTexture e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasPrevious() {
			return i > 0;
		}

		@Override
		public int nextIndex() {
			return i;
		}

		@Override
		public ImageTexture previous() {
			return images.get(--i);
		}

		@Override
		public int previousIndex() {
			return i - 1;
		}

		@Override
		public void set(ImageTexture e) {
			throw new UnsupportedOperationException();
		}

	}

	private int							frame;
	private boolean						loop;
	private final List<ImageTexture>	images;

	public ListVideoTexture(List<ImageTexture> images) {
		this(images, true);
	}

	public ListVideoTexture(List<ImageTexture> images, boolean loop) {
		frame = 0;
		this.loop = loop;
		this.images = images;
	}

	@Override
	public ImageTexture get(int frame) {
		return images.get(frame);
	}

	@Override
	public int getCurrentFrame() {
		return frame;
	}

	@Override
	public ImageTexture getCurrentImageTexture() {
		return get(getCurrentFrame());
	}

	public List<ImageTexture> getFrames() {
		return images;
	}

	@Override
	public int getNumberOfFrames() {
		return images.size();
	}

	@Override
	public VideoTextureType getVideoTextureType() {
		return VideoTextureType.LIST;
	}

	@Override
	public boolean isImageTexture() {
		return false;
	}

	@Override
	public boolean isLoopable() {
		return loop;
	}

	@Override
	public Iterator<ImageTexture> iterator() {
		return new Itr(0);
	}

	@Override
	public ListIterator<ImageTexture> listIterator() {
		return new ListItr(0);
	}

	@Override
	public void seek(int offset) {
		int newFrame = frame + offset;
		int n = getNumberOfFrames();
		if (newFrame < 0)
			frame = n - 1 - (-newFrame % n);
		else
			frame = newFrame % n;
	}

	@Override
	public void setCurrentFrame(int frame) {
		if (frame < 0)
			throw new IllegalArgumentException("frame must be non-negative");
		if (frame >= getNumberOfFrames())
			if (loop)
				frame %= getNumberOfFrames();
			else
				throw new IllegalArgumentException("frame must be less than size() for non-looped VideoTextures");
		this.frame = frame;
	}

	@Override
	public void setIsLoopable(boolean loop) {
		this.loop = loop;
	}
}
