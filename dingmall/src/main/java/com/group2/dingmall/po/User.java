package com.group2.dingmall.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
@Component
public class User implements Serializable, UserDetails {

  private static final long serialVersionUID=1L;

  @ApiModelProperty(value = "用户主键id")
  @TableId(value = "user_id", type = IdType.AUTO)
  private Long userId;

  @ApiModelProperty(value = "用户昵称")
  private String nickName;

  @ApiModelProperty(value = "登陆名称(默认为手机号)")
  private String loginName;

  @ApiModelProperty(value = "MD5加密后的密码")
  private String passwordMd5;

  @ApiModelProperty(value = "个性签名")
  private String introduceSign;

  @ApiModelProperty(value = "注销标识字段(0-正常 1-已注销)")
  private Integer isDeleted;

  @ApiModelProperty(value = "锁定标识字段(0-未锁定 1-已锁定)")
  private Integer lockedFlag;

  @ApiModelProperty(value = "注册时间")
  private Date createTime;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return passwordMd5;
  }

  @Override
  public String getUsername() {
    return loginName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
