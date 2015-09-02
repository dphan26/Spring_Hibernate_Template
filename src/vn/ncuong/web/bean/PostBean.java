package vn.ncuong.web.bean;

public class PostBean {
	private Integer postId;
	private String postTitle;
	private String postUrl;
	private String postContent;
	private String postMeta;
	
	private Integer categoryId;
	private String categoryName;
	private String categoryUrl;
	private String categoryMeta;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostMeta() {
		return postMeta;
	}

	public void setPostMeta(String postMeta) {
		this.postMeta = postMeta;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryUrl() {
		return categoryUrl;
	}

	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}

	public String getCategoryMeta() {
		return categoryMeta;
	}

	public void setCategoryMeta(String categoryMeta) {
		this.categoryMeta = categoryMeta;
	}

}
