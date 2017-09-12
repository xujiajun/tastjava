package cn.xujiajun.tastjava.entity;

public class User {
    private int id;
    private String userName;
    private String password;

    /**
     * @return userId
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the userId to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

//    public String toString() {
//        StringBuffer sb = new StringBuffer();
//        sb.append(this.id);
//        sb.append(this.userName);
//        sb.append(this.password);
//        return sb.toString();
//    }
}
