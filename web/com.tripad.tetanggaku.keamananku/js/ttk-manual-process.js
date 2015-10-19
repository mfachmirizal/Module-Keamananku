/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2012-2015 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */

OB.TTK = OB.TTK || {};

OB.TTK.Process = {
  execute: function (params, view) {
    var i, selection = params.button.contextView.viewGrid.getSelectedRecords(),
        images = [],
        callback;

    callback = function (rpcResponse, data, rpcRequest) {
      // show result
      isc.say(OB.I18N.getLabel('Ttk_Debug_Msg', [data.handlerresult])); 

      // refresh the whole grid after executing the process
      params.button.contextView.viewGrid.refreshGrid();
    };

    for (i = 0; i < selection.length; i++) {
      images.push(selection[i][OB.Constants.ID]);
    }

    OB.RemoteCallManager.call('com.tripad.tetanggaku.keamananku.erpCommon.handler.ManualProcessActionHandler', {
      images: images,
      action: params.action
    }, {}, callback);
  },
/*
  sum: function (params, view) {
    params.action = 'sum';
    OB.TTK.Process.execute(params, view);
  },
/* */
  openimage: function (params, view) {
    params.action = 'openimage';
    OB.TTK.Process.execute(params, view);
  }
};
