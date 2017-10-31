package com.terabits.meta.po;

public class AdminPO {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public Integer getType() {
    	return this.type;
    }
    
    public void setType(Integer type) {
    	this.type = type;
    }

    @Override
    public String toString() {
        return "AdminPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
