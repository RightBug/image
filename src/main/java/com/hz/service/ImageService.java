package com.hz.service;

import com.hz.model.Image;
import com.hz.model.ImageUrl;

import java.util.List;

/**
 * Created by Administrator on 2019/9/2.
 */
public interface ImageService {


    Long saveImage(Image image ,ImageUrl imageUrl);

    Long saveImageUrl(ImageUrl imageUrl);

    List<ImageUrl> queryImageList(ImageUrl imageUrl);

}
