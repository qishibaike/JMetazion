package com.metazion.jm.net;

import io.netty.channel.Channel;

public abstract class TransmitSession {

	protected Channel channel = null;
	protected boolean working = false;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean isWoring) {
		this.working = isWoring;
	}

	public void open() {
		working = true;
	}

	public void close() {
		working = false;

		if (isActive()) {
			channel.close();
		}
	}

	public void writeAndFlush(Object msg) {
		if (isActive()) {
			channel.writeAndFlush(msg);
		}
	}

	public boolean isActive() {
		return channel != null && channel.isActive();
	}

	public String detail() {
		return String.format("working[%b]", working);
	}

	public abstract void onActive() throws Exception;

	public abstract void onInactive() throws Exception;

	public abstract void onException() throws Exception;

	public abstract void onReceive(Object data) throws Exception;

	public abstract void send(Object data);
}