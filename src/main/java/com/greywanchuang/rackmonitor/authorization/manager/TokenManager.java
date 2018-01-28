package com.greywanchuang.rackmonitor.authorization.manager;

import com.greywanchuang.rackmonitor.authorization.model.TokenModel;
import com.greywanchuang.rackmonitor.entity.User;

/**
 * 对Token进行操作的接口
 * @author ScienJus
 * @date 2015/7/31.
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     * @param user 指定用户
     * @return 生成的token
     */
    public TokenModel createToken(User user);

    /**
     * 检查token是否有效
     * @param authorization token
     * @return 是否有效
     */
    public boolean checkToken(String authorization);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    public User getCurrentUser(String authentication);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    public void deleteToken(String userId);

}
