/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.footoo.common.fastjsonTest;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 测试fastjson
 * 
 * @author fengjing.yfj
 * @version $Id: FastJsonTest.java, v 0.1 2014年2月13日 上午11:21:39 fengjing.yfj Exp $
 */
public class FastJsonTest {
    public static void main(String args[]) {
        JavaBean bean = new JavaBean();
        bean.setAge(22);
        bean.setName("jeff");
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(1, "1");
        map.put("2", 2);
        bean.setMaps(map);

        System.out.println(JSON.toJSONString(bean));
        String s = JSON.toJSONString(bean);
        JavaBean object = JSON.parseObject(s, JavaBean.class);
        System.out.println(object.getAge() + " " + object.getName() + " " + object.getMaps());

    }
}

class JavaBean {
    private transient String    name;
    private int                 age;

    private Map<Object, Object> maps;

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>age</tt>.
     * 
     * @return property value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter method for property <tt>age</tt>.
     * 
     * @param age value to be assigned to property age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter method for property <tt>maps</tt>.
     * 
     * @return property value of maps
     */
    public Map<Object, Object> getMaps() {
        return maps;
    }

    /**
     * Setter method for property <tt>maps</tt>.
     * 
     * @param maps value to be assigned to property maps
     */
    public void setMaps(Map<Object, Object> maps) {
        this.maps = maps;
    }

}
