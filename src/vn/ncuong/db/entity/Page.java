package vn.ncuong.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "page")
public class Page {

	@Id
	@GeneratedValue
	@Column(name = "PAGE_ID")
	private Integer pageId;

	@Column(name = "PAGE_CONTENT")
	private String pageContent;

	@Column(name = "PAGE_NUMBER")
	private Integer pageNumber;

	@Column(name = "PAGE_META")
	private Integer pageMeta;

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageMeta() {
		return pageMeta;
	}

	public void setPageMeta(Integer pageMeta) {
		this.pageMeta = pageMeta;
	}

}
