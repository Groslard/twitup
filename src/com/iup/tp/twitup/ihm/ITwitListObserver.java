package com.iup.tp.twitup.ihm;

import java.util.List;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public interface ITwitListObserver {

	void notifyTwitListHasChanged(List<Twit> twits);

	void notifyUserUpdated(User user);
}
