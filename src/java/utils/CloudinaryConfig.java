package utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryConfig {
    private static Cloudinary cloudinary;

    static {
        // Thay các thông tin này bằng API thực tế trên Dashboard Cloudinary của bạn
        cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dc6p8f1rh", 
            "api_key", "798129765637683",       
            "api_secret", "i9Lt_53on6Gu19o-WMnMd9gqrxk", 
            "secure", true
        ));
    }

    public static Cloudinary getInstance() {
        return cloudinary;
    }
}