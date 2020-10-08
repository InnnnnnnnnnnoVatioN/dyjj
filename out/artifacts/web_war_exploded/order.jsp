<%@ page import="com.dy.bean.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dy.service.*" %><%--
  Created by IntelliJ IDEA.
  User: LWJ
  Date: 2020/2/17
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>order</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="css/mygxin.css"/>
</head>
<body><!----------------------------------------order------------------>
<%@include file="include_header.jsp"%>
<%
    //User user = (User)session.getAttribute("user");
    //0.    省份/自治区 数据
    List<CnRegion> citys = RegionService.findByLevel(1);
    //1.    收获地址数据
    List<Address> addressList = AddressService.findByUserId(user.getId());
    //2.    商品数据
    List<Goods> goodsList = null;
    String goodsId = request.getParameter("id");
    String numParameter = request.getParameter("num");
    List<Carts> cs = null;
    if(goodsId != null){
        //  2.1     直接购买
        goodsList = new ArrayList<>();
        Goods goods = GoodsService.findGoodsById(Integer.parseInt(goodsId));
        goodsList.add(goods);
    }else{
        //  2.2     购物车结算

        SqlCartsService service = new SqlCartsService();
        cs = service.findByUserId(user.getId());
        if(cs!=null && cs.size()!=0 ){
            goodsList = GoodsService.findGoodsByCarts(cs);
            ArrayList<Goods> gs2 = new ArrayList<>();
            for(int i=0;i<cs.size();i++){
                Carts c = cs.get(i);
                for(int j=0;j<goodsList.size();j++){
                    if(c.getGoodsId() == goodsList.get(j).getId()){
                        gs2.add(goodsList.get(j));
                        break;
                    }
                }
            }
            goodsList = gs2;
        }
    }

    //3.    付款方式数据
    List<Payments> pays = PaymentsService.findAll();
    //4.    物流公司数据
    List<Transports> trs = TransportsService.findAll();

%>



