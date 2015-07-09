package me.anchora.inpaasmgr.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 校验码控制器
 * </p>
 * 
 * @author liurong
 * @version ValidateCodeServlet.java,v 0.1 2008-11-20 上午09:22:31 Administrator
 *          Exp
 */
public class ValidateCodeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // 验证码图片的宽度。
    private int width = 10;

    // 验证码图片的高度。
    private int height = 5;

    // 验证码字符个数
    private int codeCount = 5;

    private int x = 0;

    // 字体高度
    private int fontHeight;

    private int codeY;

    char[] codeSequence = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

    /**
     * 初始化验证图片属性
     */
    public void init() throws ServletException {
        // 宽度
        String strWidth = this.getInitParameter("width");
        // 高度
        String strHeight = this.getInitParameter("height");
        // 字符个数
        String strCodeCount = this.getInitParameter("codeCount");

        // 将配置的信息转换成数值
        try {
            if (strWidth != null && strWidth.length() != 0) {
                width = Integer.parseInt(strWidth);
            }
            if (strHeight != null && strHeight.length() != 0) {
                height = Integer.parseInt(strHeight);
            }
            if (strCodeCount != null && strCodeCount.length() != 0) {
                codeCount = Integer.parseInt(strCodeCount);
            }
        } catch (NumberFormatException e) {

        }
        x = width / (codeCount);
        fontHeight = height - 10;
        codeY = height - 6;
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {

        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, width - 1, height - 1);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 15; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(8);
            int yl = random.nextInt(8);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            red = 0;// random.nextInt(255);
            green = 0;// random.nextInt(255);
            blue = 0;// random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i) * x, codeY);
            randomCode.append(strRand);
        }
        HttpSession session = req.getSession();
        //将验证码保存到session中，便于以后验证(即把生成的验证码randomCode.toString()保存到属性名称“validateCode”中，获取验证码值则可用request.getSession().getAttribute("validateCode"))
        session.setAttribute("validateCode", randomCode.toString());
        //当你访问服务器的时候，服务器创建一个session保存你的东西，比如账号信息等， 当你结束服务器访问的时候，session就会销毁，里面存的东西也就没了；session存放在服务器内存中，并不是在浏览器所在的机器上。Session的id保存在客户机的cookie中。

        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");

        ServletOutputStream sos = resp.getOutputStream();
        //发送图片  
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }
}
