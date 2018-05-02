package com.paobuqianjin.pbq.step.data.bean;

import java.util.List;

public class AddressDtailsEntity {
    public String Province;
    public String City;
    public String Area;
    public Data ProvinceItems;

    public class Data {
        public List<ProvinceEntity> Province;
    }

    public class ProvinceEntity {
        public String Name;
        public List<CityEntity> City;

        public class CityEntity {
            public String Name;
            public List<AreaEntity> Area;
        }
        public class AreaEntity {
            public String Name;
        }
    }
}
