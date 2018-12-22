package com.test.crud.controller.wicked.page.homePage;


import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WicketHomePage
public class HomePage extends WebPage {

    /**
     * main start view
     * ***/
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ModalWindow modalWindow;


    public HomePage(PageParameters pageParameters) {
        super(pageParameters);

        add(new DataTable("table"));


        /**
         * show modal window add user
         * */
        modalWindow = new ModalWindow("modalWindow");
        modalWindow.setPageCreator(new ModalWindow.PageCreator() {

            @Override
            public Page createPage() {
                return new AddUserModalWindow();
            }
        });


        modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
            @Override
            public void onClose(AjaxRequestTarget target) {

                setResponsePage(HomePage.class);
            }
        });


        add(new AjaxLink<String>("viewLink") {

            @Override
            public void onClick(AjaxRequestTarget target) {

                modalWindow.show(target);
            }
        });
        add(modalWindow);

    }
}
