package pgu.widget.careers.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
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

        log("has container: " + hasContainer());

        if (hasContainer()) {
            sendNotificationToContainer("");
            sendHistoryTokenToContainer(TOKEN_CAREERS);

        } else {
            showCareersView();
        }

        //        sendHistoryTokenToContainer("careers");

        History.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(final ValueChangeEvent<String> event) {
                final String token = event.getValue();
                log("history: " + token);

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

        $wnd.console.log('careers');
        $wnd.console.log(e);

          if (e.origin === 'http://localhost:8080' //
              || e.origin === 'http://127.0.0.1:8888') {

              var msg = JSON.parse(e.data);

//              if (msg.action === 'update_menu') {

               if (msg.type === 'history') {

                    var token = msg.token;
                    activity.@pgu.widget.careers.client.Pgu_test_widget_careers::show(Ljava/lang/String;)(token);


//                var nb = view.@pgu.widget.careers.client.CareersWelcome::getNbCareers()();
//
//                var response = {};
//                response.id = 'careers';
//                response.count = nb;
//
//                var msg_back = JSON.stringify(response);
//                $wnd.console.log(msg_back);
//
//                e.source.parent.postMessage(msg_back, e.origin);

              } else {
                $wnd.console.log('Unsupported action ' + msg.action);

              }

          } else {
                $wnd.console.log('Unsupported origin');

          }
    }

}-*/;

    public native void sendNotificationToContainer(String nb) /*-{

        var title = '';
        if (nb === null || nb.trim() === '') {
            title = 'Careers';
        } else {
            title = 'Careers (' + nb + ')';
        }

        var notification = {};
        notification.type = 'title';
        notification.id = 'careers';
        notification.title = title;

        var msg_back = JSON.stringify(notification);
        $wnd.console.log(msg_back);

        $wnd.console.log($wnd.parent);

        if ($wnd.parent //
                && $wnd !== $wnd.parent) {

            $wnd.parent.postMessage(msg_back, 'http://localhost:8080');
            $wnd.parent.postMessage(msg_back, 'http://127.0.0.1:8888');
        }

    }-*/;

    public void show(final String token) {
        log("show: " + token);

        if (TOKEN_CAREERS.equals(token) //
                || "".equals(token)) {

            showCareersView();

        } else {
            throw new UnsupportedOperationException("Unknown token " + token);
        }

    }

}
