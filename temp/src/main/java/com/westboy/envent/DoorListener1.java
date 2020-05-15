package com.westboy.envent;

import java.util.Optional;

/**
 * @author westboy
 * @since 2019/11/11
 */
public class DoorListener1 implements DoorListener {

	@Override
	public void doorEvent(DoorEvent doorEvent) {
		if ("open".equals(doorEvent.getDoorState())) {
			System.out.println(this + "：门打开了");
		} else {
			System.out.println(this + "：门关闭了");
		}
	}

	@Override
	public String toString() {
		return "门1监听器";
	}
}
