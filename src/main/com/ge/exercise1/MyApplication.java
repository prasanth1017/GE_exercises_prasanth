package com.ge.exercise1;

import java.util.Collection;

public class MyApplication extends Application {
    private Collection<User> users;
    private Collection<Group> groups;

    public MyApplication(String id, String name) {

        super(id, name);
    }

    @Override
    public User getUser(String userId) {

        for (User user: MyParser.myApp.getUsers()) {
            if (user.getId().equals(userId))
                return user;
        }
        return null;
    }

    public Collection<User> getUsers() {
        return this.users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public Collection<Group> getGroups() {

        return this.groups;
    }

    public void setGroups(Collection<Group> groups) {

        this.groups = groups;
    }

    @Override
    public Group getGroup(String groupId) {

        for (Group group: MyParser.myApp.getGroups()) {
            if (group.getId().equals(groupId))
                return group;
        }
        return null;
    }
}
