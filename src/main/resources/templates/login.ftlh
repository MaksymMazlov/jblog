<#include "menu.ftlh">
<!-- Page Content -->
<div class="container" xmlns="http://www.w3.org/1999/html">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">
            <h1 class="my-4"><small>Вход на сайт:</small></h1>
            <!-- Blog Post -->
            <div class="card mb-4">
                <div class="card-body">
                    <span style="color: #28a745"> <#if activated??> <b> ${activated} </b> </#if></span>
                    <span style="color: #a71d2a"> <#if no_activated??><b> ${no_activated} </b></#if></span>
                    <#if RequestParameters.error??>
                        <div class="alert alert-danger">Неверные данные</div></#if>
                    <form action="/login" method="post">
                        <input class="form-control" type="email" name="username" pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$" required="required" placeholder="Введите логин">
                        <input class="form-control" type="password" name="password" placeholder="Введите пароль">
                        <br>
                        <div class="g-recaptcha" data-sitekey="6LdGatgaAAAAAGs86pWJ_qE6T_CU5VfGhTnVr0Ks"></div>
                        <button class="btn btn-success" type="submit">Войти</button>
                    </form>
                </div>
            </div>

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

            <!-- Search Widget -->
            <#include "widgets/searchWidget.ftlh">

            <!-- Categories Widget -->
            <#include "widgets/сategoriesWidget.ftlh">

              <!-- Top Widget -->
            <#include "widgets/topPostsWidget.ftlh">
            <!-- Comments Widget -->
            <#include "widgets/topCommentsWidget.ftlh">
        </div>

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.tiny.cloud/1/i1uk7rne75xftqvv55jp2jzwxitnhcaba3oikilpy4q1vntm/tinymce/5/tinymce.min.js"
        referrerpolicy="origin"></script>
<script>
    tinymce.init({
        selector: '#postArea'
    });
</script>
</body>

</html>
