package club.yuit.auth.service.impl;


import club.yuit.auth.entity.Authority;
import club.yuit.auth.jpa.*;
import club.yuit.auth.service.AuthorityService;
import club.yuit.common.response.*;
import club.yuit.common.utils.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static club.yuit.common.response.HttpResponseUtils.*;


/**
 * @author liulu
 * @create Time 2018/8/31 13:16
 * @description
 * @modify by
 * @modify time
 **/
@Service
public class AuthorityServiceImpl implements AuthorityService {

    AuthorityJpa authorityJpa;
    RoleAuthorityJpa roleAuthorityJpa;
    ResourceAuthorityJpa resourceAuthorityJpa;
    MenusAuthorityJpa menusAuthorityJpa;

    public AuthorityServiceImpl(AuthorityJpa authorityJpa, RoleAuthorityJpa roleAuthorityJpa, ResourceAuthorityJpa resourceAuthorityJpa, MenusAuthorityJpa menusAuthorityJpa) {
        this.authorityJpa = authorityJpa;
        this.roleAuthorityJpa = roleAuthorityJpa;
        this.resourceAuthorityJpa = resourceAuthorityJpa;
        this.menusAuthorityJpa = menusAuthorityJpa;
    }

    @Override
    public SimpleResponse findAll() {
        return successSimpleResponse(authorityJpa.findAll());
    }

    /**
     * 根据Id删除权限
     * "包括角色-权限表，资源-权限表，菜单-权限表，权限表"
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse authorityDelete(String id) {
        // 删除其关联信息
        roleAuthorityJpa.deleteByAuthorityId(id);
        resourceAuthorityJpa.deleteByAuthorityId(id);
        menusAuthorityJpa.deleteByAuthorityId(id);

        authorityJpa.deleteById(id);
        return successResponse("删除成功!");

    }

    /**
     * 目前只是获取权限信息
     *
     * @param id
     * @return
     */
    @Override
    public SimpleResponse getAuthorityById(String id) {
        System.out.println(authorityJpa.findAuthorityById(id));
        return successSimpleResponse(authorityJpa.findAuthorityById(id));
    }

    /**
     * 添加权限信息，未添加相关信息
     *
     * @param authority
     * @return
     */
    @Override
    public BaseResponse authorityAdd(Authority authority) {
        Authority a = new Authority();
        try {
            CommonUtils.copy(authority, a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        a.setCreateTime(new Date());
        authorityJpa.save(a);
        return successResponse("添加成功");
    }

    @Override
    @Transactional
    public BaseResponse authorityUpdate(Authority authority) {
        Authority a = authorityJpa.findAuthorityById(authority.getId());
        try {
            CommonUtils.copy(authority, a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        a.setModifyTime(new Date());
        authorityJpa.saveAndFlush(a);
        return successResponse("修改成功!");
    }

    @Override
    public SimpleResponse findAuthsByRoleId(String id) {
        List<Authority> authorities = this.authorityJpa.findByRoleId(id);
        return successSimpleResponse(authorities);
    }

    @Override
    public PageAndSortResponse findAuthsPagination(Integer currentPage, Integer pageSize) {
        PageQueryItems items= JpaQueryUtils.query(this.authorityJpa,currentPage,pageSize, OrderType.DESC,"modifyTime");
        return successPageResponse(items);
    }

    @Override
    @Transactional
    public BaseResponse batchDeleteByIds(List<String> ids) {
        this.authorityJpa.batchDeleteByIds(ids);
        return successResponse("删除成功");
    }
}
