package com.ge.exercise1;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class MyParser implements Parser {
    public static MyApplication myApp;

    @Override
    public Application parseApplicationData(String data) {
        Matcher matcher = Pattern.compile("Application\\(id: (\\d+),name: (\\S+),users:\\[(.*?)\\],groups:\\[(.*)\\]").matcher(data);
        String applicationId = "";
        String applicationName = "";
        String users = "";
        String groups = "";

        if (matcher.find()) {
            applicationId = matcher.group(1);
            applicationName = matcher.group(2);
            users = matcher.group(3);
            groups = matcher.group(4);
        }
        myApp = new MyApplication(applicationId, applicationName);

        myApp.setUsers(getUsers(users));
        myApp.setGroups(getGroups(groups));

        return myApp;
    }

    private List<User> getUsers(String users) {
        List<User> usersList = new ArrayList<>();

        Pattern pattern = Pattern.compile("User\\(id: (\\d+),name: (.*?)\\)");
        Matcher matcher = pattern.matcher(users);

        while (matcher.find()) {
            String userId = matcher.group(1);
            String userName = matcher.group(2);
            User user = new MyUser(userId, userName);
            usersList.add(user);
        }

        return usersList;
    }

    private List<Group> getGroups(String groups) {
        List<Group> groupsList = new ArrayList<>();

        Pattern pattern = Pattern.compile("Group\\(id: (\\d+),name: (.*?),users:\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(groups);

        while (matcher.find()) {
            String groupId = matcher.group(1);
            String groupName = matcher.group(2);
            MyGroup group = new MyGroup(groupId, groupName);
            List<User> usersList = getUsers((matcher.group(3)));
            group.setUsers(usersList);
            group.setSize(usersList.size());
            groupsList.add(group);
        }

        return groupsList;
    }
}
