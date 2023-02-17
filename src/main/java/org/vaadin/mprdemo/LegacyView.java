package org.vaadin.mprdemo;

import com.vaadin.ui.*;

public class LegacyView extends VerticalLayout {

	private final Label label;

	public LegacyView() {
		label = new Label("Here we are in V7!");
		addComponent(label);
		addComponent(new Button("Show notification", e-> {
			Notification.show("V7 notification");
		}));
	}

    public void setValue(int counter) {
		label.setValue("Backgroud update (V7) "+counter);
    }
}
