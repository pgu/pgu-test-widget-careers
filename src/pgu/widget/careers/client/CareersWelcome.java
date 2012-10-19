package pgu.widget.careers.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CareersWelcome extends Composite {

    private static CareersWelcomeUiBinder uiBinder = GWT.create(CareersWelcomeUiBinder.class);

    interface CareersWelcomeUiBinder extends UiBinder<Widget, CareersWelcome> {
    }

    @UiField
    Button sendBtn;
    @UiField
    TextBox nbProjectsBox;

    public CareersWelcome() {
        initWidget(uiBinder.createAndBindUi(this));
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

}
