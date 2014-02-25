package cm.adorsys.gpao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(mappedSuperclass = true)
public abstract class GpaoBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;

	@Version
	@Column(name = "version")
	protected Integer version;

	protected  Boolean archived = Boolean.FALSE ;    		

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version ;
	}



	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public static <T> List<T> findAll(Class<T> clazz) {
		return entityManager().createQuery("SELECT o FROM "+clazz.getName()+" o ORDER BY o.id DESC", clazz).getResultList();
	}

	public static  <T> List<T> findEntries(int firstResult, int maxResults , Class<T> clazz) {
		return entityManager().createQuery("SELECT o FROM "+clazz.getName()+" o ORDER BY o.id DESC", clazz).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GpaoBaseEntity other = (GpaoBaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
