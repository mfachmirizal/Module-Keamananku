/*
* M Fachmi Rizal @ Tripad
*/

package com.tripad.tetanggaku.keamananku.service.images;

import com.tripad.tetanggaku.keamananku.data.TtkDataimage;
import com.tripad.tetanggaku.keamananku.util.ModuleUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import javax.servlet.ServletException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.openbravo.service.web.WebService;
import org.openbravo.base.exception.OBException;
import org.apache.log4j.Logger;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.service.web.BaseWebServiceServlet;
import java.io.ByteArrayOutputStream;
/**
 * Implementation of UploadImage webservice
 *
 * @author M Fachmi Rizal
 */

//TODO: ganti ke NIO
public class UploadImage extends BaseWebServiceServlet  implements WebService  {
    
    private static final long serialVersionUID = 1L;
    private static Logger log=Logger.getLogger(UploadImage.class);
    String pathModuleInServer = "";
    String pathModuleInServerTemp = "";
    String pathDataImages= "";
    String pathDataImagesTemp = "";
    private TtkDataimage tdi;
    private String device_id = "";
    
    
    public void doGet(String path, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    }
    
    public void doDelete(String path, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    }
    
    public void doPost(String path, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //
        
        
        String responseMsg = "";
        InputStream fileContent = null;
        OutputStream outputStream = null;
        ByteArrayOutputStream buffer = null;
        
        String obAppName = request.getSession().getServletContext().getContextPath();

        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    
                    switch(fieldName) {
                        case "device_id" : 
                            device_id = fieldValue;
                        break;
                    }
//                    if () {
//                        device_id = fieldValue
//                    }
                } else {
                    // Process form file field (input type="file").
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    
                    if (fieldName.equals("gambar")) {
                        fileContent = item.getInputStream();
                        
                       // String fileString = item.getString(); //tanda
                    
                      
                        Path filepath = Paths.get((ModuleUtils.ROOT_DATA_IMAGES+File.separator)+fileName);

                        try ( OutputStream out = Files.newOutputStream(filepath)) {
                        int read = 0;
                        byte[] bytes = new byte[1024];

                        while ((read = fileContent.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                            //out.write(fileContent);
                        }
                            

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
    /* deprecated                    
                        outputStream =
                                new FileOutputStream(new File((ModuleUtils.ROOT_DATA_IMAGES+File.separator)+fileName));

                        int read = 0;
                        byte[] bytes = new byte[1024];

                        while ((read = fileContent.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read);
                        }
   */
                    }
                    tdi = OBProvider.getInstance().get(TtkDataimage.class);
                    try {
                        tdi.setActive(true);
                        //edit client dan org, jadi berdasarkan nik pengirim
                        tdi.setOrganization(OBContext.getOBContext().getCurrentOrganization());
                        tdi.setClient(OBContext.getOBContext().getCurrentClient());
                        tdi.setAndroidDevice(device_id);
                        tdi.setImageLink("link-"+fileName);
                        tdi.setImageName(fileName);

                        OBDal.getInstance().save(tdi);
                        OBDal.getInstance().flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ServletException("Error Save Database : "+ e.getMessage());
                    }
                    OBDal.getInstance().commitAndClose();     
                    
                    responseMsg = ("Upload Gambar Berhasil ! ("+fileName+")");
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ServletException("Error : "+ex.getMessage());
        } finally {
            if (fileContent != null) {
                try {
                    fileContent.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
        }
        
        try {
            final Writer w = response.getWriter();
            w.write(pathModuleInServer);
            w.close();
        } catch (final Exception e) {
            throw new OBException(e);
        }
        
        // NOTE: as transaction handling is automatic the updated objects are updated automatically
        // in the db at the end of the request, this may fail as the new objects are not inserted in
        // the db, therefore rolling back, this is just for demo.
        // OBDal.getInstance().commitAndClose();
    }
    
    public void doPut(String path, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    }
    
    public String getServerPath() throws NullPointerException{
        String result ="";
        //result = this.globalParameters.prefix + "web/images";
       
        return result;
    }
   
}
