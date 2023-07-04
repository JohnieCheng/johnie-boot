package com.johnie.johnieframework.jpa.service;

import com.johnie.johnieframework.common.convert.BaseConverter;
import com.johnie.johnieframework.jpa.constants.Constants;
import com.johnie.johnieframework.jpa.repository.BaseRepository;
import com.johnie.johnieframework.jpa.repository.BaseRepositoryImpl;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;


@Slf4j
//@Service
public class BaseServiceImpl<V, E> implements BaseService<V, E> {

    @Resource
    private BaseConverter<E, V> baseConverter;

    @PersistenceContext
    EntityManager em;

    private Class<V> voClass;
    private Class<E> eClass;


    /**
     * 根据domain class创建JPA repository 实现类SimpleJpaRepository
     *
     * @return SimpleJpaRepository对象
     */
    private BaseRepository<E, Long> createRepository() {
        return new BaseRepositoryImpl<>(eClass, em);
    }


    /**
     * 根据ID，检测Entity是否存在
     *
     * @param id Entity实体类主键
     * @return boolean 是否存在
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public boolean checkExists(Long id) {
        return this.createRepository().existsById(id);
    }

    /**
     * 根据ID，获取单一Entity实体类，并转为VO对象返回
     *
     * @param id 业务类型ID
     * @return VO对象
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public V findOne(Long id) {
        this.init();
        E e = this.createRepository().getReferenceById(id);
        return this.baseConverter.sourceToTarget(e);
    }


    /**
     * 获取所有Entity实体，并转为VO集合返回
     *
     * @return List<VO> VO对象集合
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public List<V> findAll() {
        this.init();
        List<E> list = this.createRepository().findAll();
        return baseConverter.sourceToTarget(list);
    }

    /**
     * 根据map<属性名，属性值>多条件模糊查询获取所有Entity实体，并转为VO集合返回
     *
     * @param condMap 条件集合
     * @return List<VO> VO对象集合
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public List<V> findByLike(Map<String, Object> condMap) {
        this.init();
        Specification<E> spec = getSpecification(condMap);
        List<E> elist = this.createRepository().findAll(spec);
        return baseConverter.sourceToTarget(elist);
    }

    /**
     * 根据map<属性名，属性值>多条件查询、排序、分页获取所有Entity实体，并转为VO集合返回
     *
     * @param condMap        条件集合
     * @param sortProperties 排序属性集合
     * @param currentPage    当前页面
     * @param pageSize       每页显示条数
     * @return Map<String, Object> VO对象集合
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public Map<String, V> getPageByConditional(Map<String, Object> condMap, List<String> sortProperties, Integer currentPage, Integer pageSize) {
        this.init();
        if (null == sortProperties) {
            sortProperties = new ArrayList<>();
        }
        if (sortProperties.isEmpty()) {
            sortProperties.add(Constants.ID);
        }
        List<Sort.Order> orderList = sortProperties.stream()
                .map(s -> new Sort.Order(Sort.Direction.DESC, s))
                .toList();

        Pageable pageable = PageRequest.of(currentPage - Constants.DEFAULT_VALUE_ONE, pageSize, Sort.by(orderList));
        Specification<E> spec = getSpecification(condMap);
        Page<E> page = this.createRepository().findAll(spec, pageable);
        return null;
    }

    private Specification<E> getSpecification(Map<String, Object> condMap) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (null != condMap && condMap.size() > Constants.DEFAULT_VALUE_ZERO) {
                condMap.forEach((k, v) -> {
                    if (Constants.ID.equalsIgnoreCase(k)) {
                        list.add(criteriaBuilder.equal(root.get(k).as(String.class), v));
                    } else if (Constants.FACTORY_ID.equalsIgnoreCase(k)) {
                        list.add(criteriaBuilder.equal(root.get(k).as(String.class), v));
                    } else {
                        list.add(criteriaBuilder.like(root.get(k).as(String.class), Constants.DEFAULT_PERCENT + v + Constants.DEFAULT_PERCENT));
                    }
                });
                Predicate[] p = new Predicate[list.size()];
                Predicate predicate = criteriaBuilder.and(list.toArray(p));
                criteriaQuery.where(predicate);
            }
            return null;
        };
    }

    /**
     * 添加Entity实体
     *
     * @param v VO对象
     * @return boolean 是否添加成功
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean save(V v) {
        this.init();
        E e = baseConverter.targetToSource(v);
        setDefaultValue(e);
        return null != this.createRepository().save(e);
    }


    /**
     * 修改Entity实体
     *
     * @param v VO对象
     * @return boolean 是否修改成功
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean update(V v) {
        if (null != v) {
            try {
                Field idField = v.getClass().getDeclaredField(Constants.ID);
                idField.setAccessible(true);
                Long id = (Long) idField.get(v);
                if (null == id) {
                    return false;
                }
                E origin = this.createRepository().getReferenceById(id);
                if (null == origin) {
                    return false;
                }
                vo2Entity(v, origin);
                this.createRepository().save(origin);
                return true;
            } catch (NoSuchFieldException ex) {
                log.error("获取实体类ID异常" + v, ex);
            } catch (IllegalAccessException ex) {
                log.error("获取实体类属性值异常" + v, ex);
            }
        }
        return false;
    }


    /**
     * 根据ID，Map<属性名,值>，更新Entity实体
     *
     * @param id  Entity实体ID
     * @param map 需要更新的字段名、值集合
     * @return boolean 是否更新成功
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean updateValuesByMap(Long id, Map<String, Object> map) {
        this.init();
        if (null == id || null == map || map.size() <= Constants.DEFAULT_VALUE_ZERO) {
            return false;
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<E> update = cb.createCriteriaUpdate(eClass);
        Root<E> root = update.from(eClass);
        map.forEach((k, v) -> {
            // set属性、值
            update.set(root.get(k), v);
        });
        update.where(cb.equal(root.get(Constants.ID), id));
        Query q = em.createQuery(update);
        return q.executeUpdate() > Constants.DEFAULT_VALUE_ZERO;
    }

    /**
     * 根据ID， 逻辑删除Entity实体
     *
     * @param id Entity对象ID
     * @return boolean 是否删除成功
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean updateValid(Long id) {
        if (null == id) {
            return false;
        }
        Map<String, Object> map = new HashMap<>(5);
        map.put(Constants.VALID, Constants.DEFAULT_VALUE_ZERO);
        map.put(Constants.GMT_MODIFIED, new Date());
        return this.updateValuesByMap(id, map);
    }

    /**
     * 根据ID， 删除Entity实体，非逻辑删除
     *
     * @param id Entity对象ID
     * @return boolean 是否删除成功
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void delete(Long id) {
        this.createRepository().deleteById(id);
    }


    /**
     * 初始化， 通过反射获取泛型V、E的Class
     */
    private void init() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        voClass = (Class<V>) params[Constants.DEFAULT_VALUE_ZERO];
        eClass = (Class<E>) params[Constants.DEFAULT_VALUE_ONE];
    }


    /**
     * 添加操作时，为entity实体类中部分属性设置默认值
     *
     * @param entity 实体类
     */
    private void setDefaultValue(E entity) {
        if (null != entity) {
            Map<String, Object> map = new HashMap<>(5);
            map.put(Constants.VALID, Constants.DEFAULT_VALUE_ONE);
            map.put(Constants.REF_COUNT, Constants.DEFAULT_VALUE_ZERO);
            map.put(Constants.GMT_CREATE, new Date());
            Field[] fields = entity.getClass().getDeclaredFields();
            Arrays.asList(fields).forEach(field -> {
                field.setAccessible(true);
                map.forEach((k, v) -> {
                    if (k.equalsIgnoreCase(field.getName())) {
                        try {
                            field.set(entity, v);
                        } catch (IllegalAccessException e) {
                            log.error("属性赋值异常" + entity, e);
                        }
                    }
                });
            });
        }
    }

    /**
     * 用VO对象的属性值，替换Entity对象中相同属性的值，返回Entity实体类
     *
     * @param v VO对象
     * @param e Entity实体类
     */
    private void vo2Entity(V v, E e) {
        if (null == v || null == e) {
            return;
        }
        Field[] voFields = v.getClass().getDeclaredFields();
        Class<?> clazz = e.getClass();
        Arrays.asList(voFields).forEach(voField -> {
            voField.setAccessible(true);
            try {
                Field eField = clazz.getDeclaredField(voField.getName());
                eField.setAccessible(true);
                eField.set(e, voField.get(v));
                // 更新修改时间为当前时间
                Field gmtField = clazz.getDeclaredField(Constants.GMT_MODIFIED);
                gmtField.setAccessible(true);
                gmtField.set(e, new Date());
            } catch (NoSuchFieldException ex) {
                log.error("获取实体类ID异常" + v, ex);
            } catch (IllegalAccessException ex) {
                log.error("获取实体类属性值异常" + v, ex);
            }
        });
    }
}
