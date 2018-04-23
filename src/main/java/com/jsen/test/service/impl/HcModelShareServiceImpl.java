package com.jsen.test.service.impl;

import com.jsen.test.constants.ConstantResponse;
import com.jsen.test.entity.HcModel;
import com.jsen.test.entity.HcModelShare;
import com.jsen.test.mapper.HcModelMapper;
import com.jsen.test.mapper.HcModelShareMapper;
import com.jsen.test.service.HcModelShareService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
@Service
public class HcModelShareServiceImpl implements HcModelShareService {

    @Autowired
    HcModelShareMapper hcModelShareMapper;
    @Autowired
    HcModelMapper hcModelMapper;


    @Override
    public ResponseBase listAll() {
        return ResponseBase.create().code(0).data(hcModelShareMapper.listAll());
    }

    @Override
    public ResponseBase save(byte[] modelData, int creator, String name, String intro) {
        int has = hcModelShareMapper.countByName(name);
        if (has > 0) {
            return ConstantResponse.FAIL.msg("分享模型存在");
        }
        HcModelShare hcModelShare = new HcModelShare().setModelData(modelData).setCollect(0)
                .setCreateTime(new Date()).setLook(0).setCreator(creator).setName(name).setIntro(intro);
        if (hcModelShareMapper.insertOne(hcModelShare) > 0) {
            return ConstantResponse.SUCCESS;
        } else {
            return ConstantResponse.FAIL.data("保存共享模型失败");
        }
    }

    @Override
    public ResponseBase save(int modelId, int creator, String name, String intro) {
        HcModel hcModel = hcModelMapper.findModelByModelIdAndCreatorId(modelId, creator);
        if (hcModel != null) {
            return save(hcModel.getModelData(), creator, name, intro);
        } else {
            return ConstantResponse.FAIL.msg("模型不存在");
        }
    }

    @Override
    public ResponseBase updateModelBy(String shareModelName, Integer creatorId, Integer modelId) {
        // 查找share model是否存在
        HcModelShare hcModelShare = hcModelShareMapper.findByShareModelNameAndCreatorId(shareModelName, creatorId);
        if (hcModelShare == null) {
            return ConstantResponse.FAIL.msg("分享模型不存在");
        }
        // 查找model是否存在
        HcModel hcModel = hcModelMapper.findModelByModelIdAndCreatorId(modelId, creatorId);
        if(hcModel == null) {
            return ConstantResponse.FAIL.msg("模型不存在");
        }
        // 更新
        hcModelShareMapper.updateById(hcModelShare.setModelData(hcModel.getModelData()));
        return ConstantResponse.SUCCESS;
    }

    @Override
    public ResponseBase deleteShareModelById(Integer id) {
        int eff = hcModelShareMapper.deleteShareModelById(id);
        if (eff > 0) {
            return ResponseBase.create().code(0).add("eff", eff);
        } else {
            return ConstantResponse.FAIL.msg("删除失败");
        }
    }
}
