package com.test.crud.controller.wicked.page.homePage;

import com.test.crud.model.User;
import com.test.crud.service.impl.UserServiceImpl;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EmptyDataTable extends WebPage {

    /****
     *  method work if data list ( "class DataTable") is empty
     * ***/

    @SpringBean
    private UserServiceImpl userService;

    public EmptyDataTable() {
        super();

        User user = new User();


        Form<?> form = new Form("form-add-user");

        TextField<String> name = new TextField<>("name", new PropertyModel<String>(user, "name"));
        name.setOutputMarkupId(true);

        TextField<String> surname = new TextField<>("surname", new PropertyModel<String>(user, "surname"));
        surname.setOutputMarkupId(true);

        TextField<String> age = new TextField<>("age", new PropertyModel<String>(user, "age"));
        age.setOutputMarkupId(true);


        AjaxButton ajaxButton = new AjaxButton("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);

                userService.save(User.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .age(user.getAge()).build());
                setResponsePage(HomePage.class);
            }

        };

        add(form);

        form.add(name);
        form.add(surname);
        form.add(age);
        form.add(ajaxButton);
    }
}
