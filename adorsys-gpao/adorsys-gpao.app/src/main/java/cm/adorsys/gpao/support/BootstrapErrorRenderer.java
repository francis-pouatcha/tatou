package cm.adorsys.gpao.support;

import javax.validation.ConstraintViolation;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;


public class BootstrapErrorRenderer implements ErrorRenderer {

	@Override
	public void showErrror(ConstraintViolation<?> constraintViolation, Widget widget) {
		Element controlGroup = findControlGroup(widget.getElement());
		controlGroup.addClassName("error");
		addErrorMessage(constraintViolation, widget);
	}
	
	/**
	 * <span class="help-inline">Please correct the error</span>
	 */
	public void addErrorMessage(ConstraintViolation<?> constraintViolation, Widget widget) {
		SpanElement msg = DOM.createSpan().cast();
		msg.setInnerText(constraintViolation.getMessage());
		msg.addClassName("help-block");
		widget.getElement().getParentElement().appendChild(msg );
	}

	@Override
	public void removeError(Widget widget) {
		Element controlGroup = findControlGroup(widget.getElement());
		controlGroup.removeClassName("error");
		removeAllMessages(widget.getElement());
	}
	
	private void removeAllMessages(com.google.gwt.user.client.Element element) {
		NodeList<Node> childNodes = element.getParentElement().getChildNodes();
		for (int i = childNodes.getLength(); i > -1 ; i--) {
			Node n = childNodes.getItem(i);
			if (n instanceof Element) {
				Element e = (Element) n;
				if (e.getClassName() != null && e.getClassName().contains("help-")) {
					element.getParentElement().removeChild(e);
				}
			}
		}
	}

	private Element findControlGroup(Element element) {
		if (element.getAttribute("class").contains("control-group")){
			return element;
		} else {
			return findControlGroup(element.getParentElement());
		}
	}

}
