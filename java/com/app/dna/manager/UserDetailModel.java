package com.app.dna.manager;

/**
 * Created by dhawal on 26/11/15.
 */
public class UserDetailModel
{
    private String userName;

    private boolean isPending;

    private byte profilePicture;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setIsPending(boolean isPending) {
        this.isPending = isPending;
    }

    public byte getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte profilePicture) {
        this.profilePicture = profilePicture;
    }

}
