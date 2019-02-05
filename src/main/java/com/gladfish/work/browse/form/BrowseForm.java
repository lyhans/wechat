package com.gladfish.work.browse.form;

import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/2/4 18:33
 */
public class BrowseForm {
    private List<ViewRecordForm> friends;
    private boolean vip;
    private int count;

    public List<ViewRecordForm> getFriends() {
        return friends;
    }

    public void setFriends(List<ViewRecordForm> friends) {
        this.friends = friends;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
