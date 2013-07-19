package cm.adorsys.gpao.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

@Portable
@Bindable
@Entity
public class UnitOfMesure  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;
    
    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @ManyToOne
    private UdmGroup unitGroup;

    private Boolean enabled = Boolean.TRUE;

    @NotNull
    @Min(0L)
    private BigDecimal ratio =BigDecimal.ZERO;

    @NotNull
    private Boolean isRefUdm =Boolean.FALSE;

    public UnitOfMesure() {
	}
    
    public UnitOfMesure(int id) {
    	this.name = "name"+id ;
    	this.ratio = BigDecimal.valueOf(id);
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

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public UdmGroup getUnitGroup() {
	return this.unitGroup;
    }

    public void setUnitGroup(UdmGroup unitGroup) {
	this.unitGroup = unitGroup;
    }

    public Boolean getEnabled() {
	return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
    }

    public BigDecimal getRatio() {
	return this.ratio;
    }

    public void setRatio(BigDecimal ratio) {
	this.ratio = ratio;
    }

    public Boolean getIsRefUdm() {
	return this.isRefUdm;
    }

    public void setIsRefUdm(Boolean isRefUdm) {
	this.isRefUdm = isRefUdm;
    }

    @PrePersist
    public void prePersist(){
    	name = name.toUpperCase();
    }
    
    @PreUpdate
    public void preUpdate(){
    	name = name.toUpperCase();
    }
    
    @Override
    public String toString() {
	return "UnitOfMesure [id=" + id + ", version=" + version + ", name="
		+ name + ", unitGroup=" + unitGroup + ", enabled=" + enabled
		+ ", ratio=" + ratio + ", isRefUdm=" + isRefUdm + "]";
    }
    
    
}
