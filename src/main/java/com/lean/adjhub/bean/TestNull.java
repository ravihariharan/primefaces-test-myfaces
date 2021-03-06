package com.lean.adjhub.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;

import com.lean.adjhub.common.constants.PropertyConstants;

@ManagedBean
@ViewScoped
public class TestNull extends BaseBean {

    private Boolean testNullRender;

    public TestNull() {
        super();
    }

    @PostConstruct
    public void postConstruct() {
        //HtmlPanelGroup panel = new HtmlPanelGroup();

        HtmlPanelGroup panel = (HtmlPanelGroup) FacesContext.getCurrentInstance().getApplication()
                .createComponent(HtmlPanelGroup.COMPONENT_TYPE);

        String valueExpression = "#{testNull.testNullRender}";
        panel.setValueExpression(PropertyConstants.RENDERED, createValueExpression(valueExpression, Boolean.class));

        addComponent("form:panelId", panel);
    }

    public Boolean getTestNullRender() {
        return testNullRender;
    }

    public void setTestNullRender(Boolean testNullRender) {
        this.testNullRender = testNullRender;
    }

}
