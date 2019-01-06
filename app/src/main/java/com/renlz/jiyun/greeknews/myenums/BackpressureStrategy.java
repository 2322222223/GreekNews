package com.renlz.jiyun.greeknews.myenums;

/**
 * Created by Administrator on 2019/1/3.
 */


public enum BackpressureStrategy {
    /**
     * 不指定背压策略
     */
    MISSING,
    /**
     * 如果放入Flowable的异步缓存池中的数据超限（128）了，就会抛出MissingBackpressureException异常
     */
    ERROR,
    /**
     * 缓存池没有固定大小，可无限添加直到oom
     */
    BUFFER,
    /**
     * 如果异步缓存池满了，就会丢掉将要放入缓存池中的数据
     */
    DROP,
    /**
     * 如果异步缓存池满了，会丢掉将要放入缓存池的数据，但不管缓存池的状态如何，都会讲最后一条强行放入缓存池中
     */
    LATEST
}
