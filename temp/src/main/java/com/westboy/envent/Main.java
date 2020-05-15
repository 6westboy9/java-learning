package com.westboy.envent;

/**
 * @author westboy
 * @since 2019/11/11
 */
public class Main {
	public static void main(String[] args) {
		DoorManager manager = new DoorManager();
		manager.addDoorListener(new DoorListener1());
		manager.addDoorListener(new DoorListener2());

		manager.fireWorkspaceOpened();
		manager.fireWorkspaceClosed();
	}
}
