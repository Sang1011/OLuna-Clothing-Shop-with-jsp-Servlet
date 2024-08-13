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
public class UserDTO {
    private String userID;
    private String password;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String roleID;
    private int status;

    public UserDTO() {
        this.userID = "";
        this.password = "";
        this.name = "";
        this.address = "";
        this.email = "";
        this.phone = "";
        this.roleID = "";
        this.status = 0;
    }

    public UserDTO(String userID, String password, String name, String address, String email, String phone,
            String roleID) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.roleID = roleID;
    }

    public UserDTO(String userID, String password, String name, String address, String email, String phone,
            String roleID, int status) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.roleID = roleID;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", password=" + password + ", name=" + name + ", address=" + address
                + ", email=" + email + ", phone=" + phone + ", roleID=" + roleID + ", status=" + status + '}';
    }

}
