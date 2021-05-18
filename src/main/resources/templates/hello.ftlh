<#include "menu.ftlh">
<!-- Page Content -->
<div class="container">

    <div class="row">
        <div class="col-md-8">
            <div class="card mb-4">
                <br>
                <div class="card-body text-center">
                    <div class="alert alert-success">Дякуємо за реєстрацію!</div>
                    <small class="text-muted">Для завершення реєстрації на e-mail: <b>${name}</b> було відправлено посилання.</small>
                    <br>
                    <small class="text-muted">Через 7 сек. відбудеться перенаправлення на сторінку <a href="/login">авторизації</a></small>
                    <META HTTP-EQUIV="refresh" CONTENT="7;URL=/login">
                </div>
            </div>
        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">
            <!-- Search Widget -->
            <#include "searchWidget.ftlh">

            <!-- Categories Widget -->
            <#include "сategoriesWinget.ftlh">

                       <!-- Top Widget -->
            <#include "topWidget.ftlh">

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
