<#include "menu.ftlh">

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">
            <div class="card mb-4">
                <div class="card-body">
                    <#if error??>
                        <div class="alert alert-danger">${error}</div>
                    <#else>
                        <form class="form-inline" action="/category" method="post">
                            <div class="form-group mx-sm-3 mb-2">
                                <input name="name" type="text" class="form-control"
                                       placeholder="Добавить новую категорию">
                            </div>
                            <button type="submit" class="btn btn-primary mb-2">Добавить</button>
                        </form>
                    </#if>
                </div>
            </div>
            <div class="card mb-4">
                <div class="card-body">

                    <table class="table table-bordered">
                        <thead> Категории:
                        </thead>
                        <tbody>
                        <#list categoriesToHTML as cat>
                            <tr>
                                <td>${cat.name}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>

                </div>
            </div>

            <!-- Pagination -->
            <ul class="pagination justify-content-center mb-4">
                <li class="page-item">
                    <a class="page-link" href="#">&larr; Older</a>
                </li>
                <li class="page-item disabled">
                    <a class="page-link" href="#">Newer &rarr;</a>
                </li>
            </ul>

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
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
