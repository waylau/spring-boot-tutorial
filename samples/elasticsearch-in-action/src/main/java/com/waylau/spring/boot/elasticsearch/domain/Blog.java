package com.waylau.spring.boot.elasticsearch.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Blog.
 * 
 * @since 1.0.0 2017年3月5日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Document(indexName = "blog", type = "blog", shards = 1, replicas = 0, refreshInterval = "-1")
@XmlRootElement // MediaType 转为 XML
public class Blog implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id  // 主键
	private String id; // 用户的唯一标识

	private String title;
 
	private String content;

	protected Blog() {  // JPA 的规范要求无参构造函数；设为 protected 防止直接使用 
	}

	public Blog(String name, String content) {
		this.title = name;
		this.content = content;
	}
	
	public Blog(String id, String name, String content) {
		this.id = id;
		this.title = name;
		this.content = content;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
 
	@Override
    public String toString() {
        return String.format(
                "User[id=%d, title='%s', content='%s']",
                id, title, content);
    }
}
