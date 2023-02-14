package org.vaadin.mprdemo;

import com.vaadin.ui.*;

public class LegacyView extends VerticalLayout {
	
	public LegacyView() {
		addComponent(new Label("Here we are in V7!"));
		addComponent(new Button("Show notification", e-> {
			Notification.show("V7 notification");
		}));
	}

}
