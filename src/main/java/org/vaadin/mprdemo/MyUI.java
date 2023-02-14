package org.vaadin.mprdemo;

import com.vaadin.annotations.Push;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

import com.vaadin.mpr.LegacyWrapper;
import com.vaadin.mpr.core.LegacyUI;
import com.vaadin.mpr.core.MprTheme;
import com.vaadin.mpr.core.MprWidgetset;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;

@Push(value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET)
@Route("")
@MprTheme("mytheme")
@CssImport("custom.css")
@LegacyUI(OldUI.class)
@MprWidgetset("org.vaadin.mprdemo.MyWidgetSet")
public class MyUI extends VerticalLayout implements RouterLayout {

	@SuppressWarnings("deprecation")
	public MyUI() {
		add(new LegacyWrapper(new LegacyView()));

	}

	@Override
	public void onAttach(AttachEvent event) {
		if (event.getSession().getService().getDeploymentConfiguration().isProductionMode()) {
			event.getUI().getPage().executeJs("window.vaadin.debug=false;");
		}
	}

}
