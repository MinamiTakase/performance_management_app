package com.app.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;

import com.app.domain.TRN_shop;

@Mapper
public interface ShopMapper {

    Collection<TRN_shop> findAll();

    TRN_shop findOne(Integer shop_id);
    
    void save(TRN_shop shop);

    void update(TRN_shop shop);

    void dalete(Integer shop_id);

}
