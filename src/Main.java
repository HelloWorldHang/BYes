import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lenovo on 2019/11/20.
 */
public class Main {
    public static void main(String args[]) throws IOException {
        String path = "res/waterMelon";
        List<WaterMelon> waterMelons = readFromFile(path);
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        String[] strs = inputStr.split(",");
        WaterMelon waterMelon = new WaterMelon(strs[0], strs[1], strs[2], strs[3], strs[4], strs[5], Float.valueOf(strs[6]), Float.valueOf(strs[7]));

        // waterMelons.forEach(System.out::println);

        // 朴素贝叶斯分类
        // p(a\b) = p(b/a)p(a) / p(b)
        // 是好瓜的概率 只计算p(各属性/好瓜)p(好瓜)

        int qlGoodNum=0,qsGoodNum=0,zxGoodNum=0,qxGoodNum=0,axGoodNum=0,yhGoodNum=0;
        int qlBadNum=0,qsBadNum=0,zxBadNum=0,qxBadNum=0,axBadNum=0,yhBadNum=0;
        float mdGoodAve=0,htlGoodAve=0,mdGoodVariance=0,htlGoodVariance=0;
        float mdBadAve=0,htlBadAve=0,mdBadVariance=0,htlBadVariance=0;
        double qlGood,qsGood,zxGood,qxGood,axGood,yhGood,mdGood,htlGood;
        double qlBad,qsBad,zxBad,qxBad,axBad,yhBad,mdBad,htlBad;
        // 色泽,根蒂,敲声,纹理,脐部,触感,密度,含糖率,好瓜
        // 青绿,蜷缩,浊响,清晰,凹陷,硬滑,0.697,0.460
        // 先计算好瓜、坏瓜的概率
        int goodCount = 0;
        int badCount = 0;
        float good, bad; // 好瓜坏瓜的概率
        for (WaterMelon wm:waterMelons
             ) {

            if (wm.isFlag()){
                mdGoodAve+=wm.getMiDu();
                htlGoodAve+=wm.getHanTangLv();
                goodCount++;
                if (wm.getColor().equals(waterMelon.getColor()))
                    qlGoodNum++;
                if (wm.getGenTi().equals(waterMelon.getGenTi()))
                    qsGoodNum++;
                if (wm.getQiaoSheng().equals(waterMelon.getQiaoSheng()))
                    zxGoodNum++;
                if (wm.getWenLi().equals(waterMelon.getWenLi()))
                    qxGoodNum++;
                if (wm.getQiBu().equals(waterMelon.getQiBu()))
                    axGoodNum++;
                if (wm.getChuGan().equals(waterMelon.getChuGan()))
                    yhGoodNum++;
            }else{
                mdBadAve+=wm.getMiDu();
                htlBadAve+=wm.getHanTangLv();
                badCount++;
                if (wm.getColor().equals(waterMelon.getColor()))
                    qlBadNum++;
                if (wm.getGenTi().equals(waterMelon.getGenTi()))
                    qsBadNum++;
                if (wm.getQiaoSheng().equals(waterMelon.getQiaoSheng()))
                    zxBadNum++;
                if (wm.getWenLi().equals(waterMelon.getWenLi()))
                    qxBadNum++;
                if (wm.getQiBu().equals(waterMelon.getQiBu()))
                    axBadNum++;
                if (wm.getChuGan().equals(waterMelon.getChuGan()))
                    yhBadNum++;
            }
        }
        // 计算离散属性好坏瓜概率
        // float qlGood,qsGood,zxGood,qxGood,axGood,yhGood,mdGood,htlGood;
        qlGood = (float) qlGoodNum / goodCount;
        qsGood = (float) qsGoodNum / goodCount;
        zxGood = (float) zxGoodNum / goodCount;
        qxGood = (float) qxGoodNum / goodCount;
        axGood = (float) axGoodNum / goodCount;
        yhGood = (float) yhGoodNum / goodCount;

        qlBad = (float) qlBadNum / badCount;
        qsBad = (float) qsBadNum / badCount;
        zxBad = (float) zxBadNum / badCount;
        qxBad = (float) qxBadNum / badCount;
        axBad = (float) axBadNum / badCount;
        yhBad = (float) yhBadNum / badCount;

        // 计算连续属性均值
        mdGoodAve = mdGoodAve / goodCount;
        htlGoodAve = htlGoodAve / goodCount;
        mdBadAve = mdBadAve / badCount;
        htlBadAve = htlBadAve / badCount;
        // 好坏瓜概率
        good = (float) goodCount / waterMelons.size();
        bad = (float) badCount / waterMelons.size();
        // 计算连续属性方差
        for (WaterMelon wm: waterMelons
             ) {
            if (wm.isFlag()){
                mdGoodVariance+=Math.pow(wm.getMiDu()-mdGoodAve,2);
                htlGoodVariance+=Math.pow(wm.getHanTangLv()-htlGoodAve,2);
            }else{
                mdBadVariance+=Math.pow(wm.getMiDu()-mdBadAve,2);
                htlBadVariance+=Math.pow(wm.getHanTangLv()-htlBadAve,2);
            }
        }
        mdGoodVariance = mdGoodVariance / goodCount;
        htlGoodVariance = htlGoodVariance / goodCount;
        mdBadVariance = mdBadVariance / badCount;
        htlBadVariance = htlBadVariance / badCount;
        // 计算连续属性的好坏瓜概率
        mdGood = ((1 / (Math.sqrt(mdGoodVariance)*Math.sqrt(2*Math.PI))) * Math.exp(-(Math.pow(waterMelon.getMiDu()-mdGoodAve,2)/(2*mdGoodVariance))));
        mdBad = ((1 / (Math.sqrt(mdBadVariance)*Math.sqrt(2*Math.PI))) * Math.exp(-(Math.pow(waterMelon.getMiDu()-mdBadAve,2)/(2*mdBadVariance))));
        htlGood = ((1 / (Math.sqrt(htlGoodVariance)*Math.sqrt(2*Math.PI))) * Math.exp(-(Math.pow(waterMelon.getHanTangLv()-htlGoodAve,2)/(2*htlGoodVariance))));
        htlBad = ((1 / (Math.sqrt(htlBadVariance)*Math.sqrt(2*Math.PI))) * Math.exp(-(Math.pow(waterMelon.getHanTangLv()-htlBadAve,2)/(2*htlBadVariance))));
        // 好瓜概率
        double isGood = (qlGood * qsGood * zxGood * qxGood * axGood * yhGood * mdGood * htlGood) * good;
        double isBad = (qlBad * qsBad * zxBad * qxBad * axBad * yhBad * mdBad * htlBad) * bad;

        System.out.println("好瓜指数： " + isGood + " 坏瓜指数： " + isBad);
        if (isGood > isBad)
            System.out.println("大概率是好瓜");
        else
            System.out.println("大概率是坏瓜");
    }

    //

    // 根据文件名读取文件
    public static List<WaterMelon> readFromFile(String path) throws IOException {
        List<String> strList = new ArrayList<>();
        File file = new File( path );
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        // 读取第一行，第一行没用
        line = br.readLine();
        while (line != null){
            strList.add(line);
            line = br.readLine();
        }
        int n = strList.size();
        List<WaterMelon> proList = new ArrayList<>();
        for (int i = 0; i < n; i++){
            String str = strList.get(i);
            String[] s = str.split(",");
            boolean flag;
            if (s[9].equals("是")){
                flag = true;
            }else{
                flag = false;
            }
            WaterMelon waterMelon = new WaterMelon(Integer.valueOf(s[0]),s[1],s[2],s[3],s[4],s[5],s[6],Float.valueOf(s[7]),Float.valueOf(s[8]),flag);

            proList.add(waterMelon);

        }

        return proList;
    }
}
