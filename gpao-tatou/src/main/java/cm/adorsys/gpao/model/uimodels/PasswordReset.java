package cm.adorsys.gpao.model.uimodels;

import org.apache.commons.lang3.StringUtils;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class PasswordReset {
	
	private String userName;

    private String currentPassword;

    private String newPassword;

    private String confirmNewPassword;
    
    public boolean passwordsEqual(){
		return StringUtils.equals(newPassword, confirmNewPassword);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

     
}
