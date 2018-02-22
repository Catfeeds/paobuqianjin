package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by Administrator on 2018/2/21.
 */

public class WeatherResponse {
    /**
     * error : 0
     * message : success
     * data : {"weather":"阴","img":"2","temp":"19"}
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

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * weather : 阴
         * img : 2
         * temp : 19
         */

        private String weather;
        private String img;
        private String temp;

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "weather='" + weather + '\'' +
                    ", img='" + img + '\'' +
                    ", temp='" + temp + '\'' +
                    '}';
        }
    }
}
