package org.vaadin.mprdemo;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.shared.ui.Transport;
import com.vaadin.mpr.LegacyWrapper;
import com.vaadin.mpr.core.LegacyUI;
import com.vaadin.mpr.core.MprTheme;
import com.vaadin.mpr.core.MprWidgetset;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@Push(value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET_XHR)
@Route("")
@MprTheme("mytheme")
@CssImport("custom.css")
@LegacyUI(OldUI.class)
@MprWidgetset("org.vaadin.mprdemo.MyWidgetSet")
public class MyUI extends VerticalLayout {
	private final LegacyView legacyComponent;
	private final VerticalLayout pushLayout = new VerticalLayout();

	@WebServlet(value = {"/mprdemo/*", "/VAADIN"}, asyncSupported = true, initParams = {
			@WebInitParam(name = "closeIdleSessions", value = "false"),
			@WebInitParam(name = "org.atmosphere.container.JSR356AsyncSupport.mappingPath", value = "/mprdemo"),
			@WebInitParam(name = "timeout", value = "-1")
	})
	public static class Servlet extends VaadinServlet {
	}

	@SuppressWarnings("deprecation")
	public MyUI() {
		H3 flowLabel = new H3("Flow H3");
		add(pushLayout);
		add(flowLabel);
		legacyComponent = new LegacyView();
		add(new LegacyWrapper(legacyComponent));
	}

	@Override
	public void onAttach(AttachEvent event) {
		FeederThread thread = new FeederThread(event.getUI(), this, legacyComponent);
		thread.start();
	}

	private static class FeederThread extends Thread {
		private final UI ui;
		private final VerticalLayout view;
		private LegacyView legacyView;
		private int count = 0;

		public FeederThread(UI ui, VerticalLayout view, LegacyView legacyView) {
			this.ui = ui;
			this.view = view;
			this.legacyView = legacyView;
		}

		@Override
		public void run() {
			try {
				while (count < 10) {
					Thread.sleep(2000);
					String message = "This is update " + count++;
					ui.access(() -> view.add(new Span(message)));
					ui.access(() -> legacyView.setValue(count));
					System.out.println(message);
				}
				ui.access(() -> {
					view.add(new Span("Done updating"));
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
