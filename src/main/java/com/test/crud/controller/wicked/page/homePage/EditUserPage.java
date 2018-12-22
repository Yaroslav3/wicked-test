package com.test.crud.controller.wicked.page.homePage;

import com.test.crud.model.User;
import com.test.crud.service.impl.UserServiceImpl;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EditUserPage extends WebPage {


    /**
     * method edit user
     * ***/

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SpringBean
    private UserServiceImpl userService;


    public EditUserPage() {
        setDefaultModel(new Model<User>(new User()));
    }

    public EditUserPage(Long locationId) {

        User user = userService.findByIdUser(locationId);
        setDefaultModel(new Model<User>(user));
        initGui(user);

    }


    private void initGui(User user) {
        Form<User> addLocationForm = new Form<User>("form-add-user",
                new CompoundPropertyModel<User>((IModel<User>) getDefaultModel()));
        add(addLocationForm);

        TextField<String> name = new TextField<>("name", new PropertyModel<String>(user, "name"));
        name.setOutputMarkupId(true);
        TextField<String> surname = new TextField<>("surname", new PropertyModel<String>(user, "surname"));
        surname.setOutputMarkupId(true);
        TextField<String> age = new TextField<>("age", new PropertyModel<String>(user, "age"));
        age.setOutputMarkupId(true);

        AjaxButton ajaxButton = new AjaxButton("submit-update") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);

                userService.update(User.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .age(user.getAge()).build());
                setResponsePage(HomePage.class);
            }

        };
        AjaxButton ajaxButton2 = new AjaxButton("submit-exit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                setResponsePage(HomePage.class);
            }

        };

        addLocationForm.add(name);
        addLocationForm.add(surname);
        addLocationForm.add(age);
        addLocationForm.add(ajaxButton);
        addLocationForm.add(ajaxButton2);
    }
}
