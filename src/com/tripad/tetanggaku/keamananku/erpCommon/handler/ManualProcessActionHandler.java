package com.tripad.tetanggaku.keamananku.erpCommon.handler;

import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.base.exception.OBException;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.order.Order;

public class ManualProcessActionHandler extends BaseActionHandler {
    
    @Override
    protected JSONObject execute(Map<String, Object> parameters, String data) {
        try {
            final JSONObject jsonData = new JSONObject(data);
            final JSONArray imageIds = jsonData.getJSONArray("images");
            final String action = jsonData.getString("action");
            String hasil = "";
            
            for (int i = 0; i < imageIds.length(); i++) {
                final String imageId = imageIds.getString(i);
                
                if ("openimage".equals(action)) {
                    hasil = "Oke, jalan.. id : "+imageId;
                }
            }
            
            JSONObject result = new JSONObject();
            result.put("handlerresult", hasil);
            
            return result;
        } catch (Exception e) {
            throw new OBException(e);
        }
    }
}
