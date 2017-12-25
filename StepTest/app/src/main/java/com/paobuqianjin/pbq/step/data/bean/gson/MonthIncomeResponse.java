package com.paobuqianjin.pbq.step.data.bean.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pbq on 2017/12/25.
 */

public class MonthIncomeResponse {
    /**
     * error : 0
     * message : success
     * data : {"0":{"creattime":1512628228,"amount":"0.50","name":"运动红包"},"1":{"creattime":1512714628,"amount":"0.80","name":"运动红包"},"2":{"creattime":1512714628,"amount":"0.15","name":"运动红包"},"3":{"creattime":1512628228,"amount":"0.28","name":"运动红包"},"4":{"creattime":1512714628,"amount":"1.28","name":"运动红包"},"5":{"creattime":1512835200,"amount":"6.66","name":"运动红包"},"6":{"creattime":1513581442,"amount":"8.88","name":"邀请红包"},"7":{"creattime":1513597298,"amount":"8.88","name":"运动红包"},"count":27.43}
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
         * 0 : {"creattime":1512628228,"amount":"0.50","name":"运动红包"}
         * 1 : {"creattime":1512714628,"amount":"0.80","name":"运动红包"}
         * 2 : {"creattime":1512714628,"amount":"0.15","name":"运动红包"}
         * 3 : {"creattime":1512628228,"amount":"0.28","name":"运动红包"}
         * 4 : {"creattime":1512714628,"amount":"1.28","name":"运动红包"}
         * 5 : {"creattime":1512835200,"amount":"6.66","name":"运动红包"}
         * 6 : {"creattime":1513581442,"amount":"8.88","name":"邀请红包"}
         * 7 : {"creattime":1513597298,"amount":"8.88","name":"运动红包"}
         * count : 27.43
         */

        @SerializedName("0")
        private _$0Bean _$0;
        @SerializedName("1")
        private _$1Bean _$1;
        @SerializedName("2")
        private _$2Bean _$2;
        @SerializedName("3")
        private _$3Bean _$3;
        @SerializedName("4")
        private _$4Bean _$4;
        @SerializedName("5")
        private _$5Bean _$5;
        @SerializedName("6")
        private _$6Bean _$6;
        @SerializedName("7")
        private _$7Bean _$7;
        private double count;

        public _$0Bean get_$0() {
            return _$0;
        }

        public void set_$0(_$0Bean _$0) {
            this._$0 = _$0;
        }

        public _$1Bean get_$1() {
            return _$1;
        }

        public void set_$1(_$1Bean _$1) {
            this._$1 = _$1;
        }

        public _$2Bean get_$2() {
            return _$2;
        }

        public void set_$2(_$2Bean _$2) {
            this._$2 = _$2;
        }

        public _$3Bean get_$3() {
            return _$3;
        }

        public void set_$3(_$3Bean _$3) {
            this._$3 = _$3;
        }

        public _$4Bean get_$4() {
            return _$4;
        }

        public void set_$4(_$4Bean _$4) {
            this._$4 = _$4;
        }

        public _$5Bean get_$5() {
            return _$5;
        }

        public void set_$5(_$5Bean _$5) {
            this._$5 = _$5;
        }

        public _$6Bean get_$6() {
            return _$6;
        }

        public void set_$6(_$6Bean _$6) {
            this._$6 = _$6;
        }

        public _$7Bean get_$7() {
            return _$7;
        }

        public void set_$7(_$7Bean _$7) {
            this._$7 = _$7;
        }

        public double getCount() {
            return count;
        }

        public void setCount(double count) {
            this.count = count;
        }

        public static class _$0Bean {
            /**
             * creattime : 1512628228
             * amount : 0.50
             * name : 运动红包
             */

            private int creattime;
            private String amount;
            private String name;

            public int getCreattime() {
                return creattime;
            }

            public void setCreattime(int creattime) {
                this.creattime = creattime;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$1Bean {
            /**
             * creattime : 1512714628
             * amount : 0.80
             * name : 运动红包
             */

            private int creattime;
            private String amount;
            private String name;

            public int getCreattime() {
                return creattime;
            }

            public void setCreattime(int creattime) {
                this.creattime = creattime;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$2Bean {
            /**
             * creattime : 1512714628
             * amount : 0.15
             * name : 运动红包
             */

            private int creattime;
            private String amount;
            private String name;

            public int getCreattime() {
                return creattime;
            }

            public void setCreattime(int creattime) {
                this.creattime = creattime;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$3Bean {
            /**
             * creattime : 1512628228
             * amount : 0.28
             * name : 运动红包
             */

            private int creattime;
            private String amount;
            private String name;

            public int getCreattime() {
                return creattime;
            }

            public void setCreattime(int creattime) {
                this.creattime = creattime;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$4Bean {
            /**
             * creattime : 1512714628
             * amount : 1.28
             * name : 运动红包
             */

            private int creattime;
            private String amount;
            private String name;

            public int getCreattime() {
                return creattime;
            }

            public void setCreattime(int creattime) {
                this.creattime = creattime;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$5Bean {
            /**
             * creattime : 1512835200
             * amount : 6.66
             * name : 运动红包
             */

            private int creattime;
            private String amount;
            private String name;

            public int getCreattime() {
                return creattime;
            }

            public void setCreattime(int creattime) {
                this.creattime = creattime;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$6Bean {
            /**
             * creattime : 1513581442
             * amount : 8.88
             * name : 邀请红包
             */

            private int creattime;
            private String amount;
            private String name;

            public int getCreattime() {
                return creattime;
            }

            public void setCreattime(int creattime) {
                this.creattime = creattime;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class _$7Bean {
            /**
             * creattime : 1513597298
             * amount : 8.88
             * name : 运动红包
             */

            private int creattime;
            private String amount;
            private String name;

            public int getCreattime() {
                return creattime;
            }

            public void setCreattime(int creattime) {
                this.creattime = creattime;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
