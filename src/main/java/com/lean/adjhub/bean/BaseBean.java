package com.lean.adjhub.bean;

import java.io.Serializable;
import java.util.List;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class BaseBean implements Serializable {

    public BaseBean() {
        super();
    }

    public void addComponent(String name, List<UIComponent> list) {
        if (list != null && list.size() > 0) {
            findComponent(name).getChildren().addAll(list);
        }
    }

    public void addComponent(String name, UIComponent list) {
        findComponent(name).getChildren().add(list);
    }

    public static ValueExpression createValueExpression(String valueExpression, Class<?> valueType) {
        FacesContext facesContext = getFacesContext();
        return facesContext.getApplication().getExpressionFactory().createValueExpression(facesContext.getELContext(),
                valueExpression, valueType);
    }

    public static MethodExpression createActionExpression(String actionExpression, Class<?> returnType) {
        FacesContext facesContext = getFacesContext();
        return facesContext.getApplication().getExpressionFactory().createMethodExpression(facesContext.getELContext(),
                actionExpression, returnType, new Class[0]);
    }

    public static MethodExpression createActionExpression(String actionExpression, Class<?> returnType,
            Class<?>[] expectedParamTypes) {
        FacesContext facesContext = getFacesContext();
        return facesContext.getApplication().getExpressionFactory().createMethodExpression(facesContext.getELContext(),
                actionExpression, returnType, expectedParamTypes);
    }

    public static FacesContext getFacesContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context;
    }

    public static UIViewRoot getUIViewRoot() {
        FacesContext context = getFacesContext();
        UIViewRoot viewRoot = context.getViewRoot();
        return viewRoot;
    }

    public static UIComponent findComponent(String name) {
        UIComponent findComponent = getUIViewRoot().findComponent(name);
        return findComponent;
    }
}