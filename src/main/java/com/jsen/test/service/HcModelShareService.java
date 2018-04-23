package com.jsen.test.service;

import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
public interface HcModelShareService {

    ResponseBase listAll();
    ResponseBase save(byte[] modelData, int creator, String name, String intro);
    ResponseBase save(int modelId, int creator, String name, String intro);

    ResponseBase updateModelBy(String shareModelName, Integer creatorId, Integer modelId);


    ResponseBase deleteShareModelById(Integer id);
}
