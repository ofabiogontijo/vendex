package com.vendex.api.core.exception;

import com.vendex.api.core.exception.model.MessageType;

import java.util.List;

public class VendexRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> notifications;

	private MessageType messageType;

	private String description;

	private Integer errorCode;

	public VendexRuntimeException() {
	}

	public VendexRuntimeException(MessageType messageType, List<String> notifications, Integer errorCode) {
		super(new Throwable(notifications.toString()));
		this.messageType = messageType;
		this.notifications = notifications;
		this.errorCode = errorCode;
	}

	public VendexRuntimeException(MessageType messageType, List<String> notifications, Integer errorCode,
			boolean enableSuppression) {
		super(notifications.toString(), null, enableSuppression, false);
		this.messageType = messageType;
		this.notifications = notifications;
		this.errorCode = errorCode;
	}

	public VendexRuntimeException(MessageType messageType, List<String> notifications, Throwable cause,
			Integer errorCode) {
		super(cause);
		this.messageType = messageType;
		this.notifications = notifications;
		this.errorCode = errorCode;
	}

	public VendexRuntimeException(MessageType messageType, String description, List<String> notifications,
			int errorCode) {
		super(new Throwable(notifications.toString()));
		this.messageType = messageType;
		this.notifications = notifications;
		this.errorCode = errorCode;
		this.description = description;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public List<String> getNotifications() {
		return notifications;
	}

	public int getHttpErrorCode() {
		return errorCode;
	}

	public String getDescription() {
		return description;
	}

	public String getDetail() {
		return this.getNotifications().toString().replaceAll("\\[", "").replaceAll("]", "");
	}

}
