package cm.adorsys.gpao.api.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

import java.lang.Override;

@Entity
@Bindable
@Portable
public class Users implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @NotEmpty(message="userName is required !")
   @Column
   private String userName;

   @NotEmpty(message="password is reqired !")
   @Size(min=6,max=10,message="password must have 6 to 10 charts")
   @Column 
   private String password;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   public Users() {
    
}
   public void cloneFromUser(Users users) {
       this.userName = users.getUserName();
       this.password = users.getPassword();
   }
   
   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((Users) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public String getUserName()
   {
      return this.userName;
   }

   public void setUserName(final String userName)
   {
      this.userName = userName;
   }

   public String getPassword()
   {
      return this.password;
   }

   public void setPassword(final String password)
   {
      this.password = password;
   }

   @Override
   public String toString()
   {
      String result =  " ";
      if (userName != null && !userName.trim().isEmpty())
         result += "userName: " + userName;
      if (password != null && !password.trim().isEmpty())
         result += ", password: " + password;
      return result;
   }
}