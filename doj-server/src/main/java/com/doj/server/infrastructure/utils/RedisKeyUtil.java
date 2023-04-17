package com.doj.server.infrastructure.utils;

/**
 * 类描述: Redis key 工具类
 *
 * @author kongweikun@163.com
 * @date 2023/4/17
 */
public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    private static final String PREFIX_USER_LIKE = "like:user";
    private static final String PREFIX_FOLLOWER = "follower";
    private static final String PREFIX_FOLLOWEE = "followee";
    private static final String PREFIX_KAPTCHA = "kaptcha";
    private static final String PREFIX_LOGIN_TICKET = "ticket";
    private static final String PREFIX_USER = "user";
    private static final String PREFIX_UV = "uv";
    private static final String PREFIX_DAU = "dau";
    private static final String PREFIX_POST = "post";

    // 某个帖子的赞的key
    // like:entity:entityType:entityId: set(UserId1, UserId2...)
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    // 某个用户的赞的key
    // like:user:userId --> int()
    public static String getUserLikeKey(int userId){
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    // 某个用户关注的实体
    // followee:userid:entityType ---> zset(entityId, now date)
    public static String getFollowee(int userId, int entityType){
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    // 某个实体拥有的粉丝
    // follower:entityType:entityId ---> zset(userId, now )
    public static String getFollower(int entityType, int entityId){
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    // 登录验证码（之前放在session中的）
    public static String getKaptcha(String owner){
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    // 登录凭证
    public static String getLoginTicket(String ticket){
        return PREFIX_LOGIN_TICKET + SPLIT + ticket;
    }

    // 用户
    public static String getUserKey(int userId){
        return PREFIX_USER + SPLIT + userId;
    }


    // 统计帖子分数的key
    public static String getPostScoreKey(){
        return PREFIX_POST + SPLIT + "score";
    }
}
