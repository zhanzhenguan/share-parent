package com.share.rules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.rule.domain.FeeRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 费用规则Mapper接口
 *
 * @author atguigu
 * @date 2024-10-25
 */
@Mapper
public interface FeeRuleMapper extends BaseMapper<FeeRule>
{

    public List<FeeRule> selectFeeRuleList(FeeRule feeRule);
}

