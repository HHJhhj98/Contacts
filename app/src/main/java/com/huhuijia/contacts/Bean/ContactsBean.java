package com.huhuijia.contacts.Bean;

public class ContactsBean {
    public String name;
    public String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public ContactsBean() {
        super();
    }

    public ContactsBean(String name, String phone) {
        super();
        this.name = name;
        this.phone=phone;
    }

}
