<#include "menu.ftlh">
<!-- Page Content -->
<div class="container">
    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4"><small>Сохраненные посты:</small>
            </h1>
            <div class="card mb-4">
                <div class="card-body">
                    <table class="table table-striped table-sm table-hover">
                        <#list fav_posts_list as post>
                            <tr>
                                <td>${post_index+1}. ${post.title}</td>
                                <td class="text-right">
                                    <a href="/post/${post.id?c}" class="btn btn-sm btn-primary">Перейти
                                        к новости</a></td>
                            </tr>
                        </#list>
                    </table>
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
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
