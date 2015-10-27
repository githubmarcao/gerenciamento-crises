/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.web.jsf.util;

import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marco
 */
public class JSFUtil {

    public static ExternalContext getExternalContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext();
    }

    public static String getRequestParameter(String parameterName) {
        Map paramMap = getExternalContext().getRequestParameterMap();
        return (String) paramMap.get(parameterName);
    }

    public static Object getRequestAttribute(String attributeName) {
        Map attrMap = getExternalContext().getRequestMap();
        return attrMap.get(attributeName);
    }

    public static Object getSessionAttribute(String attributeName) {
        Map attrMap = getExternalContext().getSessionMap();
        return attrMap.get(attributeName);
    }

    public static void setSessionAttribute(String attributeName, Object attribute) {
        Map attrMap = getExternalContext().getSessionMap();
        attrMap.put(attributeName, attribute);
    }

    public static Object getApplicationAttribute(String attributeName) {
        Map attrMap = getExternalContext().getApplicationMap();
        return attrMap.get(attributeName);
    }

    public static void addGlobalMessage(String message) {
        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Get servlet context.
     *
     * @return the servlet context
     */
    public static ServletContext getServletContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    /**
     * Get managed bean based on the bean name.
     *
     * @param beanName the bean name
     * @return the managed bean associated with the bean name
     */
    public static Object getManagedBean(String beanName) {
        Object o =
                getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());

        return o;
    }

    /**
     * Remove the managed bean based on the bean name.
     *
     * @param beanName the bean name of the managed bean to be removed
     */
    public static void resetManagedBean(String beanName) {
        getValueBinding(getJsfEl(beanName)).setValue(FacesContext.getCurrentInstance(),
                null);
    }

    /**
     * Store the managed bean inside the session scope.
     *
     * @param beanName the name of the managed bean to be stored
     * @param managedBean the managed bean to be stored
     */
    @SuppressWarnings("unchecked")
    public static void setManagedBeanInSession(String beanName,
            Object managedBean) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(beanName, managedBean);
    }

    /**
     * Get parameter value from request scope.
     *
     * @param name the name of the parameter
     * @return the parameter value
     */
    public static Map getRequestParameters() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }

    /**
     * Add information message.
     *
     * @param msg the information message
     */
    public static void addInfoMessage(String msg) {
        addInfoMessage(null, msg);
    }

    /**
     * Add information message to a sepcific client.
     *
     * @param clientId the client id
     * @param msg the information message
     */
    public static void addInfoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                msg, msg));
    }

    /**
     * Add error message.
     *
     * @param msg the error message
     */
    public static void addErrorMessage(String msg) {
        addErrorMessage(null, msg);
    }

    /**
     * Add error message to a sepcific client.
     *
     * @param clientId the client id
     * @param msg the error message
     */
    public static void addErrorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msg, msg));
    }

    /**
     * Evaluate the integer value of a JSF expression.
     *
     * @param el the JSF expression
     * @return the integer value associated with the JSF expression
     */
    public static Integer evalInt(String el) {
        if (el == null) {
            return null;
        }

        if (UIComponentTag.isValueReference(el)) {
            Object value = getElValue(el);

            if (value == null) {
                return null;
            } else if (value instanceof Integer) {
                return (Integer) value;
            } else {
                return new Integer(value.toString());
            }
        } else {
            return new Integer(el);
        }
    }

    private static Application getApplication() {
        ApplicationFactory appFactory =
                (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);

        return appFactory.getApplication();
    }

    private static ValueBinding getValueBinding(String el) {
        return getApplication().createValueBinding(el);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    private static Object getElValue(String el) {
        return getValueBinding(el).getValue(FacesContext.getCurrentInstance());
    }

    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }

    public static ResourceBundle getBundle(String resourceBundleID) {

        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getResourceBundle(context, resourceBundleID);

    }

    public static String getMessage(String key) {        
        return getMessage(key,"msg");
    }
    
    public static String getMessage(String key, String resourceBundleID) {

        String result = null;
        try {
            result = getBundle(resourceBundleID).getString(key);
        } catch (MissingResourceException e) {
            result = "???" + key + "??? not found";
        }
        return result;
    }
}
