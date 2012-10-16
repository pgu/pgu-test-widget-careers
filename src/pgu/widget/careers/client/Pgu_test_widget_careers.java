package pgu.widget.careers.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.RootPanel;

public class Pgu_test_widget_careers implements EntryPoint {

    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    @Override
    public void onModuleLoad() {
        final CareersWelcome welcome = new CareersWelcome();
        RootPanel.get().add(welcome);

        listenToMessage(functionToApplyOnContainerAction(welcome));

        welcome.setPresenter(this);
    }

    private native void listenToMessage(JavaScriptObject fn_to_apply) /*-{
        $wnd.addEventListener('message', fn_to_apply, false);
    }-*/;

    private native JavaScriptObject functionToApplyOnContainerAction(CareersWelcome view) /*-{

    return function receiver(e) {

        $wnd.console.log('careers');
        $wnd.console.log(e);

          if (e.origin === 'http://localhost:8080') {
              var msg = JSON.parse(e.data);

              if (msg.action === 'update_menu') {

                var nb = view.@pgu.widget.careers.client.CareersWelcome::getNbCareers()();

                var response = {};
                response.id = 'careers';
                response.count = nb;

                var msg_back = JSON.stringify(response);
                $wnd.console.log(msg_back);

                e.source.parent.postMessage(msg_back, e.origin);

              } else {
                $wnd.console.log('Unsupported action ' + msg.action);

              }

          } else {
                $wnd.console.log('Unsupported origin');

          }
    }

}-*/;

    public native void sendNotificationToContainer(String nb) /*-{
        var notification = {};
        notification.type = 'notif';
        notification.id = 'careers';
        notification.count = nb;

        var msg_back = JSON.stringify(notification);
        $wnd.console.log(msg_back);

        $wnd.console.log($wnd.parent);

        if ($wnd.parent //
                && $wnd !== $wnd.parent) {

            $wnd.parent.postMessage(msg_back, 'http://localhost:8080');
        }

    }-*/;

}
