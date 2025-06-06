package jtt.tpg.dto;
/**
 * This class is needed to create Image object that saves info
 * @value url - url of image
 * @value width - width of image
 * @value height - height of image
 */
public class Image {
	private String url;
	private int width;
	private int height;
	
	/**
	 * Constructor
	 * @param url
	 * @param width
	 * @param height
	 */
	public Image(String url, int width, int height) {
		this.url = url;
		this.width = width;
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
