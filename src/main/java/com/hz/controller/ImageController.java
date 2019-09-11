package com.hz.controller;

import com.hz.model.Image;
import com.hz.model.ImageUrl;
import com.hz.service.ImageService;
import com.hz.service.impl.ImageServiceImpl;
import com.hz.util.Result;
import com.hz.util.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/9/2.
 */
@RestController
@Api(tags = "ImageController", description = "图片管理")
@RequestMapping(value = "/image")
@EnableSwagger2
public class ImageController {


    @Autowired
    private ImageServiceImpl imageServiceImpl;

    @Value("${localPath}")
    private String localPath;

    @Value("${ipAndPort}")
    private String ipAndPort;

    /***
     * 保存文件
     * @param file
     * @return
     */
    private String saveFile(MultipartFile file, String path) {
        String savePath = "";
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                File filepath = new File(path);
                if (!filepath.exists())
                    filepath.mkdirs();
                // 文件保存路径
                savePath = path + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(savePath));
                return savePath;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return savePath;
    }

    /*
     *上传图片
     */
    @RequestMapping(value = "/uploadFile" ,method = RequestMethod.POST)
    @ApiOperation("上传图片")
    public Result<ImageUrl> uploadFile(HttpServletRequest request ,@RequestParam(value = "imgFile", required = true) MultipartFile imgFile , Long imageId , Long imageUrlId ,
           @RequestParam(value = "category", required = true) String category , @RequestParam(value = "uploader", required = true)String uploader ) throws IOException {
        Result<ImageUrl> result = new Result<>();
        ImageUrl imageUrlResult = new ImageUrl();
        //保存数据库的路径
        String sqlPath = null;
        //定义文件保存的本地路径
        String upLocalPath="C:"+localPath+category+"/";
        String oldFileName = imgFile.getOriginalFilename();// 获取文件的原名字
        //定义 文件名
        String filename=null;
        //上传图片
        if(!imgFile.isEmpty()){

            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=imgFile.getContentType();
            //获得文件后缀名
            String suffixName=contentType.substring(contentType.indexOf("/")+1);
            //得到 文件名
            filename=uuid+"."+suffixName;

            //新图片
            File newFile = new File(upLocalPath+filename);
            if (!newFile.exists())
                newFile.mkdirs();
            //将内存中的数据写入磁盘
            imgFile.transferTo(newFile);
            sqlPath = ipAndPort+localPath+category+"/"+filename;

            ImageUrl imageUrl = new ImageUrl();
            imageUrl.setImageUrl(sqlPath);
            imageUrl.setImageName(filename);
            imageUrl.setImageOldName(oldFileName);
            imageUrl.setImageNameSuffix(suffixName);
            imageUrl.setState("1");
            imageUrl.setUploader(uploader);

            if(!"".equals(imageId)&& null != imageId ){
                //单表新增
                //把图片的相对路径保存至数据库
                imageUrl.setImageId(imageId);
                imageUrl.setImageUrlId(imageUrlId);
                imageServiceImpl.saveImageUrl(imageUrl);
            } else {
                //双表新增
                imageUrl.setWhichPic("1");
                Image image = new Image();
                image.setCategory(category);
                image.setImageNum("1");
                imageId = imageServiceImpl.saveImage(image ,imageUrl);
            }
            result.setCode(String.valueOf(ResultCode.SUCCESS.getCode()));
            result.setMsg("上传成功！");
            imageUrlResult.setImageId(imageUrl.getImageId());
            imageUrlResult.setImageUrl(imageUrl.getImageUrl());
            imageUrlResult.setId(imageUrl.getId());
            result.setData(imageUrlResult);
        }else{
            result.setCode(String.valueOf(ResultCode.FAILED.getCode()));
            result.setMsg("请上传文件信息！");
        }
        return result;
    }

    /*
     *查询url
     */
    @RequestMapping(value = "/queryUrl" ,method = RequestMethod.POST)
    @ApiOperation("查询图片url" )
    public Result<List<ImageUrl>> queryUrl(@RequestParam(value = "id", required = true)Long id){
        Result<List<ImageUrl>> result = new Result<>();
//        List<ImageUrl> images = new ArrayList<>();

        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setImageId(id);
        List<ImageUrl> images = imageServiceImpl.queryImageList(imageUrl);

        result.setCode(String.valueOf(ResultCode.SUCCESS.getCode()));
        result.setMsg("查询成功！");
        result.setData(images);
        return result;
    }

}
