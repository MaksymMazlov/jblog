<#include "menu.ftlh">
<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4"><small>Добавить новый пост:</small>
            </h1>
            <!-- Blog Post -->

            <div class="card mb-4">
                <div class="card-body">
                    <#if null_error??><div class="alert alert-danger">${null_error}</div></#if>
                    <form action="/post" method="post" enctype="multipart/form-data">
                        <label>Заголовок:</label>
                        <input class="form-control" type="text" name="title" placeholder="Введите заголовок поста">
                        <label>Выбери категорию:</label>
                        <select class="form-control" name="categoryId">
                            <#list categoriesToHTML as cat>
                                <option value="${cat.id?c}"> ${cat.name} </option>
                            </#list>
                        </select>
                        <label>Текст:</label>
                        <textarea id="postArea" class="form-control" rows="10" type="text" name="text"
                                  placeholder="Введите текст поста"></textarea>
                        <label>Тег:</label>
                        <input class="form-control" type="text" name="tag" placeholder="Добавьте тег">
                        <br>
                        <label for="exampleFormControlFile1">Загрузите фото</label>
                        <input type="file" name="file">
                        <button class="btn btn-success" type="submit">Опубликовать</button>
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
