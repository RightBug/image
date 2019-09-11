package com.hz.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Administrator on 2019/9/4.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ImageUrl {

    private Long id;
    private String imageUrl;
    private Long imageId;
    private String imageName;
    private String imageOldName;
    private String imageNameSuffix;
    private String uploadTime;
    private String state;
    private String uploader;
    private String whichPic;
    private Long imageUrlId;

    public Long getImageUrlId() {
        return imageUrlId;
    }

    public void setImageUrlId(Long imageUrlId) {
        this.imageUrlId = imageUrlId;
    }

    public String getWhichPic() {
        return whichPic;
    }

    public void setWhichPic(String whichPic) {
        this.whichPic = whichPic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageOldName() {
        return imageOldName;
    }

    public void setImageOldName(String imageOldName) {
        this.imageOldName = imageOldName;
    }

    public String getImageNameSuffix() {
        return imageNameSuffix;
    }

    public void setImageNameSuffix(String imageNameSuffix) {
        this.imageNameSuffix = imageNameSuffix;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
