package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2018/11/29.
 */

/*
@className :CouponListResponse v1/CoalitionLine/getCouponList  tianmao and taobao jiekou
*@date 2018/11/29
*@author
*@description
*/
public class CouponListResponse {
    /**
     * error : 0
     * message : success
     * data : {"tbk_coupon":[{"category":"50020808","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=xNLhlllCMkYGQASttHIRqUYwMhgP1uVpS7xtgf2XWVaAnVXUwZx8IpOk3LaoKR3FCfPlN1LBG6aa4VRjYTjloRfiogjUXLsIbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-29","coupon_info":"满4元减3元","coupon_remain_count":"999","coupon_start_time":"2018-11-29","coupon_total_count":"999","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=578834910065","nick":"丹丹爱的小窝","num_iid":"578834910065","pict_url":"http://img.alicdn.com/tfscom/i1/835933167/O1CN011ZGWnu70uluvMaK_!!835933167.jpg","seller_id":"835933167","shop_title":"带上潮包去旅行","small_images":{"string":"http://img.alicdn.com/tfscom/i4/835933167/O1CN011ZGWntX8kXAgjNX_!!835933167.jpg"},"title":"包邮mm巧克力豆小磁铁强磁冰箱贴 圆形强力创意磁贴办公教具","user_type":"0","volume":"0","zk_final_price":"18.15"},{"category":"16","commission_rate":"5.40","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=MAojfOdHJcwGQASttHIRqdwU22WOIVgcGegex7K4mPNYomNqLX%2FiF9CsPUVUIOegr71gr14ltEupoNvG4hpv%2Fbban8tah%2FMAbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2019-05-27","coupon_info":"满2元减1元","coupon_remain_count":"99949","coupon_start_time":"2018-11-28","coupon_total_count":"99999","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=575664292029","nick":"以诚务实为根本","num_iid":"575664292029","pict_url":"http://img.alicdn.com/tfscom/i4/3817971657/O1CN011O6wjSCbkpqc1wW_!!3817971657.jpg","seller_id":"3817971657","shop_title":"u[3817971657]","small_images":{"string":["http://img.alicdn.com/tfscom/i1/3817971657/O1CN011O6wjTOF2iU7Z9a_!!3817971657.jpg","http://img.alicdn.com/tfscom/i2/3817971657/O1CN011O6wjTOFqaH3CQz_!!3817971657.jpg","http://img.alicdn.com/tfscom/i4/3817971657/O1CN011O6wjUGRiVDEpI4_!!3817971657.jpg","http://img.alicdn.com/tfscom/i4/3817971657/O1CN011O6wjUMuwCvdt8x_!!3817971657.jpg"]},"title":"11-12-13-15-16岁初高中小学生大童女孩子夏装新款半身裙百褶短裙","user_type":"0","volume":"0","zk_final_price":"25.11"},{"category":"50008165","commission_rate":"3.00","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=LkmQRwRIk3wGQASttHIRqY0Tcj5aC6r5Tn1Q%2BAJ7al2EdvD4wwM%2BYy4Qy5V55oREEDt9jFFXZCfEy05GycOVAhltdR1qGi7wbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIGmY4hFfhtzCZ6Y%2FpkHtT5QS0Flu%2FfbSp4QsdWMikAatjH%2B8knoxVQDnios39sQPM%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-29","coupon_info":"满2元减1元","coupon_remain_count":"44","coupon_start_time":"2018-11-29","coupon_total_count":"50","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=571880703676","nick":"新鑫欣商贸","num_iid":"571880703676","pict_url":"http://img.alicdn.com/tfscom/i1/3889231298/TB1AAk8zQ9WBuNjSspeXXaz5VXa_!!0-item_pic.jpg","seller_id":"3889231298","shop_title":"新鑫欣童装","small_images":{"string":["http://img.alicdn.com/tfscom/i4/3889231298/TB2bsqbx_lYBeNjSszcXXbwhFXa_!!3889231298.jpg","http://img.alicdn.com/tfscom/i4/3889231298/TB1Jd30xKGSBuNjSspbXXciipXa_!!0-item_pic.jpg","http://img.alicdn.com/tfscom/i4/3889231298/TB2MXoGxQKWBuNjy1zjXXcOypXa_!!3889231298.jpg","http://img.alicdn.com/tfscom/i1/3889231298/TB2OcoSpC8YBeNkSnb4XXaevFXa_!!3889231298.jpg"]},"title":"儿童短裤夏季纯棉男童薄款中大童宽松裤子潮2017夏装女童热裤","user_type":"0","volume":"0","zk_final_price":"6.00"},{"category":"16","commission_rate":"6.00","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=m2OlOiroIwsGQASttHIRqSAc%2BaF2VRd9VdU2ZVFXesjyi1aSyqbpmuu1AVgJ1VDAPnyMT8ARAyRCbEwgwaqwqZg4XytZjWZkbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIGmY4hFfhtzCZ6Y%2FpkHtT5QS0Flu%2FfbSp4QsdWMikAatjH%2B8knoxVQDnios39sQPM%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-12-05","coupon_info":"满129元减50元","coupon_remain_count":"9800","coupon_start_time":"2018-11-28","coupon_total_count":"10000","item_description":"2018新款秋冬牛仔裤女","item_url":"https://detail.tmall.com/item.htm?id=578468334861","nick":"潮流前线官方旗舰店","num_iid":"578468334861","pict_url":"http://img.alicdn.com/tfscom/i1/1112725738/O1CN01Phkhcc1sG36abWr8q_!!0-item_pic.jpg","seller_id":"1112725738","shop_title":"潮流前线官方旗舰店","small_images":{"string":["http://img.alicdn.com/tfscom/i3/1112725738/O1CN011sG35Sc0GqWBgGS_!!1112725738.jpg","http://img.alicdn.com/tfscom/i1/1112725738/O1CN011sG35NW3zcftgOu_!!1112725738.jpg","http://img.alicdn.com/tfscom/i4/1112725738/O1CN011sG35Txh1NYMBPj_!!1112725738.jpg","http://img.alicdn.com/tfscom/i2/1112725738/O1CN011sG35SBFVX9p4DQ_!!1112725738.jpg"]},"title":"潮流前线2018秋冬新品女士牛仔裤修身韩版九分裤紧身中腰铅笔女裤","user_type":"1","volume":"7","zk_final_price":"129.00"},{"category":"16","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=qFsVRapoflwGQASttHIRqZsnGWpNWZlWGadBFDhhuxiEdvD4wwM%2BYzqi8D6Pk1pqX440f%2Fblz0U1lDSCQE1u5ppx%2FTyC5tyRbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-12-02","coupon_info":"满6元减5元","coupon_remain_count":"1800","coupon_start_time":"2018-11-26","coupon_total_count":"3000","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=582560439413","nick":"tb12029775","num_iid":"582560439413","pict_url":"http://img.alicdn.com/tfscom/i1/1747463478/O1CN01h79Xb81bYxzfJcVbt_!!0-item_pic.jpg","seller_id":"1747463478","shop_title":"四叶草de幸福小屋","small_images":{"string":["http://img.alicdn.com/tfscom/i3/1112725738/O1CN011sG35Sc0GqWBgGS_!!1112725738.jpg","http://img.alicdn.com/tfscom/i1/1112725738/O1CN011sG35NW3zcftgOu_!!1112725738.jpg","http://img.alicdn.com/tfscom/i4/1112725738/O1CN011sG35Txh1NYMBPj_!!1112725738.jpg","http://img.alicdn.com/tfscom/i2/1112725738/O1CN011sG35SBFVX9p4DQ_!!1112725738.jpg"]},"title":"潮流前线2018秋冬新品女士牛仔裤修身韩版九分裤紧身中腰铅笔女裤","user_type":"0","volume":"0","zk_final_price":"58.60"},{"category":"50008165","commission_rate":"2.70","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=HYwX0Bgr9NYGQASttHIRqT0Xc71eAk2OBN4DjuHgSY5Um%2Fg8xRLmNtUFUgkF%2FB4JZ3eE9fRIG10t%2B8OgXZIt%2B5NAbC4q1%2F73bd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-30","coupon_info":"满29元减5元","coupon_remain_count":"44500","coupon_start_time":"2018-11-28","coupon_total_count":"50000","item_description":"升级内衬加绒 更保暖 更舒适 更安心","item_url":"https://detail.tmall.com/item.htm?id=576767970649","nick":"hassondanny旗舰店","num_iid":"576767970649","pict_url":"http://img.alicdn.com/tfscom/i4/2453844006/O1CN01Vuxu6O1fSn7CpDilP_!!2-item_pic.png","seller_id":"2453844006","shop_title":"hassondanny旗舰店","small_images":{"string":["http://img.alicdn.com/tfscom/i3/2453844006/O1CN011fSn6afIb0qhlOt_!!0-item_pic.jpg","http://img.alicdn.com/tfscom/i3/2453844006/O1CN011fSn6aJniobio7Y_!!2453844006.jpg","http://img.alicdn.com/tfscom/i2/2453844006/TB22QQIvVooBKNjSZFPXXXa2XXa_!!2453844006.jpg","http://img.alicdn.com/tfscom/i4/2453844006/O1CN01x9D7tF1fSn79qu1yE_!!2453844006.jpg"]},"title":"哈森丹尼秋冬季新款儿童装加绒卫衣中大童加厚保暖韩版上衣潮男童","user_type":"1","volume":"475","zk_final_price":"49.90"},{"category":"16","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=XMusR1acwcMGQASttHIRqY7%2FFnW0J%2Fo1ecW7Iy%2F1FY%2FGVWvbOqykrgOuJXrLryZZ5Ccee4mWjzXg9gGa5%2BE%2Bc8dyZ%2FopsYxUbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-29","coupon_info":"满16元减5元","coupon_remain_count":"400","coupon_start_time":"2018-11-29","coupon_total_count":"500","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=580463243210","nick":"genxinli123","num_iid":"580463243210","pict_url":"http://img.alicdn.com/tfscom/i2/2639837995/O1CN0128vl0xDGl4ioqoT_!!2639837995.jpg","seller_id":"2639837995","shop_title":"三木子快时尚女装","small_images":{"string":["http://img.alicdn.com/tfscom/i3/2639837995/O1CN0128vl0xrjNKLsmhc_!!2639837995.jpg","http://img.alicdn.com/tfscom/i4/2639837995/O1CN0128vl0qz4W3UFr13_!!2639837995.jpg","http://img.alicdn.com/tfscom/i4/2639837995/O1CN0128vl0yiqTVS6XMy_!!2639837995.jpg","http://img.alicdn.com/tfscom/i2/2639837995/O1CN0128vl11jZ9ya7tT3_!!2639837995.jpg"]},"title":"晚晚风复古套装秋冬新款连帽加绒卫衣+豹纹包臀半身裙子两件套女","user_type":"0","volume":"223","zk_final_price":"39.88"},{"category":"16","commission_rate":"9.00","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=7iZDASP9YmMGQASttHIRqeAEgVHW5DyvBs%2ByHBjUrhHGVWvbOqykrpIRQAFNNU9MjPu8lOPMrTk1tOLVGQ1qHSCKm5afTLRVbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-12-31","coupon_info":"满15元减2元","coupon_remain_count":"4989","coupon_start_time":"2018-11-28","coupon_total_count":"5000","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=580085595370","nick":"朝酷之家","num_iid":"580085595370","pict_url":"http://img.alicdn.com/tfscom/i4/1112725738/O1CN011sG35V1qAbyVLmN_!!0-item_pic.jpg","seller_id":"2968035931","shop_title":"朝酷之家分店","small_images":{"string":["http://img.alicdn.com/tfscom/i3/1112725738/O1CN011sG35Sc0GqWBgGS_!!1112725738.jpg","http://img.alicdn.com/tfscom/i1/1112725738/O1CN011sG35NW3zcftgOu_!!1112725738.jpg","http://img.alicdn.com/tfscom/i4/1112725738/O1CN011sG35Txh1NYMBPj_!!1112725738.jpg","http://img.alicdn.com/tfscom/i2/1112725738/O1CN011sG35SBFVX9p4DQ_!!1112725738.jpg"]},"title":"潮流前线2018秋冬新品女士牛仔裤修身韩版九分裤紧身中腰铅笔女裤","user_type":"0","volume":"0","zk_final_price":"99.74"},{"category":"16","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=oRl0Y5NbfhgGQASttHIRqQN7fzYY9HkLHtBItxCWn5vyi1aSyqbpmlCfzYJEQotpoE0phiJbCKEs3eNA2I0wfPt3obvA%2BNv6bd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-29","coupon_info":"满5元减3元","coupon_remain_count":"95","coupon_start_time":"2018-11-29","coupon_total_count":"100","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=577849531077","nick":"我不买你的不要生气啊","num_iid":"577849531077","pict_url":"http://img.alicdn.com/tfscom/i3/2667705257/O1CN011ohkZpA8q4yU7uu_!!2667705257.jpg","seller_id":"2667705257","shop_title":"雨墨饰界666","small_images":{"string":["http://img.alicdn.com/tfscom/i2/2667705257/O1CN011ohkZpVeejYvdSL_!!2667705257.jpg","http://img.alicdn.com/tfscom/i1/2667705257/O1CN011ohkZp1f1hzUYBa_!!2667705257.jpg","http://img.alicdn.com/tfscom/i4/2667705257/O1CN011ohkZnBEuYfj71H_!!2667705257.jpg"]},"title":"11-12-13-15-16岁初高中小学生大童女孩子夏装新款半身裙百褶短裙","user_type":"0","volume":"0","zk_final_price":"39.18"},{"category":"16","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=ueTj%2F1FX%2FwQGQASttHIRqf%2FWf2uwByyLZ2k%2BM8fsuEvGVWvbOqykrpRgLSo%2BoctduPVe6eJw74TJ%2FMY%2Febg5ZS8CzIQqNGU7bd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-30","coupon_info":"满19元减3元","coupon_remain_count":"52800","coupon_start_time":"2018-11-28","coupon_total_count":"55000","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=571673741912","nick":"仙元素旗舰店","num_iid":"571673741912","pict_url":"http://img.alicdn.com/tfscom/i3/1136920335/TB2xfN9x7KWBuNjy1zjXXcOypXa_!!1136920335-0-item_pic.jpg","seller_id":"1136920335","shop_title":"仙元素旗舰店","small_images":{"string":["http://img.alicdn.com/tfscom/i4/1136920335/TB2uIesxYSYBuNjSspiXXXNzpXa_!!1136920335.jpg","http://img.alicdn.com/tfscom/i4/1136920335/TB2ZjFxpTdYBeNkSmLyXXXfnVXa_!!1136920335.jpg","http://img.alicdn.com/tfscom/i4/1136920335/TB2bOd.x4SYBuNjSsphXXbGvVXa_!!1136920335.jpg","http://img.alicdn.com/tfscom/i1/1136920335/TB2gA63pyCYBuNkHFCcXXcHtVXa_!!1136920335.jpg"]},"title":"夏装2018新款宽松t恤女短袖 韩范闺蜜装潮佛系少女衣服街拍款女装","user_type":"1","volume":"15","zk_final_price":"19.90"},{"category":"50006842","commission_rate":"2.70","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=mE9U5tEPFgQGQASttHIRqbE86WJHEc7PrUztRBVuQSzB76UlhXoJ9%2FgrPjl0r%2BrIFjmdHktNSdAtWr9v52atj8bWPGd5Kuavbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-29","coupon_info":"满14元减2元","coupon_remain_count":"999","coupon_start_time":"2018-11-29","coupon_total_count":"999","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=578349203531","nick":"好质量购物店","num_iid":"578349203531","pict_url":"http://img.alicdn.com/tfscom/i2/3245614262/O1CN011hM2UfEs6Ps2HwK_!!3245614262.jpg","seller_id":"3245614262","shop_title":"2018夏季清凉服饰","small_images":{"string":["http://img.alicdn.com/tfscom/i1/3245614262/O1CN011hM2UdWBJjjoZvx_!!3245614262.jpg","http://img.alicdn.com/tfscom/i4/3245614262/O1CN011hM2UeOXf52oOw5_!!3245614262.jpg","http://img.alicdn.com/tfscom/i1/3245614262/O1CN011hM2UdoTDtg1dMQ_!!3245614262.jpg","http://img.alicdn.com/tfscom/i1/3245614262/O1CN011hM2Ud3VzmpQijS_!!3245614262.jpg"]},"title":"箱包单肩包女士包袋包女热销女包男包休闲手拎大容量防水皮具","user_type":"0","volume":"0","zk_final_price":"16.00"},{"category":"50006842","commission_rate":"9.00","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=F%2Fphjx9BC24GQASttHIRqf%2BuctjOpARBuzh60EqMIm0h4%2FBFCEkLWIwMrwq4Tf8J9MAI04xZ5Quxz%2FsvvApg0M2INda4ht5Pbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-29","coupon_info":"满5元减1元","coupon_remain_count":"58","coupon_start_time":"2018-11-29","coupon_total_count":"70","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=578814366313","nick":"文昌百货商场","num_iid":"578814366313","pict_url":"http://img.alicdn.com/tfscom/i2/3930641132/O1CN011KEUoG2AY9UaL6e_!!3930641132.jpg","seller_id":"3930641132","shop_title":"文昌百货商场","small_images":{"string":["http://img.alicdn.com/tfscom/i4/3930641132/O1CN011KEUoHB47YaukoZ_!!3930641132.jpg","http://img.alicdn.com/tfscom/i3/3930641132/O1CN011KEUoAQUcdkuzo7_!!3930641132.jpg","http://img.alicdn.com/tfscom/i1/3930641132/O1CN011KEUoHOfH2PhhUf_!!3930641132.jpg","http://img.alicdn.com/tfscom/i4/3930641132/O1CN011KEUoGg4C7ebC8b_!!3930641132.jpg"]},"title":"箱包斜挎包女士小包包袋热销女包女皮具男包斜跨拼接布新款尼龙","user_type":"0","volume":"0","zk_final_price":"16.71"},{"category":"50006842","commission_rate":"9.00","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=tJqCW8utiQ4GQASttHIRqXA1XcXYJKI1GyB9O%2BpJf7nbLaGB9uZG%2Bnw6EvCde%2F5dy4OtFw5ASEQqo1mJdOnr4ci%2FoD00Ft8Gbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-29","coupon_info":"满5元减3元","coupon_remain_count":"85","coupon_start_time":"2018-11-29","coupon_total_count":"100","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=580253159304","nick":"tb137820559","num_iid":"580253159304","pict_url":"http://img.alicdn.com/tfscom/i2/3137015320/O1CN011pAbXIM3rMXKSvS_!!3137015320.jpg","seller_id":"3137015320","shop_title":"d[u3137015320]","small_images":{"string":["http://img.alicdn.com/tfscom/i4/3137015320/O1CN011pAbXOtjdLwfa5d_!!3137015320.jpg","http://img.alicdn.com/tfscom/i1/3137015320/O1CN011pAbXQ4pZvPMrln_!!3137015320.jpg","http://img.alicdn.com/tfscom/i1/3137015320/O1CN011pAbXOe1rjJwRBg_!!3137015320.jpg"]},"title":"休闲单肩包女士包袋包女热销女包男包箱包手拎大容量防水皮具","user_type":"0","volume":"0","zk_final_price":"15.68"},{"category":"16","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=M96REbgd9CEGQASttHIRqVMvRwWPQTWwR1jufsD7hWLGVWvbOqykrgOuJXrLryZZ5Ccee4mWjzXg9gGa5%2BE%2Bc8dyZ%2FopsYxUbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-29","coupon_info":"满16元减5元","coupon_remain_count":"400","coupon_start_time":"2018-11-29","coupon_total_count":"500","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=576945319070","nick":"genxinli123","num_iid":"576945319070","pict_url":"http://img.alicdn.com/tfscom/i1/2639837995/O1CN0128vl08NhVdtxLnX_!!2639837995.jpg","seller_id":"2639837995","shop_title":"三木子快时尚女装","small_images":{"string":["http://img.alicdn.com/tfscom/i2/2639837995/O1CN0128vl08ZxmG1HS9L_!!2639837995.jpg","http://img.alicdn.com/tfscom/i2/2639837995/TB2L1ESvAUmBKNjSZFOXXab2XXa_!!2639837995.jpg","http://img.alicdn.com/tfscom/i3/2639837995/TB2CNXfvTCWBKNjSZFtXXaC3FXa_!!2639837995.jpg","http://img.alicdn.com/tfscom/i2/2639837995/O1CN0128vl0ArDTTXwmXr_!!2639837995.jpg"]},"title":"秋季女装新款复古简约纯色百搭圆领宽松薄款卫衣学生慵懒T恤上衣","user_type":"0","volume":"170","zk_final_price":"25.88"},{"category":"16","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=JCGvqIH5qu4GQASttHIRqdaDprpoL47hMjB6L7uFAujyi1aSyqbpmvxyQ6NTyKVaWndF9t39FeF4UpfPzzlEYWpjkgFqtOSrbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-29","coupon_info":"满5元减3元","coupon_remain_count":"100","coupon_start_time":"2018-11-29","coupon_total_count":"100","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=579288797226","nick":"张晏晏77581286","num_iid":"579288797226","pict_url":"http://img.alicdn.com/tfscom/i3/3231909752/O1CN012LuStp82DEYIKNX_!!3231909752.jpg","seller_id":"3231909752","shop_title":"一帘幽梦111","small_images":{"string":["http://img.alicdn.com/tfscom/i2/3231909752/O1CN012LuStoYYotfeYkr_!!3231909752.jpg","http://img.alicdn.com/tfscom/i3/3231909752/O1CN012LuStnFBdTrk8Ne_!!3231909752.jpg","http://img.alicdn.com/tfscom/i2/3231909752/O1CN012LuSti50jEN44IL_!!3231909752.jpg"]},"title":"11-12-13-15-16岁初高中小学生大童女孩子夏装新款半身裙百褶短裙","user_type":"0","volume":"0","zk_final_price":"40.64"},{"category":"16","commission_rate":"1.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=lXdotriOGikGQASttHIRqfTcWbSBMAp%2B%2BOQpvI6u%2FgmOOynoqP64GfoDScpgu1GV5rOIFjk8RUFujZQDt%2B%2FFLgf13vwaMYUXbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIGmY4hFfhtzCZ6Y%2FpkHtT5QS0Flu%2FfbSp4QsdWMikAatjH%2B8knoxVQDnios39sQPM%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-12-30","coupon_info":"满15元减3元","coupon_remain_count":"91667","coupon_start_time":"2018-11-28","coupon_total_count":"100000","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=581280633598","nick":"纤依百秀","num_iid":"581280633598","pict_url":"http://img.alicdn.com/tfscom/i2/50594204/O1CN011gvTXCOTg4yFfGI_!!50594204.jpg","seller_id":"50594204","shop_title":"纤依百秀女装倾销店","small_images":{"string":["http://img.alicdn.com/tfscom/i4/50594204/O1CN011gvTXFjePxT9nh2_!!50594204.jpg","http://img.alicdn.com/tfscom/i1/50594204/O1CN01MMXWpe1gvTXDMtioQ_!!50594204.jpg","http://img.alicdn.com/tfscom/i4/50594204/O1CN01LsWMko1gvTXCOU530_!!50594204.jpg","http://img.alicdn.com/tfscom/i4/50594204/O1CN011gvTXDMsiOikKJl_!!50594204.jpg"]},"title":"货洋气女装韩显瘦立领羽绒棉马甲金丝绒马甲收腰显瘦中长百搭辣","user_type":"0","volume":"424","zk_final_price":"79.00"},{"category":"50006842","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=K9j%2BbxUXcPcGQASttHIRqR367nHPYBvXLIQAYdJxHQRYomNqLX%2FiF1yX5eoSot%2BldmxZR5LhKL9rLR0cegQAXt8kHnYdN%2FyAbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-29","coupon_info":"满5元减3元","coupon_remain_count":"500","coupon_start_time":"2018-11-29","coupon_total_count":"500","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=580381198431","nick":"实惠店子","num_iid":"580381198431","pict_url":"http://img.alicdn.com/tfscom/i4/3420235392/O1CN011pha3Sf3FMtx1YO_!!3420235392.jpg","seller_id":"3420235392","shop_title":"实惠店子","small_images":{"string":["http://img.alicdn.com/tfscom/i3/3420235392/O1CN011pha3U8d5NXxqBH_!!3420235392.jpg","http://img.alicdn.com/tfscom/i1/3420235392/O1CN011pha3Sf2i7Vy4mz_!!3420235392.jpg","http://img.alicdn.com/tfscom/i3/3420235392/O1CN011pha3VzJ7Vj8xWt_!!3420235392.jpg"]},"title":"休闲单肩包女士包袋包女热销女包男包箱包手拎大容量防水皮具","user_type":"0","volume":"0","zk_final_price":"20.00"},{"category":"16","commission_rate":"18.00","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=JyFFMFLd5UcGQASttHIRqX4rLLJZRhV02QTtTzfmuMbbLaGB9uZG%2BsMsp%2FtiTD3lb4Xw5n%2FWTZfC2YmLlgdeOuXPbOSng%2FLLbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-12-31","coupon_info":"满12元减3元","coupon_remain_count":"9800","coupon_start_time":"2018-11-28","coupon_total_count":"10000","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=581184717198","nick":"宏图hongtu11","num_iid":"581184717198","pict_url":"http://img.alicdn.com/tfscom/i4/2944514513/O1CN01ZwnrAu1jCzuvvJS0g_!!2944514513.jpg","seller_id":"2944514513","shop_title":"u[2944514513]","small_images":{"string":["http://img.alicdn.com/tfscom/i2/2944514513/O1CN01yFT8se1jCzuwWwmCX_!!2944514513.jpg","http://img.alicdn.com/tfscom/i4/2944514513/O1CN011jCzuuLNSFxQvNs_!!2944514513.jpg","http://img.alicdn.com/tfscom/i1/2944514513/O1CN011jCzuxEjuIX4E8t_!!2944514513.jpg","http://img.alicdn.com/tfscom/i4/2944514513/O1CN011jCzuvrmJuTrJn3_!!2944514513.jpg"]},"title":"长款大码女士打底裤女性感加绒2018保暖秋冬装潮流紧身裤加厚新款","user_type":"0","volume":"0","zk_final_price":"18.48"},{"category":"16","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=e3ZhQ5uhkKwGQASttHIRqTow9oqW0nKfkURsYM3pF4Ll43M3mIB1tydGHuFEKrhJeKVAXsb%2Fl5AD9tcDvcL12riselkgb7gLbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-30","coupon_info":"满10元减3元","coupon_remain_count":"50000","coupon_start_time":"2018-11-28","coupon_total_count":"100000","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=579597008768","nick":"倩影韩风","num_iid":"579597008768","pict_url":"http://img.alicdn.com/tfscom/i2/192707956/O1CN0128dtZBWmv7DXhyE_!!192707956.jpg","seller_id":"192707956","shop_title":"QGZ七公主女装","small_images":{"string":["http://img.alicdn.com/tfscom/i3/192707956/O1CN0128dtZBC31rXuasB_!!192707956.jpg","http://img.alicdn.com/tfscom/i2/192707956/O1CN0128dtZC47XTNyI42_!!192707956.jpg","http://img.alicdn.com/tfscom/i3/192707956/O1CN0128dtZCvL0RJGUym_!!192707956.jpg","http://img.alicdn.com/tfscom/i2/192707956/O1CN0128dtZC49Lgq4gf8_!!192707956.jpg"]},"title":"秋冬新款chic高腰系带麻花纹理针织裤时尚百搭宽松阔腿长裤女潮流","user_type":"0","volume":"644","zk_final_price":"55.98"},{"category":"16","commission_rate":"4.50","coupon_click_url":"https://uland.taobao.com/coupon/edetail?e=owWowT1KBh0GQASttHIRqavhWrz%2FYruPMbKc0M1BbgLl43M3mIB1tydGHuFEKrhJeKVAXsb%2Fl5AD9tcDvcL12riselkgb7gLbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e","coupon_end_time":"2018-11-30","coupon_info":"满10元减3元","coupon_remain_count":"50000","coupon_start_time":"2018-11-28","coupon_total_count":"100000","item_description":[],"item_url":"https://detail.tmall.com/item.htm?id=577713350385","nick":"倩影韩风","num_iid":"577713350385","pict_url":"http://img.alicdn.com/tfscom/i2/192707956/O1CN0128dtYZruvLaW8qr_!!192707956.jpg","seller_id":"192707956","shop_title":"QGZ七公主女装","small_images":{"string":["http://img.alicdn.com/tfscom/i3/192707956/TB2FqN9a9zqK1RjSZFLXXcn2XXa_!!192707956.jpg","http://img.alicdn.com/tfscom/i3/192707956/TB2TlB9aYPpK1RjSZFFXXa5PpXa_!!192707956.jpg","http://img.alicdn.com/tfscom/i1/192707956/TB2Fy49a3HqK1RjSZFkXXX.WFXa_!!192707956.jpg","http://img.alicdn.com/tfscom/i4/192707956/TB2Fzl9a4TpK1RjSZFGXXcHqFXa_!!192707956.jpg"]},"title":"慵懒风拼接网纱系带长袖圆领针织衫+时尚chic仙女气质网纱蛋糕裙","user_type":"0","volume":"338","zk_final_price":"42.98"}]}
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
        private List<TbkCouponBean> tbk_coupon;

        public List<TbkCouponBean> getTbk_coupon() {
            return tbk_coupon;
        }

        public void setTbk_coupon(List<TbkCouponBean> tbk_coupon) {
            this.tbk_coupon = tbk_coupon;
        }

        public static class TbkCouponBean {
            /**
             * category : 50020808
             * commission_rate : 4.50
             * coupon_click_url : https://uland.taobao.com/coupon/edetail?e=xNLhlllCMkYGQASttHIRqUYwMhgP1uVpS7xtgf2XWVaAnVXUwZx8IpOk3LaoKR3FCfPlN1LBG6aa4VRjYTjloRfiogjUXLsIbd76m3V5xpaZfl6r21MxewGThUhdWASnrGjLc%2FZqok5e%2Ff9urIywqgMFTfGLA%2FdyIXjFM5C6%2BEIVF%2BLQAJXviBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlGiI%2FUfnmtgWvQlAmWaphAQ%3D%3D&traceId=0b15050e15434597436284277e
             * coupon_end_time : 2018-11-29
             * coupon_info : 满4元减3元
             * coupon_remain_count : 999
             * coupon_start_time : 2018-11-29
             * coupon_total_count : 999
             * item_description : []
             * item_url : https://detail.tmall.com/item.htm?id=578834910065
             * nick : 丹丹爱的小窝
             * num_iid : 578834910065
             * pict_url : http://img.alicdn.com/tfscom/i1/835933167/O1CN011ZGWnu70uluvMaK_!!835933167.jpg
             * seller_id : 835933167
             * shop_title : 带上潮包去旅行
             * small_images : {"string":"http://img.alicdn.com/tfscom/i4/835933167/O1CN011ZGWntX8kXAgjNX_!!835933167.jpg"}
             * title : 包邮mm巧克力豆小磁铁强磁冰箱贴 圆形强力创意磁贴办公教具
             * user_type : 0
             * volume : 0
             * zk_final_price : 18.15
             */

