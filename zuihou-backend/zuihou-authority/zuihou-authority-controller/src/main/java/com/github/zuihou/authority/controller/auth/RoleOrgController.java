package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.R;
import com.github.zuihou.common.utils.context.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.authority.entity.auth.RoleOrg;
import com.github.zuihou.authority.dto.auth.RoleOrgSaveDTO;
import com.github.zuihou.authority.dto.auth.RoleOrgUpdateDTO;
import com.github.zuihou.authority.service.auth.RoleOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.github.zuihou.base.entity.SuperEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.zuihou.base.BaseController;

/**
 * <p>
 * 前端控制器
 * 角色部门关系
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/roleOrg")
@Api(value = "RoleOrg", tags = "角色部门关系")
public class RoleOrgController extends BaseController {

    @Autowired
    private RoleOrgService roleOrgService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询角色部门关系
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询角色部门关系", notes = "分页查询角色部门关系")
    @GetMapping("/page")
    @SysLog("分页查询角色部门关系")
    public R<IPage<RoleOrg>> page(RoleOrg data) {
        IPage<RoleOrg> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<RoleOrg> query = Wraps.lbQ(data);
        roleOrgService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询角色部门关系
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "单体查询角色部门关系", notes = "单体查询角色部门关系")
    @GetMapping("/{id}")
    @SysLog("单体查询角色部门关系")
    public R<RoleOrg> get(@PathVariable Long id) {
        return success(roleOrgService.getById(id));
    }

    /**
     * 保存角色部门关系
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存角色部门关系", notes = "保存角色部门关系不为空的字段")
    @PostMapping
    @SysLog("保存角色部门关系")
    public R<RoleOrg> save(@RequestBody @Valid RoleOrgSaveDTO data) {
        RoleOrg roleOrg = dozer.map(data, RoleOrg.class);
        roleOrgService.save(roleOrg);
        return success(roleOrg);
    }

    /**
     * 修改角色部门关系
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改角色部门关系", notes = "修改角色部门关系不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    @SysLog("修改角色部门关系")
    public R<RoleOrg> update(@RequestBody @Valid RoleOrgUpdateDTO data) {
        RoleOrg roleOrg = dozer.map(data, RoleOrg.class);
        roleOrgService.updateById(roleOrg);
        return success(roleOrg);
    }

    /**
     * 删除角色部门关系
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除角色部门关系", notes = "根据id物理删除角色部门关系")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除角色部门关系")
    public R<Boolean> delete(@PathVariable Long id) {
        roleOrgService.removeById(id);
        return success(true);
    }

}
