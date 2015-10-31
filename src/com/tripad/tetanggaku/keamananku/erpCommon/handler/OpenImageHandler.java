package com.tripad.tetanggaku.keamananku.erpCommon.handler;

import com.tripad.tetanggaku.keamananku.service.images.UploadImage;
import com.tripad.tetanggaku.keamananku.util.ImageUtils;
import com.tripad.tetanggaku.keamananku.util.ModuleUtils;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.base.exception.OBException;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.order.Order;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.openbravo.client.kernel.RequestContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import org.openbravo.base.HttpBaseServlet;
import org.openbravo.dal.core.OBContext;
import org.openbravo.utils.FileUtility;

public class OpenImageHandler extends BaseActionHandler {
    private static Logger log=Logger.getLogger(OpenImageHandler.class);
    @Override
    protected JSONObject execute(Map<String, Object> parameters, String data) {
        try {
            final JSONObject jsonData = new JSONObject(data);
            final JSONArray imageNames = jsonData.getJSONArray("imgname");
            //helper 
            ImageUtils iu = new ImageUtils();
            String hasil = ""; 
            
            final String imageName = imageNames.getString(0);
            
            //dapatkanPath Tomcat
            HttpServletRequest request = RequestContext.get().getRequest();
            String webserverDataImages = request.getSession().getServletContext().getRealPath("/")+ModuleUtils.WEB_SERVER_DATA_IMAGES;
            
            try {
                if (iu.transferImageTo(imageName,webserverDataImages)) {
                    //okay
                    //dapatkan link image
//                    commit dan update dari laptop, btw mendapatkan link untuk gambar belum sempurna, jangan ada port
                    try {
                        hasil = iu.getImageLink(request,imageName);
                    } catch(Throwable t){
                        log.error(t.getMessage());
                        log.trace(t);
                    }
                    
                    log.debug("masuk request link !");
                }
            }catch(NoSuchFileException nsf){
                //ganti dengan link ke error.html
                log.trace(nsf);
                log.error("Error : "+nsf.getMessage());
            }
            catch(IOException iex) {
                //ganti dengan link ke error.html
                log.trace(iex);
                log.error("Error : "+iex.getMessage());
            }
            finally {
                iu = null;
            }
            
            JSONObject result = new JSONObject();
            result.put("handlerresult", hasil);
            
            return result;
        } catch (Exception e) {
            throw new OBException(e);
        }
    }
    
}