            private String category;
            private String commission_rate;
            private String coupon_click_url;
            private String coupon_end_time;
            private String coupon_info;
            private String coupon_remain_count;
            private String coupon_start_time;
            private String coupon_total_count;
            private String item_url;
            private String nick;
            private String num_iid;
            private String pict_url;
            private String seller_id;
            private String shop_title;
            private SmallImagesBean small_images;
            private String title;
            private String user_type;
            private String volume;
            private String zk_final_price;
            private List<?> item_description;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getCommission_rate() {
                return commission_rate;
            }

            public void setCommission_rate(String commission_rate) {
                this.commission_rate = commission_rate;
            }

            public String getCoupon_click_url() {
                return coupon_click_url;
            }

            public void setCoupon_click_url(String coupon_click_url) {
                this.coupon_click_url = coupon_click_url;
            }

            public String getCoupon_end_time() {
                return coupon_end_time;
            }

            public void setCoupon_end_time(String coupon_end_time) {
                this.coupon_end_time = coupon_end_time;
            }

            public String getCoupon_info() {
                return coupon_info;
            }

            public void setCoupon_info(String coupon_info) {
                this.coupon_info = coupon_info;
            }

            public String getCoupon_remain_count() {
                return coupon_remain_count;
            }

            public void setCoupon_remain_count(String coupon_remain_count) {
                this.coupon_remain_count = coupon_remain_count;
            }

