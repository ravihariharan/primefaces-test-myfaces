package com.lean.adjhub.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.view.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.password.Password;
import org.primefaces.component.spacer.Spacer;

import com.lean.adjhub.common.constants.PropertyConstants;
import com.lean.adjhub.model.LoginModel;

@ManagedBean
@ViewScoped
public class Login extends BaseBean {

    private LoginModel model = new LoginModel();

    public Login() {
        super();
        System.out.println("Login Called");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Login postConstruct");
        List<UIComponent> list = new ArrayList<UIComponent>();

        list.addAll(getEditBox("company", "Company"));
        list.addAll(getEditBox("group", "Group"));
        list.addAll(getEditBox("user", "User"));
        list.addAll(getPasswordBox("password", "Password"));
        
        CommandButton buttonLogin = getButton("loginGeneral", "Login", "loginGeneral(false)", false);
        buttonLogin.setStyleClass("button primary");
        list.add(buttonLogin);
        /* 
        CommandButton buttonForce = getButton("loginForce", "Login Force", "loginGeneral(true)", true);
        buttonForce.setStyleClass("button secondary");
        list.add(buttonForce);
        list.add(new Spacer());*/
        list.add(new Spacer());

        addComponent("form:addComponenetGrid", list);
    }

    private CommandButton getButton(String name, String caption, String action, boolean forece) {

        String managedBeanName = "login";
        String modelBeanName = "model";

        CommandButton commandButton = new CommandButton();
        commandButton.setId(name);
        commandButton.setValue(caption);
        commandButton
                .setActionExpression(createActionExpression("#{" + managedBeanName + "." + action + "}", String.class));

        if (forece) {
            String valueExpression = "#{" + managedBeanName + "." + modelBeanName + "." + "loginForceRender" + "}";
            commandButton.setValueExpression(PropertyConstants.RENDERED,
                    createValueExpression(valueExpression, Boolean.class));
        }

        commandButton.setAjax(false);

        return commandButton;
    }

    private List<UIComponent> getEditBox(String name, String caption) {

        final String managedBeanName = "login";
        final String modelBeanName = "model";
        List<UIComponent> searchList = new ArrayList<UIComponent>();

        OutputLabel label = new OutputLabel();
        label.setId("lbl" + name);
        label.setValue(caption);
        label.setStyleClass("labelstyle");
        searchList.add(label);

        InputText text = new InputText();
        text.setId(name);
        text.setAutocomplete("off");
        String valueExpression = "#{" + managedBeanName + "." + modelBeanName + "." + name + "}";
        text.setValueExpression(PropertyConstants.VALUE, createValueExpression(valueExpression, String.class));
        text.setStyleClass("inputText");
        text.setRequired(true);
        Message message = new Message();
        message.setFor(text.getId());

        searchList.add(text);

        searchList.add(message);

        searchList.add(new Spacer());

        return searchList;
    }

    private List<UIComponent> getPasswordBox(String name, String caption) {

        final String managedBeanName = "login";
        final String modelBeanName = "model";
        List<UIComponent> searchList = new ArrayList<UIComponent>();

        OutputLabel label = new OutputLabel();
        label.setId("lbl" + name);
        label.setValue(caption);
        label.setStyleClass("labelstyle");
        searchList.add(label);

        Password text = new Password();
        text.setId(name);
        text.setAutocomplete("off");
        text.setRedisplay(true);
        String valueExpression = "#{" + managedBeanName + "." + modelBeanName + "." + name + "}";
        text.setValueExpression(PropertyConstants.VALUE, createValueExpression(valueExpression, String.class));
        text.setStyleClass("inputText");
        text.setRequired(true);

        Message message = new Message();
        message.setFor(text.getId());

        searchList.add(text);

        searchList.add(message);

        searchList.add(new Spacer());

        return searchList;
    }

    public LoginModel getModel() {
        return model;
    }

    public void setModel(LoginModel model) {
        this.model = model;
    }

    private String msgText;

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public Boolean getMessageRender() {
        return (msgText != null && msgText.length() > 0);
    }

}
