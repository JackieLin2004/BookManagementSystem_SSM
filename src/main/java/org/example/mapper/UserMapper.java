package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.entity.Account;

public interface UserMapper {
    @Select("select * from book_manage_ssm.user where user.username = #{username}")
    Account findByUsername(String username);
}
