/**
 * Created by lenovo on 2019/11/20.
 */
public class WaterMelon {
    // 编号,色泽,根蒂,敲声,纹理,脐部,触感,密度,含糖率,好瓜
    private int id;
    private String color;
    private String genTi;
    private String qiaoSheng;
    private String wenLi;
    private String qiBu;
    private String chuGan;
    private float miDu;
    private float hanTangLv;
    private boolean flag;
    public WaterMelon(){

    }

    public WaterMelon(int id, String color, String genTi, String qiaoSheng, String wenLi, String qiBu, String chuGan, float miDu, float hanTangLv, boolean flag) {
        this.id = id;
        this.color = color;
        this.genTi = genTi;
        this.qiaoSheng = qiaoSheng;
        this.wenLi = wenLi;
        this.qiBu = qiBu;
        this.chuGan = chuGan;
        this.miDu = miDu;
        this.hanTangLv = hanTangLv;
        this.flag = flag;
    }

    public WaterMelon(String color, String genTi, String qiaoSheng, String wenLi, String qiBu, String chuGan, float miDu, float hanTangLv) {
        this.color = color;
        this.genTi = genTi;
        this.qiaoSheng = qiaoSheng;
        this.wenLi = wenLi;
        this.qiBu = qiBu;
        this.chuGan = chuGan;
        this.miDu = miDu;
        this.hanTangLv = hanTangLv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGenTi() {
        return genTi;
    }

    public void setGenTi(String genTi) {
        this.genTi = genTi;
    }

    public String getQiaoSheng() {
        return qiaoSheng;
    }

    public void setQiaoSheng(String qiaoSheng) {
        this.qiaoSheng = qiaoSheng;
    }

    public String getWenLi() {
        return wenLi;
    }

    public void setWenLi(String wenLi) {
        this.wenLi = wenLi;
    }

    public String getQiBu() {
        return qiBu;
    }

    public void setQiBu(String qiBu) {
        this.qiBu = qiBu;
    }

    public String getChuGan() {
        return chuGan;
    }

    public void setChuGan(String chuGan) {
        this.chuGan = chuGan;
    }

    public float getMiDu() {
        return miDu;
    }

    public void setMiDu(float miDu) {
        this.miDu = miDu;
    }

    public float getHanTangLv() {
        return hanTangLv;
    }

    public void setHanTangLv(float hanTangLv) {
        this.hanTangLv = hanTangLv;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "WaterMelon{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", genTi='" + genTi + '\'' +
                ", qiaoSheng='" + qiaoSheng + '\'' +
                ", wenLi='" + wenLi + '\'' +
                ", qiBu='" + qiBu + '\'' +
                ", chuGan='" + chuGan + '\'' +
                ", miDu=" + miDu +
                ", hanTangLv=" + hanTangLv +
                ", flag=" + flag +
                '}';
    }
}
