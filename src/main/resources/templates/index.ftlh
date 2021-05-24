<#include "menu.ftlh">
<!-- Page Content -->
<div class="container">
    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4"><small>Последние новости</small>
            </h1>
            <#list posts_list as post>
                <#include "shortpost.ftlh"/>
            </#list>
            <!-- Pagination -->
            <ul class="pagination pagination-sm">
                <#list 1..pages as n>
                        <#if n==page>
                            <li class="page-item active"><a class="page-link" href="/?page=${n}">${n}</a></li>
                        <#else>
                            <li class="page-item"><a class="page-link" href="/?page=${n}">${n}</a></li>
                        </#if>
                </#list>
            </ul>

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