<div class="order cart mt"><!-----------------site------------------->
    <div class="site"><p class="wrapper clearfix"><span class="fl">订单确认</span><img class="top"
                                                                                   src="img/temp/cartTop02.png"></p>
    </div><!-----------------orderCon------------------->
    <div class="orderCon wrapper clearfix">
        <div class="orderL fl"><!--------h3----------------><h3>收件信息
            <a href="javascript:addressShow(1,null)" class="fr">新增地址</a>
        </h3>
        <div class="addres clearfix" id="addressList">
            <!--------addres---------------->
            <%
                if(addressList.size()==0){
                    %>
                        <div id="addressNull" style="height:100px;line-height:100px;text-align:center;font-size:32px">
                            暂无收货地址， 请先添加！
                        </div>
                    <%
                }else{
                    Address a1 = addressList.get(0);
                    %>
                <div onclick="addressId=<%=a1.getId()%>;" class="addre fl on"  id="a<%=a1.getId()%>">
                    <div class="tit clearfix"><p class="fl"><%=addressList.get(0).getUserName()%> <span class="default">[默认地址]</span></p>
                        <p class="fr"><a href="javascript:deleteAddress(<%=a1.getId()%>,this)">删除</a><span>|</span><a href="javascript:updateAddress(<%=a1.getId()%>,'<%=a1.getUserName()%>','<%=a1.getUserPhone()%>',<%=a1.getProvinceId()%>,<%=a1.getCityId()%>,<%=a1.getAreaId()%>,<%=a1.getStreetId()%>,'<%=a1.getUserAddress()%>')" class="edit">编辑</a></p></div>
                    <%
                        String provinceName = RegionService.findByCode(addressList.get(0).getProvinceId()+"").getName();
                        String cityName = RegionService.findByCode(addressList.get(0).getCityId()+"").getName();
                        String areaName = RegionService.findByCode(addressList.get(0).getAreaId()+"").getName();
                        String streetName = RegionService.findByCode(addressList.get(0).getStreetId()+"").getName();
                    %>
                    <div class="addCon"><p><%=provinceName%>&nbsp;<%=cityName%>&nbsp;<%=areaName%>&nbsp;<%=streetName%></p>
                        <p><%=addressList.get(0).getUserPhone()%></p></div>
                </div>
                <%
                    for(int i=1;i<addressList.size();i++){
                        a1 = addressList.get(i);
                        provinceName = RegionService.findByCode(a1.getProvinceId()+"").getName();
                        cityName = RegionService.findByCode(a1.getCityId()+"").getName();
                        areaName = RegionService.findByCode(a1.getAreaId()+"").getName();
                        streetName = RegionService.findByCode(a1.getStreetId()+"").getName();
                        %>
                        <div onclick="addressId=<%=a1.getId()%>;" class="addre fl" id="a<%=a1.getId()%>">
                            <div class="tit clearfix"><p class="fl"><%=a1.getUserName()%></p>
                                <p class="fr"><a href="javascript:setDefault(<%=a1.getId()%>)" class="setDefault">设为默认</a><span>|</span><a
                                        href="javascript:deleteAddress(<%=a1.getId()%>,this)">删除</a><span>|</span><a href="javascript:updateAddress(<%=a1.getId()%>,'<%=a1.getUserName()%>','<%=a1.getUserPhone()%>',<%=a1.getProvinceId()%>,<%=a1.getCityId()%>,<%=a1.getAreaId()%>,<%=a1.getStreetId()%>,'<%=a1.getUserAddress()%>')" class="edit">编辑</a></p></div>
                            <div class="addCon"><p><%=provinceName%>&nbsp;<%=cityName%>&nbsp;<%=areaName%>&nbsp;<%=streetName%></p>
                                <p><%=addressList.get(i).getUserPhone()%></p></div>
                        </div>
                <%
                    }
                %>


            <%
                }
            %>
            </div>
            <h3>支付方式</h3><!--------way---------------->
            <div class="way clearfix">
                <%
                    for(int i=0;i<pays.size();i++){
                        %>
                            <img onclick="payId=<%=pays.get(i).getId()%>" <%=i==0?"class=\"on\"":""%> src="img/<%=pays.get(i).getImg()%>" >
                        <%
                    }
                %>
            </div>
            <h3>选择快递</h3><!--------dis---------------->
            <div class="dis clearfix">
                <%
                    for(int i=0;i<trs.size();i++){
                %>
                    <span onclick="trId=<%=trs.get(i).getId()%>" <%=i==0?"class=\"on\"":""%>><%=trs.get(i).getName()%></span>
                <%
                    }
                %>
            </div>
        </div>
        <div class="orderR fr">
            <div class="msg"><h3>订单内容<a href="cart.jsp" class="fr">返回购物车</a></h3><!--------ul---------------->
                <%
                    double price = 0;
                    for(int i=0;i<goodsList.size();i++){
                        Goods g = goodsList.get(i);
                        int numInt = numParameter!=null?Integer.parseInt(numParameter):cs.get(i).getCartNum();
                        price += g.getPrice()*numInt;
                        %>
                        <ul class="clearfix">
                            <li class="fl"><img style="height:87px;width:87px" src="img/small/<%=g.getImg().get(0)%>"></li>
                            <li class="fl"><p><%=g.getName().length()>18?(g.getName().substring(0,15)+"…"):g.getName()%></p>
                                <p>商品分类：此商品无分类</p>
                                <p>数量：<%=numInt%></p></li>
                            <li class="fr">￥<%=g.getPrice()*numInt%></li>
                        </ul>
                <%
                    }
                %>
            </div><!--------tips---------------->
            <div class="tips"><p><span class="fl">商品金额：</span><span class="fr">￥<%=price%></span></p>
                <p><span class="fl">优惠金额：</span><span class="fr">￥0.00</span></p>
                <p><span class="fl">运费：</span><span class="fr">免运费</span></p></div><!--------tips count---------------->
            <div class="count tips"><p><span class="fl">合计：</span><span class="fr">￥<%=price%></span></p></div>
            <!--<input type="button" name="" value="去支付">--> <a href="javascript:buy()" class="pay">下单</a></div>
        <script>

            //支付方式
            var payId = <%=pays.get(0).getId()%>;
            //配送方式
            var trId = <%=trs.get(0).getId()%>;
            //收货地址
            var addressId = <%=addressList.size()>0?addressList.get(0).getId():"null"%>;
            //当用户通过立即购买跳转到此页面时, 我们需要传递商品id和数量.
            var goodsId = <%=request.getParameter("id")%>;
            var goodsNum = <%=request.getParameter("num")%>;



            function buy(){
                //进行下单, 访问buy.do
                if(addressId == null){
                    layer.msg("暂无收获地址, 请先新增收获地址.");
                    return;
                }
                var para = "?addressId="+addressId+"&payId="+payId+"&trId="+trId;
                if(goodsId!=null){
                    //立即购买传递的goodsId 和 数量goodsNum
                    para+="&goodsId="+goodsId+"&goodsNum="+goodsNum
                }
                window.location.href="buy.do"+para;
            }


        </script>
    </div>
