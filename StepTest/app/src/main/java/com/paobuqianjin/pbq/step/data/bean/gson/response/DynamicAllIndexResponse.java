package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.util.List;

/**
 * Created by pbq on 2017/12/26.
 */
/*
@className :DynamicAllIndexResponse
*@date 2018/1/12
*@author
*@description   GET index 获取动态列表
*/
public class DynamicAllIndexResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":1,"totalPage":40,"totalCount":40},"data":[{"id":101,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg",""],"city":"深圳市","vote":1,"comment":3,"create_time":1520674416,"one_comment":{"id":236,"parent_id":0,"reply_userid":1,"userid":30,"dynamicid":101,"content":"59999","create_time":1520856704,"nickname":"黄钦平"},"is_vote":1},{"id":95,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"看看","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180309_104301.jpg",""],"city":"深圳市","vote":1,"comment":0,"create_time":1520564235,"one_comment":{},"is_vote":0},{"id":94,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"阿狸了","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180309_104301.jpg",""],"city":"深圳市","vote":0,"comment":0,"create_time":1520564082,"one_comment":{},"is_vote":0},{"id":93,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"安卓测试","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180309_104301.jpg",""],"city":"深圳市","vote":1,"comment":0,"create_time":1520563704,"one_comment":{},"is_vote":1},{"id":69,"userid":61,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","nickname":"俊一共可以有十六个字符以内都可以","dynamic":"加油加油加油！","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/C4D015CE-F59E-4C40-A0CB-3B261480DFFC.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6DBBFB6C-B1DB-4425-8B40-284A41DDC2F4.jpg"],"city":"深圳市","vote":3,"comment":10,"create_time":1520336444,"one_comment":{"id":195,"parent_id":0,"reply_userid":61,"userid":63,"dynamicid":69,"content":"图片拉伸了","create_time":1520342048,"nickname":"旺旺xwy"},"is_vote":1},{"id":68,"userid":61,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","nickname":"俊一共可以有十六个字符以内都可以","dynamic":"抱抱胖胖的自己","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/01751840-DC60-48AC-B36F-0C719535D0B6.jpg"],"city":"深圳市","vote":0,"comment":2,"create_time":1520335885,"one_comment":{"id":226,"parent_id":0,"reply_userid":61,"userid":57,"dynamicid":68,"content":"韩国复古好","create_time":1520495009,"nickname":"周周周"},"is_vote":0},{"id":62,"userid":63,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/8042CCF8-59DD-475D-A79D-09E40F6F5CA5.jpg","nickname":"旺旺xwy","dynamic":"我不知道为什么","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/8526FD5B-2505-4019-ADFA-9BD10FACFA29.jpg"],"city":"深圳市","vote":1,"comment":16,"create_time":1519959353,"one_comment":{"id":49,"parent_id":0,"reply_userid":0,"userid":60,"dynamicid":62,"content":"我是张丽薇！","create_time":1520249812,"nickname":"Annabelle"},"is_vote":1},{"id":55,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"你的手机没有话费哦！你","images":[],"city":"深圳市","vote":1,"comment":2,"create_time":1519890774,"one_comment":{"id":145,"parent_id":0,"reply_userid":1,"userid":30,"dynamicid":55,"content":"哦抱歉","create_time":1520322263,"nickname":"黄钦平"},"is_vote":1},{"id":54,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"我们的生活方式是什么时候回来呀","images":[],"city":"深圳市","vote":0,"comment":0,"create_time":1519873949,"one_comment":{},"is_vote":0},{"id":53,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"测试一下2","images":[],"city":"深圳","vote":2,"comment":0,"create_time":1519811735,"one_comment":{},"is_vote":1},{"id":52,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"测试一下","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1519811647,"one_comment":{},"is_vote":0},{"id":50,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"湖北卫视黄金档强势开播","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/F8055DC4-B930-486F-A99A-AB3DC11A4973.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/A7E8F75D-DF7B-4B85-84DE-00EBFE0ED487.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/5F539D1E-2FB9-4675-AED7-E8D80C59A129.jpg"],"city":"深圳市","vote":0,"comment":0,"create_time":1519802048,"one_comment":{},"is_vote":0},{"id":49,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"我的手机号码已经收到\n","images":[],"city":"深圳市","vote":1,"comment":0,"create_time":1519790437,"one_comment":{},"is_vote":1},{"id":48,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"打开多久啊","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1519731410,"one_comment":{},"is_vote":0},{"id":44,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"4图片动态","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519625328,"one_comment":{},"is_vote":0},{"id":43,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"3图片动态","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519625318,"one_comment":{},"is_vote":0},{"id":42,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"2图片动态","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519625305,"one_comment":{},"is_vote":0},{"id":41,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"1图片动态","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519625294,"one_comment":{},"is_vote":0},{"id":40,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"1图片动态","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1519625257,"one_comment":{},"is_vote":0},{"id":39,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"无图片动态","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1519625234,"one_comment":{},"is_vote":0},{"id":38,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"动次打次啊A","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519624993,"one_comment":{},"is_vote":0},{"id":37,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"动次打次啊A","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519624983,"one_comment":{},"is_vote":0},{"id":36,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"动次打次啊","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1519624971,"one_comment":{},"is_vote":0},{"id":33,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"我饿哒嗯嗯","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519143528,"one_comment":{},"is_vote":0},{"id":27,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"最后一次煤油没有内容","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1515756743,"one_comment":{},"is_vote":0},{"id":26,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"还是煤油没有内容","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1515756570,"one_comment":{},"is_vote":0},{"id":25,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"没有内容","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1515756175,"one_comment":{},"is_vote":0},{"id":24,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"zzz","vote":0,"comment":0,"create_time":1514963876,"one_comment":{},"is_vote":0},{"id":23,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"zzz","vote":0,"comment":1,"create_time":1514963855,"one_comment":{"id":25,"parent_id":1,"reply_userid":1,"userid":1,"dynamicid":23,"content":"AAAAA2323","create_time":1520227764,"nickname":"嗯额"},"is_vote":0},{"id":19,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1514961395,"one_comment":{},"is_vote":0},{"id":18,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1514961235,"one_comment":{},"is_vote":0},{"id":16,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"你是不是傻？？","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":2,"comment":0,"create_time":1514873370,"one_comment":{},"is_vote":0},{"id":5,"userid":5,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央","dynamic":"看到刘亦菲这些照片，我要哭了","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"东莞","vote":100,"comment":1,"create_time":1513393715,"one_comment":{},"is_vote":0},{"id":3,"userid":3,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣","dynamic":"王祖蓝晒与宋仲基的合照，笑称长胖了 网友表示：果然幸福的人都一样啊！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"江门","vote":100,"comment":0,"create_time":1513393713,"one_comment":{},"is_vote":0},{"id":2,"userid":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","dynamic":"我有个小小的愿望，下次如路人相遇的时候，人们都能大大方方的来打个招呼，而不是偷偷的举起相机。","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"惠州","vote":100,"comment":6,"create_time":1513393712,"one_comment":{"id":10,"parent_id":0,"reply_userid":0,"userid":32,"dynamicid":2,"content":"哎呦","create_time":0,"nickname":"rm_13424156025"},"is_vote":0},{"id":4,"userid":4,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","nickname":"团魂","dynamic":"被问到从王思聪薛之谦吴亦凡中选一个拍吻戏时，韩雪竟这样回答！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"广州","vote":100,"comment":0,"create_time":1513393712,"one_comment":{},"is_vote":0},{"id":6,"userid":6,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","dynamic":"5位明星想要掩盖的身体缺陷，他摔倒失去蛋蛋，她天生没有子宫！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":0,"create_time":1513393712,"one_comment":{},"is_vote":0},{"id":8,"userid":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","dynamic":"易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":113,"comment":91,"create_time":1513393712,"one_comment":{"id":22,"parent_id":0,"reply_userid":0,"userid":1,"dynamicid":8,"content":"wwwwwee","create_time":1520060313,"nickname":"嗯额"},"is_vote":0},{"id":1,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"你马上要8岁了哟 看我一眼好不好","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":53,"create_time":1513393712,"one_comment":{"id":2,"parent_id":0,"reply_userid":0,"userid":3,"dynamicid":1,"content":"原图老哥谢谢","create_time":1513394223,"nickname":"九卿臣"},"is_vote":0},{"id":7,"userid":7,"avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","nickname":"沉秋","dynamic":"赵丽颖被各卫视拒上跨年，现又被春晚拒绝，曝光的真相你能接受吗？","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"广州","vote":100,"comment":5,"create_time":1513393712,"one_comment":{"id":21,"parent_id":0,"reply_userid":0,"userid":1,"dynamicid":7,"content":"223333","create_time":1520060206,"nickname":"嗯额"},"is_vote":0}]}
     */

