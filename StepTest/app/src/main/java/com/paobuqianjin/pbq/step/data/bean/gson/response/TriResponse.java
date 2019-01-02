package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2019/1/2.
 */

public class TriResponse {
    /**
     * error : 0
     * message : success
     * data : {"LogisticCode":"803302366676880171","ShipperCode":"YTO","Traces":[{"AcceptStation":"【广西省南宁市琅东公司】 已收件","AcceptTime":"2018-12-11 19:59:14"},{"AcceptStation":"【南宁转运中心】 已收入","AcceptTime":"2018-12-12 01:24:18"},{"AcceptStation":"【南宁转运中心】 已发出 下一站 【郑州转运中心】","AcceptTime":"2018-12-12 03:14:40"},{"AcceptStation":"【郑州转运中心】 已收入","AcceptTime":"2018-12-14 03:59:48"},{"AcceptStation":"【郑州转运中心】 已发出 下一站 【漯河转运中心】","AcceptTime":"2018-12-14 04:26:27"},{"AcceptStation":"【漯河转运中心】 已收入","AcceptTime":"2018-12-14 10:41:00"},{"AcceptStation":"【漯河转运中心】 已发出 下一站 【河南省周口市公司】","AcceptTime":"2018-12-14 10:42:30"},{"AcceptStation":"【河南省周口市公司】 已收入","AcceptTime":"2018-12-14 20:06:57"},{"AcceptStation":"【河南省周口市公司】 派件人: 姚攀登 派件中 派件员电话13639858281","AcceptTime":"2018-12-20 08:47:57"},{"AcceptStation":"客户 签收人: 王 已签收 感谢使用圆通速递，期待再次为您服务","AcceptTime":"2018-12-20 13:09:21"}],"State":"3","OrderCode":"COM20181231055146269","EBusinessID":"1400704","Success":true,"company_name":"圆通速递"}
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
         * LogisticCode : 803302366676880171
         * ShipperCode : YTO
         * Traces : [{"AcceptStation":"【广西省南宁市琅东公司】 已收件","AcceptTime":"2018-12-11 19:59:14"},{"AcceptStation":"【南宁转运中心】 已收入","AcceptTime":"2018-12-12 01:24:18"},{"AcceptStation":"【南宁转运中心】 已发出 下一站 【郑州转运中心】","AcceptTime":"2018-12-12 03:14:40"},{"AcceptStation":"【郑州转运中心】 已收入","AcceptTime":"2018-12-14 03:59:48"},{"AcceptStation":"【郑州转运中心】 已发出 下一站 【漯河转运中心】","AcceptTime":"2018-12-14 04:26:27"},{"AcceptStation":"【漯河转运中心】 已收入","AcceptTime":"2018-12-14 10:41:00"},{"AcceptStation":"【漯河转运中心】 已发出 下一站 【河南省周口市公司】","AcceptTime":"2018-12-14 10:42:30"},{"AcceptStation":"【河南省周口市公司】 已收入","AcceptTime":"2018-12-14 20:06:57"},{"AcceptStation":"【河南省周口市公司】 派件人: 姚攀登 派件中 派件员电话13639858281","AcceptTime":"2018-12-20 08:47:57"},{"AcceptStation":"客户 签收人: 王 已签收 感谢使用圆通速递，期待再次为您服务","AcceptTime":"2018-12-20 13:09:21"}]
         * State : 3
         * OrderCode : COM20181231055146269
         * EBusinessID : 1400704
         * Success : true
         * company_name : 圆通速递
         */

        private String LogisticCode;
        private String ShipperCode;
        private int State;
        private String OrderCode;
        private String EBusinessID;
        private boolean Success;
        private String company_name;
        private List<TracesBean> Traces;

        public String getLogisticCode() {
            return LogisticCode;
        }

        public void setLogisticCode(String LogisticCode) {
            this.LogisticCode = LogisticCode;
        }

        public String getShipperCode() {
            return ShipperCode;
        }

        public void setShipperCode(String ShipperCode) {
            this.ShipperCode = ShipperCode;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public String getOrderCode() {
            return OrderCode;
        }

        public void setOrderCode(String OrderCode) {
            this.OrderCode = OrderCode;
        }

        public String getEBusinessID() {
            return EBusinessID;
        }

        public void setEBusinessID(String EBusinessID) {
            this.EBusinessID = EBusinessID;
        }

        public boolean isSuccess() {
            return Success;
        }

        public void setSuccess(boolean Success) {
            this.Success = Success;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public List<TracesBean> getTraces() {
            return Traces;
        }

        public void setTraces(List<TracesBean> Traces) {
            this.Traces = Traces;
        }

        public static class TracesBean {
            /**
             * AcceptStation : 【广西省南宁市琅东公司】 已收件
             * AcceptTime : 2018-12-11 19:59:14
             */

            private String AcceptStation;
            private String AcceptTime;

            public String getAcceptStation() {
                return AcceptStation;
            }

            public void setAcceptStation(String AcceptStation) {
                this.AcceptStation = AcceptStation;
            }

            public String getAcceptTime() {
                return AcceptTime;
            }

            public void setAcceptTime(String AcceptTime) {
                this.AcceptTime = AcceptTime;
            }
        }
    }
}
