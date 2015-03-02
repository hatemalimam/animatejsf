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
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

/**
 * 
 */
@FacesRenderer(componentFamily = AnimateRenderer.COMPONENT_FAMILY, rendererType = AnimateRenderer.RENDERER_TYPE)
@ResourceDependencies({ @ResourceDependency(library = "animatejsf", name = "animate.css"),
		@ResourceDependency(library = "animatejsf", name = "animatejsf.js") })
public class AnimateRenderer extends Renderer {

	public static final String RENDERER_TYPE = "org.animatejsf.AnimateRenderer";
	public static final String COMPONENT_FAMILY = "javax.faces.Output";

	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException {
		super.encodeEnd(facesContext, uiComponent);

		if (!uiComponent.isRendered()) {
			return;
		}
		ResponseWriter writer = facesContext.getResponseWriter();

		AnimateComponent animateComponent = (AnimateComponent) uiComponent;
		String type = animateComponent.getType();
		String target = animateComponent.getTarget();
		UIComponent targetUiComponent = facesContext.getViewRoot().findComponent(target);
		if (targetUiComponent != null) {
			writer.startElement("script", animateComponent);
			writer.writeAttribute("id", animateComponent.getClientId(), null);
			writer.writeAttribute("type", "text/javascript", null);
			writer.write("animatejsf('" + targetUiComponent.getClientId() + "','" + type + "')");
			writer.endElement("script");
		} else {
			throw new IllegalArgumentException("Target Component: " + target + "can not be found");
		}

	}
}
