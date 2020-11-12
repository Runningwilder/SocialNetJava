package com.Socialnet.project.entity;

import java.time.LocalDate;

public class Message {

	private int id;
	private String content;
	private LocalDate time;
	private int fromId;
	private int toId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

}
