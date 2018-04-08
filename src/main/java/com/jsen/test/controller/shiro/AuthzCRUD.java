package com.jsen.test.controller.shiro;

import com.jsen.test.entity.SysUser;
import com.jsen.test.service.*;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/8
 */
@RestController
@CrossOrigin
@RequestMapping("/ACRUD")
public class AuthzCRUD {

    @Autowired
    SysFilterChainService sysFilterChainService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysPermissionService sysPermissionService;
    @Autowired
    SysUserRoleService sysUserRoleService;
    @Autowired
    SysRolePermissionService sysRolePermissionService;

    @GetMapping("/create/{url}/{filters}/{sort}")
    public ResponseBase createFilter(@PathVariable("url") String url, @PathVariable("filters") String filters, @PathVariable("sort") int sort) {
        return sysFilterChainService.createFilter(url, filters, sort);
    }
    @GetMapping("/delete/{url}")
    public ResponseBase removeFilterByUrl(@PathVariable("url") String url) {
        return sysFilterChainService.deleteByUrl(url);
    }

    // user
    @GetMapping("/create/user/{username}")
    public ResponseBase createUser(@PathVariable("username") String username) {
        return sysUserService.createUser(username);
    }
    @GetMapping("/delete/user/{username}")
    public ResponseBase deleteUser(@PathVariable("username") String username) {
        return sysUserService.deleteUser(username);
    }

    // role
    @GetMapping("/create/role/{role}")
    public ResponseBase createRole(@PathVariable("role") String role) {
        return sysRoleService.createRole(role);
    }
    @GetMapping("/delete/role/{role}")
    public ResponseBase deleteRole(@PathVariable("role") String role) {
        return sysRoleService.deleteRole(role);
    }

    // permission
    @GetMapping("/create/permission/{permission}")
    public ResponseBase createPermission(@PathVariable("permission") String permission) {
        return sysPermissionService.createPermission(permission);
    }
    @GetMapping("/delete/permission/{permission}")
    public ResponseBase deletePermission(@PathVariable("permission") String permission) {
        return sysPermissionService.deletePermission(permission);
    }

    // user add role
    @GetMapping("/create/uar/{uid}/{rid}")
    public ResponseBase addRoleToUser(@PathVariable("uid") int uid, @PathVariable("rid") int rid) {
        return sysUserRoleService.createUserRole(uid, rid);
    }
    @GetMapping("/delete/uar/{id}")
    public ResponseBase deleteRoleToUser(@PathVariable("id") int id) {
        return sysUserRoleService.deleteUserRole(id);
    }

    // role add permission
    @GetMapping("/create/rap/{rid}/{pid}")
    public ResponseBase addPermissionToRole(@PathVariable("rid") int rid, @PathVariable("pid") int pid) {
        return sysRolePermissionService.createRolePermission(rid, pid);
    }
    @GetMapping("/delete/rap/{id}")
    public ResponseBase deletePermissionToRole(@PathVariable("id") int id) {
        return sysRolePermissionService.deleteRolePermission(id);
    }
}
