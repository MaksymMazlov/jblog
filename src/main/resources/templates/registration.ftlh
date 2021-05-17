<#include "menu.ftlh">
<!-- Page Content -->
<div class="container" xmlns="http://www.w3.org/1999/html">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4"><small>Регистрация:</small>
            </h1>
            <!-- Blog Post -->
            <div class="card mb-4">
                <div class="card-body">
                    <form action="/registration" method="post" class="text-muted">
                        <#if error_req_email??>
                            <span style="color: #a71d2a"> ${error_req_email}</span>
                        <#else>
                            <label class="form-label">E-mail:</label>
                        </#if>
                        <input class="form-control" type="text" name="name"
                               value="<#if userRegDto??>${userRegDto.name}</#if>" placeholder="Введіть e-mail">
                        <div class="form-text small">На вказаний E-mail ми відправимо листа для підтвердження.</div>

                        <#if error_req_pass??>
                            <span style="color: #a71d2a"> ${error_req_pass}</span>
                        <#else>
                            <label class="form-label">Пароль:</label>
                        </#if>
                        <input class="form-control" type="text" name="password" placeholder="Введіть пароль">
                        <div class="form-text small">Пароль повинен бути від 6 до 24 символів</div>
                        <br>
                        <#if captcha_error??><span style="color: #a71d2a"> ${captcha_error}</span></#if>
                        <div class="g-recaptcha" data-sitekey="6LdGatgaAAAAAGs86pWJ_qE6T_CU5VfGhTnVr0Ks"></div>
                        <button class="btn btn-success" type="submit">Реєстрація</button>
                    </form>
                </div>
            </div>

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">
            <!-- Search Widget -->
            <#include "searchWidget.ftlh">

            <!-- Categories Widget -->
            <#include "сategoriesWinget.ftlh">

            <!-- Side Widget -->
            <div class="card my-4">
                <h5 class="card-header">Side Widget</h5>
                <div class="card-body">
                    You can put anything you want inside of these side widgets. They are easy to use, and feature the
                    new Bootstrap 4 card containers!
                </div>
            </div>

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
