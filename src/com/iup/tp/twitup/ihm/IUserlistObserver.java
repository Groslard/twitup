package com.iup.tp.twitup.ihm;

import java.util.Set;

import com.iup.tp.twitup.datamodel.User;

public interface IUserlistObserver {
	void notifyUserListHasChanged(Set<User> users);
}
