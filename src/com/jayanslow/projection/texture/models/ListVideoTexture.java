package com.jayanslow.projection.texture.models;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListVideoTexture extends AbstractTexture implements VideoTexture {

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

	private final List<ImageTexture>	images;

	public ListVideoTexture(List<ImageTexture> images) {
		super(false);

		this.images = images;
	}

	public List<ImageTexture> getFrames() {
		return images;
	}

	@Override
	public ImageTexture getImageTexture(int frame) {
		return images.get(frame);
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
	public Iterator<ImageTexture> iterator() {
		return new Itr(0);
	}

	@Override
	public ListIterator<ImageTexture> listIterator() {
		return new ListItr(0);
	}
}
