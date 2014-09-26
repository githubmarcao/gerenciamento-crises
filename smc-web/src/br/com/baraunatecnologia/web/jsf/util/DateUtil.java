/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.baraunatecnologia.web.jsf.util;

import java.util.MissingResourceException;

/**
 * @author Marco
 */
public class DateUtil {

    public static String getMessage(String key, String resourceBundleID) {

        String result = null;
        try {
            //result = getBundle(resourceBundleID).getString(key);
        } catch (MissingResourceException e) {
            result = "???" + key + "??? not found";
        }
        return result;
    }
}
