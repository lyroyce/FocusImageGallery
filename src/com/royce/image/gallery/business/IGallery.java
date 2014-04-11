package com.royce.image.gallery.business;

import java.util.List;
/**
 * This interface represents a gallery which contains a list of images.
 * @author yinli
 *
 */
public interface IGallery {

	public abstract List<Image> getImages();

}