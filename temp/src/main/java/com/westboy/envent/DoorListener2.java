package com.westboy.envent;

/**
 * @author westboy
 * @since 2019/11/11
 */
public class DoorListener2 implements DoorListener {
	@Override
	public void doorEvent(DoorEvent doorEvent) {
		if ("open".equals(doorEvent.getDoorState())) {
			System.out.println(this + "：门打开了，同时打开走廊的灯");
		} else {
			System.out.println(this + "：门关闭了，同时关闭走廊的灯");
		}
	}

	@Override
	public String toString() {
		return "门2监听器";
	}
}
