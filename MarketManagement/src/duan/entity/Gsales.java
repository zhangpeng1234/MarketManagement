package duan.entity;

/**
 * @author zhang
 * @version 1.0
 * Gsales g购物结算实体类
 */
public final class Gsales {
    private static int count;
    private int gsid;
    private int gId;
    private int sId;
    private int sNum;

    private String gName;
    private double gPrice;
    private int gNum;

    /**
     * 购物结算
     * @param gId,sId,sNum
     */
    public Gsales(int gId,int sId, int sNum)
    {
        this.gId = gId;
        this.sId = sId;
        this.sNum = sNum;
        count++;
        setGsid(count);
    }

    /**
     * 展现商品列表
     * @param gName,gPrice,gNum,allSnum
     */
    public Gsales(String gName,double gPrice,int gNum)
    {
        this.gName = gName;
        this.gPrice = gPrice;
        this.gNum = gNum;
    }

    //共有set、get
    public int getGId()
    {
        return gId;
    }
    public void setGId(int id)
    {
        gId = id;
    }
    public int getSId()
    {
        return sId;
    }
    public void setSId(int id)
    {
        sId = id;
    }
    public int getSNum()
    {
        return sNum;
    }
    public void setSNum(int num)
    {
        sNum = num;
    }
    public String getGName()
    {
        return gName;
    }
    public void setGName(String name)
    {
        gName = name;
    }
    public double getGPrice()
    {
        return gPrice;
    }
    public void setGPrice(double price)
    {
        gPrice = price;
    }
    public int getGNum()
    {
        return gNum;
    }
    public void setGNum(int num)
    {
        gNum = num;
    }

    public int getGsid() {
        return gsid;
    }

    public void setGsid(int gsid) {
        this.gsid = gsid;
    }
}
