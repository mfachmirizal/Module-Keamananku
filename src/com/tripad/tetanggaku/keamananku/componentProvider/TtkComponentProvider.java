package com.tripad.tetanggaku.keamananku.componentProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.openbravo.client.kernel.BaseComponentProvider;
import org.openbravo.client.kernel.Component;
import org.openbravo.client.kernel.ComponentProvider;
import org.openbravo.client.kernel.KernelConstants;

@ApplicationScoped
@ComponentProvider.Qualifier(TtkComponentProvider.QUALIFIER)
public class TtkComponentProvider extends BaseComponentProvider {
  public static final String QUALIFIER = "TTK_COMPONENTPROVIDER";

  /*
   * (non-Javadoc)
   *
   * @see org.openbravo.client.kernel.ComponentProvider#getComponent(java.lang.String,
   * java.util.Map)
   */
  @Override
  public Component getComponent(String componentId, Map<String, Object> parameters) {
    throw new IllegalArgumentException("Component id " + componentId + " not supported.");
    /* in this howto we only need to return static resources so there is no need to return anything here */
  }

  @Override
  public List<ComponentResource> getGlobalComponentResources() {
    final List<ComponentResource> globalResources = new ArrayList<ComponentResource>();
                globalResources.add(createStaticResource("web/com.tripad.tetanggaku.keamananku/js/ttk-canvas-field.js", false));
    return globalResources;
  }

  @Override
  public List<String> getTestResources() {
    return Collections.emptyList();
  }
}
