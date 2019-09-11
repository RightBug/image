package com.hz.service.impl;

import com.hz.dao.ImageMapper;
import com.hz.model.Image;
import com.hz.model.ImageUrl;
import com.hz.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/9/3.
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Long saveImage(Image image ,ImageUrl imageUrl) {
        Long id = imageMapper.saveImage(image);
        imageUrl.setImageId(image.getId());
//        ImageUrl ImageUr = imageMapper.queryImageUrl(imageUrl);
//        if(null != ImageUr){
//            imageMapper.updateImageUrl(ImageUr);
//        }
        imageMapper.saveImageUrl(imageUrl);
        return image.getId();
    }

    @Override
    public Long saveImageUrl(ImageUrl imageUrl) {
        //更新图片数量
        Image image = new Image();
        Long id = 0l;
        image.setId(imageUrl.getImageId());
        imageMapper.updateImage(image);
        if(null != imageUrl.getImageUrlId() && !"".equals(imageUrl.getImageUrlId())){
            ImageUrl ImageUr = imageMapper.queryImageUrl1(imageUrl);
            imageUrl.setWhichPic(ImageUr.getWhichPic());
            if(null != ImageUr){
                imageMapper.updateImageUrl(ImageUr);
            }
        }else {
            ImageUrl ImageUr = imageMapper.queryImageUrl(imageUrl);
            imageUrl.setWhichPic(ImageUr.getWhichPic());
        }
        id = imageMapper.saveImageUrl(imageUrl);
        return id ;
    }

    @Override
    public List<ImageUrl> queryImageList(ImageUrl imageUrl) {
        return imageMapper.queryImageList(imageUrl);
    }
}
