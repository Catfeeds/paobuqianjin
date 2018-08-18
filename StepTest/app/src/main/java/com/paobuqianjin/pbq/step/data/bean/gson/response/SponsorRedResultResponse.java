package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/6/27.
 */

public class SponsorRedResultResponse {
    /**
     * error : 0
     * message : 领取成功
     * data : {"statue":0,"allmoney":"11.71","result":[]}
     */

    private int error;
    private String message;
    private DataBean data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * statue : 0
         * allmoney : 11.71
         * result : []
         */

        private int statue;
        private float allmoney;
        private List<?> result;

        public int getStatue() {
            return statue;
        }

        public void setStatue(int statue) {
            this.statue = statue;
        }

        public float getAllmoney() {
            return allmoney;
        }

        public void setAllmoney(float allmoney) {
            this.allmoney = allmoney;
        }

        public List<?> getResult() {
            return result;
        }

        public void setResult(List<?> result) {
            this.result = result;
        }
    }
}
