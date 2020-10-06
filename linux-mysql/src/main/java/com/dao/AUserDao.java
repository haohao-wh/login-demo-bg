package com.dao;

import com.model.AUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AUserDao extends JpaRepository<AUser, String> {
@Query(value = "select *  from  a_user  limit :page,:pageSize ",nativeQuery = true)
List<AUser> queryAllAUser(@Param("page") int page, @Param("pageSize") int pageSize);
    @Query(value = "select count(*) from  a_user ",nativeQuery = true)
int queryPageCout();

}
