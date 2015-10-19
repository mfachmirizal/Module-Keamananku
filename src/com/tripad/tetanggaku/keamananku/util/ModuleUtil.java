/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tripad.tetanggaku.keamananku.util;

import java.io.File;
import org.openbravo.base.session.OBPropertiesProvider;

/**
 *
 * @author mfachmirizal
 */
public class ModuleUtil {
    public static String ROOT_PACKAGE ="com.tripad.tetanggaku.keamananku";
    public static String CATALINA_DIR = System.getProperty("catalina.base");
    //public static String DEFAULT_ROOT_PATH = System.getProperty("catalina.base");
    
    public static String ROOT_PATH = OBPropertiesProvider.getInstance().getOpenbravoProperties()
        .getProperty("source.path", null);
    
    public static String ROOT_DATA_IMAGES = ROOT_PATH + File.separator +"modules"+File.separator+ROOT_PACKAGE+File.separator+"dataimages";
    
}
