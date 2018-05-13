package com.paobuqianjin.pbq.step.data.bean.gson.param;

/**
 * Created by pbq on 2018/5/13.
 */

public class UserCenterVoteData {
    int position = -1, is_vote = -1, vote = -1, comment = -1;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIs_vote() {
        return is_vote;
    }

    public void setIs_vote(int is_vote) {
        this.is_vote = is_vote;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "UserCenterVoteData{" +
                "position=" + position +
                ", is_vote=" + is_vote +
                ", vote=" + vote +
                ", comment=" + comment +
                '}';
    }
}
