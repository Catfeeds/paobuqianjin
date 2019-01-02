package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2019/1/2.
 */

public class ExToTrifResponse {
    /**
     * error : 0
     * message : success
     * data : {"consign_info":{"buyer_id":35828,"buyer_consigner":"周","buyer_mobile":2147483647,"buyer_addr":"广东省深圳市南山区大新路88号","buyer_address":"63栋501","buyer_zip_code":"0"},"express_list":[{"name":"顺丰速运","code":"SF"},{"name":"百世快递","code":"HTKY"},{"name":"中通快递","code":"ZTO"},{"name":"申通快递","code":"STO"},{"name":"圆通速递","code":"YTO"},{"name":"韵达速递","code":"YD"},{"name":"邮政快递包裹","code":"YZPY"},{"name":"EMS","code":"EMS"},{"name":"天天快递","code":"HHTT"},{"name":"京东快递","code":"JD"},{"name":"优速快递","code":"UC"},{"name":"德邦快递","code":"DBL"},{"name":"宅急送","code":"ZJS"},{"name":"TNT快递","code":"ZJS"},{"name":"UPS","code":"UPS"},{"name":"DHL","code":"DHL"},{"name":"FEDEX联邦","code":"FEDEX"}]}
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
         * consign_info : {"buyer_id":35828,"buyer_consigner":"周","buyer_mobile":2147483647,"buyer_addr":"广东省深圳市南山区大新路88号","buyer_address":"63栋501","buyer_zip_code":"0"}
         * express_list : [{"name":"顺丰速运","code":"SF"},{"name":"百世快递","code":"HTKY"},{"name":"中通快递","code":"ZTO"},{"name":"申通快递","code":"STO"},{"name":"圆通速递","code":"YTO"},{"name":"韵达速递","code":"YD"},{"name":"邮政快递包裹","code":"YZPY"},{"name":"EMS","code":"EMS"},{"name":"天天快递","code":"HHTT"},{"name":"京东快递","code":"JD"},{"name":"优速快递","code":"UC"},{"name":"德邦快递","code":"DBL"},{"name":"宅急送","code":"ZJS"},{"name":"TNT快递","code":"ZJS"},{"name":"UPS","code":"UPS"},{"name":"DHL","code":"DHL"},{"name":"FEDEX联邦","code":"FEDEX"}]
         */

        private ConsignInfoBean consign_info;
        private List<ExpressListBean> express_list;

        public ConsignInfoBean getConsign_info() {
            return consign_info;
        }

        public void setConsign_info(ConsignInfoBean consign_info) {
            this.consign_info = consign_info;
        }

        public List<ExpressListBean> getExpress_list() {
            return express_list;
        }

        public void setExpress_list(List<ExpressListBean> express_list) {
            this.express_list = express_list;
        }

        public static class ConsignInfoBean {
            /**
             * buyer_id : 35828
             * buyer_consigner : 周
             * buyer_mobile : 2147483647
             * buyer_addr : 广东省深圳市南山区大新路88号
             * buyer_address : 63栋501
             * buyer_zip_code : 0
             */

            private String buyer_id;
            private String buyer_consigner;
            private String buyer_mobile;
            private String buyer_addr;
            private String buyer_address;
            private String buyer_zip_code;

            public String getBuyer_id() {
                return buyer_id;
            }

            public void setBuyer_id(String buyer_id) {
                this.buyer_id = buyer_id;
            }

            public String getBuyer_consigner() {
                return buyer_consigner;
            }

            public void setBuyer_consigner(String buyer_consigner) {
                this.buyer_consigner = buyer_consigner;
            }

            public String getBuyer_mobile() {
                return buyer_mobile;
            }

            public void setBuyer_mobile(String buyer_mobile) {
                this.buyer_mobile = buyer_mobile;
            }

            public String getBuyer_addr() {
                return buyer_addr;
            }

            public void setBuyer_addr(String buyer_addr) {
                this.buyer_addr = buyer_addr;
            }

            public String getBuyer_address() {
                return buyer_address;
            }

            public void setBuyer_address(String buyer_address) {
                this.buyer_address = buyer_address;
            }

            public String getBuyer_zip_code() {
                return buyer_zip_code;
            }

            public void setBuyer_zip_code(String buyer_zip_code) {
                this.buyer_zip_code = buyer_zip_code;
            }
        }

        public static class ExpressListBean {
            /**
             * name : 顺丰速运
             * code : SF
             */

            private String name;
            private String code;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
