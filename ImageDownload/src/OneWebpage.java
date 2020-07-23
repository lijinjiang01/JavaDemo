import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* 下载一个网页内的指定图片
* */
public class OneWebpage {
    // 网页地址
    private static String URL = "";
    // 获取 img 标签的正则表达式
    private static final String ImgTag_REG = "<img.*?src=[\\\"|\\']?(.*?)[\\\"|\\']?\\s.*?>";
    // 获取 src 属性的正则表达式
    private static final String ImgSrc_REG = "[a-zA-z]+://[^\\s]*";
    // 保存文件名
    private static String filename = "";

    public static void main(String[] args) throws Exception {
        String savePath = "images"; // 存储图片地址
        URL = "https://max.book118.com/html/2019/0429/8136072030002021.shtm";
        String html = getHTML(URL);
        List<String> imgList = getImageTag(html);
        for(String img:imgList){
            System.out.println(img);
        }
        /*for (int i = 1,j = 1;i<imgList.size();i++) {
            try {
                String imgSrc = getImageSrc(imgList.get(i));
                if ("".equals(imgSrc)||imgSrc == null){
                    continue;
                }else{
                    filename = j+".jpg";
                    Download(imgSrc, filename, savePath);
                    j++;
                }
            }
            catch (Exception e){
                e.printStackTrace();
                break;
            }
        }*/
    }

    // 获取 html 内容
    public static String getHTML(String targetUrl) throws Exception {
        URL url = new URL(targetUrl);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0"); // 添加请求头
        InputStream is = conn.getInputStream(); // 获取输入流
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = br.readLine()) != null) {
            buffer.append(line);
            buffer.append("\n");
        }
        br.close();
        isr.close();
        is.close();
        return buffer.toString();
    }

    // 匹配所有符合要求的 img 标签
    public static List<String> getImageTag(String html) {
        Matcher matcher= Pattern.compile(ImgTag_REG).matcher(html);
        List<String> listImgTag=new ArrayList<String>();
        while (matcher.find()){
            listImgTag.add(matcher.group());
        }
        return listImgTag;
    }

    // 获取 img 标签里需要的 src 属性值
    public static String getImageSrc(String imgTag) {
        String imgSrc = "";
        Matcher matcher = Pattern.compile(ImgSrc_REG).matcher(imgTag);
        while (matcher.find()) {
            imgSrc=matcher.group().substring(0,matcher.group().length() - 1);
            return imgSrc;
        }
        return imgSrc;
    }

    // 根据 imgSrc 下载图片，保存到指定位置
    private static void Download(String imgSrc,String filename, String savePath) throws Exception{
        try {
            URL uri = new URL(imgSrc);
            URLConnection conn = uri.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0"); // 添加请求头
            InputStream in = conn.getInputStream();;
            FileOutputStream fo = new FileOutputStream(new File(savePath+"\\"+filename));// 文件输出流
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = in.read(buf, 0, buf.length)) != -1) {
                fo.write(buf, 0, length);
            }
            // 关闭流
            in.close();
            fo.close();
            System.out.println(filename + "下载完成");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(imgSrc + "图片下载失败");
            return;
        }
    }

}
