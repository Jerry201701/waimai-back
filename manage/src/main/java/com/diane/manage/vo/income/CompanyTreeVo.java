package com.diane.manage.vo.income;

import lombok.Data;
import java.util.List;

@Data
public class CompanyTreeVo {
//    private Long companyId;
//    private String companyName;
//    private List<ShopTreeVo> shops;
    private String id;
    private String name;
    private List<CompanyTreeVo> children;
    private Integer level;
}
