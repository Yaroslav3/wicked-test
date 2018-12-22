package com.test.crud.controller.wicked.page.homePage;

import com.test.crud.model.User;
import com.test.crud.service.impl.UserServiceImpl;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DataTable extends Panel {

    /****
     *
     * method show data table users
     *
     * ****/
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<String> filterChoices = new ArrayList<>();

    @SpringBean
    private UserServiceImpl userService;


    public DataTable(String id) {
        super(id);


        filterChoices.add("name");
        filterChoices.add("surname");
        filterChoices.add("age");



        ListDataProvider<User> listDataProvider = null;

        List<User> all = userService.getAll();


        /**
         * if user list empty
         * **/
        if (all.isEmpty()) {
            setResponsePage(EmptyDataTable.class);


            /***
             * if user list not empty
             * **/
        } else {




            for (User elem : all) {
                listDataProvider = new ListDataProvider<User>(all);
            }


            assert listDataProvider != null;
            DataView<User> dataView = new DataView<User>("rows", listDataProvider) {

                @Override
                protected void populateItem(Item<User> item) {
                    User person = item.getModelObject();

                    final User user = item.getModelObject();
                    item.add(new Label("id", user.getId()));
                    item.add(new Label("name", user.getName()));
                    item.add(new Label("surname", user.getSurname()));
                    item.add(new Label("age", user.getAge()));

                    Link<User> editLocationLink = new Link<User>("editLocationLink") {
                        @Override
                        public void onClick() {
                            setResponsePage(new EditUserPage(item.getModelObject().getId()));
                        }
                    };
                    /***
                     * delete user in table
                     *
                     * **/

                    Link<User> deleteLocationLink = new Link<User>("deleteLocationLink") {
                        @Override
                        public void onClick() {

                            userService.delete(user.getId());
                            setResponsePage(HomePage.class);
                        }
                    };
                    item.add(editLocationLink);
                    item.add(deleteLocationLink);

                }

                ;

            };

            add(dataView);
        }
    }


}
