/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author Sang1011
 */
public class UserError {
    private String userIDError;
    private String nameError;
    private String addressError;
    private String emailError;
    private String phoneError;
    private String roleIDError;
    private String passwordError;
    private String confirmError;
    private String error;

    public UserError() {
        this.userIDError = "";
        this.nameError = "";
        this.addressError = "";
        this.emailError = "";
        this.phoneError = "";
        this.roleIDError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.error = "";
    }

    public UserError(String userIDError, String nameError, String addressError, String emailError, String phoneError,
            String roleIDError, String passwordError, String confirmError, String error) {
        this.userIDError = userIDError;
        this.nameError = nameError;
        this.addressError = addressError;
        this.emailError = emailError;
        this.phoneError = phoneError;
        this.roleIDError = roleIDError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.error = error;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
