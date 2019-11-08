package com.actstrady.wmall.utils;

import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ActStrady@tom.com
 * @date : 2019/11/7 20:21
 * @fileName : BeanCopy.java
 * @gitHub : https://github.com/ActStrady/wmall
 */
@Component
public class ListCopy<T1, T2> {
    private final Mapper mapper;

    public ListCopy(Mapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 把List<T1> 转换成List<T2>
     *
     * @param list 要转换的list
     * @param t2Class 要转换的类型
     * @return 转换后的List
     */
    public List<T2> listBuild(List<T1> list, Class<T2> t2Class) {
        if (list == null || list.size() == 0) {
            return new ArrayList<>(0);
        }
        List<T2> result = new ArrayList<>();
        for (T1 item : list) {
            // 使用dozer复制对象
            T2 t2 = beanBuild(item, t2Class);
            result.add(t2);
        }
        return result;
    }

    public T2 beanBuild(T1 t1, Class<T2> t2Class){
        return mapper.map(t1, t2Class);
    }
}