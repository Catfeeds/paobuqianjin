package com.paobuqianjin.pbq.step.data.bean.gson.response;

/**
 * Created by Administrator on 2018/2/21.
 */

public class WeatherResponse {

    /**
     * error : 0
     * message : success
     * data : {"is_weather":0,"weather":"","img":"","temp":"","city":"","citycode":"","main_tips":"跑步钱进"}
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
         * is_weather : 0
         * weather :
         * img :
         * temp :
         * city :
         * citycode :
         * main_tips : 跑步钱进
         */

        private int is_weather;
        private String weather;
        private String img;
        private String temp;
        private String city;
        private String citycode;
        private String main_tips;

        public int getIs_weather() {
            return is_weather;
        }

        public void setIs_weather(int is_weather) {
            this.is_weather = is_weather;
        }

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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getMain_tips() {
            return main_tips;
        }

        public void setMain_tips(String main_tips) {
            this.main_tips = main_tips;
        }
    }
}
