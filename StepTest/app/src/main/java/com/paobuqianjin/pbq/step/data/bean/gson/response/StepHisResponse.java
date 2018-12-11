package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/12/11.
 */

public class StepHisResponse {
    /**
     * error : 0
     * message : success
     * data : {"step_list":[{"step_number":1566,"create_day":"2018-12-05"},{"step_number":1758,"create_day":"2018-12-06"},{"step_number":284,"create_day":"2018-12-07"},{"step_number":0,"create_day":"2018-12-08"},{"step_number":0,"create_day":"2018-12-09"},{"step_number":662,"create_day":"2018-12-10"},{"step_number":0,"create_day":"2018-12-11"}],"today_step":0}
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
         * step_list : [{"step_number":1566,"create_day":"2018-12-05"},{"step_number":1758,"create_day":"2018-12-06"},{"step_number":284,"create_day":"2018-12-07"},{"step_number":0,"create_day":"2018-12-08"},{"step_number":0,"create_day":"2018-12-09"},{"step_number":662,"create_day":"2018-12-10"},{"step_number":0,"create_day":"2018-12-11"}]
         * today_step : 0
         */

        private int today_step;
        private List<StepListBean> step_list;

        public int getToday_step() {
            return today_step;
        }

        public void setToday_step(int today_step) {
            this.today_step = today_step;
        }

        public List<StepListBean> getStep_list() {
            return step_list;
        }

        public void setStep_list(List<StepListBean> step_list) {
            this.step_list = step_list;
        }

        public static class StepListBean {
            /**
             * step_number : 1566
             * create_day : 2018-12-05
             */

            private int step_number;
            private String create_day;

            public int getStep_number() {
                return step_number;
            }

            public void setStep_number(int step_number) {
                this.step_number = step_number;
            }

            public String getCreate_day() {
                return create_day;
            }

            public void setCreate_day(String create_day) {
                this.create_day = create_day;
            }
        }
    }
}
