/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package ha.animatejsf.component.animate;

import ha.animatejsf.component.animate.Animate;

import java.io.IOException;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;

/**
 * 
 */
@JSFRenderer(renderKitId = "HTML_BASIC", family = "javax.faces.Output", type = "ha.animatejsf.AnimateRenderer")
@ResourceDependencies({
		@ResourceDependency(library = "animatejsf", name = "animate.css"),
		@ResourceDependency(library = "animatejsf", name = "animatejsf.js") })
public class AnimateRenderer extends Renderer {

	@Override
	public void decode(FacesContext facesContext, UIComponent uiComponent) {
		super.decode(facesContext, uiComponent);
		// nothing to decode
	}

	@Override
	public void encodeBegin(FacesContext facesContext, UIComponent uiComponent)
			throws IOException {
		super.encodeBegin(facesContext, uiComponent);
		// no need to use encodeBegin.

	}

	@Override
	public void encodeChildren(FacesContext facesContext,
			UIComponent uiComponent) throws IOException {
		super.encodeChildren(facesContext, uiComponent);
		// this component does not have children
	}

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent uiComponent)
			throws IOException {
		super.encodeEnd(facesContext, uiComponent);

		if (!uiComponent.isRendered()) {
			return;
		}
		ResponseWriter writer = facesContext.getResponseWriter();

		Animate animate = (Animate) uiComponent;
		String type = animate.getType();		
		String target = animate.getTarget();
		UIComponent targetUiComponent = facesContext.getViewRoot().findComponent(target);
		if(targetUiComponent != null) {
			writer.write("<script>animatejsf('" + targetUiComponent.getClientId()
					+ "','" + type + "')</script>");
		} else {
			throw new IllegalArgumentException("Target Component: " + target + "can not be found");
		}
		
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

}
