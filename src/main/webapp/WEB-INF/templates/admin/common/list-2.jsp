<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basepath %>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>员工列表</title>
    <%
        /*服务器路径为基准*/
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <%--web不以/开始的相对路径，找资源，已当前资源的路径为基准，相对路径  经常容易出问题--%>
    <%--
        以/开始的相对路径，找资源，以服务器的路径为基准(http://localhost:3306)需要加上项目名
            http://localhost:3306/Crud1
    --%>
    <%--引入jQuery--%>
    <script src="${APP_PATH}/static/js/jquery-3.2.1.js"></script>
    <!-- Bootstrap -->
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<%--员工修改模态框--%>
<!-- Modal -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改员工</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_add_input" class="col-sm-2 control-label">员工名字</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="empName_update_static"></p>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">员工邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" id="email_update_input"
                                   onpropertychange="javascript:echange();" placeholder="email@gmail.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">员工性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_update_input1" value="M" checked="checked">
                                男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_update_input2" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">员工部门</label>
                        <div class="col-sm-4">
                            <%--部门提交部门Id即可--%>
                            <select class="form-control" name="dId" id="dept_update_select">

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
            </div>
        </div>
    </div>
</div>

<%--员工添加模态框--%>
<!-- Modal -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加员工</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_add_input" class="col-sm-2 control-label">员工名字</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="empName" id="empName_add_input"
                                   placeholder="empName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_add_input" class="col-sm-2 control-label">员工邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" id="email_add_input"
                                   placeholder="email@gmail.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">员工性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_add_input1" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_add_input2" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">员工部门</label>
                        <div class="col-sm-4">
                            <%--部门提交部门Id即可--%>
                            <select class="form-control" name="dId" id="dept_add_select">

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>


<%--搭建显示页面--%>
<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <%--按钮--%>
    <div class="col-md-3 col-md-offset-10">
        <button class="btn btn-primary" id="empAddModal_btn">新增</button>
        <button class="btn btn-danger" id="emp_delall_btn">删除</button>
    </div>
    <%--显示表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" border="0px" id="emps_table">
					<thead>
					<tr>
						<th><input type="checkbox" id="check_all"></th>
						<th>编号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>邮箱</th>
						<th>部门</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody>

					</tbody>
            </table>
        </div>
    </div>
    <%--显示分页数据--%>
    <div class="row">
        <%--分页信息--%>
        <div class="col-md-4" id="page_info_area">

        </div>
        <div class="col-md-5 col-md-offset-3" id="page_nav_area">
            <%--分页条信息--%>

        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    /*
        1.页面加载完成之后，直接发送一个ajax请求，要到分页数据
     */
    var totalRecord, currentPage;
    $(function () {
        //去首页
        to_page(1);
    });

    function to_page(pn) {
        $.ajax({
            url: "${APP_PATH}/emps",
            data: "pn=" + pn,
            type: "GET",
            success: function (result) {
                // console.log(result);
                //1.解析并显示员工数据
                build_emp_table(result);
                //2.解析并显示分页条信息
                build_page_info(result);
                //2.解析并显示分页条信息
                build_page_nav(result);
            }
        });
    };

    //1.解析并显示员工数据
    function build_emp_table(result) {
        //清空table表格
        $("#emps_table tbody").empty();
        var emps = result.extend.pageInfo.list;
        /*jquery遍历方法*/
        $.each(emps, function (index, item) {
            var checkbox = $("<td><input type='checkbox' class='check_item'/></td>");
            var empIdTd = $("<td></td>").append(item.empId);
            var empNameTd = $("<td></td>").append(item.empName);
            var empgenderTd = $("<td></td>").append(item.gender == 'M' ? "男" : "女");
            var empEmailTd = $("<td></td>").append(item.email);
            var empDeptTd = $("<td></td>").append(item.department.deptName);
            var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
                .append("编辑");
				
            //为编辑按钮添加一个自定义的属性，将每一个员工的id赋值到编辑按钮上去
            editBtn.attr("edit-id", item.empId);

            var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
                .append("删除");
            //为删除按钮添加一个自定义的属性，将每一个员工id复制到删除按钮上去
            delBtn.attr("del-id",item.empId);
            var btn = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            //append方法执行完成之后 还是会返回原来的元素
            $("<tr></tr>").append(checkbox).append(empIdTd)
                .append(empNameTd).append(empgenderTd).append(empEmailTd)
                .append(empDeptTd).append(btn)
                .appendTo("#emps_table tbody");
        });
    }

    //2.解析并显示分页信息
    function build_page_info(result) {
        $("#page_info_area").empty();
        var page = result.extend.pageInfo;
        $("#page_info_area").append("当前" + page.pageNum + "页,总" + page.pages + ",总" + page.total + "条记录")
        totalRecord = page.total;
        currentPage = page.pageNum;
    }

    //2.解析并显示分页条信息,点击分页实行跳转
    function build_page_nav(result) {
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>").addClass("pagination");
        var firstpageli = $("<li></li>").append($("<a></a>").append("首页"));
        var prepageli = $("<li></li>").append($("<a></a>").append("&laquo;"));

        if (result.extend.pageInfo.hasPreviousPage == false) {
            firstpageli.addClass("disabled");
            prepageli.addClass("disabled");
        } else {
            //点击跳首页
            firstpageli.click(function () {
                to_page(1);
            });
            prepageli.click(function () {
                to_page(result.extend.pageInfo.pageNum - 1)
            });
        }

        var nextpageli = $("<li></li>").append($("<a></a>").append("&raquo;"));
        var lastpageli = $("<li></li>").append($("<a></a>").append("末页"));
        if (result.extend.pageInfo.hasNextPage == false) {
            nextpageli.addClass("disabled");
            lastpageli.addClass("disabled");
        } else {
            nextpageli.click(function () {
                to_page(result.extend.pageInfo.pageNum + 1)
            });
            lastpageli.click(function () {
                to_page(result.extend.pageInfo.pages);
            });
        }
        ul.append(firstpageli).append(prepageli);
        var pages = result.extend.pageInfo.navigatepageNums;
        $.each(pages, function (index, item) {
            var numli = $("<li></li>").append($("<a></a>").append(item));
            //判断高亮页面
            if (result.extend.pageInfo.pageNum == item) {
                numli.addClass("active");
            }
            //点击需要跳转的页面
            numli.click(function () {
                to_page(item);
            });
            ul.append(numli);
        });
        ul.append(nextpageli).append(lastpageli);
        // /*把ul加入到nav元素中*/
        var nav = $("<nav></nav>").append(ul);
        nav.appendTo("#page_nav_area");
    }


    //清空表单样式及内容
    function reset_form(ele) {
        $(ele)[0].reset();
        //清空表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }

    /*新增员工
    * 点击新增按钮，弹出模态框
    * */
    $("#empAddModal_btn").click(function () {
        //清除表单数据（表单重置,表单数据，表单样式）
        reset_form("#empAddModal form");
        //发送ajax请求，查出部门信息，显示下拉列表中
        getDetps("#empAddModal select");
        //弹出模态框
        $("#empAddModal").modal({
            backdrop: "static"
        });
    });

    //查出所有部门信息，显示下拉列表中
    function getDetps(ele) {
        //清空之前下拉列表的值
        $(ele).empty();
        $.ajax({
            url: "${APP_PATH}/depts",
            type: "GET",
            success: function (result) {
                //console.log(result);
                //显示部门信息在下拉列表中
                $.each(result.extend.depts, function () {
                    var optionEle = $("<option></option>").append(this.deptName).attr("value", this.deptId);
                    optionEle.appendTo(ele);
                });
            }
        });
    };

    //表单校验
    function validate_add_form() {
        // 先拿到要校验的数据，使用正则表达式
        var empName = $("#empName_add_input").val();
        var regename = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)/;
        if (!regename.test(empName)) {
            //alert("用户名可以是2-5位中文，6-16位英文和数字组合");
            //清空这个元素之前的样式
            show_validate_msg("#empName_add_input", "error", "用户名必须是2-5位中文或6-16位英文和数字组合。")
            return false;
        } else {
            show_validate_msg("#empName_add_input", "success", "")
        }
        //2.校验邮箱信息
        var email = $("#email_add_input").val();
        var regemail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if (!regemail.test(email)) {
            //alert("邮箱格式不正确");
            show_validate_msg("#email_add_input", "error", "邮箱格式不正确。");
            return false;
        } else {
            show_validate_msg("#email_add_input", "success", "");
        }
        return true;
    }

    //显示校验结果的提示信息。  校验的ele status msg
    function show_validate_msg(ele, status, msg) {
        //清除当前元素校验状态
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");
        if ("success" == status) {
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        } else if ("error" == status) {
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }

    $("#empName_add_input").blur(function () {
        //发送ajax请求 校验用户名是否重复
        var empName = this.value;
        $.ajax({
            url: "${APP_PATH}/checkuser",
            data: "empName=" + empName,
            type: "POST",
            success: function (result) {
                if (result.code == 100) {
                    show_validate_msg("#empName_add_input", "success", "用户名可用。");
                    //如果用户名可用 给save按钮 赋值为success
                    $("#emp_save_btn").attr("ajax-va", "success");
                } else {
                    show_validate_msg("#empName_add_input", "error", result.extend.va_msg);
                    $("#emp_save_btn").attr("ajax-va", "error");
                }
            }
        });
    });

    //点击保存员工方法
    $("#emp_save_btn").click(function () {
        //判断之前的ajax用户名校验是否成功，如果成功 继续执行。
        //2.发送ajax请求保存员工
        //alert($("#empAddModal form").serialize());
        if ($(this).attr("ajax-va") == "error") {
            show_validate_msg("#empName_add_input", "error", "用户名不可用。");
            return false;
        }
        //1.模态框中填写的表单数据提交给服务器进行保存
        // 先要提交给服务器的数据进行校验
        if (!validate_add_form()) {
            return false;
        }
        ;
        $.ajax({
            url: "${APP_PATH}/emp",
            type: "POST",
            data: $("#empAddModal form").serialize(),
            success: function (result) {
                //alert(result.msg);
                if (result.code == 100) {
                    //员工保存成功
                    //1.关闭模态框
                    $("#empAddModal").modal('hide');
                    //2.来到最后一页显示刚才增加的数据
                    //发送ajax请求显示最后一页数据
                    to_page(totalRecord);
                } else {
                    //显示失败信息
                    //console.log(result);
                    //有哪个字段的错误信息，就显示哪个字段的(后端校验)
                    if (undefined != result.extend.errorFileds.email) {
                        //显示邮箱错误信息
                        show_validate_msg("#email_add_input", "error", result.extend.errorFileds.email);
                    }
                    if (undefined != result.extend.errorFileds.empName) {
                        //显示员工名字错误信息
                        show_validate_msg("#empName_add_input", "error", result.extend.errorFileds.empName);
                    }
                }
            }
        });
    });

    //修改员工信息
    //1.我们是按钮创建之前就绑定了click，所以绑定不上
    //2.我们可以在创建按钮的时候 绑定单击
    //jQuery新版没有 live方法
    $(document).on("click", ".edit_btn", function () {
        //0.查出员工信息
        //1.查出部门信息，并显示部门列表
        getDetps("#empUpdateModal select");
        //查询员工信息
        getEmp($(this).attr("edit-id"));

        //把员工的id传递给模态框的更新按钮
        $("#emp_update_btn").attr("edit-id", $(this).attr("edit-id"));
        $("#empUpdateModal").modal({
            backdrop: "static"
        });
    });
    /*
    * 加载员工信息
    * */
    function getEmp(id) {
        $.ajax({
            url: "${APP_PATH}/emp/" + id,
            type: "GET",
            success: function (result) {
                var empData = result.extend.emp;
                $("#empName_update_static").text(empData.empName);
                $("#email_update_input").val(empData.email);
                $("#empUpdateModal input[name=gender]").val([empData.gender]);
                $("#empUpdateModal select").val([empData.dId]);
            }
        });
    }

    //点击更新，更新员工信息
    function echange() {
        if (!regemail.test(email)) {
            show_validate_msg("#email_update_input", "error", "邮箱格式不正确。");
            return false;
        } else {
            show_validate_msg("#email_update_input", "success", "");
        }
    }

    $("#email_update_input").change(function () {
        //验证邮箱是否合法
        //2.校验邮箱信息
        var email = $("#email_update_input").val();
        var regemail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if (!regemail.test(email)) {
            show_validate_msg("#email_update_input", "error", "邮箱格式不正确。");
            return false;
        } else {
            show_validate_msg("#email_update_input", "success", "");
        }
        $("#emp_update_btn").click(function () {
            //发送ajax请求，保存更新的数据
            $.ajax({
                url: "${APP_PATH}/emp/" + $(this).attr("edit-id"),
                type: "POST",
                data: $("#empUpdateModal form").serialize() + "&_method=PUT",
                success: function (result) {
                    //关闭模态框
                    $("#empUpdateModal").modal("hide");
                    //回到本页面
                    to_page(currentPage);
                }
            });
        });
    });

    /**
     * 根据id删除员工
     */
    //动态创建的按钮，用on来绑定事件
    $(document).on("click",".delete_btn",function () {
        //弹出是否确认删除对话框
        var empName = $(this).parents("tr").find("td:eq(2)").text();
        var empId = $(this).attr("del-id");
        if(confirm("确认删除["+empName+"]吗？")){
            $.ajax({
                url:"${APP_PATH}/emp/"+empId,
                type:"DELETE",
                success:function (result) {
                    //回到当前页码
                    to_page(currentPage);
                }
            });
        }
    });

    //完成全选 全不选
    $("#check_all").click(function () {
        //attr（）获取checked是undefined
        //我们原生的dom属性，attr获取自定义属性的值。prop修改和读取dom原生属性的值
        //$(this).prop("checked") 全选按妞的值。 使.check_item 得checked属性和全选按钮的值保持一致
        $(".check_item").prop("checked",$(this).prop("checked"));
    });
    //checkitem,后来创建的按钮。绑定点击事件
    $(document).on("click",".check_item",function () {
        //判断当前选中的元素是不是5个
        var flag = $(".check_item:checked").length == $(".check_item").length;
        $("#check_all").prop("checked",flag);
    });

    //点击批量删除
    $("#emp_delall_btn").click(function () {
        //alert($(".check_item:checked").parents("tr").find("td:eq(2)").text());
        var empNames = ""
        var empIds = ""
        $.each($(".check_item:checked"),function () {
            empNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
            //组装员工id字符串
            empIds+=$(this).parents("tr").find("td:eq(1)").text()+"-";
        });
        //去除后面多余的-
        empIds.substring(0,empIds.length-1);
        //去除后面多余的逗号
        empNames.substring(0,empNames.length-1);
        if(confirm("确认删除["+empNames+"]吗？")){
            //发送ajax请求
            $.ajax({
                url:"${APP_PATH}/emp/"+empIds,
                type:"DELETE",
                success:function (result) {
                    alert(result);
                    //回到当前页面
                    to_page(currentPage);
                }
            });
        }
    });
</script>
</html>