</div><!--编辑弹框--><!--遮罩-->
<div class="mask"></div>
<div class="adddz editAddre">
    <form action="#" method="get">
        <input  id="userName" type="text" placeholder="姓名" class="on"/>
        <input  id="userPhone" type="text" placeholder="手机号"/>
        <div class="city dyjjcity">

            <select name="" onchange="selectChange(1,this)">
            <%--<option value="省份/自治区">省份/自治区</option>--%>
            <%
                for(CnRegion r:citys){
                    %>
                <option value="<%=r.getCode()%>"><%=r.getName()%></option>
                    <%
                }
            %>
            </select>
            <select onchange="selectChange(2,this)">
                <option value="城市/地区">城市/地区</option>
            </select>
            <select onchange="selectChange(3,this)">
                <option value="区/县">区/县</option>
            </select>
            <select  onchange="selectChange(4,this)">
                <option value="配送区域">配送区域</option>
            </select>
        </div>
        <textarea id="userAddress" name="" rows="" cols="" placeholder="详细地址"></textarea>
        <div class="bc"><input type="button" onclick="save()" value="保存"/><input type="button" value="取消"/></div>
    </form>
</div><!--返回顶部-->
<!--footer-->
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/layer/layer.js" type="text/javascript" charset="utf-8"></script>

