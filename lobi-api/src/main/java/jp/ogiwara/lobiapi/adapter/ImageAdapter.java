package jp.ogiwara.lobiapi.adapter;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import jp.ogiwara.lobiapi.model.Image;

public class ImageAdapter {

    @ToJson
    public String toJson(Image image){
        throw new IllegalStateException("Image toJson is not supported");
    }

    @FromJson
    public Image fromJson(String image){
        return Image.fromUrl(image);
    }

}
