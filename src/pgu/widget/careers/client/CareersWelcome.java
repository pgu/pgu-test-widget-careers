package pgu.widget.careers.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CareersWelcome extends Composite {

    private static CareersWelcomeUiBinder uiBinder = GWT.create(CareersWelcomeUiBinder.class);

    interface CareersWelcomeUiBinder extends UiBinder<Widget, CareersWelcome> {
    }

    @UiField
    Button sendBtn;
    @UiField
    TextBox nbProjectsBox;
    @UiField
    HTMLPanel chatRow;
    @UiField
    Button chat1Btn, chat2Btn;
    @UiField
    DisclosurePanel chatDisclosure;

    public CareersWelcome() {
        initWidget(uiBinder.createAndBindUi(this));

        chatDisclosure.setHeader(new HTML("<b>Chat</b>"));
        chatRow.setVisible(false);
        chat1Btn.setVisible(false);
        chat2Btn.setVisible(false);
    }

    public String getNbProjects() {
        return nbProjectsBox.getText();
    }

    @UiHandler("sendBtn")
    public void clickSend(final ClickEvent e) {
        pgu_test_widget_careers.sendTitleToContainer(getNbProjects());
    }

    private Pgu_test_widget_careers pgu_test_widget_careers;

    public void setPresenter(final Pgu_test_widget_careers pgu_test_widget_careers) {
        this.pgu_test_widget_careers = pgu_test_widget_careers;
    }

    public void start() {
        pgu_test_widget_careers.sendTitleToContainer(getNbProjects());
    }

    @UiHandler("chat1Btn")
    public void clickChat1(final ClickEvent e) {
        pgu_test_widget_careers.sendChatToContainer(chat1Btn.getText());
    }

    @UiHandler("chat2Btn")
    public void clickChat2(final ClickEvent e) {
        pgu_test_widget_careers.sendChatToContainer(chat2Btn.getText());
    }

    public void showChat1Btn() {
        chatRow.setVisible(true);
        chat1Btn.setVisible(true);
        chat2Btn.setVisible(false);
    }

    public void showChat2Btn() {
        chatRow.setVisible(true);
        chat2Btn.setVisible(true);
    }

    public void resetChat() {
        chatRow.setVisible(false);
        chat1Btn.setVisible(false);
        chat2Btn.setVisible(false);
    }

}
