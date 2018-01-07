package jp.ogiwara.lobiapi.model;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.regex.Pattern;

public class Image {

    private static String REPLACEMENT = "%q";

    /**
     * 24,32,48,56,64,72,80,88,112,144,152,192,
     * 200,264,320,400,600,640,800,1600...
     *
     */
    private enum Quality{
        Low(72),
        Middle(200),
        High(400),
        VeryHigh(640);

        private int pix;

        Quality(int pix){
            this.pix = pix;
        }
    }

    public static Image fromUrl(String url){
        Image image = new Image();

        if(!url.contains("none")){ //use image setting not done
            if(url.contains(".png")){
                image.baseUrl = url.replace("_"+ StringUtils.substringBetween(url,"_",".png") + ".png","_" + REPLACEMENT + ".png");
            }

            if(url.contains(".jpg")){
                image.baseUrl = url.replace("_"+ StringUtils.substringBetween(url,"_",".jpg") + ".jpg","_" + REPLACEMENT + ".jpg");
            }
        }else{
            image.baseUrl = url;
        }

        return image;
    }

    private String baseUrl;

    public String getRawQuality(){
        return baseUrl.replaceAll(REPLACEMENT,"raw");
    }

    public String getVeryHighQuality(){
        return baseUrl.replaceAll(REPLACEMENT,String.valueOf(Quality.VeryHigh.pix));
    }

    public String getHighQuality(){
        return baseUrl.replaceAll(REPLACEMENT,String.valueOf(Quality.High.pix));
    }

    public String getMiddleQuality(){
        return baseUrl.replaceAll(REPLACEMENT,String.valueOf(Quality.Middle.pix));
    }

    public String getLowQuality(){
        return baseUrl.replaceAll(REPLACEMENT,String.valueOf(Quality.Low.pix));
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
