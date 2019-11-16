package com.ge.exercise1;
import java.util.Collection;

public class MyGroup extends Group {
    private Collection<User> users;

    public MyGroup(String id, String name) {
        super(id, name);
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
