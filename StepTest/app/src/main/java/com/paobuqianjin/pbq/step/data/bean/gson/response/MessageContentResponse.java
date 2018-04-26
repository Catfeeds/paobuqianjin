package com.paobuqianjin.pbq.step.data.bean.gson.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pbq on 2018/3/27.
 */
/*
@className :MessageContentResponse
*@date 2018/3/27
*@author
*@description 评论消息
*/
public class MessageContentResponse {

    /**
     * error : 0
     * message : success
     * data : {"pagenation":{"page":1,"pageSize":10,"totalPage":2,"totalCount":14},"data":[{"id":158,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"海报","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522736956,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":157,"dynamicid":125,"from_userid":57,"from_nickanme":"周粥粥","from_avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"哈哈哈[0x1f612][0x1f612][0x1f612]","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670846,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":156,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f633][0x1f633][0x1f633]","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670729,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":155,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f61e][0x1f620][0x1f625][0x1f62d]","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670699,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":154,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f612][0x1f633][0x1f633]","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670691,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":153,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f612][0x1f633][0x1f601]非常重新","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670683,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":152,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f603][0x1f620][0x1f620]哥徽杭古道","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670668,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":151,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f603]发广告[0x1f60d][0x1f612]","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670655,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":150,"dynamicid":125,"from_userid":57,"from_nickanme":"周粥粥","from_avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"一回家看看","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670316,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":149,"dynamicid":125,"from_userid":57,"from_nickanme":"周粥粥","from_avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"是我社恶即可","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670299,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}}]}
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
        return "MessageContentResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBeanX {
        /**
         * pagenation : {"page":1,"pageSize":10,"totalPage":2,"totalCount":14}
         * data : [{"id":158,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"海报","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522736956,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":157,"dynamicid":125,"from_userid":57,"from_nickanme":"周粥粥","from_avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"哈哈哈[0x1f612][0x1f612][0x1f612]","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670846,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":156,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f633][0x1f633][0x1f633]","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670729,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":155,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f61e][0x1f620][0x1f625][0x1f62d]","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670699,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":154,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f612][0x1f633][0x1f633]","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670691,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":153,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f612][0x1f633][0x1f601]非常重新","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670683,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":152,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f603][0x1f620][0x1f620]哥徽杭古道","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670668,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":151,"dynamicid":125,"from_userid":61,"from_nickanme":"","from_avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"[0x1f603]发广告[0x1f60d][0x1f612]","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670655,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":150,"dynamicid":125,"from_userid":57,"from_nickanme":"周粥粥","from_avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"一回家看看","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670316,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}},{"id":149,"dynamicid":125,"from_userid":57,"from_nickanme":"周粥粥","from_avatar":"http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/FFE8A7E1-0D24-49B2-A6FB-1AE77F2C5CF8.jpg","to_userid":30,"to_nickanme":"黄钦平","typeid":2,"title":"收到一条评论","content":"是我社恶即可","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"create_time":1522670299,"dynamicdata":{"id":125,"userid":30,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Itaoxich6dW72MleNPMxR8icXGjflzuNGbicF1tOoebBGfpRONnDCIBwl3nJyickqgLSATxzfTGlLwAK5aprymCPzg/132","nickname":"黄钦平","dynamic":"Vivo Android 5.0  720*1280 ","images":["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"],"city":"上海市","vote":2,"comment":25,"create_time":1522409030,"is_vote":1}}]
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
             * totalCount : 14
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
             * id : 158
             * dynamicid : 125
             * from_userid : 61
             * from_nickanme :
             * from_avatar : https://thirdwx.qlogo.cn/mmopen/vi_32/cKuFQO8UEyKesahdoW8hahuDr3FXuSKwVNv7lJHY7Y6gDlejf7TBbMhz866ZAX9iaibLmam2VAibCYY0gBMsFyBPw/132
             * to_userid : 30
             * to_nickanme : 黄钦平
             * typeid : 2
             * title : 收到一条评论
             * content : 海报
             * dynamic : Vivo Android 5.0  720*1280
             * images : ["http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/exit_0.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/2018_03_13__04_37_21.png","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/hua2.jpg","http://runmoney-1255484416.cos.ap-guangzhou.myqcloud.com/app_icon.png"]
             * create_time : 1522736956
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
