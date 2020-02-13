package cn.com.xuxiaowei.servlet;

import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

import static cn.com.xuxiaowei.util.Constants.*;
import static com.google.common.net.HttpHeaders.*;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

/**
 * GitHub 图片验证码 Patchca
 * <p>
 * https://mvnrepository.com/artifact/com.github.bingoohuang/patchca
 * <p>
 * 所需依赖：guava
 *
 * @author xuxiaowei
 * @see <a href="https://mvnrepository.com/artifact/com.google.guava/guava">guava</a>
 * @since 0.0.1
 */
@Slf4j
public class PatchcaHttpServlet extends HttpServlet {

    /**
     * 图片验证码服务
     */
    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();

    /**
     * 随机数
     */
    private static Random random = new Random();

    static {

        /* cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170))); */

        // 设置颜色
        cs.setColorFactory(new ColorFactory() {

            @Override
            public Color getColor(int index) {

                // 创建一个数组，用于储存 rgb 颜色
                int[] c = new int[3];

                // 在 3 以内的随机数
                int i = random.nextInt(c.length);

                // 使用两次随机数组合（第一次在0、1、2，第二次在0-71或0-256），
                // 使得rgb颜色的三个数，必有两个分别在0-71和0-256之间，另一个必在0-71或0-256中
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        // 在 71 以内的随机数
                        c[fi] = random.nextInt(71);
                    } else {
                        // 在 256 以内的随机数
                        c[fi] = random.nextInt(256);
                    }
                }

                // 返回颜色
                return new Color(c[0], c[1], c[2]);
            }

        });

        // 随机字符
        RandomWordFactory randomWordFactory = new RandomWordFactory();

        // 字符范围
        // 去掉了容易混淆的 1、0、l、o、I、O
        randomWordFactory.setCharacters("23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ");

        // 字符数量范围
        // 最多5个
        randomWordFactory.setMaxLength(5);
        // 最少3个
        randomWordFactory.setMinLength(3);
        // 设置随机字符
        cs.setWordFactory(randomWordFactory);

        // 图片像素
        cs.setHeight(50);
        cs.setWidth(150);

        // 字体大小范围
        RandomFontFactory randomFontFactory = new RandomFontFactory();

        // 字体最大大小
        randomFontFactory.setMaxSize(35);
        // 字体最小大小
        randomFontFactory.setMinSize(25);

        // 设置 字体大小范围
        cs.setFontFactory(randomFontFactory);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取 Session 需要在 getPatchca(req, resp) 之前
        HttpSession session = req.getSession();

        String patchca = getPatchca(req, resp);

        session.setAttribute(PATCHCA, patchca);

        log.debug("");
        log.debug("图片验证码为：" + patchca);
        log.debug("");

    }

    /**
     * 获取图片验证码对应的字符串
     */
    private String getPatchca(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        switch (random.nextInt(5)) {
            case 0:
                // 曲线波纹
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 1:
                // 大理石纹波
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                // 双纹波
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                // 摆动波纹
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 4:
                // 漫反射纹波
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
            default:
        }

        // 设置图片验证码的响应
        setResponseHeaders(resp);

        return EncoderHelper.getChallangeAndWriteImage(cs, "png", resp.getOutputStream());
    }

    /**
     * 设置图片验证码的响应
     * <p>
     * 禁止图片验证码缓存
     */
    private void setResponseHeaders(HttpServletResponse response) {
        // 发送到客户端的响应的内容类型
        response.setContentType(IMAGE_PNG_VALUE);

        // 设置具有给定名称和值的响应标头。
        // 如果已设置标头，则新值将覆盖前一个标头。
        // Cache-control：指定所有缓存机制在整个请求/响应链中必须服从的指令。
        // 这些指令指定用于阻止缓存对请求或响应造成不利干扰的行为。
        // 这些指令通常覆盖默认缓存算法。
        // 缓存指令是单向的，即请求中存在一个指令并不意味着响应中将存在同一个指令。
        // no-cache：必须先与服务器确认返回的响应是否被更改，然后才能使用该响应来满足后续对同一个网址的请求。
        // 因此，如果存在合适的验证令牌 (ETag)，no-cache 会发起往返通信来验证缓存的响应，如果资源未被更改，可以避免下载。
        // no-store：所有内容都不会被缓存到缓存或 Internet 临时文件中
        response.setHeader(CACHE_CONTROL, NO_CACHE_NO_STORE);
        response.setHeader(PRAGMA, NO_CACHE);

        // 时间戳
        long time = System.currentTimeMillis();

        // 设置具有给定名称和日期值的响应标头。
        // 日期以自纪元以来的毫秒数指定。
        // 如果已设置标头，则新值将覆盖前一个标头。
        // Last-Modified：在浏览器第一次请求某一个URL时，服务器端的返回状态会是200，内容是客户端请求的资源，
        // 同时有一个Last-Modified的属性标记此文件在服务器端最后被修改的时间。
        response.setDateHeader(LAST_MODIFIED, time);
        response.setDateHeader(DATE, time);
        // Expires：Expires是RFC 2616（HTTP/1.0）协议中和网页缓存相关字段。
        // 用来控制缓存的失效日期，要注意的是，HTTP/1.0有一个功能比较弱的缓存控制机制：Pragma，使用HTTP/1.0的缓存将忽略Expires和Cache-Control头。
        response.setDateHeader(EXPIRES, time);
    }

}
