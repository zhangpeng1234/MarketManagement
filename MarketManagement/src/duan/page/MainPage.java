package duan.page;

/**
 * @author zhang
 * @version 1.0
 * 商超购物管理系统主界面
 */

import java.util.ArrayList;

import duan.dao.GoodsDao;
import duan.dao.GsalesDao;
import duan.dao.SalesManDao;
import duan.entity.Goods;
import duan.entity.Gsales;
import duan.entity.SalesMan;
import duan.tools.Arith;
import duan.tools.QueryPrint;
import duan.tools.ScannerChoice;

    public final class MainPage extends ScannerChoice {

        public static void main(String[] args) {
            MainPage.mainPage();
        }

        /**
         * 主界面 已实现！
         */
        public static void mainPage() {
            System.out.println("***************************\n");
            System.out.println("\t 1.商品维护\n");
            System.out.println("\t 2.前台收银\n");
            System.out.println("\t 3.商品管理\n");
            System.out.println("***************************");

            System.out.println("\n请输入选项,或者按0退出.");
            do {
                String choice = ScannerInfoString();
                String regex = "[0-3]";//正则表达式
                if (choice.matches(regex)) {
                    int info = Integer.parseInt(choice);
                    switch (info) {
                        case 0:
                            System.out.println("------------------");
                            System.out.println("您已经退出系统!");
                            System.exit(1);//退出程序，返回值随便设置
                            break;
                        case 1:
                            MaintenancePage();
                            break;
                        case 2:
                            checkstandLogPage();
                            break;
                        case 3:
                            commodityManagementPage();
                            break;
                        default:
                            break;
                    }
                }
                System.err.println("!输入有误!");
                System.out.println("重新选择或者按0退出.");
            } while (true);

        }

        /**
         * 1.商品维护界面
         */
        public static void MaintenancePage() {

            System.out.println("***************************\n");
            System.out.println("\t 1.添加商品\n");
            System.out.println("\t 2.更改商品\n");
            System.out.println("\t 3.删除商品\n");
            System.out.println("\t 4.查询商品\n");
            System.out.println("\t 5.显示所有商品\n");
            System.out.println("***************************");

            System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
            do {
                String choice = ScannerInfoString();
                String regex = "[0-5]";
                if (choice.matches(regex)) {
                    int info = Integer.parseInt(choice);
                    switch (info) {
                        case 0:
                            mainPage();
                            break;
                        case 1:
                            GoodsPage.addGoodsPage();
                            break;
                        case 2:
                            GoodsPage.updateGoodsPage();
                            break;
                        case 3:
                            GoodsPage.deleteGoodsPage();
                            break;
                        case 4:
                            GoodsPage.queryGoodsPage();
                            break;
                        case 5:
                            GoodsPage.displayGoodsPage();
                            break;
                        default:
                            break;
                    }
                }
                System.err.println("!输入有误!");
                System.out.println("重新输入或按 0 返回上一级菜单.");
            } while (true);
        }

        /**
         * 2.前台收银登陆界面
         */
        public static void checkstandLogPage() {
            System.out.println("\n*******欢迎使用商超购物管理系统*******\n");
            System.out.println("\t 1.登录系统\n");
            System.out.println("\t 2.退出\n");
            System.out.println("-----------------------------");
            System.out.println("请输入选项,或者按 0 返回上一级菜单.");

            do {
                String choice = ScannerInfoString();
                String regex = "[0-2]";
                if (choice.matches(regex)) {
                    int info = Integer.parseInt(choice);
                    switch (info) {
                        case 0:
                            mainPage();
                            break;
                        case 1:
                            int loginTimes = 3;//3次登陆机会

                            while (loginTimes != 0) {
                                loginTimes--;
                                System.out.println("---用户名---");
                                String sName = ScannerInfoString();
                                System.out.println("---密码---");
                                String sPssWord = ScannerInfoString();

                                ArrayList<SalesMan> salesManInfo = new SalesManDao().checkstandLog(sName); //以用户名从数据库中获取用户密码.

                                if (salesManInfo == null || salesManInfo.size() == 0)//没有此用户的情况！
                                {
                                    System.err.println("\t!!用户名输入有误!!\n");
                                    System.out.println("\n剩余登陆次数：" + loginTimes);
                                } else {
                                    SalesMan salesMan = salesManInfo.get(0);//此地，只返回了一组数值，只遍历1次即可

                                    if (sPssWord.equals(salesMan.getSPassWord()))//验证密码，登陆成功了！！
                                    {
                                        System.out.println("\t  ---账户成功登陆---");
                                        shoppingSettlementPage(salesMan.getSId());//参数为营业员编号sId
                                    } else {
                                        System.err.println("\t!!密码错误!!\n");
                                        System.out.println("\n剩余登陆次数：" + loginTimes);
                                    }
                                }
                            }
                            //loginTimes = 0
                            System.out.println("------------------");
                            System.err.println("\t！！您已被强制退出系统！！");
                            System.exit(1);
                            break;
                        case 2:
                            System.out.println("------------------");
                            System.out.println("您已经退出系统!");
                            System.exit(-1);
                            break;
                        default:
                            break;
                    }
                }
                System.err.println("!输入有误!");
                System.out.println("重新输入或按 0 返回上一级菜单");
            } while (true);
        }

        /**
         * 3.商品管理界面
         */
        public static void commodityManagementPage() {
            System.out.println("***************************\n");
            System.out.println("\t 1.售货员管理\n");
            System.out.println("\t 2.列出当日卖出列表\n");
            System.out.println("***************************");

            System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
            do {
                String choice = ScannerInfoString();
                String regex = "[0-2]";
                if (choice.matches(regex)) {
                    int info = Integer.parseInt(choice);
                    switch (info) {
                        case 0:
                            mainPage();
                            break;
                        case 1:
                            salesManManagementPage();
                            break;
                        case 2:
                            GsalesPage.dailySaleGoodsPage();
                            break;
                        default:
                            break;
                    }
                }
                System.err.println("!输入有误!");
                System.out.println("重新输入或按 0 返回上一级菜单.");
            } while (true);
        }

        /**
         * 购物结算界面
         */
        public static void shoppingSettlementPage(int salesManSid) {
            System.out.println("\n\t*******购物结算*******\n");
            do {
                System.out.println("按 S 开始购物结算.按 0 返回账户登录界面");
                String choNext = ScannerInfoString();
                if ("0".equals(choNext)) {
                    checkstandLogPage();

                } else if ("s".equals(choNext) || "S".equals(choNext)) {
                    System.out.println("\n--请输入商品关键字--");

                    int gid = QueryPrint.querySettlement();//当商品件数有且只有一件时返回商品gid号，商品已售空时返回 -1. >1件时返回-2 . 查无此商品时返回-3

                    switch (gid) {
                        case -3:
                            //无此商品,重新循环
                            break;
                        case -1:
                            System.err.println("\t--抱歉，该商品已售空--");
                            break;

                        default:
                            System.out.println("--按商品编号选择商品--");

                            //传参gid，调用精确查询商品
                            int shoppingGid = ScannerNum();

                            ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(shoppingGid, null);
                            if (goodsList == null || goodsList.size() == 0) {
                                System.err.println("\t！！查无此商品 ！！\n");
                            } else {
                                Goods goods = goodsList.get(0);
                                int gNum = goods.getGnum();
                                double gPrice = goods.getGprice();

                                System.out.println("--请输入购买数量--");
                                do {
                                    int choicegoodsNum = ScannerNum();//获取用户要购买的数量

                                    if (choicegoodsNum > gNum) {
                                        System.err.println("\t！！仓库储备不足！！");
                                        System.out.println("--请重新输入购买数量--");
                                    } else {
                                        double allPrice = Arith.mul(choicegoodsNum, gPrice);//利用BigDecimal作乘法运算
                                        System.out.println("\t\t\t  购物车结算\n");
                                        System.out.println("\t\t商品名称\t商品单价\t购买数量\t总价\n");
                                        System.out.println("\t\t" + goods.getGname() + "\t" + gPrice + " $\t" + choicegoodsNum + "\t" + allPrice + " $\n");

                                        do {
                                            System.out.println("确认购买：Y/N");
                                            String choShopping = ScannerInfoString();
                                            if ("y".equals(choShopping) || "Y".equals(choShopping)) {
                                                System.out.println("\n总价：" + allPrice + " $");
                                                System.out.println("\n实际缴费金额");

                                                do {
                                                    double amount = ScannerInfo();
                                                    double balance = Arith.sub(amount, allPrice);  //用户交钱与购买物品总价间的差额
                                                    if (balance < 0) {
                                                        System.err.println("\t！！缴纳金额不足！！");
                                                        System.out.println("\n请重新输入缴纳金额($)");
                                                    } else {

		/*	这里是购物结算操作数据库！！！！！！----------------------	  1.更改goods表数量
		  														  2.增加sales表数量
																原商品数量gNum。 结算员Id  salesManSid */

                                                        //对sales表操作
                                                        Gsales gSales = new Gsales(goods.getGid(), salesManSid, choicegoodsNum);
                                                        boolean insert = new GsalesDao().shoppingSettlement(gSales);

                                                        //对goods表操作
                                                        int goodsNewNum = gNum - choicegoodsNum; //现在goods表中该商品数量
                                                        Goods newGoods = new Goods(goods.getGid(), goodsNewNum);
                                                        boolean update = new GoodsDao().updateGoods(3, newGoods);

                                                        if (update && insert) {
                                                            System.out.println("找零：" + balance);
                                                            System.out.println("\n谢谢光临，欢迎下次惠顾");
                                                        } else {
                                                            System.err.println("！支付失败！"); //出现这个错误一定是数据库操作有问题！
                                                        }
                                                        shoppingSettlementPage(salesManSid);//最后跳转到到购物结算页面
                                                        //	结束购物结算操作数据库！！！！！！-----------------------------------
                                                    }
                                                } while (true);

                                            } else if ("N".equals(choShopping) || "n".equals(choShopping)) {
                                                shoppingSettlementPage(salesManSid);
                                            }
                                            System.err.println("\t！！请确认购物意向！！");
                                        } while (true);
                                    }
                                } while (true);
                            }
                            break;
                    }
                } else {
                    System.err.println("\t!!请输入合法字符!!\n");
                }
            } while (true);
        }

        /**
         * 售货员管理界面
         */
        public static void salesManManagementPage() {

            System.out.println("***************************\n");
            System.out.println("\t 1.添加售货员\n");
            System.out.println("\t 2.更改售货员\n");
            System.out.println("\t 3.删除售货员\n");
            System.out.println("\t 4.查询售货员\n");
            System.out.println("\t 5.显示所有售货员\n");
            System.out.println("***************************");

            System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
            do {
                String choice = ScannerInfoString();
                String regex = "[0-5]";
                if (choice.matches(regex)) {
                    int info = Integer.parseInt(choice);
                    switch (info) {
                        case 0:
                            commodityManagementPage();
                            break;
                        case 1:
                            SalesManPage.addSalesManPage();
                            break;
                        case 2:
                            SalesManPage.updateSalesManPage();
                            break;
                        case 3:
                            SalesManPage.deleteSalesManPage();
                            break;
                        case 4:
                            SalesManPage.querySalesManPage();
                            break;
                        case 5:
                            SalesManPage.displaySalesManPage();
                            break;
                        default:
                            break;
                    }
                }
                System.err.println("\t!输入有误!");
                System.out.println("重新输入或按 0 返回上一级菜单.");
            } while (true);
        }
    }
