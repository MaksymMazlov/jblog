<#include "menu.ftlh">
<!-- Page Content -->
<div class="container">
    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4"><small>Последние новости</small>
            </h1>
            <#list posts_list as post>
                <!-- Blog Post -->
                <div class="card mb-4">
                    <div class="card-body">
                        <h2 class="card-title">${post.title}</h2>
                        <img class="card-img-top" src="${post.img?no_esc}" alt="Card image cap">
                        <p class="card-text">${post.shortText?no_esc}</p>
                        <a href="/post/${post.id?c}" class="btn btn-primary">Read More &rarr;</a>
                    </div>
                    <div class="card-footer text-muted">
                        Posted on ${post.created} : Category: ${post.categoryName} --- Views: ${post.views?c} |
                        Likes: ${post.likes?c} | Author:
                        ${post.author}
                    </div>
                </div>
            </#list>
            <!-- Pagination -->
            <ul class="pagination pagination-sm">
                <#list 0..pages as n>
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
