package duan.page;

import java.util.ArrayList;

import duan.dao.GsalesDao;
import duan.entity.Gsales;
import duan.tools.ScannerChoice;
/**
 * @author zhang
 * @version 1.0
 * 当日卖出商品列表界面
 */
public class GsalesPage {
    public static void dailySaleGoodsPage() {
        System.out.println("\t正在执行列出当日售出商品列表操作\n");
        ArrayList<Gsales> GsalesList = new GsalesDao().dailyGsales();//当日售出商品数组集

        if (GsalesList.size() <= 0) {
            System.err.println("\t！！今日无商品售出！！");
            MainPage.commodityManagementPage();
        } else {
            System.out.println("\t\t\t\t今日售出商品列表\n");
            System.out.println("\t商品名称\t\t商品价格\t\t商品数量\t\t销量\t\t备注\n");

            for (int i = 0, length = GsalesList.size(); i < length; i++) {
                //获取售出商品：gname,gprice,gnum
                Gsales gSales = GsalesList.get(i);
                System.out.print("\t" + gSales.getGName() + "\t\t" + gSales.getGPrice() + " $\t\t" + gSales.getGNum() + "\t\t");
                int gNUm = gSales.getGNum();
                if (gNUm == 0) {
                    System.out.println("\t\t该商品已售空");
                } else if (gNUm < 10) {
                    System.out.println("\t\t该商品已不足10件");
                } else {
                    System.out.println("\t\t-");
                }
                System.out.println("\t");
            }
            do {
                System.out.println("\n\n输入 0 返回上一级菜单");
                String choice = ScannerChoice.ScannerInfoString();
                if ("0".equals(choice)) {
                    MainPage.salesManManagementPage();
                }
                MainPage.commodityManagementPage();
            } while (true);
        }
    }
}