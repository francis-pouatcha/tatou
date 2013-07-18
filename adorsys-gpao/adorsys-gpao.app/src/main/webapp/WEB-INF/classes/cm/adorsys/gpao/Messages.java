package cm.adorsys.gpao;

/**
 * Interface to represent the messages contained in resource bundle:
 * 	/projects/geeks/tatou-project/adorsys-gpao/adorsys-gpao.app/src/main/java/cm/adorsys/gpao/Messages.properties'.
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
