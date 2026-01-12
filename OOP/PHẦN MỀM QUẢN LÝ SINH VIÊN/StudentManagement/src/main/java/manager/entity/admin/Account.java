/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.entity.admin;

/**
 *
 * @author Dell
 */
public class Account {

    public Account(String userName, String passWord, String accountType) {
        this.userName = userName;
        this.passWord = passWord;
        this.accountType = accountType;
    }

    public Account() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    private String userName;
    private String passWord;
    private String accountType;
}
