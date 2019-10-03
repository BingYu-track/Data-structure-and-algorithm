package util;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2019/10/3 16:34
 */
public class LogarithmUtils {

    /**
     * @param value 真数
     * @param base 底数
     * @return 求以base为底，value的对数
     */
     public static double log(double value, double base) {
        return Math.log(value) / Math.log(base); //根据对数换底公式 logx(y) =loge(y) / loge(x)
    }
}
