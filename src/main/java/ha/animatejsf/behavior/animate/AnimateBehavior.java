package ha.animatejsf.behavior.animate;

import javax.faces.FacesException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorBase;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.FacesBehavior;

@FacesBehavior("ha.animatejsf.behavior.animate")
@ResourceDependencies({
		@ResourceDependency(library = "animatejsf", name = "animate.css"),
		@ResourceDependency(library = "animatejsf", name = "animatejsf.js") })
public class AnimateBehavior extends ClientBehaviorBase {
	protected String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getScript(ClientBehaviorContext behaviorContext) {
		//FacesContext ctx = behaviorContext.getFacesContext();
		UIComponent parent = behaviorContext.getComponent();
		String targetId = parent.getClientId();
		if (type != null) {
			return "animatejsf('" + targetId + "','"+ type +"')";
		} else
			throw new FacesException("Cannot find target component");
		
	}
}
