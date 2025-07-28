package com.nelioalves.cursomc.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer stattus;
	private String msg;
	private Long timeStamp;
	public StandardError(Integer stattus, String msg, Long timeStamp) {
		super();
		this.stattus = stattus;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}
	public Integer getStattus() {
		return stattus;
	}
	public void setStattus(Integer stattus) {
		this.stattus = stattus;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	
}
