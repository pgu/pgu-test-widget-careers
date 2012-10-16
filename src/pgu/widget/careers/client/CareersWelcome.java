package pgu.widget.careers.client;

import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CareersWelcome extends Composite {

    private static CareersWelcomeUiBinder uiBinder = GWT.create(CareersWelcomeUiBinder.class);

    interface CareersWelcomeUiBinder extends UiBinder<Widget, CareersWelcome> {
    }

    @UiField
    TextBox nbCareersBox;

    public CareersWelcome() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public String getNbCareers() {
        return nbCareersBox.getText();
    }

}
