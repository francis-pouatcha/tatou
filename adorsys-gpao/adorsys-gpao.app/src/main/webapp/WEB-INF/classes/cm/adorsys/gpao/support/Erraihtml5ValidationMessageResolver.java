package cm.adorsys.gpao.support;

import org.hibernate.validator.ValidationMessages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.validation.client.AbstractValidationMessageResolver;
import com.google.gwt.validation.client.ProviderValidationMessageResolver;

public class Erraihtml5ValidationMessageResolver extends AbstractValidationMessageResolver implements ProviderValidationMessageResolver
{

   public Erraihtml5ValidationMessageResolver()
   {
      super((ConstantsWithLookup) GWT.create(ValidationMessages.class));
   }
}