package com.test.crud.controller.wicked.init;

import com.test.crud.controller.wicked.page.homePage.HomePage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class MyWicketApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
        super.init();
    }
}
