package com.jayanslow.projection.texture.models;

import java.util.LinkedList;
import java.util.List;

import com.jayanslow.projection.texture.controllers.TextureController;
import com.jayanslow.projection.texture.listeners.TextureListener;
import com.jayanslow.projection.world.models.Face;

public class PreviewTexture implements Texture {

	public static PreviewTexture preview(TextureController controller, Face face, Texture preview) {
		Texture original = controller.getTexture(face);
		PreviewTexture t;
		if (original instanceof PreviewTexture)
			t = (PreviewTexture) original;
		else {
			t = new PreviewTexture(controller, face, original);
			controller.putTexture(face, t);
		}
		t.preview(preview);
		return t;
	}

	private final Texture				original;
	private final TextureController		controller;
	private final Face					face;
	private Texture						preview;

	private final TextureListener		internalListener	= new TextureListener() {

																@Override
																public void textureChange(Texture texture) {
																	fireTextureChange();
																}

																@Override
																public void textureFrameChange(int current, int old) {}
															};
	private final List<TextureListener>	listeners			= new LinkedList<>();

	public PreviewTexture(TextureController controller, Face face) {
		this(controller, face, controller.getTexture(face));
	}

	public PreviewTexture(TextureController controller, Face face, Texture original) {
		this.controller = controller;
		this.face = face;

		this.original = original;
		preview = original;
	}

	@Override
	public void addTextureListener(TextureListener l) throws NullPointerException {
		if (preview != original)
			preview.addTextureListener(l);
		original.addTextureListener(l);

		listeners.add(l);
	}

	private void fireTextureChange() {
		for (TextureListener l : listeners)
			l.textureChange(this);
	}

	@Override
	public ImageTexture getImageTexture(int frame) {
		return preview.getImageTexture(frame);
	}

	@Override
	public int getNumberOfFrames() {
		return preview.getNumberOfFrames();
	}

	@Override
	public TextureType getTextureType() {
		return TextureType.PREVIEW;
	}

	public void preview(Texture preview) {
		if (this.preview == null)
			this.preview.removeTextureListener(internalListener);

		if (preview == null)
			this.preview = original;
		else {
			this.preview = preview;
			preview.addTextureListener(internalListener);
		}
		fireTextureChange();
	}

	@Override
	public void removeTextureListener(TextureListener l) {
		listeners.remove(l);
		if (preview != original)
			preview.removeTextureListener(l);
		original.removeTextureListener(l);
	}

	public Texture restore() {
		controller.putTexture(face, original);
		fireTextureChange();
		return original;
	}

}
