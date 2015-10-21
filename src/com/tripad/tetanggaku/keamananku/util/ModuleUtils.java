/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tripad.tetanggaku.keamananku.util;

import java.io.File;
import org.openbravo.base.session.OBPropertiesProvider;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.HttpBaseServlet;
import org.openbravo.dal.core.OBContext;
import org.openbravo.client.kernel.RequestContext;
/**
 *
 * @author mfachmirizal
 */
public class ModuleUtils {
    
    
    public static final String ROOT_PACKAGE ="com.tripad.tetanggaku.keamananku";
    public static final String CATALINA_DIR = System.getProperty("catalina.base");
    //public static String DEFAULT_ROOT_PATH = System.getProperty("catalina.base");
    
    public static final String ROOT_PATH = OBPropertiesProvider.getInstance().getOpenbravoProperties()
        .getProperty("source.path", null);
    
    public static final String APP_CONTEXT = OBPropertiesProvider.getInstance().getOpenbravoProperties()
        .getProperty("context.name", null);
    
    public static final String ROOT_DATA_IMAGES = ROOT_PATH + File.separator +"modules"+File.separator+ROOT_PACKAGE+File.separator+"dataimages"; 
    
    public static final String WEB_SERVER_DATA_IMAGES = "web"+File.separator+ROOT_PACKAGE+File.separator+"dataimages"; 
    
//    HttpServletRequest request = RequestContext.get().getRequest();
//    
//    public final String WEB_SERVER_PATH = request.getSession().getServletContext().getRealPath("/");
}
