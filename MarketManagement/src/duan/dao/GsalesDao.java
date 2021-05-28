package duan.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import duan.db.DbClose;
import duan.db.DbConn;
import duan.entity.Gsales;
/**
 * @author zhang
 * @version 1.0
 * 数据库gSales表操作
 */
public final class GsalesDao {
    Connection        conn  = null;
    PreparedStatement pstmt = null;
    ResultSet 		  rs    = null;

    /**
     * 1.当天卖出的商品
     * @return ArrayList<Gsales> 商品信息,包括 allSum (单种商品的销售总和)
     */
    public ArrayList<Gsales> dailyGsales()
    {
        ArrayList<Gsales> GsalesList = new ArrayList<Gsales>();
        conn = DbConn.getconn();
        String sql = "select* from gsales left join goods using(gid) where sdate=?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, new java.sql.Date(new Date().getTime()));
            rs 	  = pstmt.executeQuery();
            while (rs.next())
            {
                String gName = rs.getString(6);
                double gPrice = rs.getDouble(7);
                int gNum = rs.getInt(8);

                Gsales Gsales = new Gsales(gName,gPrice,gNum);
                GsalesList.add(Gsales);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            DbClose.queryClose(pstmt,rs,conn);
        }
        return GsalesList;
    }

    /**
     *2.购物结算-向sales表中插入商品数据！
     *@param gSales 售卖商品对象
     *@return boolean
     */
    public boolean shoppingSettlement(Gsales gSales)
    {
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "INSERT INTO GSALES(gsid,GID,SID,sdate,SNUM) VALUES(?,?,?,?,?)";

        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,gSales.getGsid());
            pstmt.setInt(2,gSales.getGId());
            pstmt.setDate(4, new java.sql.Date(new Date().getTime()));
            pstmt.setInt(3,gSales.getSId());
            pstmt.setInt(5,gSales.getSNum());

            int rs = pstmt.executeUpdate();
            if (rs > 0)
            {
                bool = true;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }
}
