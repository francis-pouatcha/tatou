package cm.adorsys.gpao.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
@Bindable
@Portable
@Entity
public class UdmGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version",unique=true)
    private Integer version;
    @NotEmpty(message="le liebele es requis!")
    @Size(min = 2)
    private String name;
   
    public UdmGroup() {
	}
    
    public UdmGroup(String name) {
    	this.name = name ;
	}
    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    

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
	this.version = version;
    }

    @Override
    public String toString() {
	return "UdmGroup [id=" + id + ", version=" + version + ", name=" + name
		+ "]";
    }
    
    
}
