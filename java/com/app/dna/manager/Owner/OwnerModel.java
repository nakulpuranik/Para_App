package com.app.dna.manager.Owner;

/**
 * Created by dhawal on 6/2/16.
 */
public class OwnerModel
{
    int ownId, ownStatus;
    String ownName, owncontact, ownEmail, ownGcmId;

    private OwnerModel()
    {

    }

    public int getOwnId()
    {
        return ownId;
    }

    public void setOwnId(int ownId) {
        this.ownId = ownId;
    }

    public int getOwnStatus() {
        return ownStatus;
    }

    public void setOwnStatus(int ownStatus) {
        this.ownStatus = ownStatus;
    }

    public String getOwnName() {
        return ownName;
    }

    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    public String getOwncontact() {
        return owncontact;
    }

    public void setOwncontact(String owncontact) {
        this.owncontact = owncontact;
    }

    public String getOwnEmail() {
        return ownEmail;
    }

    public void setOwnEmail(String ownEmail) {
        this.ownEmail = ownEmail;
    }

    public String getOwnGcmId() {
        return ownGcmId;
    }

    public void setOwnGcmId(String ownGcmId) {
        this.ownGcmId = ownGcmId;
    }

    public OwnerModel getInstance()
    {
        return new OwnerModel();
    }
}
