package cm.adorsys.gpao.model.uimodels;

import java.util.List;

public interface GpaoEntityFinder<T> {
  public List<T> find(int page , int size);
  public List<T> find();
}