<script>
    $(function(){
        selectChange(1,$(".dyjjcity>select:eq(0)").get(0));
    });


    function setDefault(id){
        layer.load();
        $.get("addressSetDefault.do","id="+id,function(data){
            layer.closeAll();
            layer.msg(data.msg);
            setTimeout(function(){
                window.location.reload();
            },750);
        });
    }
    function deleteAddress(id,a){
        layer.load();
        $.get("addressDelete.do","id="+id,function(data){
            layer.closeAll();
            layer.msg(data.msg);
            $("#a"+id).remove();
        });
    }


    function updateAddress(id,userName,userPhone,code1,code2,code3,code4,userAddress){
        addressShow(0,id);
        $("#userName").val(userName);
        $("#userPhone").val(userPhone);
        $("#userAddress").val(userAddress);
        //选中了省份
        var $opt = $("option[value='"+code1+"']");
        $opt.attr("selected",true);
        selectChange(1,$opt.parent().get(0));
        //设置市的选中
        $opt = $("option[value='"+code2+"']");
        $opt.attr("selected",true);
        selectChange(2,$opt.parent().get(0));
        //设置县的选中
        $opt = $("option[value='"+code3+"']");
        $opt.attr("selected",true);
        selectChange(3,$opt.parent().get(0));
        //设置街道的选中
        $opt = $("option[value='"+code4+"']");
        $opt.attr("selected",true);
        selectChange(4,$opt.parent().get(0));
    }
    /**
     * 1表示新增  0表示修改
     */
    var saveFlag = null;
    var updateId = null;
    function addressShow(type,addressId){
        if(type == 1){
            saveFlag = 1;
            $("#userName").val("");
            $("#userPhone").val("");
            $("#userAddress").val("");
        }else{
            saveFlag = 0;
            updateId = addressId;
        }
        $(".mask,.editAddre").show();
    }

    function save(){
        //1.    获取输入的姓名 input id=userName
        var userName = $("#userName").val();
        //2.    获取输入的电话号 input id=userPhone
        var userPhone = $("#userPhone").val();
        //3.    获取选中的省份 ， 市 ， 区 ， 街道信息 . dyjjcity select[0-3]
        var code1 = $(".dyjjcity option:selected").get(0).value;
        var code2 = $(".dyjjcity option:selected").get(1).value;
        var code3 = $(".dyjjcity option:selected").get(2).value;
        var code4 = $(".dyjjcity option:selected").get(3).value;
        //4.    获取输入的详细地址信息 id=userAddress
        var userAddress = $("#userAddress").val();
        layer.load();
        if(saveFlag == 1){
            //新增
            $.get("addressAdd.do",{
                "userName":userName,
                "userPhone":userPhone,
                "code1":code1,
                "code2":code2,
                "code3":code3,
                "code4":code4,
                "userAddress":userAddress,
                "new":$("#addressNull").length
            },function(data){
                layer.closeAll();
                layer.msg("新增地址成功");
                $(".mask,.editAddre").hide();
                //data:  {status:0|1,msg:"",data:{id:,userName:,userPhone:...}}
                var text = null;
                if(data.status == 1){
                    //第一次新增
                    $("#addressNull").remove();
                    addressId = data.data.id;
                    text = "<div onclick=\"addressId="+data.data.id+"\" class=\"addre fl on\" id=\"a"+data.data.id+"\">\n" +
                        "<div class=\"tit clearfix\"><p class=\"fl\">"+data.data.userName+"<span class=\"default\">[默认地址]</span></p>\n" +
                        "<p class=\"fr\"><a href=\"javascript:deleteAddress("+data.data.id+",this)\">删除</a><span>|</span><a href=\"javascript:updateAddress("+data.data.id+",'"+data.data.userName+"','"+data.data.userPhone+"',"+code1+","+code2+","+code3+","+code4+",'"+data.data.userAddress+"')\" class=\"edit\">编辑</a></p></div>\n" +
                        "\n" +
                        "<div class=\"addCon\"><p>"+$(".dyjjcity option:selected").get(0).innerHTML+"&nbsp;"+$(".dyjjcity option:selected").get(1).innerHTML+"&nbsp;"+$(".dyjjcity option:selected").get(2).innerHTML+"&nbsp;"+$(".dyjjcity option:selected").get(3).innerHTML+"</p>\n" +
                        "<p>"+data.data.userPhone+"</p></div>\n" +
                        "  </div>\n";
                }else{
                    //非第一次新增
                    text = "<div onclick=\"addressId="+data.data.id+"\" class=\"addre fl\" id=\"a"+data.data.id+"\">\n" +
                        "<div class=\"tit clearfix\"><p class=\"fl\">"+data.data.userName+"</p>\n" +
                        "<p class=\"fr\"><a href=\"javascript:setDefault("+data.data.id+")\" class=\"setDefault\">设为默认</a><span>|</span><a href=\"javascript:deleteAddress("+data.data.id+",this)\">删除</a><span>|</span><a href=\"javascript:updateAddress("+data.data.id+",'"+data.data.userName+"','"+data.data.userPhone+"',"+code1+","+code2+","+code3+","+code4+",'"+data.data.userAddress+"')\" class=\"edit\">编辑</a></p></div>\n" +
                        "\n" +
                        "<div class=\"addCon\"><p>"+$(".dyjjcity option:selected").get(0).innerHTML+"&nbsp;"+$(".dyjjcity option:selected").get(1).innerHTML+"&nbsp;"+$(".dyjjcity option:selected").get(2).innerHTML+"&nbsp;"+$(".dyjjcity option:selected").get(3).innerHTML+"</p>\n" +
                        "<p>"+data.data.userPhone+"</p></div>\n" +
                        "  </div>\n";
                }
                $("#addressList").append($(text));

            },"JSON");

        }else{
            //修改 updateId变量为修改的地址id值
            $.get("addressUpdate.do",{
                "id":updateId,
                "userName":userName,
                "userPhone":userPhone,
                "code1":code1,
                "code2":code2,
                "code3":code3,
                "code4":code4,
                "userAddress":userAddress
            },function(data){
                //刷新页面
                layer.closeAll();
                layer.msg("修改成功");
                setTimeout(function(){
                    window.location.reload();
                },750);
            });
        }

    }

    function selectChange(type,select){
        if(type == 4){
            //街道被选中 ： 地址已经选择完毕。
            return;
        }
        //获取选中的城市的 code值 (code值存储在了option标签的value属性中)
        var code = $(select).find("option:selected").val();
        //同步  单线程异步不阻塞的机制。

        $.ajax({
            url:"getRegionByParentCode.do",
            type:"GET",
            async:false,
            data:"code="+code,
            dataType:"JSON",
            success:function(data){
                var $select;
                switch (type) {
                    case 1://省份被选中 , 获取下一级所有信息 , data 就是市信息
                        //清空下拉选择框
                        $select = $(".dyjjcity>select:eq(1)");
                        break;
                    case 2://城市被选中 , 获取下一级所有信息, data 就是县或区信息
                        $select = $(".dyjjcity>select:eq(2)");
                        break;
                    case 3://县/区被选中 , 获取下一级所有信息 , data 就是乡镇街道信息
                        $select = $(".dyjjcity>select:eq(3)");
                        break;

                }
                $select.jsp("");
                for(var i=0;i<data.data.length;i++){
                    var city = data.data[i];
                    var $newOption = $("<option value='"+city.code+"'>"+city.name+"</option>");
                    $select.append($newOption);
                }
                //假设当前选中了一个省份 , 导致上面代码执行, 显示了市.
                //现在我们要联动 显示 县或区 , 要显示县或区,
                // 需要递归调用自身, 传递两个参数:
                // 1.   传递一个type=2 , 刚好是type+1
                // 2.   传递一个市的选择框 ,  因为当前函数 需要使用市的选择框,  得到被选中的市, 根据被选中的市 获取县信息
                // 3.   市的下标是1 , 刚好是type
                select = $(".dyjjcity>select:eq("+type+")").get(0);
                selectChange(type+1,select);
            }
        });




/*
        $.get("getRegionByParentCode.do","code="+code,function(data){
            //data:{status:200,msg:"",citys:[{id:xx,code:xx,name:xx},...]}
            var $select;
            switch (type) {
                case 1://省份被选中 , 获取下一级所有信息 , data 就是市信息
                    //清空下拉选择框
                    $select = $(".dyjjcity>select:eq(1)");
                    break;
                case 2://城市被选中 , 获取下一级所有信息, data 就是县或区信息
                    $select = $(".dyjjcity>select:eq(2)");
                    break;
                case 3://县/区被选中 , 获取下一级所有信息 , data 就是乡镇街道信息
                    $select = $(".dyjjcity>select:eq(3)");
                    break;

            }
            $select.jsp("");
            for(var i=0;i<data.data.length;i++){
                var city = data.data[i];
                var $newOption = $("<option value='"+city.code+"'>"+city.name+"</option>");
                $select.append($newOption);
            }
            //假设当前选中了一个省份 , 导致上面代码执行, 显示了市.
            //现在我们要联动 显示 县或区 , 要显示县或区,
            // 需要递归调用自身, 传递两个参数:
            // 1.   传递一个type=2 , 刚好是type+1
            // 2.   传递一个市的选择框 ,  因为当前函数 需要使用市的选择框,  得到被选中的市, 根据被选中的市 获取县信息
            // 3.   市的下标是1 , 刚好是type
            select = $(".dyjjcity>select:eq("+type+")").get(0);
            selectChange(type+1,select);

        });*/


















    }

</script>

<%@include file="footer.jsp"%>

<script src="js/layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>

