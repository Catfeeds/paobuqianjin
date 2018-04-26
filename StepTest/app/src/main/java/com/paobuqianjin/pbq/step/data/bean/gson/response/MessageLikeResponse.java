package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/3/27.
 */
/*
@className :MessageLikeResponse
*@date 2018/3/27
*@author
*@description 点赞消息
*/
public class MessageLikeResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":12},"data":[{"id":210,"dynamicid":125,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"Vivo Android 5.0  720*1280 ","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1523448910,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":205,"dynamicid":109,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"改变主意","dynamic":"改变主意","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170610.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg",""],"create_time":1523322156,"dynamicdata":{"id":109,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"改变主意","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170610.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg",""],"city":"深圳市","vote":1,"comment":0,"create_time":1521186763,"is_vote":1}},{"id":204,"dynamicid":110,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg",""],"create_time":1523322139,"dynamicdata":{"id":110,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg",""],"city":"深圳","vote":1,"comment":0,"create_time":1521188097,"is_vote":1}},{"id":203,"dynamicid":128,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/CROP_1522748481986.jpg"],"create_time":1523322045,"dynamicdata":{"id":128,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/CROP_1522748481986.jpg"],"city":"深圳市","vote":1,"comment":0,"create_time":1522748487,"is_vote":1}},{"id":198,"dynamicid":100,"from_userid":66,"from_nickanme":"wensen","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"create_time":1523258499,"dynamicdata":{"id":100,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"city":"深圳市","vote":2,"comment":3,"create_time":1520584220,"is_vote":0}},{"id":186,"dynamicid":100,"from_userid":57,"from_nickanme":"周粥粥","from_avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"create_time":1523243747,"dynamicdata":{"id":100,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"city":"深圳市","vote":2,"comment":3,"create_time":1520584220,"is_vote":0}},{"id":173,"dynamicid":96,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg",""],"create_time":1522830553,"dynamicdata":{"id":96,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg",""],"city":"深圳市","vote":2,"comment":0,"create_time":1520580651,"is_vote":1}},{"id":172,"dynamicid":97,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"create_time":1522830545,"dynamicdata":{"id":97,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"city":"深圳市","vote":1,"comment":0,"create_time":1520580786,"is_vote":1}},{"id":171,"dynamicid":111,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_165312.jpg"],"create_time":1522830534,"dynamicdata":{"id":111,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_165312.jpg"],"city":"深圳市","vote":1,"comment":0,"create_time":1521445371,"is_vote":1}},{"id":170,"dynamicid":129,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"好几年","dynamic":"好几年","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/I01027272.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/22-50-16-E24EBB5A20EF11FD3956D31FADD05D7C8C73DF9B_size160_w500_h310.jpeg"],"create_time":1522830523,"dynamicdata":{"id":129,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"好几年","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/I01027272.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/22-50-16-E24EBB5A20EF11FD3956D31FADD05D7C8C73DF9B_size160_w500_h310.jpeg"],"city":"深圳","vote":3,"comment":0,"create_time":1522749471,"is_vote":1}}]}
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
        return "MessageLikeResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":12}
         * data : [{"id":210,"dynamicid":125,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"Vivo Android 5.0  720*1280 ","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1523448910,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":205,"dynamicid":109,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"改变主意","dynamic":"改变主意","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170610.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg",""],"create_time":1523322156,"dynamicdata":{"id":109,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"改变主意","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170610.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg",""],"city":"深圳市","vote":1,"comment":0,"create_time":1521186763,"is_vote":1}},{"id":204,"dynamicid":110,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg",""],"create_time":1523322139,"dynamicdata":{"id":110,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg",""],"city":"深圳","vote":1,"comment":0,"create_time":1521188097,"is_vote":1}},{"id":203,"dynamicid":128,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/CROP_1522748481986.jpg"],"create_time":1523322045,"dynamicdata":{"id":128,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/CROP_1522748481986.jpg"],"city":"深圳市","vote":1,"comment":0,"create_time":1522748487,"is_vote":1}},{"id":198,"dynamicid":100,"from_userid":66,"from_nickanme":"wensen","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er2Axe2WAXB2EwX2NjgJN6Lt2l36I1ibSRM01qjExBgbaMSkoFEapyrvUjwnicic4gVtW92SUeXoddOw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"create_time":1523258499,"dynamicdata":{"id":100,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"city":"深圳市","vote":2,"comment":3,"create_time":1520584220,"is_vote":0}},{"id":186,"dynamicid":100,"from_userid":57,"from_nickanme":"周粥粥","from_avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"create_time":1523243747,"dynamicdata":{"id":100,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"city":"深圳市","vote":2,"comment":3,"create_time":1520584220,"is_vote":0}},{"id":173,"dynamicid":96,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg",""],"create_time":1522830553,"dynamicdata":{"id":96,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg",""],"city":"深圳市","vote":2,"comment":0,"create_time":1520580651,"is_vote":1}},{"id":172,"dynamicid":97,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"create_time":1522830545,"dynamicdata":{"id":97,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180111_085245_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180104_181522_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg",""],"city":"深圳市","vote":1,"comment":0,"create_time":1520580786,"is_vote":1}},{"id":171,"dynamicid":111,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_165312.jpg"],"create_time":1522830534,"dynamicdata":{"id":111,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_170343.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20180101_094810_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171004_165312.jpg"],"city":"深圳市","vote":1,"comment":0,"create_time":1521445371,"is_vote":1}},{"id":170,"dynamicid":129,"from_userid":30,"from_nickanme":"黄钦平","from_avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","to_userid":30,"to_nickanme":"黄钦平","typeid":3,"title":"收到点赞","content":"好几年","dynamic":"好几年","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/I01027272.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/22-50-16-E24EBB5A20EF11FD3956D31FADD05D7C8C73DF9B_size160_w500_h310.jpeg"],"create_time":1522830523,"dynamicdata":{"id":129,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"好几年","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/I01027272.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/IMG_20171219_123043_HDR.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/22-50-16-E24EBB5A20EF11FD3956D31FADD05D7C8C73DF9B_size160_w500_h310.jpeg"],"city":"深圳","vote":3,"comment":0,"create_time":1522749471,"is_vote":1}}]
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
             * pageSize : 10
             * totalPage : 2
             * totalCount : 12
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

        public static class DataBean implements Serializable {
            /**
             * id : 210
             * dynamicid : 125
             * from_userid : 30
             * from_nickanme : 黄钦平
             * from_avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
             * to_userid : 30
             * to_nickanme : 黄钦平
             * typeid : 3
             * title : 收到点赞
             * content : Vivo Android 5.0  720*1280
             * dynamic : Vivo Android 5.0  720*1280
             * images : ["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"]
             * create_time : 1523448910
             * dynamicdata : {"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}
             */

            private int id;
            private int dynamicid;
            private int from_userid;
            private String from_nickanme;
            private String from_avatar;
            private int to_userid;
            private String to_nickanme;
            private int typeid;
            private String title;
            private String content;
            private String dynamic;
            private int create_time;
            private DynamicdataBean dynamicdata;
            private List<String> images;

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            private int vip;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDynamicid() {
                return dynamicid;
            }

            public void setDynamicid(int dynamicid) {
                this.dynamicid = dynamicid;
            }

            public int getFrom_userid() {
                return from_userid;
            }

            public void setFrom_userid(int from_userid) {
                this.from_userid = from_userid;
            }

            public String getFrom_nickanme() {
                return from_nickanme;
            }

            public void setFrom_nickanme(String from_nickanme) {
                this.from_nickanme = from_nickanme;
            }

            public String getFrom_avatar() {
                return from_avatar;
            }

            public void setFrom_avatar(String from_avatar) {
                this.from_avatar = from_avatar;
            }

            public int getTo_userid() {
                return to_userid;
            }

            public void setTo_userid(int to_userid) {
                this.to_userid = to_userid;
            }

            public String getTo_nickanme() {
                return to_nickanme;
            }

            public void setTo_nickanme(String to_nickanme) {
                this.to_nickanme = to_nickanme;
            }

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDynamic() {
                return dynamic;
            }

            public void setDynamic(String dynamic) {
                this.dynamic = dynamic;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public DynamicdataBean getDynamicdata() {
                return dynamicdata;
            }

            public void setDynamicdata(DynamicdataBean dynamicdata) {
                this.dynamicdata = dynamicdata;
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
                        ", dynamicid=" + dynamicid +
                        ", from_userid=" + from_userid +
                        ", from_nickanme='" + from_nickanme + '\'' +
                        ", from_avatar='" + from_avatar + '\'' +
                        ", to_userid=" + to_userid +
                        ", to_nickanme='" + to_nickanme + '\'' +
                        ", typeid=" + typeid +
                        ", title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", dynamic='" + dynamic + '\'' +
                        ", create_time=" + create_time +
                        ", dynamicdata=" + dynamicdata +
                        ", images=" + images +
                        ", vip=" + vip +
                        '}';
            }

            public static class DynamicdataBean implements Serializable {
                /**
                 * id : 125
                 * userid : 30
                 * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132
                 * nickname : 黄钦平
                 * dynamic : Vivo Android 5.0  720*1280
                 * images : ["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"]
                 * city : 上海市
                 * vote : 2
                 * comment : 25
                 * create_time : 1522409030
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
                private int is_vote;
                private List<String> images;

                public int getVip() {
                    return vip;
                }

                public void setVip(int vip) {
                    this.vip = vip;
                }

                private int vip;

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
                    return "DynamicdataBean{" +
                            "id=" + id +
                            ", userid=" + userid +
                            ", avatar='" + avatar + '\'' +
                            ", nickname='" + nickname + '\'' +
                            ", dynamic='" + dynamic + '\'' +
                            ", city='" + city + '\'' +
                            ", vote=" + vote +
                            ", comment=" + comment +
                            ", create_time=" + create_time +
                            ", is_vote=" + is_vote +
                            ", images=" + images +
                            ", vip=" + vip +
                            '}';
                }
            }
        }
    }
}
