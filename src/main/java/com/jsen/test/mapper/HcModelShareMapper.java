package com.jsen.test.mapper;

import com.jsen.test.entity.HcModelShare;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
@Service
public interface HcModelShareMapper {

    int insertOne(HcModelShare hcModelShare);

    int updateById(HcModelShare hcModelShare);

    List<HcModelShare> listByCreator(Integer creator);

    HcModelShare findByShareModelNameAndCreatorId(String shareModelName, Integer creatorId);

    int countByName(String shareModelName);

    List<HcModelShare> listAll();

    int updateShareModelNameByName(String oldName, String newName);

    int deleteShareModelById(Integer id);
}
