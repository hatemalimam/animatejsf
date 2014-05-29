package org.animatejsf.behavior;

import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorBase;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.context.FacesContext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFBehavior;
import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFProperty;

@ResourceDependencies({
		@ResourceDependency(library = "animatejsf", name = "animate.css"),
		@ResourceDependency(library = "animatejsf", name = "animatejsf.js") })
public class AnimateBehavior extends ClientBehaviorBase {
	
	public static final String BEHAVIOR_ID = "org.animatejsf.behavior.AnimateBehavior";
	private String type;
	private String target;
	 
	/**
     * The type of animation
     * 
     */
    @JSFProperty
    public String getType() {
    	return type;
    }

	public void setType(String type) {
		this.type = type;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	/**
     * JSF component to run animation on
     * 
     */
    @JSFProperty
    public String getTarget() {
    	return target;
    }
    
	@Override
	public String getScript(ClientBehaviorContext behaviorContext) {
		FacesContext facesContext = behaviorContext.getFacesContext();				
		if (getType() != null) {
			UIComponent targetUiComponent = facesContext.getViewRoot().findComponent(getTarget());
			return "animatejsf('" + targetUiComponent.getClientId()
					+ "','" + getType() + "')";
		} else
			throw new FacesException("Cannot find target component");
		
	}
}
