<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!doctype html>
    <html lang="en">

    <head>
        <title>商家管理</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/storeupdate.css" />
        <!-- Font Awesome Cdn Link -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

    </head>

    <body>
        <!--第一層 導覽列-->
        <div class="container">

            <%@include file="successmember.jsp" %>


                <!-- 第二層 -->
                <section class="main">

                    <div class="row users">
                        <div class="col-3 sidenav"></div>

                        <div class="col-8 card">
                            <h4>會員更新</h4>
                            
                            <form action="<%=request.getContextPath()%>/MemberWebServlet" method="POST">
                                <div class="mb-3">
                                    <label for="exampleFormControInput1" class="form-label">頭像
                                    </label><input type="text" class="form-control" id="exampleFormControInput1" placeholder="頭像" name="mPicture"
                                        value="<%=request.getParameter("mPicture")%>">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleFormControlInput1" class="form-label">名字
                                    </label><input type="text" class="form-control" id="exampleFormControlInput1" placeholder="名字" name="mName"
                                        value="<%=request.getParameter("mName")%>">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleFormControInput1" class="form-label">帳號
                                    </label><input type="text" class="form-control" id="exampleFormControInput1" placeholder="帳號" name="mAccount"
                                        value="<%=request.getParameter("mAccount")%>">
                                </div>
                            
                                <div class="mb-3">
                                    <label for="exampleFormControInput1" class="form-label">密碼
                                    </label><input type="text" class="form-control" id="exampleFormControInput1" placeholder="密碼" name="mPassword"
                                        value="<%=request.getParameter("mPassword")%>">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleFormControInput1" class="form-label">信箱
                                    </label><input type="text" class="form-control" id="exampleFormControInput1" placeholder="信箱" name="mEmail"
                                        value="<%=request.getParameter("mEmail")%>">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleFormControInput1" class="form-label">電話
                                    </label><input type="text" class="form-control" id="exampleFormControInput1" placeholder="電話" name="mPhone"
                                        value="<%=request.getParameter("mPhone")%>">
                                </div>
                            
                            
                                <div>
                                    <button type="submit" class="btn btn-danger">更新</button>
                                    <input type="hidden" name="status" value="update"> <input type="hidden" name="mId"
                                        value="<%=request.getParameter("mId")%>">
                                </div>
                            
                            </form>
                            <a style=" margin:0 auto;"  href="javascript:;" onClick="javascript:history.back(-1);">返回上一頁</a>
                        </div>
                        <div class="col-3 sidenav"></div>

                    </div>


                </section>
                <!-- 第三層 -->
        </div>
        <%@include file="footer.jsp" %>


            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>

    </html>