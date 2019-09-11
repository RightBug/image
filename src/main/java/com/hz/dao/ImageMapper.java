package com.hz.dao;

import com.hz.model.Image;
import com.hz.model.ImageUrl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/9/3.
 */
@Mapper
public interface ImageMapper {


    Long saveImage(Image image);

    Long updateImage(Image image);

    Long saveImageUrl(ImageUrl imageUrl);

    ImageUrl queryImageUrl(ImageUrl imageUrl);

    ImageUrl queryImageUrl1(ImageUrl imageUrl);

    Long updateImageUrl(ImageUrl imageUrl);

    List<ImageUrl> queryImageList(ImageUrl imageUrl);

}
