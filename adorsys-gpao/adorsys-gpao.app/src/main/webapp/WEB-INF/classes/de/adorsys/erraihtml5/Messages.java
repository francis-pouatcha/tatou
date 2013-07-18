package de.adorsys.erraihtml5;

/**
 * Interface to represent the messages contained in resource bundle:
 * 	/projects/geeks/errai-example/errai-example.client/target/classes/de/adorsys/erraihtml5/Messages.properties'.
 */
public interface Messages extends com.google.gwt.i18n.client.Messages {
  
  /**
   * Translated "GWT rocks".
   * 
   * @return translated "GWT rocks"
   */
  @DefaultMessage("GWT rocks")
  @Key("testMessage")
  String testMessage();
}
