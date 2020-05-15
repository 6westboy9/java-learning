package com.westboy.envent;

import java.util.Collection;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author westboy
 * @since 2019/11/11
 */
public class DoorManager {
	private Collection<EventListener> listeners;

	/**
	 * 添加事件
	 */
	public void addDoorListener(DoorListener listener) {
		if (listeners == null) {
			listeners = new HashSet<>();
		}
		listeners.add(listener);
	}

	/**
	 * 移除事件
	 */
	public void removeDoorListener(DoorListener listener) {
		if (listeners == null) {
			return;
		}
		listeners.remove(listener);
	}

	/**
	 * 触发开门事件
	 */
	void fireWorkspaceOpened() {
		if (listeners == null) {
			return;
		}
		DoorEvent event = new DoorEvent(this, "open");
		notifyListeners(event);
	}

	/**
	 * 触发关门事件
	 */
	void fireWorkspaceClosed() {
		if (listeners == null) {
			return;
		}
		DoorEvent event = new DoorEvent(this, "close");
		notifyListeners(event);
	}

	/**
	 * 通知所有的监听器
	 */
	private void notifyListeners(DoorEvent event) {
		for (EventListener eventListener : listeners) {
			DoorListener listener = (DoorListener) eventListener;
			listener.doorEvent(event);
		}
	}
}