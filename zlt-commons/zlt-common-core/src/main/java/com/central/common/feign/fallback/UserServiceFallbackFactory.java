package com.central.common.feign.fallback;

import com.central.common.feign.UserService;
import com.central.common.model.Result;
import com.central.common.model.SysRole;
import com.central.common.model.SysUser;
import org.springframework.cloud.openfeign.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

/**
 * userService降级工场
 *
 * @author zlt
 * @date 2019/1/18
 */
@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public SysUser selectByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new SysUser();
            }

            @Override
            public SysUser findByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new SysUser();
            }

            @Override
            public SysUser findByMobile(String mobile) {
                log.error("通过手机号查询用户异常:{}", mobile, throwable);
                return new SysUser();
            }

            @Override
            public SysUser findByOpenId(String openId) {
                log.error("通过openId查询用户异常:{}", openId, throwable);
                return new SysUser();
            }

            /**
             * 获取带角色的用户信息
             *
             * @param username
             * @return
             */
            @Override
            public SysUser selectRoleUser(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new SysUser();
            }

            /**
             * 获取用户的角色
             *
             * @param id@return
             */
            @Override
            public List<SysRole> findRolesByUserId(Long id) {
                log.error("通过用户id查询角色列表异常:{}", id, throwable);
                return Collections.emptyList();
            }

            /**
             * 保存用户
             */
            @PostMapping("/users/saveOrUpdate")
            public Result saveOrUpdate(@RequestBody SysUser sysUser) {
                log.error("保存用户异常", throwable);
                return Result.failed(throwable.getMessage());
            }
        };
    }
}
