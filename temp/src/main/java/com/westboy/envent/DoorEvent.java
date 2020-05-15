package com.westboy.envent;

import java.util.EventObject;

/**
 * @author westboy
 * @since 2019/11/11
 */
public class DoorEvent extends EventObject {

	private static final long serialVersionUID = 6496098798146410884L;

	/** 表示门的状态，有“开”和“关”两种 */
	private String doorState = "";

	public DoorEvent(Object source, String doorState) {
		super(source);
		this.doorState = doorState;
	}

	public void setDoorState(String doorState) {
		this.doorState = doorState;
	}

	public String getDoorState() {
		return this.doorState;
	}

}
