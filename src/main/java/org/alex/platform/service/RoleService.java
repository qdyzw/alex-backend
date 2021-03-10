package org.alex.platform.service;

import com.github.pagehelper.PageInfo;
import org.alex.platform.exception.BusinessException;
import org.alex.platform.pojo.RoleDO;
import org.alex.platform.pojo.RoleDTO;
import org.alex.platform.pojo.RoleVO;

import java.util.List;

public interface RoleService {
    RoleVO findRoleById(Integer roleId);
    PageInfo<RoleVO> findRole(RoleDTO roleDTO, Integer pageNum, Integer pageSize);
    List<RoleVO> findAllRole(RoleDTO roleDTO);
    void saveRole(RoleDO roleDO) throws BusinessException;
    void modifyRole(RoleDO roleDO) throws BusinessException;
    void removeRoleById(Integer roleId) throws BusinessException;
    List<RoleVO> checkRoleNameRepeat(RoleDTO roleDTO);
}