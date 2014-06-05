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
package org.animatejsf.component.animate;

import java.io.IOException;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.animatejsf.component.animate.Animate;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFRenderer;

/**
 * 
 */
@JSFRenderer(renderKitId = "HTML_BASIC", family = "javax.faces.Output", type = "org.animatejsf.AnimateRenderer")
@ResourceDependencies({
		@ResourceDependency(library = "animatejsf", name = "animate.css"),
		@ResourceDependency(library = "animatejsf", name = "animatejsf.js") })
public class AnimateRenderer extends Renderer {

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
			writer.startElement("script", animate);
			writer.writeAttribute("id", animate.getClientId(), null);
			writer.writeAttribute("type", "text/javascript", null);
			writer.write("animatejsf('" + targetUiComponent.getClientId()
					+ "','" + type + "')");
			writer.endElement("script");
		} else {
			throw new IllegalArgumentException("Target Component: " + target + "can not be found");
		}
		
	}
}
