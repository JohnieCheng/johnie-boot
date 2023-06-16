package com.johnie.johnieframework.jpa.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    @Meta(comment = "保存")
    <S extends T> S save(S entity);

    @Meta(comment = "根据id查找")
    T getReferenceById(ID id);

    @Meta(comment = "查找全部并排序")
    List<T> findAll(Sort sort);

    @Override
    boolean existsById(ID id);

//    void sharedCustomMethodA(ID id);
}
