package com.diane.manage.service.impl;

import com.diane.manage.dao.GoodMapper;
import com.diane.manage.dao.ShopCartMapper;
import com.diane.manage.model.GoodInfo;
import com.diane.manage.model.ShopCart;
import com.diane.manage.model.ShopCartGood;
import com.diane.manage.service.ShopCartService;
import com.diane.manage.vo.GoodQuery;
import com.diane.manage.vo.ShopCartVo;
import com.diane.manage.vo.cart.CartResultVo;
import com.diane.manage.vo.cart.ShopGoodRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ShopCartServiceImpl implements ShopCartService {
    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Override
    public Long insertSelective(ShopCart shopCart) {
        shopCart.setFlag(false);
        int i=shopCartMapper.insertSelective(shopCart);
     //   shopCartGoodMapper.insertSelective(new ShopCartGood(shopCart.getGoodId(),shopCart.getCartId(),shopCart.getQuantity()));

        /*
        for (OrderGoodQuery orderGoodQuery : shopCart.getGoodList()){
            shopCartGoodMapper.insertSelective(new ShopCartGood(orderGoodQuery.getGoodId(),shopCart.getCartId(),orderGoodQuery.getQuantity()));
        }
        */


        return shopCart.getCartId();
    }

    @Override
    public int updateByPrimaryKeySelective(ShopCart shopCart) {
        if (shopCart.getType()==3){
            shopCart.setFlag(true);
        }
        int i=shopCartMapper.updateByPrimaryKeySelective(shopCart);
     //   shopCartGoodMapper.updateByPrimaryKeySelective(new ShopCartGood(shopCart.getGoodId(),shopCart.getCartId(),shopCart.getQuantity()));

        return i;
    }

    @Override
    public List<ShopCart> listShopCartCondition(ShopCartGood shopCartGood) {

        return null;
    }

    @Override
    public ShopCartVo showShopCartDetail(ShopCart shopCart) {
        ShopCartVo shopCartVo=shopCartMapper.showShopCartByUser(shopCart);
        shopCartVo.setGoodInfo(goodMapper.getGoodDetailsById(shopCart.getGoodId()));
      //  shopCartVo.setGoodRespVo(goodMapper.getGoodDetailsById(shopCart.getGoodId()));
        return shopCartVo;
    }

    @Override
    public List<GoodInfo> listShopGood(Long shopId, Long categoryId) {
        GoodQuery goodQuery=new GoodQuery();
        goodQuery.setCategoryId(categoryId);
        goodQuery.setShopId(shopId);
        //获取系统当前时间星期几
        String[][] strArray = {{"MONDAY", "一"}, {"TUESDAY", "二"}, {"WEDNESDAY", "三"}, {"THURSDAY", "四"}, {"FRIDAY", "五"}, {"SATURDAY", "六"}, {"SUNDAY", "日"}};
        LocalDate currentDate = LocalDate.now();
        String k = String.valueOf(currentDate.getDayOfWeek());
        for (int i = 0; i < strArray.length; i++) {
            if (k.equals(strArray[i][0])) {
                k = strArray[i][1];
                break;
            }
        }
        goodQuery.setSalePlan("星期".concat(k));

        return goodMapper.listGoodByConditions(goodQuery);
    }


    @Override
    public List<ShopGoodRespVo> shopCartList(ShopCart shopCart) {


        return shopCartMapper.shopCartList(shopCart.getOpenid(),shopCart.getShopId());
    }

    @Override
    public List<CartResultVo> listCartByOpenid(String openid) {
        List<CartResultVo> resultVos=shopCartMapper.listCartByOpenid(openid);
        for (CartResultVo cartResultVo:resultVos){
            cartResultVo.setTotalPrice(shopCartMapper.countTotalPrice(openid,cartResultVo.getShopId()));
            cartResultVo.setGoodList(shopCartMapper.shopCartList(openid,cartResultVo.getShopId()));
        }
        return resultVos;
    }

    @Override
    public CartResultVo singleShopCartInfo(String openid, Long shopId) {
        List<ShopGoodRespVo> list=shopCartMapper.shopCartList(openid,shopId);
        if (list==null||list.size()==0){
            return null;
        }
        CartResultVo cartResultVo=shopCartMapper.singleShopCartInfo(openid,shopId);
        Integer total=shopCartMapper.countTotalPrice(openid,shopId);
        if (total!=0){
        cartResultVo.setTotalPrice(total);
        }
        cartResultVo.setGoodList(list);
        return cartResultVo;
    }

    @Override
    public List<Long> findAllCartId(String openid, Long shopId) {
        return shopCartMapper.findAllCartId(openid,shopId);
    }

    @Override
    public int cleanShopCart(Long id) {
        int i=shopCartMapper.cleanShopCart(id);

        return i;
    }
}
