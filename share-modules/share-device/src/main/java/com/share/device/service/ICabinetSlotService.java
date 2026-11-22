package com.share.device.service;

import java.util.List;
import com.share.device.domain.CabinetSlot;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 柜机插槽Service接口
 *
 * @author atguigu
 * @date 2024-10-22
 */
public interface ICabinetSlotService extends IService<CabinetSlot>
{

    CabinetSlot getBtSlotNo(Long cabinetId, String slotNo);

    /**
     * 查询柜机插槽列表
     *
     * @param cabinetSlot 柜机插槽查询条件
     * @return 柜机插槽集合
     */
    List<CabinetSlot> selectCabinetSlotList(CabinetSlot cabinetSlot);

}
