package com.banking.api.models.oauth2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="groups")
public class Group {

    @Id
    @Column(name="group_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int groupId;
    
    @Column(name="group_name")
    private String groupName;
    
    private boolean enabled;
    
    public Group() { }
    
    public Group(String groupName, boolean enabled) {
        this.groupName = groupName;
        this.enabled = enabled;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
