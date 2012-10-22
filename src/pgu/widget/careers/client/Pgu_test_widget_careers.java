package pgu.widget.careers.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Pgu_test_widget_careers implements EntryPoint {

    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    final SimplePanel                              simplePanel     = new SimplePanel();

    private static final String TOKEN_CAREERS = "careers";

    private CareersWelcome welcome ;

    private native void log(String msg) /*-{
        $wnd.console.log("careers: " + msg);
    }-*/;

    @Override
    public void onModuleLoad() {


        welcome = new CareersWelcome();
        welcome.setPresenter(this);

        RootPanel.get().add(simplePanel);

        final String href = Window.Location.getHref();
        log("href: " + href);

        if (href.contains("#")) {

            final String[] parts = href.split("#");
            if (parts.length == 2) {

                final String place = parts[1];
                show(place);

            } else {
                showCareersView();
            }
        } else {
            showCareersView();
        }

        if (hasContainer()) {

            //            sendTitleToContainer("");
            //            sendHistoryTokenToContainer(TOKEN_CAREERS);

        } else {
        }
        //        sendHistoryTokenToContainer("careers");


        History.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(final ValueChangeEvent<String> event) {
                final String token = event.getValue();
                log("history: [" + token + "]");

                if (TOKEN_CAREERS.equals(token) //
                        || "".equals(token)) {

                    if (hasContainer()) {
                        sendHistoryTokenToContainer(token);

                    } else {
                        showCareersView();
                    }

                } else {
                    throw new UnsupportedOperationException("Unknown token " + token);
                }

            }

        });

        listenToMessage(functionToApplyOnContainerAction(this));

    }

    private void showCareersView() {
        simplePanel.setWidget(welcome);
        welcome.start();
    }

    private native boolean hasContainer() /*-{
        return $wnd.parent //
                && $wnd !== $wnd.parent;
    }-*/;

    private native void sendHistoryTokenToContainer(final String token)  /*-{

        var notification = {};
        notification.type = 'history';
        notification.id = 'careers';
        notification.token = token;

        var msg_back = JSON.stringify(notification);
        $wnd.console.log(msg_back);

        if ($wnd.parent //
                && $wnd !== $wnd.parent) {

            $wnd.parent.postMessage(msg_back, 'http://localhost:8080');
            $wnd.parent.postMessage(msg_back, 'http://127.0.0.1:8888');
        }

    }-*/;

    private native void listenToMessage(JavaScriptObject fn_to_apply) /*-{
        $wnd.addEventListener('message', fn_to_apply, false);
    }-*/;

    private native JavaScriptObject functionToApplyOnContainerAction(Pgu_test_widget_careers activity) /*-{

        return function receiver(e) {

            $wnd.console.log('receiving: careers: ' + e.data);

              if (e.origin === 'http://localhost:8080' //
                  || e.origin === 'http://127.0.0.1:8888') {

                  var msg = JSON.parse(e.data);

                  if (msg.type === 'history') {

                      var place = msg.place;
                      activity.@pgu.widget.careers.client.Pgu_test_widget_careers::show(Ljava/lang/String;)(place);

                  } else if (msg.type === 'chat') {

                      activity.@pgu.widget.careers.client.Pgu_test_widget_careers::showChat(Ljava/lang/String;)(msg.msg);

                  } else {
                    $wnd.console.log('Unsupported action ' + msg.action);
                  }
              } else {
                    $wnd.console.log('Unsupported origin');
              }
        }

    }-*/;

    public void showChat(final String text) {
        if ("Obi-Wan never told you what happened to your father.".equals(text)) {
            welcome.showChat1Btn();

        } else if ("No. I am your father.".equals(text)) {
            welcome.showChat2Btn();

        } else if ("reset".equals(text)) {
            welcome.resetChat();
        }
    }

    public native void sendTitleToContainer(String nb) /*-{

        var title = '';
        if (nb === null || nb.trim() === '') {
            title = 'Projects';
        } else {
            title = 'Projects (' + nb + ')';
        }

        var notification = {};
        notification.type = 'title';
        notification.id = 'careers';
        notification.code = '0';
        notification.title = title;

        var msg_back = JSON.stringify(notification);
        $wnd.console.log(msg_back);

        if ($wnd.parent //
                && $wnd !== $wnd.parent) {

            $wnd.parent.postMessage(msg_back, 'http://localhost:8080');
            $wnd.parent.postMessage(msg_back, 'http://127.0.0.1:8888');
        }

    }-*/;

    public void show(final String place) {
        log("show: " + place);

        if (TOKEN_CAREERS.equals(place) //
                || "".equals(place)) {

            showCareersView();

        } else {
            throw new UnsupportedOperationException("Unknown token " + place);
        }

    }

    public native void sendChatToContainer(final String text) /*-{

        var notification = {};
        notification.id = 'careers';
        notification.type = 'chat';
        notification.msg = text;

        var msg_back = JSON.stringify(notification);
        $wnd.console.log(msg_back);

        if ($wnd.parent //
                && $wnd !== $wnd.parent) {

            $wnd.parent.postMessage(msg_back, 'http://localhost:8080');
            $wnd.parent.postMessage(msg_back, 'http://127.0.0.1:8888');
        }

    }-*/;


}
