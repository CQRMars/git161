package cn.jbit.cms.entity;

import java.util.*;

public class News {
	private String title;//标题
	private String author;//作者
	private Date createTime;//发布时间
	private String content;//内容
	private int id;//标号

	@Override
	public String toString() {
		return "News [title=" + title + ", author=" + author + ", createTime=" + createTime + ", content=" + content
				+ ", id=" + id + "]";
	}
	public News() {}
	public News(int id,String title, String author, Date createTime, String content) {
		super();
		this.id=id;
		this.title = title;
		this.author = author;
		this.createTime = createTime;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
