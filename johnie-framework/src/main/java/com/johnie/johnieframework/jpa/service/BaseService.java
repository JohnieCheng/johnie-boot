package com.johnie.johnieframework.jpa.service;

import java.util.List;
import java.util.Map;

public interface BaseService<V, E> {

    /**
     * 根据ID，检测Entity是否存在
     *
     * @param id Entity实体类主键
     * @return boolean 是否存在
     */
    boolean checkExists(Long id);

    /**
     * 根据ID，获取单一Entity实体类，并转为VO对象返回
     *
     * @param id Entity实体类ID
     * @return V 返回Entity实体类对应的VO对象
     */
    V findOne(Long id);

    /**
     * 获取所有Entity实体，并转为VO集合返回
     *
     * @return List<VO>
     */
    List<V> findAll();

    /**
     * 根据map<属性名，属性值>多条件模糊查询获取所有Entity实体，并转为VO集合返回
     *
     * @param condMap 条件集合
     * @return List<VO> VO对象集合
     */
    List<V> findByLike(Map<String, Object> condMap);

    /**
     * 根据map<属性名，属性值>多条件查询、排序、分页获取所有Entity实体，并转为VO集合返回
     *
     * @param condMap        条件集合
     * @param sortProperties 排序属性集合
     * @param currentPage    当前页面
     * @param pageSize       每页显示条数
     * @return Map<String, Object> VO对象集合
     */
    Map<String, V> getPageByConditional(Map<String, Object> condMap, List<String> sortProperties, Integer currentPage, Integer pageSize);

    /**
     * 添加Entity实体
     * @param v VO对象
     * @return boolean 是否添加成功
     */
    boolean save(V v);

    /**
     * 修改Entity实体
     * @param v VO对象
     * @return boolean 是否修改成功
     */
    boolean update(V v);

    /**
     * 根据ID，Map<属性名,值>，更新Entity实体
     * @param id Entity实体ID
     * @param map 需要更新的字段名、值集合
     * @return boolean 是否更新成功
     */
    boolean updateValuesByMap(Long id, Map<String, Object> map);

    /**
     * 根据ID， 逻辑删除Entity实体
     * @param id Entity对象ID
     * @return boolean 是否删除成功
     */
    boolean updateValid(Long id);

    /**
     * 根据ID， 删除Entity实体，非逻辑删除
     * @param id Entity对象ID
     * @return boolean 是否删除成功
     */
    void delete(Long id);
}
