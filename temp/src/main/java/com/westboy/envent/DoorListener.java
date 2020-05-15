package com.westboy.envent;

import java.util.EventListener;

/**
 * @author westboy
 * @since 2019/11/11
 */
public interface DoorListener extends EventListener {
	void doorEvent(DoorEvent doorEvent);
}