    private int error;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DynamicAllIndexResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":1,"totalPage":40,"totalCount":40}
         * data : [{"id":101,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg",""],"city":"深圳市","vote":1,"comment":3,"create_time":1520674416,"one_comment":{"id":236,"parent_id":0,"reply_userid":1,"userid":30,"dynamicid":101,"content":"59999","create_time":1520856704,"nickname":"黄钦平"},"is_vote":1},{"id":95,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"看看","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180309_104301.jpg",""],"city":"深圳市","vote":1,"comment":0,"create_time":1520564235,"one_comment":{},"is_vote":0},{"id":94,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"阿狸了","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180309_104301.jpg",""],"city":"深圳市","vote":0,"comment":0,"create_time":1520564082,"one_comment":{},"is_vote":0},{"id":93,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"安卓测试","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180309_104301.jpg",""],"city":"深圳市","vote":1,"comment":0,"create_time":1520563704,"one_comment":{},"is_vote":1},{"id":69,"userid":61,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","nickname":"俊一共可以有十六个字符以内都可以","dynamic":"加油加油加油！","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/C4D015CE-F59E-4C40-A0CB-3B261480DFFC.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/6DBBFB6C-B1DB-4425-8B40-284A41DDC2F4.jpg"],"city":"深圳市","vote":3,"comment":10,"create_time":1520336444,"one_comment":{"id":195,"parent_id":0,"reply_userid":61,"userid":63,"dynamicid":69,"content":"图片拉伸了","create_time":1520342048,"nickname":"旺旺xwy"},"is_vote":1},{"id":68,"userid":61,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","nickname":"俊一共可以有十六个字符以内都可以","dynamic":"抱抱胖胖的自己","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/01751840-DC60-48AC-B36F-0C719535D0B6.jpg"],"city":"深圳市","vote":0,"comment":2,"create_time":1520335885,"one_comment":{"id":226,"parent_id":0,"reply_userid":61,"userid":57,"dynamicid":68,"content":"韩国复古好","create_time":1520495009,"nickname":"周周周"},"is_vote":0},{"id":62,"userid":63,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/8042CCF8-59DD-475D-A79D-09E40F6F5CA5.jpg","nickname":"旺旺xwy","dynamic":"我不知道为什么","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/8526FD5B-2505-4019-ADFA-9BD10FACFA29.jpg"],"city":"深圳市","vote":1,"comment":16,"create_time":1519959353,"one_comment":{"id":49,"parent_id":0,"reply_userid":0,"userid":60,"dynamicid":62,"content":"我是张丽薇！","create_time":1520249812,"nickname":"Annabelle"},"is_vote":1},{"id":55,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"你的手机没有话费哦！你","images":[],"city":"深圳市","vote":1,"comment":2,"create_time":1519890774,"one_comment":{"id":145,"parent_id":0,"reply_userid":1,"userid":30,"dynamicid":55,"content":"哦抱歉","create_time":1520322263,"nickname":"黄钦平"},"is_vote":1},{"id":54,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"我们的生活方式是什么时候回来呀","images":[],"city":"深圳市","vote":0,"comment":0,"create_time":1519873949,"one_comment":{},"is_vote":0},{"id":53,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"测试一下2","images":[],"city":"深圳","vote":2,"comment":0,"create_time":1519811735,"one_comment":{},"is_vote":1},{"id":52,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"测试一下","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1519811647,"one_comment":{},"is_vote":0},{"id":50,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"湖北卫视黄金档强势开播","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/F8055DC4-B930-486F-A99A-AB3DC11A4973.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/A7E8F75D-DF7B-4B85-84DE-00EBFE0ED487.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/5F539D1E-2FB9-4675-AED7-E8D80C59A129.jpg"],"city":"深圳市","vote":0,"comment":0,"create_time":1519802048,"one_comment":{},"is_vote":0},{"id":49,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"我的手机号码已经收到\n","images":[],"city":"深圳市","vote":1,"comment":0,"create_time":1519790437,"one_comment":{},"is_vote":1},{"id":48,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"打开多久啊","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1519731410,"one_comment":{},"is_vote":0},{"id":44,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"4图片动态","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519625328,"one_comment":{},"is_vote":0},{"id":43,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"3图片动态","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519625318,"one_comment":{},"is_vote":0},{"id":42,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"2图片动态","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519625305,"one_comment":{},"is_vote":0},{"id":41,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"1图片动态","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519625294,"one_comment":{},"is_vote":0},{"id":40,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"1图片动态","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1519625257,"one_comment":{},"is_vote":0},{"id":39,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"无图片动态","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1519625234,"one_comment":{},"is_vote":0},{"id":38,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"动次打次啊A","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519624993,"one_comment":{},"is_vote":0},{"id":37,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"动次打次啊A","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519624983,"one_comment":{},"is_vote":0},{"id":36,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"动次打次啊","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1519624971,"one_comment":{},"is_vote":0},{"id":33,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"我饿哒嗯嗯","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1519143528,"one_comment":{},"is_vote":0},{"id":27,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"最后一次煤油没有内容","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1515756743,"one_comment":{},"is_vote":0},{"id":26,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"还是煤油没有内容","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1515756570,"one_comment":{},"is_vote":0},{"id":25,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"没有内容","images":[],"city":"深圳","vote":0,"comment":0,"create_time":1515756175,"one_comment":{},"is_vote":0},{"id":24,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"zzz","vote":0,"comment":0,"create_time":1514963876,"one_comment":{},"is_vote":0},{"id":23,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"zzz","vote":0,"comment":1,"create_time":1514963855,"one_comment":{"id":25,"parent_id":1,"reply_userid":1,"userid":1,"dynamicid":23,"content":"AAAAA2323","create_time":1520227764,"nickname":"嗯额"},"is_vote":0},{"id":19,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1514961395,"one_comment":{},"is_vote":0},{"id":18,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":0,"comment":0,"create_time":1514961235,"one_comment":{},"is_vote":0},{"id":16,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"你是不是傻？？","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":2,"comment":0,"create_time":1514873370,"one_comment":{},"is_vote":0},{"id":5,"userid":5,"avatar":"http://pic.qqtn.com/up/2017-12/15127898935630842.jpg","nickname":"孤央","dynamic":"看到刘亦菲这些照片，我要哭了","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"东莞","vote":100,"comment":1,"create_time":1513393715,"one_comment":{},"is_vote":0},{"id":3,"userid":3,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184280328.jpg","nickname":"九卿臣","dynamic":"王祖蓝晒与宋仲基的合照，笑称长胖了 网友表示：果然幸福的人都一样啊！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"江门","vote":100,"comment":0,"create_time":1513393713,"one_comment":{},"is_vote":0},{"id":2,"userid":2,"avatar":"http://e.hiphotos.baidu.com/image/pic/item/9922720e0cf3d7ca7f053ebcfb1fbe096a63a90c.jpg","nickname":"李五","dynamic":"我有个小小的愿望，下次如路人相遇的时候，人们都能大大方方的来打个招呼，而不是偷偷的举起相机。","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"惠州","vote":100,"comment":6,"create_time":1513393712,"one_comment":{"id":10,"parent_id":0,"reply_userid":0,"userid":32,"dynamicid":2,"content":"哎呦","create_time":0,"nickname":"rm_13424156025"},"is_vote":0},{"id":4,"userid":4,"avatar":"http://pic.qqtn.com/up/2017-12/2017120911184236664.jpg","nickname":"团魂","dynamic":"被问到从王思聪薛之谦吴亦凡中选一个拍吻戏时，韩雪竟这样回答！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"广州","vote":100,"comment":0,"create_time":1513393712,"one_comment":{},"is_vote":0},{"id":6,"userid":6,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","dynamic":"5位明星想要掩盖的身体缺陷，他摔倒失去蛋蛋，她天生没有子宫！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":0,"create_time":1513393712,"one_comment":{},"is_vote":0},{"id":8,"userid":8,"avatar":"http://pic.qqtn.com/up/2017-12/15127898937460203.jpg","nickname":"酒自斟","dynamic":"易烊千玺登银十刊封面，天生超模脸，苏芒大赞：高级脸！","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":113,"comment":91,"create_time":1513393712,"one_comment":{"id":22,"parent_id":0,"reply_userid":0,"userid":1,"dynamicid":8,"content":"wwwwwee","create_time":1520060313,"nickname":"嗯额"},"is_vote":0},{"id":1,"userid":1,"avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg","nickname":"嗯额","dynamic":"你马上要8岁了哟 看我一眼好不好","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"深圳","vote":100,"comment":53,"create_time":1513393712,"one_comment":{"id":2,"parent_id":0,"reply_userid":0,"userid":3,"dynamicid":1,"content":"原图老哥谢谢","create_time":1513394223,"nickname":"九卿臣"},"is_vote":0},{"id":7,"userid":7,"avatar":"http://pic.qqtn.com/up/2017-12/15127898936485304.jpg","nickname":"沉秋","dynamic":"赵丽颖被各卫视拒上跨年，现又被春晚拒绝，曝光的真相你能接受吗？","images":["https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg","https://wx1.sinaimg.cn/mw690/005WPY4oly1fmi8vy22pfj30io0g5t94.jpg"],"city":"广州","vote":100,"comment":5,"create_time":1513393712,"one_comment":{"id":21,"parent_id":0,"reply_userid":0,"userid":1,"dynamicid":7,"content":"223333","create_time":1520060206,"nickname":"嗯额"},"is_vote":0}]
         */

        private PagenationBean pagenation;
        private List<DataBean> data;

        public PagenationBean getPagenation() {
            return pagenation;
        }

        public void setPagenation(PagenationBean pagenation) {
            this.pagenation = pagenation;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "pagenation=" + pagenation +
                    ", data=" + data +
                    '}';
        }

        public static class PagenationBean {
            /**
             * page : 1
             * pageSize : 1
             * totalPage : 40
             * totalCount : 40
             */

            private int page;
            private int pageSize;
            private int totalPage;
            private int totalCount;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            @Override
            public String toString() {
                return "PagenationBean{" +
                        "page=" + page +
                        ", pageSize=" + pageSize +
                        ", totalPage=" + totalPage +
                        ", totalCount=" + totalCount +
                        '}';
            }
        }

        public static class DataBean {
            /**
             * id : 101
             * userid : 1
             * avatar : http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/0988B1E1-E86E-4E9E-8584-A911925440EC.jpg
             * nickname : 嗯额
             * dynamic :
             * images : ["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg",""]
             * city : 深圳市
             * vote : 1
             * comment : 3
             * create_time : 1520674416
             * one_comment : {"id":236,"parent_id":0,"reply_userid":1,"userid":30,"dynamicid":101,"content":"59999","create_time":1520856704,"nickname":"黄钦平"}
             * is_vote : 1
             */

            private int id;
            private int userid;
            private String avatar;
            private String nickname;
            private String dynamic;
            private String city;
            private int vote;
            private int comment;
            private int create_time;
            private OneCommentBean one_comment;
            private int is_vote;
            private List<String> images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getDynamic() {
                return dynamic;
            }

            public void setDynamic(String dynamic) {
                this.dynamic = dynamic;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
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

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public OneCommentBean getOne_comment() {
                return one_comment;
            }

            public void setOne_comment(OneCommentBean one_comment) {
                this.one_comment = one_comment;
            }

            public int getIs_vote() {
                return is_vote;
            }

            public void setIs_vote(int is_vote) {
                this.is_vote = is_vote;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id=" + id +
                        ", userid=" + userid +
                        ", avatar='" + avatar + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", dynamic='" + dynamic + '\'' +
                        ", city='" + city + '\'' +
                        ", vote=" + vote +
                        ", comment=" + comment +
                        ", create_time=" + create_time +
                        ", one_comment=" + one_comment +
                        ", is_vote=" + is_vote +
                        ", images=" + images +
                        '}';
            }

            public static class OneCommentBean {
                /**
                 * id : 236
                 * parent_id : 0
                 * reply_userid : 1
                 * userid : 30
                 * dynamicid : 101
                 * content : 59999
                 * create_time : 1520856704
                 * nickname : 黄钦平
                 */

                private int id;
                private int parent_id;
                private int reply_userid;
                private int userid;
                private int dynamicid;
                private String content;
                private int create_time;
                private String nickname;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getParent_id() {
                    return parent_id;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
                }

                public int getReply_userid() {
                    return reply_userid;
                }

                public void setReply_userid(int reply_userid) {
                    this.reply_userid = reply_userid;
                }

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
                }

                public int getDynamicid() {
                    return dynamicid;
                }

                public void setDynamicid(int dynamicid) {
                    this.dynamicid = dynamicid;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
                    this.create_time = create_time;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                @Override
                public String toString() {
                    return "OneCommentBean{" +
                            "id=" + id +
                            ", parent_id=" + parent_id +
                            ", reply_userid=" + reply_userid +
                            ", userid=" + userid +
                            ", dynamicid=" + dynamicid +
                            ", content='" + content + '\'' +
                            ", create_time=" + create_time +
                            ", nickname='" + nickname + '\'' +
                            '}';
                }
            }
        }
    }
}