            public String getCoupon_start_time() {
                return coupon_start_time;
            }

            public void setCoupon_start_time(String coupon_start_time) {
                this.coupon_start_time = coupon_start_time;
            }

            public String getCoupon_total_count() {
                return coupon_total_count;
            }

            public void setCoupon_total_count(String coupon_total_count) {
                this.coupon_total_count = coupon_total_count;
            }

            public String getItem_url() {
                return item_url;
            }

            public void setItem_url(String item_url) {
                this.item_url = item_url;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getNum_iid() {
                return num_iid;
            }

            public void setNum_iid(String num_iid) {
                this.num_iid = num_iid;
            }

            public String getPict_url() {
                return pict_url;
            }

            public void setPict_url(String pict_url) {
                this.pict_url = pict_url;
            }

            public String getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(String seller_id) {
                this.seller_id = seller_id;
            }

            public String getShop_title() {
                return shop_title;
            }

            public void setShop_title(String shop_title) {
                this.shop_title = shop_title;
            }

            public SmallImagesBean getSmall_images() {
                return small_images;
            }

            public void setSmall_images(SmallImagesBean small_images) {
                this.small_images = small_images;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUser_type() {
                return user_type;
            }

            public void setUser_type(String user_type) {
                this.user_type = user_type;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getZk_final_price() {
                return zk_final_price;
            }

            public void setZk_final_price(String zk_final_price) {
                this.zk_final_price = zk_final_price;
            }

            public List<?> getItem_description() {
                return item_description;
            }

            public void setItem_description(List<?> item_description) {
                this.item_description = item_description;
            }

            public static class SmallImagesBean {
                /**
                 * string : http://img.alicdn.com/tfscom/i4/835933167/O1CN011ZGWntX8kXAgjNX_!!835933167.jpg
                 */

                private String string;

                public String getString() {
                    return string;
                }

                public void setString(String string) {
                    this.string = string;
                }
            }
        }
    }
}
