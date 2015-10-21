/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tripad.tetanggaku.keamananku.util;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mfachmirizal
 */
public class ImageUtils {
    
    public ImageUtils() {
    }
    
    
    /**
     * Transfer gambar dari ROOT_DATA_IMAGES ke path yg di tentukan
     * @param imagename nama image dari ROOT_DATA_IMAGES yg akan di transfer
     * @param path path tujuan
     *
     */
    public boolean transferImageTo(String imagename,String path) throws NoSuchFileException,IOException{
        boolean hasil = false;
        try {
            Path sourcePath      = Paths.get(ModuleUtils.ROOT_DATA_IMAGES+File.separator+imagename);
            Path destinationPath = Paths.get(path+File.separator+imagename);
            
            if (Files.exists(destinationPath)) {
                hasil = true;
            }
            else {
                Files.copy(sourcePath, destinationPath);
            }
        }
        catch(FileAlreadyExistsException s) {
            s.printStackTrace();
        }
        hasil = true;
        return hasil;
    }
    
    public String getImageLink(HttpServletRequest request,String namefile) throws Throwable{
        return "http://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getLocalPort()+"/"+ModuleUtils.APP_CONTEXT+"/web/"+ModuleUtils.ROOT_PACKAGE+"/dataimages/"+namefile;
    }
}
