package com.test.crud.controller.wicked.page.homePage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class MessageCreate extends WebPage{


    /**
     * shows alert on succes create userc
     * ***/
    public MessageCreate() {
        super();

        add(new Label("message", "user create"));
    }
}
